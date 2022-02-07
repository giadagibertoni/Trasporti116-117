package openhostservice;

import vehiclebc.AmbulanceDigitalTwin;
import vehiclebc.OperatorDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {
    @PostMapping("/API/Vehicle/createAmbulance")
    @ResponseBody
    public String createAmbulance(@RequestBody String resource) {
        return AmbulanceDigitalTwin.createAmbulance(resource);
    }

    @PostMapping("/API/Vehicle/createOperator")
    @ResponseBody
    public String createOperator(@RequestBody String resource) {
        return OperatorDigitalTwin.createOperator(resource);
    }

    @GetMapping("/API/Vehicle/getAmbulances")
    @ResponseBody
    public List<String> getAmbulances() {
        return AmbulanceDigitalTwin.getAmbulances();
    }

    @GetMapping("/API/Vehicle/getOperators")
    @ResponseBody
    public List<String> getOperators() {
        return OperatorDigitalTwin.getOperators();
    }


}