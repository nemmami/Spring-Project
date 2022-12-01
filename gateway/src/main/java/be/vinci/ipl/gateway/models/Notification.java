package be.vinci.ipl.gateway.models;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Notification {
  private int userId;
  private int tripId;
  private String Date;
  private String NotificationText;
}