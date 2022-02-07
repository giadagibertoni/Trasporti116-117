package transportbc;

public class TransportConstants {
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
     * Patient reference
     */
    public static final String PATIENT_REF = "Patient/";

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

    /**
     * Device reference
     */
    public static final String DEVICE_REF = "Device/";

    /**
     * Practitioner reference
     */
    public static final String PRACTITIONER_REF = "Practitioner/";

}
