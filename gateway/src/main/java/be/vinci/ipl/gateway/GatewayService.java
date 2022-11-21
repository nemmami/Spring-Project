package be.vinci.ipl.gateway;


import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.data.TripsProxy;
import be.vinci.ipl.gateway.data.UsersProxy;
import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

  private final AuthenticationProxy authenticationProxy;
  private final TripsProxy tripsProxy;
  private final UsersProxy usersProxy;

  public GatewayService(AuthenticationProxy authenticationProxy,
      TripsProxy tripsProxy, UsersProxy usersProxy) {
    this.authenticationProxy = authenticationProxy;
    this.tripsProxy = tripsProxy;
    this.usersProxy = usersProxy;
  }

  public String connect(Credentials credentials){
    return authenticationProxy.connect(credentials);
  }

  public String verify(String token){
    return authenticationProxy.verify(token);
  }

  public void createUser(NewUser newUser){
    authenticationProxy.createCredentials(newUser.getEmail(), new Credentials(newUser.getEmail(),
        newUser.getPassword()));
    usersProxy.createUser( newUser);
  }

  public void findUserByMail(String mail){
    usersProxy.findUserEmail(mail);
  }

  public void UpdatePassword(Credentials credentials){
    usersProxy.updatePassword(credentials);
    authenticationProxy.updateCredentials(credentials.getEmail(), credentials);
  }

}
