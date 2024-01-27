package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Main {

    public static List<String> data;
    public static void main(String[] args) throws IOException {

        CSV csvUtility = new CSV();
        Requests request = new Requests();

        try {
            data= csvUtility.readFile("C:\\Users\\odunc\\Desktop\\example.csv");
            System.out.println(data);
            for (int i = 1; i < data.size(); i++) {

                var splittedLine = data.get(i).split(";");
                if(splittedLine.length > 2 ){
                    continue;
                }

                String location = splittedLine[1];
                String url = "https://api.open-elevation.com/api/v1/lookup?locations=" + location + "\n";
                System.out.println(url);
                String response = request.get(url);
                JSONObject jsonResponse = new JSONObject(response);
                var elevation = jsonResponse.getJSONArray("results").getJSONObject(0).getFloat("elevation");
                String elevationAsString = String.valueOf(elevation);

                data.set(i, data.get(i) + ";" + elevationAsString);
                System.out.println(i);
                Thread.sleep(200);


            }

            csvUtility.writeToFile("C:\\Users\\odunc\\Desktop\\result.csv",data);

        } catch (Exception e) {
            System.out.println("oh GOD");
            csvUtility.writeToFile("C:\\Users\\odunc\\Desktop\\result.csv",data);
            //e.printStackTrace();


        }


    }
}
