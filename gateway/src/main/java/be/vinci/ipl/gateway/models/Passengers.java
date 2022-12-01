package be.vinci.ipl.gateway.models;


import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Passengers {
  private ArrayList<User> pending;
  private ArrayList<User> accepted;
  private ArrayList<User> refused;


}
