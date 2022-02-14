
import transportbc.dtmodel.*;
import vehiclebc.dtmodel.operator.OperatorAddress;
import vehiclebc.dtmodel.operator.OperatorCity;
import vehiclebc.dtmodel.operator.OperatorDistrict;
import vehiclebc.dtmodel.operator.OperatorPostalCode;
import vehiclebc.dtmodel.ambulance.AmbulanceDtModel;
import vehiclebc.dtmodel.ambulance.AmbulanceState;
import vehiclebc.dtmodel.ambulance.GPSCoordinates;
import vehiclebc.dtmodel.operator.OperatorDtModel;
import vehiclebc.dtmodel.operator.OperatorPersonalData;
import vehiclebc.dtmodel.operator.OperatorResidence;
import patientbc.dtmodel.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class TestDataValue {

    private TestDataValue() { }
    //Patient
    public static final PatientFiscalCode PATIENT_FISCAL_CODE = new PatientFiscalCode().setFiscalCode("RSSMRA70H60G827I");
    public static final String PATIENT_NAME = "Mario";
    public static final String PATIENT_SURNAME = "Rossi";
    public static final LocalDate PATIENT_BIRTHDAY = LocalDate.of(1970, 7, 5);
    public static final PatientAddress PATIENT_ADDRESS = new PatientAddress().setAddress("via Ferrari");
    public static final PatientCity PATIENT_CITY = new PatientCity().setCity("Gambettola");
    public static final PatientDistrict PATIENT_DISTRICT = new PatientDistrict().setDistrict("FC");
    public static final PatientPostalCode PATIENT_POSTAL_CODE = new PatientPostalCode().setPostalCode(47035);
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
    public static final String PATIENT_RESOURCE = "{\"resourceType\":\"Patient\",\"contained\":[{\"resourceType\":\"Condition\",\"id\":\"1\",\"code\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"160245001\"}]}}],\"identifier\":[{\"value\":\"" + PATIENT_FISCAL_CODE.getFiscalCode() +"\"}],\"name\":[{\"use\":\"official\",\"family\":\"" + PATIENT_SURNAME + "\",\"given\":[\"" + PATIENT_NAME + "\"]}],\"birthDate\":\"" + PATIENT_BIRTHDAY + "\",\"address\":[{\"line\":[\"" + PATIENT_ADDRESS.getAddress() + "\"],\"city\":\"" + PATIENT_CITY.getCity() + "\",\"district\":\"" + PATIENT_DISTRICT.getDistrict() + "\",\"postalCode\":\"" + PATIENT_POSTAL_CODE.getPostalCode() + "\"}]}";
    public static final PatientDtModel PATIENT_DT_MODEL = new PatientDtModel().setCondition(PATIENT_CONDITION).setPersonalData(PATIENT_PERSONALDATA);

    //Operator
    public static final String OPERATOR_ID = "Operator3";
    public static final String OPERATOR_NAME = "Francesco";
    public static final String OPERATOR_SURNAME = "Bianchi";
    public static final LocalDate OPERATOR_BIRTHDAY = LocalDate.of(1990, 7, 5);
    public static final OperatorAddress OPERATOR_ADDRESS = new OperatorAddress().setAddress("Corso Cavour, 1");
    public static final OperatorCity OPERATOR_CITY = new OperatorCity().setCity("Cesena");
    public static final OperatorDistrict OPERATOR_DISTRICT = new OperatorDistrict().setDistrict("FC");
    public static final OperatorPostalCode OPERATOR_POSTAL_CODE = new OperatorPostalCode().setPostalCode(47521);
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
    public static final String OPERATOR_RESOURCE = "{\"resourceType\":\"Practitioner\",\"contained\":[{\"resourceType\":\"PractitionerRole\",\"id\":\"1\",\"specialty\":[{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"159738005\"}]}]}],\"identifier\":[{\"value\":\"" + OPERATOR_ID +"\"}],\"name\":[{\"family\":\"" + OPERATOR_SURNAME + "\",\"given\":[\"" + OPERATOR_NAME + "\"]}],\"address\":[{\"line\":[\"" + OPERATOR_ADDRESS.getAddress() + "\"],\"city\":\"" + OPERATOR_CITY.getCity() + "\",\"district\":\"" + OPERATOR_DISTRICT.getDistrict() + "\",\"postalCode\":\"" + OPERATOR_POSTAL_CODE.getPostalCode() + "\"}],\"birthDate\":\"" + OPERATOR_BIRTHDAY + "\"}";

    //Ambulance
    public static final String AMBULANCE_ID = "Ambulance1111";
    public static final GPSCoordinates AMBULANCE_COORDINATES = new GPSCoordinates().setLongitude(1.11).setLatitude(1.11);
    public static final String AMBULANCE_RESOURCE = "{\"resourceType\":\"Device\",\"contained\":[{\"resourceType\":\"Location\",\"id\":\"1\",\"physicalType\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"897293009\"}]},\"position\":{\"longitude\":1.11,\"latitude\":1.11}}],\"identifier\":[{\"value\":\""+ AMBULANCE_ID + "\"}],\"status\":\"inactive\",\"type\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"465341007\"}]}}\n";
    public static final LocalDate WORK_DATE = LocalDate.of(2022, 3, 11);
    public static final AmbulanceDtModel AMBULANCE_DT_MODEL = new AmbulanceDtModel().setId(AMBULANCE_ID).setState(AmbulanceState.FREE).setCoordinates(AMBULANCE_COORDINATES);
    public static final String REL_AMBULANCE_OPERATOR_ID = AMBULANCE_ID + "DriveBy" + OPERATOR_ID + WORK_DATE;

    //Transport
    public static final String TRANSPORT_ID = "TransportTest";
    public static final TransportAddress DEPARTURE_ADDRESS = new TransportAddress().setAddress(" Viale Giovanni Ghirotti, 286");
    public static final TransportCity DEPARTURE_CITY = new TransportCity().setCity("Cesena");
    public static final TransportDistrict DEPARTURE_DISTRICT = new TransportDistrict().setDistrict("FC");
    public static final TransportPostalCode DEPARTURE_POSTAL_CODE = new TransportPostalCode().setPostalCode(47521);
    public static final TransportAddress DESTINATION_ADDRESS = new TransportAddress().setAddress("Via Ferrari");
    public static final TransportCity DESTINATION_CITY = new TransportCity().setCity("Gambettola");
    public static final TransportDistrict DESTINATION_DISTRICT = new TransportDistrict().setDistrict("FC");
    public static final TransportPostalCode DESTINATION_POSTAL_CODE = new TransportPostalCode().setPostalCode(47035);
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
            .setStartDateTime(LocalDateTime.of(2022, 02,10, 15,  00))
            .setEndDateTime(LocalDateTime.of(2022, 02, 10, 16, 00))
            .setPhase(Phase.SCHEDULED)
            .setRoute(new TransportRoute().setDeparture(DEPARTURE).setDestination(DESTINATION));

    public static final TransportDtModel TRANSPORT_DT_MODEL_IN_PROGRESS = new TransportDtModel()
            .setId(TRANSPORT_ID)
            .setStartDateTime(LocalDateTime.of(2022, 02, 10, 15, 00))
            .setEndDateTime(LocalDateTime.of(2022, 02, 10, 16, 00))
            .setPhase(Phase.IN_PROGRESS)
            .setRoute(new TransportRoute().setDeparture(DEPARTURE).setDestination(DESTINATION));

    public static final TransportDtModel TRANSPORT_DT_MODEL_COMPLETED = new TransportDtModel()
            .setId(TRANSPORT_ID)
            .setStartDateTime(LocalDateTime.of(2022, 02, 10, 15, 00))
            .setEndDateTime(LocalDateTime.of(2022, 02, 10, 16, 00))
            .setPhase(Phase.COMPLETED)
            .setRoute(new TransportRoute().setDeparture(DEPARTURE).setDestination(DESTINATION));


    public static final String TRANSPORT_SCHEDULED_RESURCE = "{\"resourceType\":\"Appointment\",\"contained\":[{\"resourceType\":\"Location\",\"id\":\"1\",\"name\":\"departure\",\"address\":{\"line\":[\"" + DEPARTURE_ADDRESS.getAddress() + "\"],\"city\":\"" + DEPARTURE_CITY.getCity() + "\",\"district\":\""  + DEPARTURE_DISTRICT.getDistrict() + "\",\"postalCode\":\"" + DEPARTURE_POSTAL_CODE.getPostalCode() + "\"}},{\"resourceType\":\"Location\",\"id\":\"2\",\"name\":\"destination\",\"address\":{\"line\":[\"" + DESTINATION_ADDRESS.getAddress() +"\"],\"city\":\"" + DESTINATION_CITY.getCity() + "\",\"district\":\"" + DESTINATION_DISTRICT.getDistrict() +"\",\"postalCode\":\""+ DESTINATION_POSTAL_CODE.getPostalCode() +"\"}}],\"identifier\":[{\"value\":\"" + TRANSPORT_ID + "-appointment\"}],\"serviceCategory\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/service-category\",\"code\":\"33\",\"display\":\"Transport\"}]}],\"serviceType\":[{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/service-type\",\"code\":\"230\",\"display\":\"Patient Transport\"}]}],\"start\":\"2022-02-10T14:00:00.000+01:00\",\"end\":\"2022-02-10T16:00:00.000+01:00\",\"participant\":[{\"actor\":{\"reference\":\"Patient/" + PATIENT_FISCAL_CODE.getFiscalCode() + "\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Device/" + AMBULANCE_ID + "\"},\"status\":\"accepted\"}]}";

    //Assert messagge
    public static final String NOT_EQUALS_VALUE = "recovered value is not equal to the past one";
    public static final String EQUALS_VALUE = "recovered value is equal to the correct one";
    public static final String EQUALS_DT = "recovered digital twin is equal to the correct one";
    public static final String EQUALS_REL = "recovered digital twin relationship is equal to the correct one";
    public static final String DELETED = "deleted with success";
    public static final String NOT_EXIST = "the digital twin correctly does not exist";
}
