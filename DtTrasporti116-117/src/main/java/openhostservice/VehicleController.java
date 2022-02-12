package openhostservice;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import vehiclebc.AmbulanceDigitalTwin;
import vehiclebc.OperatorDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public String addOperatorWorkDay(@RequestParam String ambulanceId, @RequestParam String operatorId, @RequestParam String date) throws ParseException {
        return AmbulanceDigitalTwin.addOperatorWorkDay(ambulanceId, operatorId, LocalDate.parse(date));
    }

    @PostMapping("/API/Vehicle/setAmbulanceUnderMaintenance")
    @ResponseBody
    public String setAmbulanceUnderMaintenance(@RequestBody String id) {
        return AmbulanceDigitalTwin.setAmbulanceUnderMaintenance(id);
    }

    @PostMapping("/API/Vehicle/setAmbulanceFree")
    @ResponseBody
    public String setAmbulanceFree(@RequestBody String id) {
        return AmbulanceDigitalTwin.setAmbulanceFree(id);
    }

    @PostMapping("/API/Vehicle/setAmbulanceDisused")
    @ResponseBody
    public String setAmbulanceDisused(@RequestBody String id) {
        return AmbulanceDigitalTwin.setAmbulanceDisused(id);
    }

    @PostMapping("/API/Vehicle/setAmbulanceBusy")
    @ResponseBody
    public String setAmbulanceBusy(@RequestBody String id) {
        return AmbulanceDigitalTwin.setAmbulanceBusy(id);
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
    public JSONArray getAmbulances() {
        JSONArray ambulances = new JSONArray();
        JSONParser parser = new JSONParser();
        AmbulanceDigitalTwin.getAmbulances().forEach(a -> {
            try {
                ambulances.add(parser.parse(a));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return ambulances;
    }

    @GetMapping("/API/Vehicle/getOperators")
    @ResponseBody
    public JSONArray getOperators() {
        JSONArray operators = new JSONArray();
        JSONParser parser = new JSONParser();
        OperatorDigitalTwin.getOperators().forEach(o -> {
            try {
                operators.add(parser.parse(o));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return operators;
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