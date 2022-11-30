package be.vinci.ipl.calculated_distance.data;

import be.vinci.ipl.calculated_distance.models.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "trips")
public interface TripsProxy {
    @GetMapping("/trips/{id}")
    Trip readOne(@PathVariable int id);
}
