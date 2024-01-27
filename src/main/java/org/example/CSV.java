package org.example;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSV {

    public List<String> readFile(String path) throws IOException {

        List<String> result = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {

                result.add(line);


            }
        }

        return result;

    }

    public void writeToFile(String path, List<String> data) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {


            for (String line : data) {

                writer.write(line);
                writer.newLine();
            }
        }
    }
}
