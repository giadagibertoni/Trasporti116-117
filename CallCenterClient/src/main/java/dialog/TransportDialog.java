package dialog;

import fhir.FHIRParser;
import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.*;
import org.hl7.fhir.r4.model.*;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import utils.ControllInputField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class TransportDialog extends DtDialog{

    /**
     * Coding system HL7 SYSTEMS SERVICE CATEGORY
     */
    public static final String HL7_SYSTEMS_SERVICE_CATEGORY = "http://terminology.hl7.org/CodeSystem/service-category";

    /**
     * Coding system HL7 SYSTEMS SERVICE TYPE
     */
    public static final String HL7_SYSTEMS_SERVICE_TYPE = "http://terminology.hl7.org/CodeSystem/service-type";

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
     * Patient reference
     */
    public static final String PATIENT_REF = "Patient/";

    /**
     * add to transportId for resource appointment
     */
    public static final String APPOINTMENT = "-appointment";

    /**
     * Device reference
     */
    public static final String DEVICE_REF = "Device/";

    public static final String ADDRESS = "Via";
    public static final String NUMBER = "Numero";
    public static final String CITY = "Città";
    public static final String DISTRICT = "Provincia";
    public static final String POSTAL_CODE = "Cap";

    public void createEntity()  {
        initialize("Richiesta di servizio", ButtonType.OK, ButtonType.CANCEL);
        getDtGridPane().add(new Label("Partenza"), 0, 1);

        TextField departureAddress = new TextField();
        departureAddress.setPromptText(ADDRESS);
        departureAddress.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, departureAddress);
            }
        });
        getDtGridPane().add(new Label(ADDRESS), 0, 2);
        getDtGridPane().add(departureAddress, 1, 2);

        TextField departureNumber = new TextField();
        departureNumber.setPromptText(NUMBER);
        departureNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, departureNumber);
            }
        });
        getDtGridPane().add(new Label(NUMBER), 0, 3);
        getDtGridPane().add(departureNumber, 1, 3);

        TextField departureCity = new TextField();
        departureCity.setPromptText(CITY);
        departureCity.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.CITY_PATTERN, departureCity);
            }
        });
        getDtGridPane().add(new Label(CITY), 0, 4);
        getDtGridPane().add(departureCity, 1, 4);

        TextField departureDistrict = new TextField();
        departureDistrict.setPromptText(DISTRICT);
        departureDistrict.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, departureDistrict);
            }
        });
        getDtGridPane().add(new Label(DISTRICT), 0, 5);
        getDtGridPane().add(departureDistrict, 1, 5);

        TextField departurePostalCode = new TextField();
        departurePostalCode.setPromptText(POSTAL_CODE);
        departurePostalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, departurePostalCode);
            }
        });
        getDtGridPane().add(new Label(POSTAL_CODE), 0, 6);
        getDtGridPane().add(departurePostalCode, 1, 6);

        getDtGridPane().add(new Label("Arrivo"), 0, 7);

        TextField destinationAddress = new TextField();
        destinationAddress.setPromptText(ADDRESS);
        destinationAddress.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, destinationAddress);
            }
        });
        getDtGridPane().add(new Label(ADDRESS), 0, 8);
        getDtGridPane().add(destinationAddress, 1, 8);

        TextField destinationNumber = new TextField();
        destinationNumber.setPromptText(NUMBER);
        destinationNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, destinationNumber);
            }
        });
        getDtGridPane().add(new Label(NUMBER), 0, 9);
        getDtGridPane().add(destinationNumber, 1, 9);

        TextField destinationCity = new TextField();
        destinationCity.setPromptText(CITY);
        destinationCity.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.CITY_PATTERN, destinationCity);
            }
        });
        getDtGridPane().add(new Label(CITY), 0, 10);
        getDtGridPane().add(destinationCity, 1, 10);

        TextField destinationDistrict = new TextField();
        destinationDistrict.setPromptText(DISTRICT);
        destinationDistrict.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, destinationDistrict);
            }
        });
        getDtGridPane().add(new Label(DISTRICT), 0, 11);
        getDtGridPane().add(destinationDistrict, 1, 11);

        TextField destinationPostalCode = new TextField();
        destinationPostalCode.setPromptText(POSTAL_CODE);
        destinationPostalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, destinationPostalCode);
            }
        });
        getDtGridPane().add(new Label(POSTAL_CODE), 0, 12);
        getDtGridPane().add(destinationPostalCode, 1, 12);

        DatePicker transportDate = new DatePicker();
        transportDate.setEditable(false);
        getDtGridPane().add(new Label("Data trasporto"), 0, 13);
        getDtGridPane().add(transportDate, 1, 13);

        TextField hourStartTrasport = new TextField();
        hourStartTrasport.setPromptText("hh:mm");
        hourStartTrasport.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.HOUR_PATTERN, hourStartTrasport);
            }
        });
        getDtGridPane().add(new Label("Ora inizio trasporto"), 0, 14);
        getDtGridPane().add(hourStartTrasport, 1, 14);

        TextField hourEndTrasport = new TextField();
        hourEndTrasport.setPromptText("hh:mm");
        hourEndTrasport.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.HOUR_PATTERN, hourEndTrasport);
            }
        });
        getDtGridPane().add(new Label("Ora fine trasporto prevista"), 0, 15);
        getDtGridPane().add(hourEndTrasport, 1, 15);

        getDtGridPane().add(new Label("Ambulanza"), 0, 16);
        Label ambulanceLabel = new Label("");
        getDtGridPane().add(ambulanceLabel, 1, 16);

        //--> CHECK AMBULANCE
        Button checkAmbulance = new Button("Controlla ambulanza disponibile");
        checkAmbulance.setOnAction(a -> {
            try {
                ambulanceLabel.setText(Trasporti116117HttpRequest.getFreeAmbulance(
                        LocalDateTime.of(transportDate.getValue().getYear(),
                                transportDate.getValue().getMonth(),
                                transportDate.getValue().getDayOfMonth(),
                                Integer.parseInt(hourStartTrasport.getText().split(":")[0]),
                                Integer.parseInt(hourStartTrasport.getText().split(":")[1])),
                        LocalDateTime.of(transportDate.getValue().getYear(),
                                transportDate.getValue().getMonth(),
                                transportDate.getValue().getDayOfMonth(),
                                Integer.parseInt(hourEndTrasport.getText().split(":")[0]),
                                Integer.parseInt(hourEndTrasport.getText().split(":")[1]))));

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        getDtGridPane().add(checkAmbulance, 2,16);
        //--> FINE CHECK AMBULANCE

        //--->PAZIENTE
        ComboBox<String> patient = new ComboBox<>();
        try {
            List<Patient> patients = Trasporti116117HttpRequest.getPatients();
            patients.forEach(p -> patient.getItems().add(p.getIdentifierFirstRep().getValue()));
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }

        getDtGridPane().add(new Label("Paziente"), 0, 17);
        getDtGridPane().add(patient, 1, 17);
        //--->FINE PAZIENTE

        getDtDialog().getDialogPane().setContent(getDtGridPane());
        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (ControllInputField.CITY_PATTERN.matcher(departureCity.getText()).matches()
                            && ControllInputField.ADDRESS_PATTERN.matcher(departureAddress.getText()).matches()
                            && ControllInputField.DISTRICT_PATTERN.matcher(departureDistrict.getText()).matches()
                            && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(departurePostalCode.getText()).matches()
                            && ControllInputField.NUMBER_PATTERN.matcher(departureNumber.getText()).matches()
                            && ControllInputField.CITY_PATTERN.matcher(destinationCity.getText()).matches()
                            && ControllInputField.ADDRESS_PATTERN.matcher(destinationAddress.getText()).matches()
                            && ControllInputField.DISTRICT_PATTERN.matcher(destinationDistrict.getText()).matches()
                            && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(destinationPostalCode.getText()).matches()
                            && ControllInputField.NUMBER_PATTERN.matcher(destinationNumber.getText()).matches()
                            && ControllInputField.HOUR_PATTERN.matcher(hourStartTrasport.getText()).matches()
                            && ControllInputField.HOUR_PATTERN.matcher(hourEndTrasport.getText()).matches()
                            && ! (ambulanceLabel.getText().equals(ControllInputField.AMBULANCE_NOT_FOUND) || ambulanceLabel.getText().isEmpty())
                    ) {
                        LocalDateTime startDateTime = LocalDateTime.of(transportDate.getValue().getYear(),
                                transportDate.getValue().getMonth(),
                                transportDate.getValue().getDayOfMonth(),
                                Integer.parseInt(hourStartTrasport.getText().split(":")[0]),
                                Integer.parseInt(hourStartTrasport.getText().split(":")[1]));
                        LocalDateTime endDateTime = LocalDateTime.of(transportDate.getValue().getYear(),
                                transportDate.getValue().getMonth(),
                                transportDate.getValue().getDayOfMonth(),
                                Integer.parseInt(hourEndTrasport.getText().split(":")[0]),
                                Integer.parseInt(hourEndTrasport.getText().split(":")[1]));

                        DomainResource transportAppointment = new Appointment()
                                .addIdentifier(new Identifier().setValue(patient.getValue() + startDateTime.toString().replace(":", "-") + APPOINTMENT))
                                .addServiceCategory(getServiceCategory())
                                .addServiceType(getServiceType())
                                .setStart(Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                                .setEnd(Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                                .setParticipant(List.of(
                                        new Appointment.AppointmentParticipantComponent()
                                                .setActor(getPatientRef(patient.getValue()))
                                                .setStatus(Appointment.ParticipationStatus.ACCEPTED),
                                        new Appointment.AppointmentParticipantComponent()
                                                .setActor(getAmbulanceReference(ambulanceLabel.getText()))
                                                .setStatus(Appointment.ParticipationStatus.ACCEPTED)))
                                .setContained(List.of(
                                        new Location()
                                                .setName("departure")
                                                .setAddress(new Address()
                                                        .addLine(departureAddress.getText() + ", " + departureNumber.getText())
                                                        .setCity(departureCity.getText())
                                                        .setDistrict(departureDistrict.getText())
                                                        .setPostalCode(departurePostalCode.getText())),
                                        new Location()
                                                .setName("destination")
                                                .setAddress(new Address()
                                                        .addLine(destinationAddress.getText() + ", " + destinationNumber.getText())
                                                        .setCity(destinationCity.getText())
                                                        .setDistrict(destinationDistrict.getText())
                                                        .setPostalCode(destinationPostalCode.getText()))));
                        try {
                            new Alert(Alert.AlertType.INFORMATION,
                                    ControllInputField.BOOKING_CONFIRM +
                                            Trasporti116117HttpRequest.createTransport(FHIRParser.getParser().encodeResourceToString(transportAppointment)),
                                    ButtonType.CLOSE).show();
                        } catch (IOException | InterruptedException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                    }
                });
    }

    private static CodeableConcept getServiceCategory(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(HL7_SYSTEMS_SERVICE_CATEGORY)
                        .setCode(SERVICE_CATEGORY)
                        .setDisplay(SERVICE_CATEGORY_DISPLAY));
    }

    private static CodeableConcept getServiceType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(HL7_SYSTEMS_SERVICE_TYPE)
                        .setCode(SERVICE_TYPE)
                        .setDisplay(SERVICE_TYPE_DISPLAY));
    }

    private static Reference getPatientRef(@NotNull String patientId){
        return new Reference().setReference(PATIENT_REF + patientId);
    }

    private static Reference getAmbulanceReference(@NotNull String ambulanceId){
        return new Reference().setReference(DEVICE_REF + ambulanceId);
    }
}
