package dialog;

import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ViewTransportDialog extends DtDialog {
    public static final String APPOINTMENT = "-appointment";
    public static final String ENCOUNTER = "-encounter";

    @Override
    public void createEntity() {
        initialize("Elenco trasporti", ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();

        try {
            Trasporti116117HttpRequest.getScheduledTransport().forEach(t ->
                    lv.getItems().add(t.getIdentifierFirstRep().getValue().replace(APPOINTMENT, "") + " Stato: Prenotato"));
            Trasporti116117HttpRequest.getCancelledTransport().forEach(t ->
                    lv.getItems().add(t.getIdentifierFirstRep().getValue().replace(APPOINTMENT, "") + " Stato: Cancellato"));
            Trasporti116117HttpRequest.getInProgressTransport().forEach(t ->
                    lv.getItems().add(t.getIdentifierFirstRep().getValue().replace(ENCOUNTER, "") + " Stato: In corso"));
            Trasporti116117HttpRequest.getCompletedTransport().forEach(t ->
                    lv.getItems().add(t.getIdentifierFirstRep().getValue().replace(ENCOUNTER, "") + " Stato: Completato"));
        } catch (IOException | ParseException | InterruptedException  e) {
            e.printStackTrace();
        }

        getDtGridPane().add(lv, 0, 0);
        getDtDialog().getDialogPane().setContent(getDtGridPane());
        getDtDialog().showAndWait();
    }
}
