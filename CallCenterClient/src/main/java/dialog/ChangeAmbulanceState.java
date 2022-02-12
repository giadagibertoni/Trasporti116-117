package dialog;

import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import org.json.simple.parser.ParseException;
import utils.ControllInputField;

import java.io.IOException;

public class ChangeAmbulanceState extends DtDialog{

    private final ButtonType start_under_maintanence = new ButtonType("Metti in manutenzione");
    private final ButtonType end_under_maintanence = new ButtonType("Termina manutenzione");
    private final ButtonType disused = new ButtonType("Metti in disuso");

    @Override
    public void createEntity() {
        initialize("Stato ambulanza", start_under_maintanence, end_under_maintanence, disused, ButtonType.CANCEL);
        ListView<String> lv = new ListView<>();

        try {
            Trasporti116117HttpRequest.getAmbulances().forEach(a ->
                    lv.getItems().add(a.getIdentifierFirstRep().getValue()));
        } catch (IOException | ParseException | InterruptedException  e) {
            e.printStackTrace();
        }

        getDtGridPane().add(lv, 0, 0);
        getDtDialog().getDialogPane().setContent(getDtGridPane());

        ButtonType buttonType = getDtDialog().showAndWait().get();

        if (start_under_maintanence.equals(buttonType)) {
            try {
                new Alert(Alert.AlertType.INFORMATION,
                        Trasporti116117HttpRequest.setAmbulanceUnderMaintenance(lv.getFocusModel().getFocusedItem()) + " è ora in manutenzione.",
                        ButtonType.CLOSE).show();

            } catch (IOException | InterruptedException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        } else if (end_under_maintanence.equals(buttonType)) {
            try {
                new Alert(Alert.AlertType.INFORMATION,
                        Trasporti116117HttpRequest.setAmbulanceFree(lv.getFocusModel().getFocusedItem()) + " è ora disponibile.",
                        ButtonType.CLOSE).show();
            } catch (IOException | InterruptedException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        } else if (disused.equals(buttonType)) {
            try {
                new Alert(Alert.AlertType.INFORMATION,
                        Trasporti116117HttpRequest.setAmbulanceDisused(lv.getFocusModel().getFocusedItem()) + " è ora in disuso.",
                        ButtonType.CLOSE).show();
            } catch (IOException | InterruptedException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        }
    }
}
