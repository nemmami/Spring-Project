package be.vinci.ipl.gateway.models;


import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PassengerTrips {
  private ArrayList<Trip> pending;
  private ArrayList<User> accepted;
  private ArrayList<User> refused;


}
