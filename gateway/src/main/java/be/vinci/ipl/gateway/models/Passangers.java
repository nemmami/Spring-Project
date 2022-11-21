package be.vinci.ipl.gateway.models;


import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Passangers {
  private ArrayList<User> pending;
  private ArrayList<User> accepted;
  private ArrayList<User> refused;


}
