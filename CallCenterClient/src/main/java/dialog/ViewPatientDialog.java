package dialog;

import httprequest.Trasporti116117HttpRequest;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ViewPatientDialog extends DtDialog{
    @Override
    public void createEntity() {
        initialize("Elenco pazienti", ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();

        try {
            Trasporti116117HttpRequest.getPatients().forEach(t ->
                    lv.getItems().add(t.getName().get(0).getGivenAsSingleString() + " " +
                            t.getName().get(0).getFamily() + " (" +
                            t.getIdentifierFirstRep().getValue() + ")"));
        } catch (IOException | ParseException | InterruptedException  e) {
            e.printStackTrace();
        }
        lv.setMinWidth(150);
        lv.setMaxWidth(150);
        getDtGridPane().add(lv, 0, 0);
        getDtDialog().getDialogPane().setContent(getDtGridPane());
        getDtDialog().showAndWait();
    }
}
