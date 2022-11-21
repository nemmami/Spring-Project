package be.vinci.ipl.gateway;


import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.data.TripsProxy;
import be.vinci.ipl.gateway.data.UsersProxy;
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
}
