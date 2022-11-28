package be.vinci.ipl.projet.passengers.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PassengerUsers {

  private List<Long> pending = new ArrayList<>();
  private List<Long> accepted = new ArrayList<>();
  private List<Long> refused = new ArrayList<>();

  public PassengerUsers() {
  }

  public void addUserPending(long user) {
    pending.add(user);
  }

  public void addUserAccepted(long user) {
    accepted.add(user);
  }

  public void addUserRefused(long user) {
    refused.add(user);
  }

//  private Map<String, List<User>> pending = new HashMap<>();
//  private Map<String, List<User>> accepted = new HashMap<>();
//  private Map<String, List<User>> refused = new HashMap<>();
//
//  public PassengerUsers() {
//    pending.put("pending", new ArrayList<>());
//    accepted.put("accepted", new ArrayList<>());
//    refused.put("refused", new ArrayList<>());
//  }
//
//  public void addUserPending(User user){
//    pending.get("pending").add(user);
//  }
//
//  public void addUserAccepted(User user){
//    accepted.get("accepted").add(user);
//  }
//
//  public void addUserRefused(User user){
//    refused.get("refused").add(user);
//  }

}
