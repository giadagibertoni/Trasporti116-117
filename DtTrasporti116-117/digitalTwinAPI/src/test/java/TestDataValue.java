import domain.*;
import domain.patient.*;
import domain.transport.TransportLocation;
import domain.vehicle.operator.OperatorResidence;

import java.time.LocalDate;

public final class TestDataValue {

    private TestDataValue() { }
    //Ambulance
    public static final int AMBULANCE_NUMBER = 1111;

    //Patient
    public static final PatientFiscalCode PATIENT_FISCAL_CODE = new PatientFiscalCode("patientTest");
    public static final String PATIENT_NAME = "namePatient";
    public static final String PATIENT_SURNAME = "surnamePatient";
    public static final LocalDate PATIENT_BIRTHDAY = LocalDate.of(1970, 7, 5);
    public static final Address PATIENT_ADDRESS = new Address("Ferrari");
    public static final City PATIENT_CITY = new City("Forlì");
    public static final District PATIENT_DISTRICT = new District("FC");
    public static final PostalCode PATIENT_POSTAL_CODE = new PostalCode(47722);
    public static final PatientResidence PATIENT_RESIDENCE = new PatientResidence(PATIENT_ADDRESS, PATIENT_CITY, PATIENT_DISTRICT, PATIENT_POSTAL_CODE);
    public static final PatientCondition PATIENT_CONDITION = new PatientCondition(160245001, "http://snomed.info/sct");

    public static final PatientPersonalData PATIENT_PERSONALDATA = new PatientPersonalData(PATIENT_NAME, PATIENT_SURNAME, PATIENT_BIRTHDAY, PATIENT_RESIDENCE);
    public static final String PATIENT_RESOURCE = "{\"resourceType\":\"Patient\",\"contained\":[{\"resourceType\":\"Condition\",\"id\":\"1\",\"code\":{\"coding\":[{\"system\":\"http://snomed.info/sct\",\"code\":\"160245001\"}]}}],\"identifier\":[{\"value\":\"patientTest\"}],\"name\":[{\"use\":\"official\",\"family\":\"surnamePatient\",\"given\":[\"namePatient\"]}],\"telecom\":[{\"system\":\"phone\",\"value\":\"3334455678\"}],\"birthDate\":\"1970-07-05\",\"address\":[{\"line\":[\"Ferrari\"],\"city\":\"Forlì\",\"district\":\"FC\",\"postalCode\":\"47722\"}]}";
    //Operator
    public static final String OPERATOR_ID = "operatorTest";
    public static final String OPERATOR_NAME = "nameOperator";
    public static final String OPERATOR_SURNAME = "surnameOperator";
    public static final LocalDate OPERATOR_BIRTHDAY = LocalDate.of(1990, 7, 5);
    public static final Address OPERATOR_ADDRESS = new Address("Corso Cavour");
    public static final HouseNumber OPERATOR_HOUSENUMBER = new HouseNumber(1);
    public static final City OPERATOR_CITY = new City("Gambettola");
    public static final District OPERATOR_DISTRICT = new District("FC");
    public static final PostalCode OPERATOR_POSTAL_CODE = new PostalCode(47521);
    public static final OperatorResidence OPERATOR_RESIDENCE = new OperatorResidence(OPERATOR_ADDRESS, OPERATOR_HOUSENUMBER, OPERATOR_CITY, OPERATOR_DISTRICT, OPERATOR_POSTAL_CODE);

    //Transport
    public static final Address DEPARTURE_ADDRESS = new Address("IV Settembre");
    public static final HouseNumber DEPARTURE_HOUSENUMBER = new HouseNumber(1);
    public static final City DEPARTURE_CITY = new City("Cesena");
    public static final District DEPARTURE_DISTRICT = new District("FC");
    public static final PostalCode DEPARTURE_POSTAL_CODE = new PostalCode(47521);
    public static final Address DESTINATION_ADDRESS = new Address("Giovanni Bovio");
    public static final HouseNumber DESTINATION_HOUSENUMBER = new HouseNumber(122);
    public static final City DESTINATION_CITY = new City("Ravenna");
    public static final District DESTINATION_DISTRICT = new District("FC");
    public static final PostalCode DESTINATION_POSTAL_CODE = new PostalCode(47521);
    public static final TransportLocation DEPARTURE = new TransportLocation(DEPARTURE_ADDRESS, DEPARTURE_HOUSENUMBER, DEPARTURE_CITY, DEPARTURE_DISTRICT, DEPARTURE_POSTAL_CODE);
    public static final TransportLocation DESTINATION = new TransportLocation(DESTINATION_ADDRESS, DESTINATION_HOUSENUMBER, DESTINATION_CITY, DESTINATION_DISTRICT, DESTINATION_POSTAL_CODE);

    //General location
    public static final String ADDRESS_VALUE = "Rossi";
    public static final int HOUSENUMBER_VALUE = 123;
    public static final String CITY_VALUE = "Forlì";
    public static final String DISTRICT_VALUE = "FC";
    public static final int POSTAL_CODE_VALUE = 47521;

    public static final Address ADDRESS = new Address(ADDRESS_VALUE);
    public static final HouseNumber HOUSENUMBER = new HouseNumber(HOUSENUMBER_VALUE);
    public static final City CITY = new City(CITY_VALUE);
    public static final District DISTRICT = new District(DISTRICT_VALUE);
    public static final PostalCode POSTAL_CODE = new PostalCode(POSTAL_CODE_VALUE);

    //Assert messagge
    public static final String NOT_EQUALS_VALUE = "recovered value is not equal to the past one";
    public static final String EQUALS_VALUE = "recovered value is equal to the correct one";
    public static final String EQUALS_DT = "recovered digital twin is equal to the correct one";
    public static final String EQUALS_REL = "recovered digital twin relationship is equal to the correct one";
    public static final String DELETED = "deleted with success";
    public static final String NOT_EXIST = "the digital twin correctly does not exist";
}
