package be.vinci.ipl.gateway.models;


import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassangerTrips {
  private ArrayList<Trip> pending;
  private ArrayList<User> accepted;
  private ArrayList<User> refused;


}
