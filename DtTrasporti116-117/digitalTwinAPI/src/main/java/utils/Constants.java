/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package utils;

/**
 * Class that contains a set of constants
 */
public final class Constants {
    private Constants() { }

    /**
     * Endpoint path string
     */
    public static final String ENDPOINT = "https://DTInstance116117.api.weu.digitaltwins.azure.net";

    /**
     * Tenant identifier stirng
     */
    public static final String TENANT_ID = "9a97eb40-e341-4a0f-8c5d-c6264663964c";

    /**
     * Client identifier string
     */
    public static final String CLIENT_ID = "f00ec15a-5310-4dcf-a766-77fcc1bcf40e";

    /**
     * Client secret
     */
    public static final String CLIENT_SECRET = "n5_i0mRm8TD6x725E~k2jgtTunx.f-lGzc";

    /**
     * Ambulance model identifier
     */
    public static final String AMBULANCE_MODEL_ID = "dtmi:num116117:ambulance;1";

    /**
     * Operator model identifier
     */
    public static final String OPERATOR_MODEL_ID = "dtmi:num116117:ambulanceOperator;1";

    /**
     * Patient model identifier
     */
    public static final String PATIENT_MODEL_ID = "dtmi:num116117:patient;1";

    /**
     * Transport model identifier
     */
    public static final String TRANSPORT_MODEL_ID = "dtmi:num116117:transport;1";

    /**
     * Coding system SNOMED
     */
    public static final String SNOMED_SYSTEMS = "http://snomed.info/sct";

    /**
     * Ambulance device code in SNOMED
     */
    public static final String AMBULANCE_CODE = "465341007";

    /**
     * GPS code in SNOMED
     */
    public static final String GPS_CODE = "897293009";

    /**
     * PractitionerRole AmbulanceMan code in SNOMED
     */
    public static final String PRACTITIONER_ROLE_CODE = "159738005";


}

