package org.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class BibleAPIClient {

    private static final String API_URL = "http://ibibles.net/quote.php?kor-";

    public String getVerse(String verse) {
        HttpURLConnection con = null;
        BufferedReader in = null;
        try {
            String[] parts = verse.split(" ");
            if (parts.length != 2) {
                return "Invalid verse format. Please use format '책 제목 chapter:verse'";
            }
            String bookName = parts[0];
            String passage = parts[1];

            String englishAbbreviation = BibleBookMapper.getEnglishAbbreviation(bookName);
            if (englishAbbreviation.isEmpty()) {
                return "Invalid book name.";
            }

            URL url = new URL(API_URL + englishAbbreviation + "/" + passage);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while fetching Bible verse";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
