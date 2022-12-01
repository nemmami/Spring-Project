package be.vinci.ipl.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "notifications")
public class Notification {
    @Id
    private Integer id;
    private Integer userId;
    private Integer tripId;
    private LocalDate date;
    private String notificationText;

}
