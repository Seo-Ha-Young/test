package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibleService {

    private final APIClient apiClient;

    @Autowired
    public BibleService() {
        this.apiClient = new APIClient();
    }

    public String getApiResponse() {
        try {
            return apiClient.sendGetRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while fetching API response";
        }
    }
}
