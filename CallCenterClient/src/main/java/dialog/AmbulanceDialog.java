package dialog;

import fhir.FHIRParser;
import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hl7.fhir.r4.model.*;
import utils.ControllInputField;

import java.io.IOException;

public class AmbulanceDialog extends DtDialog{
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

    @Override
    public void createEntity() {
        initialize("Crea Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        TextField ambulanceNumber = new TextField();
        ambulanceNumber.setPromptText("Numero ambulanza");
        getDtGridPane().add(new Label("Numero ambulanza"), 0, 0);
        getDtGridPane().add(ambulanceNumber, 1, 0);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (ControllInputField.NUMBER_PATTERN.matcher(ambulanceNumber.getText()).matches()) {
                        Device ambulance = new Device();
                        ambulance.addIdentifier(new Identifier().setValue("Ambulance" + ambulanceNumber.getText()));
                        ambulance.setStatus(Device.FHIRDeviceStatus.INACTIVE);

                        ambulance.setType(new CodeableConcept()
                                .addCoding(new Coding()
                                        .setSystem(SNOMED_SYSTEMS)
                                        .setCode(AMBULANCE_CODE)));

                        ambulance.addContained(new Location()
                                .setPhysicalType(new CodeableConcept()
                                        .addCoding(new Coding()
                                                .setSystem(SNOMED_SYSTEMS)
                                                .setCode(GPS_CODE)))
                                .setPosition(new Location.LocationPositionComponent(
                                        new DecimalType(0),
                                        new DecimalType(0))));

                        try {
                            new Alert(Alert.AlertType.INFORMATION,
                                    ControllInputField.AMBULANCE_CONFIRM +
                                            Trasporti116117HttpRequest.createAmbulance(FHIRParser.getParser().encodeResourceToString(ambulance)),
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
