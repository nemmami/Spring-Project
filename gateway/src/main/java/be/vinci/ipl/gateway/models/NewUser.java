package be.vinci.ipl.gateway.models;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewUser {
  private String email;
  private String firstname;
  private String lastname;
  private String password;
}