package be.vinci.ipl.gateway;



import be.vinci.ipl.gateway.data.*;
import be.vinci.ipl.gateway.models.*;
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

  public void updatePassword(Credentials credentials){
    usersProxy.updatePassword(credentials);
    authenticationProxy.updateCredentials(credentials.getEmail(), credentials);
  }

  public void getUserInfo(int id){
    usersProxy.getUserInfo(id);
  }

  public void upateUserInfo(int id, User user){
    usersProxy.updateUserInfo(id, user);
  }

  public void deleteUser(User user){
    usersProxy.deleteUser(user.getId());
    authenticationProxy.deletCredentials(user.getEmail());
    //tripsProxy.deleteTrip(xx);
  }

  public void getFutureDriverTrips(int id){
    usersProxy.getFutureDriverTrips(id);
  }

  public void getFuturePassengerTrips(int id){
    usersProxy.getFuturePassengerTrips(id);
  }

  public void getUserNotification(int id){
    usersProxy.getUserNotification(id);
  }

  public void deleteAllUserNotification(int id){
    usersProxy.deleteAllUserNotification(id);
  }

  public void createTrip(NewTrip newTrip){
    tripsProxy.createTrip(newTrip);
  }

  public void getListTrips(String dateDeparture){
    tripsProxy.getListTrips(dateDeparture);
    //TODO
  }


}
