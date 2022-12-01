package be.vinci.ipl.gateway;



import be.vinci.ipl.gateway.data.*;
import be.vinci.ipl.gateway.models.*;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

  private final AuthenticationProxy authenticationProxy;
  private final TripsProxy tripsProxy;
  private final UsersProxy usersProxy;
  private final PassengersProxy passengersProxy;
  private final NotificationsProxy notificationsProxy;

  public GatewayService(AuthenticationProxy authenticationProxy,
      TripsProxy tripsProxy, UsersProxy usersProxy, PassengersProxy passengersProxy, NotificationsProxy notificationsProxy) {
    this.authenticationProxy = authenticationProxy;
    this.tripsProxy = tripsProxy;
    this.usersProxy = usersProxy;
    this.passengersProxy = passengersProxy;
    this.notificationsProxy = notificationsProxy;

  }
  /**
   * connect a user
   *
   * @return a token as a String
   */

  public String connect(Credentials credentials){
    return authenticationProxy.connect(credentials);
  }

  /**
   * verify a token
   *
   * @return all passengers
   */
  public String verify(String token){
    return authenticationProxy.verify(token);
  }

  public void createUser(NewUser newUser){
    authenticationProxy.createCredentials(newUser.getEmail(), new Credentials(newUser.getEmail(),
        newUser.getPassword()));
    usersProxy.createUser(newUser.getEmail(), newUser);
  }


  //TODO
  public User findUserByMail(String mail){
     return null;
  }

  public void updatePassword(Credentials credentials){
    authenticationProxy.updateCredentials(credentials.getEmail(), credentials);
  }

  public User getUserInfo(int id){
   return usersProxy.readeUser(id);
  }

  public void updateUserInfo(int id, User user){
    usersProxy.updateUser(id, user);
  }

  public void deleteUser(int id){
    usersProxy.deleteUser(id);
    User user = usersProxy.readeUser(id);
    authenticationProxy.deletCredentials(user.getEmail());
    tripsProxy.deleteAllTripsWhereUserIsDriver(id);
  }

  public Iterable<Trip> getFutureDriverTrips(int id){
   return tripsProxy.getAllTripsWhereUserIsDriver(id);
  }

  //TODO
  public Iterable<Trip> getFuturePassengerTrips(int id){

    return null;
  }

  public Iterable<Notification> getUserNotification(int id){
   return notificationsProxy.getUserNotif(id);
  }

  public void deleteAllUserNotification(int id){
    notificationsProxy.deleteAllUserNotif(id);
  }

  public void createTrip(NewTrip newTrip){
    tripsProxy.createTrip(newTrip);
  }

  public Iterable<Trip> getListTrips(String dateDeparture, String originLon, String destinationLat, String destinationLon){
    return tripsProxy.getListTrips(dateDeparture,originLon,destinationLat,destinationLon);
  }

  public Trip getTripInfo(int id){
   return tripsProxy.getTripInfo(id);
  }

  public void deletTrip(int id){
    tripsProxy.deleteTrip(id);
  }

  public Iterable<Passengers> getPassangerList(int id){
    return passengersProxy.getAllPassengersFromTrip(id);
  }

  public void addPasengerToTrip(int trip_id, int user_id){
    passengersProxy.addUserToTrip(trip_id, user_id);
  }

  public String getPassengerStatus(int trip_id, int user_id){
    return passengersProxy.getPassengerStatus(trip_id, user_id);
  }

  public void updatePassengerStatus(int trip_id, int user_id, String status){
    passengersProxy.updatePassengerStatus(trip_id, user_id, status);
  }

  public void removePassengerFromTrip(int trip_id, int user_id){
    passengersProxy.deletePassengerFromTrip(trip_id, user_id);
  }



}
