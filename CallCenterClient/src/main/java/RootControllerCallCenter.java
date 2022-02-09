/*
 * Copyright (c) 2022. Gibertoni Giada
 */

import dialog.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public final class RootControllerCallCenter implements Initializable {

    @FXML
    private transient Button addPatient;
    @FXML
    private transient Button addOperator;
    @FXML
    private transient Button addAmbulance;
    @FXML
    private transient Button trackAmbulance;
    @FXML
    private transient Button viewTransport;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        addPatient.setOnAction(event -> new PatientDialog().createEntity());
        addOperator.setOnAction(event -> new OperatorDialog().createEntity());
        addAmbulance.setOnAction(event -> new AmbulanceDialog().createEntity());
    }
}
