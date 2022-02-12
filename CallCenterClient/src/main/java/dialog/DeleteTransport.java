package dialog;

import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import org.json.simple.parser.ParseException;
import utils.ControllInputField;

import java.io.IOException;


public class DeleteTransport extends DtDialog{
    /**
     * add to transportId for resource appointment
     */
    public static final String APPOINTMENT = "-appointment";

    @Override
    public void createEntity() {
        initialize("Annulla trasporto", ButtonType.OK, ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();

        try {
            Trasporti116117HttpRequest.getScheduledTransport().forEach(t ->
                    lv.getItems().add(t.getIdentifierFirstRep().getValue().replace(APPOINTMENT, "")));
        } catch (IOException | ParseException | InterruptedException  e) {
            e.printStackTrace();
        }
        getDtGridPane().add(lv, 0, 0);
        getDtDialog().getDialogPane().setContent(getDtGridPane());
        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    try {
                        ;
                        new Alert(Alert.AlertType.INFORMATION,
                                ControllInputField.BOOKING_DELETED + Trasporti116117HttpRequest.deleteTransport(lv.getFocusModel().getFocusedItem()),
                                ButtonType.CLOSE).show();
                    } catch (IOException | InterruptedException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                    }
                });
    }
}
