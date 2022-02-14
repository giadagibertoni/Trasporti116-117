package dialog;

import httprequest.ResourceNotFoundException;
import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.*;
import org.hl7.fhir.r4.model.Device;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddOperatorWorkDayDialog extends DtDialog{
    @Override
    public void createEntity() {
        initialize("Inserisci giorno lavorativo operatore", ButtonType.OK, ButtonType.CLOSE);

        //--->OPERATORS
        ComboBox<String> operator = new ComboBox<>();
        try {
            List<Practitioner> operators = Trasporti116117HttpRequest.getOperators();
            operators.forEach(o -> operator.getItems().add(o.getIdentifierFirstRep().getValue()));
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }
        getDtGridPane().add(new Label("Operatore"), 0, 0);
        getDtGridPane().add(operator, 1, 0);

        //--->AMBULANCES
        ComboBox<String> ambulance = new ComboBox<>();
        try {
            List<Device> ambulances = Trasporti116117HttpRequest.getAmbulances();
            ambulances.forEach(a -> ambulance.getItems().add(a.getIdentifierFirstRep().getValue()));
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }
        getDtGridPane().add(new Label("Ambulanza"), 0, 1);
        getDtGridPane().add(ambulance, 1, 1);

        //--->DATE
        DatePicker workDate = new DatePicker();
        workDate.setEditable(false);
        workDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
            }
        });
        getDtGridPane().add(new Label("Giorno assegnato"), 0, 2);
        getDtGridPane().add(workDate, 1, 2);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    try {
                        new Alert(Alert.AlertType.INFORMATION, "Aggiunto turno lavorativo per l'operatore: " +
                                Trasporti116117HttpRequest.addOperatorWorkDay(ambulance.getValue(), operator.getValue(), workDate.getValue())).show();
                    } catch (IOException | ResourceNotFoundException | InterruptedException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }
                });

    }
}
