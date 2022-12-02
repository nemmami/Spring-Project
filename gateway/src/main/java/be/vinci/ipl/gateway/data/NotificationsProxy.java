package be.vinci.ipl.gateway2.data;

import be.vinci.ipl.gateway.models.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name = "notifications")
public interface NotificationsProxy {

  @GetMapping("/notifications/users/{id}")
  Iterable<Notification> getUserNotif(@PathVariable int id);

  @PostMapping("/notifications/users/{id}")
  Notification addUserNotif(@PathVariable int id);

  @DeleteMapping("/notifications/users/{id}")
  void deleteAllUserNotif(@PathVariable int id);








}
