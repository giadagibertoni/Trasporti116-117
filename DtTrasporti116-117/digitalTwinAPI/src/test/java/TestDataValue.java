import dtModel.*;
import dtModel.patient.*;
import dtModel.transport.Phase;
import dtModel.transport.TransportDtModel;
import dtModel.transport.TransportLocation;
import dtModel.transport.TransportRoute;
import dtModel.vehicle.ambulance.AmbulanceDtModel;
import dtModel.vehicle.ambulance.AmbulanceState;
import dtModel.vehicle.ambulance.GPSCoordinates;
import dtModel.vehicle.operator.OperatorDtModel;
import dtModel.vehicle.operator.OperatorPersonalData;
import dtModel.vehicle.operator.OperatorResidence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class TestDataValue {

    private TestDataValue() { }
    //Patient
    public static final PatientFiscalCode PATIENT_FISCAL_CODE = new PatientFiscalCode().setFiscalCode("patientTest");
    public static final String PATIENT_NAME = "namePatient";
    public static final String PATIENT_SURNAME = "surnamePatient";
    public static final LocalDate PATIENT_BIRTHDAY = LocalDate.of(1970, 7, 5);
    public static final Address PATIENT_ADDRESS = new Address().setAddress("Ferrari");
    public static final City PATIENT_CITY = new City().setCity("Forlì");
    public static final District PATIENT_DISTRICT = new District().setDistrict("FC");
    public static final PostalCode PATIENT_POSTAL_CODE = new PostalCode().setPostalCode(47722);
    public static final PatientResidence PATIENT_RESIDENCE = new PatientResidence()
        .setAddress(PATIENT_ADDRESS)
        .setCity(PATIENT_CITY)
        .setDistrict(PATIENT_DISTRICT)
        .setPostalCode(PATIENT_POSTAL_CODE);
    public static final PatientCondition PATIENT_CONDITION = new PatientCondition().setCode(60245001).setSystem("http://snomed.info/sct");

    public static final PatientPersonalData PATIENT_PERSONALDATA = new PatientPersonalData()
            .setFiscalCode(PATIENT_FISCAL_CODE)
            .setName(PATIENT_NAME)
            .setSurname(PATIENT_SURNAME)
            .setBirthDate(PATIENT_BIRTHDAY)
            .setResidence(PATIENT_RESIDENCE);
    public static final String PATIENT_RESOURCE = "{\"resourceType\":\"Patient\",\"contained\":[{\"resourceType\":\"Condition\",\"id\":\"1\",\"code\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"160245001\"}]}}],\"identifier\":[{\"value\":\"patientTest\"}],\"name\":[{\"use\":\"official\",\"family\":\"surnamePatient\",\"given\":[\"namePatient\"]}],\"birthDate\":\"1970-07-05\",\"address\":[{\"line\":[\"Ferrari\"],\"city\":\"Forlì\",\"district\":\"FC\",\"postalCode\":\"47722\"}]}";
    public static final PatientDtModel PATIENT_DT_MODEL = new PatientDtModel().setCondition(PATIENT_CONDITION).setPersonalData(PATIENT_PERSONALDATA);

    //Operator
    public static final String OPERATOR_ID = "operatorTest";
    public static final String OPERATOR_NAME = "nameOperator";
    public static final String OPERATOR_SURNAME = "surnameOperator";
    public static final LocalDate OPERATOR_BIRTHDAY = LocalDate.of(1990, 7, 5);
    public static final Address OPERATOR_ADDRESS = new Address().setAddress("Corso Cavour, 1");
    public static final City OPERATOR_CITY = new City().setCity("Gambettola");
    public static final District OPERATOR_DISTRICT = new District().setDistrict("FC");
    public static final PostalCode OPERATOR_POSTAL_CODE = new PostalCode().setPostalCode(47521);
    public static final OperatorResidence OPERATOR_RESIDENCE = new OperatorResidence()
            .setAddress(OPERATOR_ADDRESS)
            .setCity(OPERATOR_CITY)
            .setDistrict(OPERATOR_DISTRICT)
            .setPostalCode(OPERATOR_POSTAL_CODE);
    public static final OperatorPersonalData OPERATOR_PERSONALDATA = new OperatorPersonalData()
            .setName(OPERATOR_NAME)
            .setSurname(OPERATOR_SURNAME)
            .setBirthDate(OPERATOR_BIRTHDAY)
            .setResidence(OPERATOR_RESIDENCE);
    public static final OperatorDtModel OPERATOR_DT_MODEL = new OperatorDtModel().setPersonalData(OPERATOR_PERSONALDATA).setOperatorId(OPERATOR_ID);
    public static final String OPERATOR_RESOURCE = "{\"resourceType\":\"Practitioner\",\"contained\":[{\"resourceType\":\"PractitionerRole\",\"id\":\"1\",\"specialty\":[{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"159738005\"}]}]}],\"identifier\":[{\"value\":\"operatorTest\"}],\"name\":[{\"family\":\"surnameOperator\",\"given\":[\"nameOperator\"]}],\"address\":[{\"line\":[\"Corso Cavour, 1\"],\"city\":\"Gambettola\",\"district\":\"FC\",\"postalCode\":\"47521\"}],\"birthDate\":\"1990-07-05\"}";

    //Ambulance
    public static final String AMBULANCE_ID = "Ambulance1111";
    public static final GPSCoordinates AMBULANCE_COORDINATES = new GPSCoordinates().setLongitude(1.11).setLatitude(1.11);
    public static final String AMBULANCE_RESOURCE = "{\"resourceType\":\"Device\",\"contained\":[{\"resourceType\":\"Location\",\"id\":\"1\",\"physicalType\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"897293009\"}]},\"position\":{\"longitude\":1.11,\"latitude\":1.11}}],\"identifier\":[{\"value\":\"Ambulance1111\"}],\"status\":\"inactive\",\"type\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"465341007\"}]}}\n";
    public static final AmbulanceDtModel AMBULANCE_DT_MODEL = new AmbulanceDtModel().setId(AMBULANCE_ID).setState(AmbulanceState.FREE).setCoordinates(AMBULANCE_COORDINATES);
    public static final String REL_AMBULANCE_OPERATOR_ID = AMBULANCE_ID + "DriveBy" + OPERATOR_ID;

    //Transport
    public static final String TRANSPORT_ID = "Transport1111";
    public static final Address DEPARTURE_ADDRESS = new Address().setAddress("IV Settembre");
    public static final City DEPARTURE_CITY = new City().setCity("Cesena");
    public static final District DEPARTURE_DISTRICT = new District().setDistrict("FC");
    public static final PostalCode DEPARTURE_POSTAL_CODE = new PostalCode().setPostalCode(47521);
    public static final Address DESTINATION_ADDRESS = new Address().setAddress("Giovanni Bovio");
    public static final City DESTINATION_CITY = new City().setCity("Ravenna");
    public static final District DESTINATION_DISTRICT = new District().setDistrict("FC");
    public static final PostalCode DESTINATION_POSTAL_CODE = new PostalCode().setPostalCode(47521);
    public static final TransportLocation DEPARTURE = new TransportLocation()
            .setAddress(DEPARTURE_ADDRESS)
            .setCity(DEPARTURE_CITY)
            .setDistrict(DEPARTURE_DISTRICT)
            .setPostalCode(DEPARTURE_POSTAL_CODE);
    public static final TransportLocation DESTINATION = new TransportLocation()
            .setAddress(DESTINATION_ADDRESS)
            .setCity(DESTINATION_CITY)
            .setDistrict(DESTINATION_DISTRICT)
            .setPostalCode(DESTINATION_POSTAL_CODE);
    public static final TransportDtModel TRANSPORT_DT_MODEL_SCHEDULED = new TransportDtModel()
            .setId(TRANSPORT_ID)
            .setStartDateTime(LocalDateTime.of(2022, 02,10,15, 00))
            .setPhase(Phase.SCHEDULED)
            .setRoute(new TransportRoute().setDeparture(DEPARTURE).setDestination(DESTINATION));

    public static final TransportDtModel TRANSPORT_DT_MODEL_IN_PROGRESS = new TransportDtModel()
            .setId(TRANSPORT_ID)
            .setStartDateTime(LocalDateTime.of(2022, 02,10,15, 00))
            .setPhase(Phase.IN_PROGRESS)
            .setRoute(new TransportRoute().setDeparture(DEPARTURE).setDestination(DESTINATION));

    public static final TransportDtModel TRANSPORT_DT_MODEL_COMPLETED = new TransportDtModel()
            .setId(TRANSPORT_ID)
            .setStartDateTime(LocalDateTime.of(2022, 02,10,15, 00))
            .setPhase(Phase.COMPLETED)
            .setRoute(new TransportRoute().setDeparture(DEPARTURE).setDestination(DESTINATION));

    public static final String TRANSPORT_SCHEDULED_RESURCE = "{\"resourceType\":\"Appointment\",\"contained\":[{\"resourceType\":\"Location\",\"id\":\"1\",\"name\":\"departure\",\"address\":{\"line\":[\"IV Settembre\"],\"city\":\"Cesena\",\"district\":\"FC\",\"postalCode\":\"47521\"}},{\"resourceType\":\"Location\",\"id\":\"2\",\"name\":\"destination\",\"address\":{\"line\":[\"IV Settembre\"],\"city\":\"Cesena\",\"district\":\"FC\",\"postalCode\":\"47521\"}}],\"identifier\":[{\"value\":\"Transport1111-appointment\"}],\"serviceCategory\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/service-category\",\"code\":\"33\",\"display\":\"Transport\"}]}],\"serviceType\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/service-type\",\"code\":\"230\",\"display\":\"Patient Transport\"}]}],\"start\":\"2022-02-10T15:00:00.000+01:00\",\"participant\":[{\"actor\":{\"reference\":\"Patient/patientTest\",\"display\":\"namePatient surnamePatient\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Device/Ambulance1111\"},\"status\":\"accepted\"}]}";
    public static final String TRANSPORT_IN_PROGRESS_RESURCE = "{\"resourceType\":\"Encounter\",\"contained\":[{\"resourceType\":\"Procedure\",\"id\":\"1\",\"identifier\":[{\"value\":\"Transport1111-procedure\"}],\"status\":\"in-progress\",\"code\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"715537001\",\"display\":\"Transportation by ambulance (procedure)\"}]},\"subject\":{\"reference\":\"Patient/patientTest\",\"display\":\"namePatient surnamePatient\"},\"encounter\":{\"reference\":\"Transport1111-encounter\"},\"performer\":[{\"actor\":{\"reference\":\"Practitioner/operatorTest\",\"display\":\"nameOperator surnameOperator\"}},{\"actor\":{\"reference\":\"Device/Ambulance1111\"}}]}],\"identifier\":[{\"value\":\"Transport1111encounter\"}],\"status\":\"in-progress\",\"class\":{\"system\":\"http://terminology.hl7.org/CodeSystem/v3 -ActCode\",\"code\":\"AMB\"},\"serviceType\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/service-type\",\"code\":\"230\",\"display\":\"Patient Transport\"}]},\"subject\":{\"reference\":\"Patient/patientTest\",\"display\":\"namePatient surnamePatient\"},\"participant\":[{\"type\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v3-ParticipationType\",\"code\":\"ESC\"}]}],\"individual\":{\"reference\":\"Practitioner/operatorTest\",\"display\":\"nameOperator surnameOperator\"}},{\"type\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v3-ParticipationType\",\"code\":\"DIR\"}]}],\"individual\":{\"reference\":\"Device/Ambulance1111\"}}],\"appointment\":[{\"reference\":\"Transport1111appointment\"}],\"period\":{\"start\":\"2022-02-10T15:00:00+01:00\"}}";
    public static final String TRANSPORT_COMPLETED_RESURCE = "{\"resourceType\":\"Encounter\",\"contained\":[{\"resourceType\":\"Procedure\",\"id\":\"1\",\"identifier\":[{\"value\":\"Transport1111-procedure\"}],\"status\":\"completed\",\"code\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"715537001\",\"display\":\"Transportation by ambulance (procedure)\"}]},\"subject\":{\"reference\":\"Patient/patientTest\",\"display\":\"namePatient surnamePatient\"},\"encounter\":{\"reference\":\"Transport1111-encounter\"},\"performer\":[{\"actor\":{\"reference\":\"Practitioner/operatorTest\",\"display\":\"nameOperator surnameOperator\"}},{\"actor\":{\"reference\":\"Device/Ambulance1111\"}}]}],\"identifier\":[{\"value\":\"Transport1111-encounter\"}],\"status\":\"finished\",\"class\":{\"system\":\"http://terminology.hl7.org/CodeSystem/v3 -ActCode\",\"code\":\"AMB\"},\"serviceType\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/service-type\",\"code\":\"230\",\"display\":\"Patient Transport\"}]},\"subject\":{\"reference\":\"Patient/patientTest\",\"display\":\"namePatient surnamePatient\"},\"participant\":[{\"type\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v3-ParticipationType\",\"code\":\"ESC\"}]}],\"individual\":{\"reference\":\"Practitioner/operatorTest\",\"display\":\"nameOperator surnameOperator\"}},{\"type\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v3-ParticipationType\",\"code\":\"DIR\"}]}],\"individual\":{\"reference\":\"Device/Ambulance1111\"}}],\"appointment\":[{\"reference\":\"Transport1111-appointment\"}],\"period\":{\"start\":\"2022-02-10T15:00:00+01:00\"}}";

    //General location
    public static final String ADDRESS_VALUE = "Rossi";
    public static final String CITY_VALUE = "Forlì";
    public static final String DISTRICT_VALUE = "FC";
    public static final int POSTAL_CODE_VALUE = 47521;

    public static final Address ADDRESS = new Address().setAddress(ADDRESS_VALUE);
    public static final City CITY = new City().setCity(CITY_VALUE);
    public static final District DISTRICT = new District().setDistrict(DISTRICT_VALUE);
    public static final PostalCode POSTAL_CODE = new PostalCode().setPostalCode(POSTAL_CODE_VALUE);

    //Assert messagge
    public static final String NOT_EQUALS_VALUE = "recovered value is not equal to the past one";
    public static final String EQUALS_VALUE = "recovered value is equal to the correct one";
    public static final String EQUALS_DT = "recovered digital twin is equal to the correct one";
    public static final String EQUALS_REL = "recovered digital twin relationship is equal to the correct one";
    public static final String DELETED = "deleted with success";
    public static final String NOT_EXIST = "the digital twin correctly does not exist";
}
