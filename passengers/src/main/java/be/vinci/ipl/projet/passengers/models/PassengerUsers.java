package be.vinci.ipl.projet.passengers.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassengerUsers {

  private List<User> pending = new ArrayList<>();
  private List<User> accepted = new ArrayList<>();
  private List<User> refused = new ArrayList<>();


  public void addUserPending(User user) {
    pending.add(user);
  }

  public void addUserAccepted(User user) {
    accepted.add(user);
  }

  public void addUserRefused(User user) {
    refused.add(user);
  }


}
