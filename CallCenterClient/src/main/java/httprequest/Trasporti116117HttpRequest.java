/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package httprequest;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Trasporti116117HttpRequest {
    private static final String HOST = "http://localhost:8080";
    private static final String CREATE_PATIENT = "/API/Patient/createPatient";
    private static final String GET_PATIENT = "/API/Patient/getPatient";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String createPatient(String patientResource) throws IOException, InterruptedException {
        return sendPOSTRequestWithJSONBody(HOST + CREATE_PATIENT, patientResource);
    }

    public static String getPatient(String id) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        return sendGETRequestWithParams(HOST + GET_PATIENT, params);
    }

    private static String sendGETRequestWithParams(String URL, Map<String, String> params) throws IOException, InterruptedException {
        String formattedParams = params.keySet().stream().map(key -> key + "=" + params.get(key)).collect(Collectors.joining("&", "?", ""));

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(URL + formattedParams))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static String sendGETRequest(String URL) throws IOException, InterruptedException {
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static String sendPOSTRequestWithJSONBody(String URL, String jsonBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
