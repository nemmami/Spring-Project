package be.vinci.ipl.gateway;



import be.vinci.ipl.gateway.data.*;
import be.vinci.ipl.gateway.models.*;
import com.google.common.reflect.ImmutableTypeToInstanceMap;
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

  public User findUserByMail(String mail){
     return usersProxy.findUserEmail(mail);
  }

  public void updatePassword(Credentials credentials){
    usersProxy.updatePassword(credentials);
    authenticationProxy.updateCredentials(credentials.getEmail(), credentials);
  }

  public User getUserInfo(int id){
   return usersProxy.getUserInfo(id);
  }

  public void updateUserInfo(int id, User user){
    usersProxy.updateUserInfo(id, user);
  }

  public void deleteUser(int id){
    usersProxy.deleteUser(id);
    User user = usersProxy.getUserInfo(id);
    authenticationProxy.deletCredentials(user.getEmail());
    //tripsProxy.deleteTrip(xx);
  }

  public Iterable<Trip> getFutureDriverTrips(int id){
   return usersProxy.getFutureDriverTrips(id);
  }

  public Iterable<Trip> getFuturePassengerTrips(int id){
    return  usersProxy.getFuturePassengerTrips(id);
  }

  public Iterable<Notification> getUserNotification(int id){
   return usersProxy.getUserNotification(id);
  }

  public void deleteAllUserNotification(int id){
    usersProxy.deleteAllUserNotification(id);
  }

  public void createTrip(NewTrip newTrip){
    tripsProxy.createTrip(newTrip);
  }

  public void getListTrips(String dateDeparture, String originLon, String destinationLat, String destinationLon){
    tripsProxy.getListTrips(dateDeparture,originLon,destinationLat,destinationLon);
  }

  public Trip getTripInfo(int id){
   return tripsProxy.getTripInfo(id);
  }

  public void deletTrip(int id){
    tripsProxy.deleteTrip(id);
  }

  public Iterable<Passengers> getPassangerList(int id){
    return tripsProxy.getPassengerList(id);
  }

  public void addPasengerToTrip(int trip_id, int user_id){
    tripsProxy.addPasengerToTrip(trip_id, user_id);
  }

  public String getPassengerStatus(int trip_id, int user_id){
    return tripsProxy.getPassengerStatus(trip_id, user_id);
  }

  public void updatePassengerStatus(int trip_id, int user_id, String status){
    tripsProxy.updatePassengerStatus(trip_id, user_id, status);
  }

  public void removePassengerFromTrip(int trip_id, int user_id){
    tripsProxy.removePassengerFromTrip(trip_id, user_id);
  }



}
