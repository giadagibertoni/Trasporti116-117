package openhostservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import vehiclebc.AmbulanceDigitalTwin;
import vehiclebc.OperatorDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/API/Vehicle/addOperatorWorkDay")
    @ResponseBody
    public void addOperatorWorkDay(@RequestParam String ambulanceId, @RequestParam String operatorId, @RequestParam String date) {
        AmbulanceDigitalTwin.addOperatorWorkDay(ambulanceId, operatorId, LocalDate.parse(date));
    }

    @GetMapping("/API/Vehicle/getFreeAmbulance")
    @ResponseBody
    public String getFreeAmbulance(@RequestParam String startDateTime, @RequestParam String endDateTime) {
        Optional<String> ambulance = AmbulanceDigitalTwin.getFreeAmbulance(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
        if (ambulance.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Ambulance not found"
            );
        return ambulance.get();
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

    @GetMapping("/API/Vehicle/getAmbulance")
    @ResponseBody
    public String getAmbulance(@RequestParam String id) {
        if (AmbulanceDigitalTwin.getAmbulance(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ambulance not found"
            );
        return AmbulanceDigitalTwin.getAmbulance(id).get();
    }

    @GetMapping("/API/Vehicle/getOperator")
    @ResponseBody
    public String getOperator(@RequestParam String id) {
        if (OperatorDigitalTwin.getOperator(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "operator not found"
            );
        return OperatorDigitalTwin.getOperator(id).get();
    }

}