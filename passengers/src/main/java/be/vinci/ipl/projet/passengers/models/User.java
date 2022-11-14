package be.vinci.ipl.projet.passengers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

  private int id;
  private String email;
  private String firstname;
  private String lastname;
}
