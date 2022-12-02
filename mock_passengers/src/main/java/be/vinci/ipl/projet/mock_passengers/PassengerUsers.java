package be.vinci.ipl.projet.mock_passengers;

import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassengerUsers {
  private ArrayList<User> pending;
  private ArrayList<User> accepted;
  private ArrayList<User> refused;
}
