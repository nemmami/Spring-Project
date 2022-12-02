package be.vinci.ipl.trips.data;

import be.vinci.ipl.trips.models.PositionHolder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name = "calculatedDistance")
public interface CalculatedDistanceProxy {
    @GetMapping("/calculatedDistance/{id}")
    double getDistanceFromTrip(@PathVariable int id);

    @GetMapping("/calculatedDistance/")
    double getDistance(@RequestBody PositionHolder p);
}
