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
     * IoT hub connection string
     */
    public static final String IOT_HUB_CONNECTION_STRING = "HostName=IoTHub116117.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=ajHdtr2d6QRliDGnpSe/FR/RVUWrM8fKjE5P12DpcaY=";

    /**
     * regisrty manager num max devices
     */
    public static final Integer MAX_DEVICES = 10000;
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
     * Patient-Transport relationship
     */
    public static final String TRANSPORT_PATIENT_REL = "transport";


    /**
     * Ambulance-Transport relationship
     */
    public static final String TRANSPORT_AMBULANCE_REL = "use";

    /**
     * Ambulance-Operator relationship
     */
    public static final String AMBULANCE_OPERATOR_REL = "driveBy";


    /**
     * Coding system SNOMED
     */
    public static final String SNOMED_SYSTEMS = "http://snomed.info/sct";

    /**
     * Coding system HL7 SYSTEMS SERVICE CATEGORY
     */
    public static final String HL7_SYSTEMS_SERVICE_CATEGORY = "http://terminology.hl7.org/CodeSystem/service-category";

    /**
     * Coding system HL7 SYSTEMS ACT CODE
     */
    public static final String HL7_SYSTEMS_ACT_CODE = "http://terminology.hl7.org/CodeSystem/v3 -ActCode";

    /**
     * Coding system HL7 SYSTEMS SERVICE TYPE
     */
    public static final String HL7_SYSTEMS_SERVICE_TYPE = "http://terminology.hl7.org/CodeSystem/service-type";

    /**
     * Coding system HL7 SYSTEMS PARTICIPATION TYPE
     */
    public static final String HL7_SYSTEMS_PARTICIPATION_TYPE = "http://terminology.hl7.org/CodeSystem/v3-ParticipationType";


    /**
     * Ambulance device code in SNOMED
     */
    public static final String AMBULANCE_CODE = "465341007";

    /**
     * Encounter ACT CODE
     */
    public static final String ENCOUNTER_ACT_CODE = "AMB";

    /**
     * Encounter operator PARTICIPATION TYPE
     */
    public static final String ENCOUNTER_OPERATOR_PARTICIPATION_TYPE = "ESC";

    /**
     * Encounter ambulance PARTICIPATION TYPE
     */
    public static final String ENCOUNTER_AMBULANCE_PARTICIPATION_TYPE = "DIR";

    /**
     * GPS code in SNOMED
     */
    public static final String GPS_CODE = "897293009";

    /**
     * PractitionerRole AmbulanceMan code in SNOMED
     */
    public static final String PRACTITIONER_ROLE_CODE = "159738005";

    /**
     * Service category code in HL7
     */
    public static final String SERVICE_CATEGORY = "33";

    /**
     * Service category display
     */
    public static final String SERVICE_CATEGORY_DISPLAY = "Transport";

    /**
     * Service type code in HL7
     */
    public static final String SERVICE_TYPE = "230";

    /**
     * Service type display
     */
    public static final String SERVICE_TYPE_DISPLAY = "Patient Transport";

    /**
     * Service type code in HL7
     */
    public static final String CODE_PROCEDURE = "715537001";

    /**
     * Service type display
     */
    public static final String CODE_PROCEDURE_DISPLAY = "Transportation by ambulance (procedure)";

    /**
     * Device reference
     */
    public static final String DEVICE_REF = "Device/";

    /**
     * Patient reference
     */
    public static final String PATIENT_REF = "Patient/";

    /**
     * Practitioner reference
     */
    public static final String PRACTITIONER_REF = "Practitioner/";

    /**
     * add to transportId for resource appointment
     */
    public static final String APPOINTMENT = "-appointment";

    /**
     * add to transportId for resource procedure
     */
    public static final String PROCEDURE = "-procedure";

    /**
     * add to transportId for resource encounter
     */
    public static final String ENCOUNTER = "-encounter";


}

