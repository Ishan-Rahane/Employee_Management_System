package com.htek.dto;

import java.util.HashMap;
import java.util.List;

public class ResponseDTO {

    private String response;

    private HashMap<String, List> history;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public HashMap<String, List> getHistory() {
        return history;
    }

    public void setHistory(HashMap<String, List> history) {
        this.history = history;
    }
}
