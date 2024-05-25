package com.example.pdfrender;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class  Pdf {
    String name;
    String path;
    float size;

    public Pdf(String name,String path ,int size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }
    public static ArrayList<Pdf> findObjectsByName(List<Pdf> objects, String searchString) {
        // Use the Stream API to filter the objects
        return objects.stream()
                .filter(obj -> {
                    // Check if the name contains the search string
                    if (obj.name.contains(searchString)) {
                        return true;
                    }
                    // Check if the name is similar to the search string using a regular expression
                    String pattern = ".*" + searchString + ".*";
                    return Pattern.matches(pattern, obj.name);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
