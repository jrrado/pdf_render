package com.example.pdfrender;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PdfList extends AppCompatActivity {
    private static final int REQUEST_CODE_STORAGE = 1;
    ArrayList<Pdf> allFiles = new ArrayList<>();
    ArrayList<Pdf> data = new ArrayList<>();
    private ListView listView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_list_layout);
        listView = findViewById(R.id.pdf_list);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
        } else {
//            loadFiles();
            for (int i = 0; i < 100; i++) {
                data.add(new Pdf("Title " + i, "Text " + i,i));
            }
            PdfAdapter adapter = new PdfAdapter(this, data);
            listView.setAdapter(adapter);
        }
    }

    private void loadFiles() {
        ArrayList<String> fileList = new ArrayList<>();
        File directory = Environment.getExternalStorageDirectory();
        searchFiles(directory, fileList);
    }

    private void searchFiles(File directory, ArrayList<String> fileList) {
        List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "webp", "tiff", "ico");
        List<String> videoExtensions = Arrays.asList("mp4", "avi", "mkv", "mov", "wmv", "flv", "webm", "vob", "ogg");

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFiles(file, fileList);
                } else {
                    String fileName = file.getName().toLowerCase();
                    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

                    if (!imageExtensions.contains(fileExtension) && !videoExtensions.contains(fileExtension)) {
                        fileList.add(file.getAbsolutePath());
                        allFiles.add(new Pdf(fileName,file.getAbsolutePath(),12));
                    }
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, access PDFs
                loadFiles();
            } else {
                // Permission denied, inform user
                Toast.makeText(this, "Storage permission is required to access PDFs", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
