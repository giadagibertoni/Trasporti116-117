/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package httprequest;


import fhir.FHIRParser;
import org.hl7.fhir.r4.model.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.ControllInputField;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Trasporti116117HttpRequest {
    private static final String HOST = "http://localhost:8080";
    private static final String CREATE_PATIENT = "/API/Patient/createPatient";
    private static final String CREATE_OPERATOR = "/API/Vehicle/createOperator";
    private static final String CREATE_AMBULANCE = "/API/Vehicle/createAmbulance";
    private static final String CREATE_TRANSPORT = "/API/Transport/createTransport";
    private static final String GET_SCHEDULED_TRANSPORT = "/API/Transport/getScheduledTransports";
    private static final String GET_INPROGRESS_TRANSPORT = "/API/Transport/getInProgressTransports";
    private static final String GET_COMPLETED_TRANSPORT = "/API/Transport/getCompletedTransports";
    private static final String GET_CANCELED_TRANSPORT = "/API/Transport/getCancelledTransports";
    private static final String DELETE_TRANSPORT = "/API/Transport/setTransportDeleted";
    private static final String GET_PATIENT = "/API/Patient/getPatient";
    private static final String GET_AMBULANCES = "/API/Vehicle/getAmbulances";
    private static final String GET_OPERATORS = "/API/Vehicle/getOperators";
    private static final String GET_PATIENTS = "/API/Patient/getPatients";
    private static final String GET_FREE_AMBULANCE = "/API/Vehicle/getFreeAmbulance";
    private static final String ADD_OPERATOR_WORK_DAY = "/API/Vehicle/addOperatorWorkDay";
    private static final String SET_AMBULANCE_FREE = "/API/Vehicle/setAmbulanceFree";
    private static final String SET_AMBULANCE_UNDER_MAINTENANCE = "/API/Vehicle/setAmbulanceUnderMaintenance";
    private static final String SET_AMBULANCE_DISUSED = "/API/Vehicle/setAmbulanceDisused";

    private static final HttpClient client = HttpClient.newHttpClient();
    public static String createPatient(String resource) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + CREATE_PATIENT, resource);
        } catch (ResourceNotFoundException e) {
            return "resource not found";
        }
    }

    public static String createOperator(String resource) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + CREATE_OPERATOR, resource);
        } catch (ResourceNotFoundException e) {
            return "resource not found";
        }
    }

    public static String createAmbulance(String resource) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + CREATE_AMBULANCE, resource);
        } catch (ResourceNotFoundException e) {
            return "resource not found";
        }
    }

    public static String createTransport(String resource) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + CREATE_TRANSPORT, resource);
        } catch (ResourceNotFoundException e) {
            return "resource not found";
        }
    }

    public static String deleteTransport(String id) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + DELETE_TRANSPORT, id);
        } catch (ResourceNotFoundException e) {
            return "resource not found";
        }
    }

    public static List<Appointment> getScheduledTransport() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Appointment> transport = new ArrayList<>();
        JSONArray jsonTransport = (JSONArray) parser.parse(sendGETRequest(HOST + GET_SCHEDULED_TRANSPORT));
        jsonTransport.forEach(p -> transport.add(FHIRParser.getParser().parseResource(Appointment.class, p.toString())));
        return transport;
    }

    public static List<Appointment> getCancelledTransport() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Appointment> transport = new ArrayList<>();
        JSONArray jsonTransport = (JSONArray) parser.parse(sendGETRequest(HOST + GET_CANCELED_TRANSPORT));
        jsonTransport.forEach(p -> transport.add(FHIRParser.getParser().parseResource(Appointment.class, p.toString())));
        return transport;
    }

    public static List<Encounter> getInProgressTransport() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Encounter> transport = new ArrayList<>();
        JSONArray jsonTransport = (JSONArray) parser.parse(sendGETRequest(HOST + GET_INPROGRESS_TRANSPORT));
        jsonTransport.forEach(p -> transport.add(FHIRParser.getParser().parseResource(Encounter.class, p.toString())));
        return transport;
    }

    public static List<Encounter> getCompletedTransport() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Encounter> transport = new ArrayList<>();
        JSONArray jsonTransport = (JSONArray) parser.parse(sendGETRequest(HOST + GET_COMPLETED_TRANSPORT));
        jsonTransport.forEach(p -> transport.add(FHIRParser.getParser().parseResource(Encounter.class, p.toString())));
        return transport;
    }

    public static String getPatient(String id) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        try {
            return sendGETRequestWithParams(HOST + GET_PATIENT, params);
        } catch (ResourceNotFoundException e) {
            return "Patient not found";
        }
    }

    public static List<Patient> getPatients() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Patient> patients = new ArrayList<>();
        JSONArray jsonPatients = (JSONArray) parser.parse(sendGETRequest(HOST + GET_PATIENTS));
        jsonPatients.forEach(p -> patients.add(FHIRParser.getParser().parseResource(Patient.class, p.toString())));
        return patients;
    }

    public static List<Device> getAmbulances() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Device> ambulances = new ArrayList<>();
        JSONArray jsonAmbulances = (JSONArray) parser.parse(sendGETRequest(HOST + GET_AMBULANCES));
        jsonAmbulances.forEach(a -> ambulances.add(FHIRParser.getParser().parseResource(Device.class, a.toString())));
        return ambulances;
    }

    public static List<Practitioner> getOperators() throws IOException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        List<Practitioner> operators = new ArrayList<>();
        JSONArray jsonOperators = (JSONArray) parser.parse(sendGETRequest(HOST + GET_OPERATORS));
        jsonOperators.forEach(o -> operators.add(FHIRParser.getParser().parseResource(Practitioner.class, o.toString())));
        return operators;
    }

    public static String getFreeAmbulance(LocalDateTime startDateTime, LocalDateTime endDateTime) throws IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("startDateTime", startDateTime.toString());
        params.put("endDateTime", endDateTime.toString());

        try {
            return FHIRParser.getParser().parseResource(Device.class, sendGETRequestWithParams(HOST + GET_FREE_AMBULANCE, params)).getIdentifierFirstRep().getValue();
        } catch (ResourceNotFoundException e) {
           return ControllInputField.AMBULANCE_NOT_FOUND;
        }
    }

    public static String addOperatorWorkDay(String ambulanceId, String operatorId, LocalDate date) throws IOException, ResourceNotFoundException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("ambulanceId", ambulanceId);
        params.put("operatorId", operatorId);
        params.put("date", date.toString());

        return sendPOSTRequestWithParams(HOST + ADD_OPERATOR_WORK_DAY, params);
    }

    public static String setAmbulanceDisused(String ambulanceId) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + SET_AMBULANCE_DISUSED, ambulanceId);
        } catch (ResourceNotFoundException e) {
            return "Ambualance not found";
        }
    }

    public static String setAmbulanceUnderMaintenance(String ambulanceId) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + SET_AMBULANCE_UNDER_MAINTENANCE, ambulanceId);
        } catch (ResourceNotFoundException e) {
            return "Ambualance not found";
        }
    }

    public static String setAmbulanceFree(String ambulanceId) throws IOException, InterruptedException {
        try {
            return sendPOSTRequestWithJSONBody(HOST + SET_AMBULANCE_FREE, ambulanceId);
        } catch (ResourceNotFoundException e) {
            return "Ambualance not found";
        }
    }

    private static String sendGETRequestWithParams(String URL, Map<String, String> params) throws IOException, InterruptedException, ResourceNotFoundException {
        String formattedParams = params.keySet().stream().map(key -> key + "=" + params.get(key)).collect(Collectors.joining("&", "?", ""));

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(URL + formattedParams))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404)
            throw new ResourceNotFoundException();

        return response.body();
    }

    private static String sendPOSTRequestWithParams(String URL, Map<String, String> params) throws IOException, InterruptedException, ResourceNotFoundException {
        String formattedParams = params.keySet().stream().map(key -> key + "=" + params.get(key)).collect(Collectors.joining("&", "?", ""));

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(URL + formattedParams))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404)
            throw new ResourceNotFoundException();

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

    private static String sendPOSTRequestWithJSONBody(String URL, String jsonBody) throws IOException, InterruptedException, ResourceNotFoundException {
        HttpClient client = HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(URL))

                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404)
            throw new ResourceNotFoundException();

        return response.body();
    }
}
