/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dialog;

import fhir.FHIRParser;
import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.*;
import org.hl7.fhir.r4.model.*;
import utils.ControllInputField;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class AddPatientDialog extends DtDialog {
    @Override
    public void createEntity() {
        initialize("Aggiungi Paziente", ButtonType.OK, ButtonType.CANCEL);

        TextField name = new TextField();
        name.setPromptText("Nome");
        name.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.STRING_PATTERN, name);
            }
        });
        getDtGridPane().add(new Label("Nome"), 0, 0);
        getDtGridPane().add(name, 1, 0);

        TextField surname = new TextField();
        surname.setPromptText("Cognome");
        surname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.STRING_PATTERN, surname);
            }
        });
        getDtGridPane().add(new Label("Cognome"), 0, 1);
        getDtGridPane().add(surname, 1, 1);

        DatePicker birthday = new DatePicker();
        birthday.setEditable(false);
        getDtGridPane().add(new Label("Data di nascita"), 0, 2);
        getDtGridPane().add(birthday, 1, 2);

        TextField address = new TextField();
        address.setPromptText("Via");
        address.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, address);
            }
        });
        getDtGridPane().add(new Label("Via"), 0, 3);
        getDtGridPane().add(address, 1, 3);

        TextField houseNumber = new TextField();
        houseNumber.setPromptText("Numero");
        houseNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, houseNumber);
            }
        });
        getDtGridPane().add(new Label("Numero"), 0, 4);
        getDtGridPane().add(houseNumber, 1, 4);

        TextField city = new TextField();
        city.setPromptText("Città");
        city.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, city);
            }
        });
        getDtGridPane().add(new Label("Città"), 0, 5);
        getDtGridPane().add(city, 1, 5);

        TextField district = new TextField();
        district.setPromptText("Provincia");
        district.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, district);
            }
        });
        getDtGridPane().add(new Label("Provincia"), 0, 6);
        getDtGridPane().add(district, 1, 6);

        TextField postalCode = new TextField();
        postalCode.setPromptText("Cap");
        postalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, postalCode);
            }
        });
        getDtGridPane().add(new Label("Cap"), 0, 7);
        getDtGridPane().add(postalCode, 1, 7);

        TextField fiscalCode = new TextField();
        fiscalCode.setPromptText("Codice fiscale");
        fiscalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.FISCAL_CODE, fiscalCode);
            }
        });
        getDtGridPane().add(new Label("Codice fiscale"), 0, 8);
        getDtGridPane().add(fiscalCode, 1, 8);

        TextField systemCondition = new TextField();
        systemCondition.setPromptText("System");
        TextField codeCondition = new TextField();
        codeCondition.setPromptText("Codice FHIR");
        getDtGridPane().add(new Label("Condizioni di salute"), 0, 9);
        systemCondition.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.STRING_PATTERN, systemCondition);
            }
        });
        getDtGridPane().add(systemCondition, 1, 9);
        systemCondition.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, codeCondition);
            }
        });
        getDtGridPane().add(codeCondition, 2, 9);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (ControllInputField.STRING_PATTERN.matcher(name.getText()).matches()
                            && ControllInputField.STRING_PATTERN.matcher(surname.getText()).matches()
                            && ControllInputField.CITY_PATTERN.matcher(city.getText()).matches()
                            && ControllInputField.ADDRESS_PATTERN.matcher(address.getText()).matches()
                            && ControllInputField.DISTRICT_PATTERN.matcher(district.getText()).matches()
                            && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(postalCode.getText()).matches()
                            && ControllInputField.NUMBER_PATTERN.matcher(houseNumber.getText()).matches()
                            && ControllInputField.FISCAL_CODE.matcher(fiscalCode.getText()).matches()
                    ) {
                        Patient patient = new Patient();
                        patient.addIdentifier(new Identifier().setValue(fiscalCode.getText()));
                        patient.addName(new HumanName().setFamily(surname.getText()).addGiven(name.getText()).setUse(HumanName.NameUse.OFFICIAL));
                        patient.setBirthDate(Date.from(LocalDate.of(birthday.getValue().getYear(), birthday.getValue().getMonth(), birthday.getValue().getDayOfMonth())
                                .atStartOfDay(ZoneId.systemDefault()).toInstant()));

                        patient.addAddress(new Address()
                                .setDistrict(district.getText())
                                .setCity(city.getText())
                                .addLine(address.getText() + ", " + houseNumber.getText())
                                .setPostalCode(postalCode.getText()));

                        patient.addContained(new Condition().setCode(new CodeableConcept(
                                new Coding().setCode(codeCondition.getText()).setSystem(systemCondition.getText()))));

                        try {
                            new Alert(Alert.AlertType.INFORMATION,
                                    ControllInputField.PATIENT_CONFIRM +
                                    Trasporti116117HttpRequest.createPatient(FHIRParser.getParser().encodeResourceToString(patient)),
                                    ButtonType.CLOSE).show();
                        } catch (IOException | InterruptedException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }

                    } else {
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                    }
                });

    }

}
