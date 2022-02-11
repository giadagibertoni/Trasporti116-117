package openhostservice;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import patientbc.PatientDigitalTwin;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    @PostMapping("/API/Patient/createPatient")
    @ResponseBody
    public String createPatient(@RequestBody String resource) {
       return PatientDigitalTwin.createPatient(resource);
    }

    @GetMapping("/API/Patient/getPatients")
    @ResponseBody
    public JSONArray getPatients() {
        JSONArray patients = new JSONArray();
        JSONParser parser = new JSONParser();
        PatientDigitalTwin.getPatients().forEach(p -> {
            try {
                patients.add(parser.parse(p));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return patients;
    }

    @GetMapping("/API/Patient/getPatient")
    @ResponseBody
    public String getPatient(@RequestParam String id) {
        if (PatientDigitalTwin.getPatient(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "patient not found"
            );
        return PatientDigitalTwin.getPatient(id).get();
    }
}