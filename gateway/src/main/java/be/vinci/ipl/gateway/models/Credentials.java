package be.vinci.ipl.gateway2.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
  private String email;
  private String password;
}