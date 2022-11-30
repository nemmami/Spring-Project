package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "notifications")
public interface NotificationsProxy {

  @GetMapping("/notifications/users/{id}")
  Notification getUserNotif(@PathVariable int id);

  @DeleteMapping("/notifications/users/{id}")
  void deleteAllUserNotif(@PathVariable int id);

  @GetMapping("/notifications/trip/passengers/{id}")
  Notification getPassengerNotif(@PathVariable int id);





}
