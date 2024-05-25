package com.example.pdfrender;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PdfAdapter extends ArrayAdapter<Pdf> {
    private final Context context;
    private final ArrayList<Pdf> data;
    public PdfAdapter(Context context, ArrayList<Pdf> data) {
        super(context, R.layout.pdf_card, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the item_card layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pdf_card, parent, false);

        // Get the TextView and ImageView and basic items
        TextView textView = view.findViewById(R.id.map_pdf_name);
        textView.setText(data.get(position).name);
        TextView textView2 = view.findViewById(R.id.map_pdf_size);
        textView2.setText(data.get(position).size + "Mb");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the car data for the clicked position
            }
        });
        return view;
    }
}
