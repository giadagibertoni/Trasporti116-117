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
    @FXML
    private transient Button addTransport;
    @FXML
    private transient  Button addOperatorWorkDay;
    @FXML
    private transient Button deleteTransport;
    @FXML
    private transient Button changeAmbulanceState;


    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        addPatient.setOnAction(event -> new AddPatientDialog().createEntity());
        addOperator.setOnAction(event -> new AddOperatorDialog().createEntity());
        addAmbulance.setOnAction(event -> new AddAmbulanceDialog().createEntity());
        addTransport.setOnAction(event -> new AddTransportDialog().createEntity());
        deleteTransport.setOnAction(event -> new DeleteTransport().createEntity());
        addOperatorWorkDay.setOnAction(event -> new AddOperatorWorkDayDialog().createEntity());
        changeAmbulanceState.setOnAction(event -> new ChangeAmbulanceState().createEntity());
        viewTransport.setOnAction(event -> new ViewTransportDialog().createEntity());
    }
}
