package be.vinci.ipl.calculated_distance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CalculatedDistanceController {
    private final CalculatedDistanceService service;

    public CalculatedDistanceController(CalculatedDistanceService service) {
        this.service = service;
    }

    @GetMapping("/calculatedDistance/{id}")
    public double getDistance(@PathVariable int id) {
        double distance = service.getDistance(id);
        if(distance == -1)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return distance;
    }
}
