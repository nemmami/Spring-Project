package be.vinci.ipl.gateway2;



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
   * @param credentials user email and password
   * @return a token as a String
   */

  public String connect(Credentials credentials){
    return authenticationProxy.connect(credentials);
  }

  /**
   * verify a token
   *
   * @param token token
   * @return all passengers
   */
  public String verify(String token){
    return authenticationProxy.verify(token);
  }


  /**
   * Create new user
   *
   * @param newUser the user to create
   * @return the new user
   */
  public User createUser(NewUser newUser){
    authenticationProxy.createCredentials(newUser.getEmail(), new Credentials(newUser.getEmail(),
        newUser.getPassword()));

     return usersProxy.createUser( newUser);
  }



  /**
   * find user by email
   *
   * @param mail of the user we want to find
   * @return the user
   */
  public User findUserByMail(String mail){
     return usersProxy.readeUserByMail(mail);
  }


  /**
   * update user password
   *
   * @param credentials the new credentials
   */
  public void updatePassword(Credentials credentials){
    authenticationProxy.updateCredentials(credentials.getEmail(), credentials);
  }


  /**
   * get user by ID
   *
   * @param id of the user
   * @return user
   */
  public User getUserInfo(int id){
   return usersProxy.readeUser(id);
  }

  /**
   * update user info
   *
   * @param id of the user
   * @param user new user info
   */
  public void updateUserInfo(int id, User user){
    usersProxy.updateUser(id, user);
  }


  /**
   * delete user by id
   *
   * @param id of the user
   */
  public void deleteUser(int id){
    usersProxy.deleteUser(id);
    User user = usersProxy.readeUser(id);
    authenticationProxy.deletCredentials(user.getEmail());
    tripsProxy.deleteAllTripsWhereUserIsDriver(id);
  }

  /**
   * get all the trips where the user is the driver
   *
   * @param id of the user
   * @return all the trips
   */
  public Iterable<Trip> getFutureDriverTrips(int id){
   return tripsProxy.getAllTripsWhereUserIsDriver(id);
  }


  /**
   * get all the trips where the user is  a passenger
   *
   * @param id of the user
   * @return all the trips
   */
  public PassengerTrips getFuturePassengerTrips(int id){
    return passengersProxy.getAllTripsFromPassenger(id);

  }

  /**
   * get all the notifications of a user
   *
   * @param id of the user
   * @return all the notifications
   */
  public Iterable<Notification> getUserNotification(int id){
   return notificationsProxy.getUserNotif(id);
  }

  /**
   * delete all the notifications of a user
   *
   * @param id of the user
   */
  public void deleteAllUserNotification(int id){
    notificationsProxy.deleteAllUserNotif(id);
  }

  /**
   * create a new trip
   *
   * @param newTrip new trip we want to create
   */
  public void createTrip(NewTrip newTrip){
    tripsProxy.createTrip(newTrip);
  }

  /**
   *
   * Get list of trips with optional search queries
   *
   * @param dateDeparture the departure date
   * @param originLon  the start longitude
   * @param originLat the start latitude
   * @param destinationLon the destination longitude
   * @param destinationLat the destination latitude
   *
   * @return list of trips
   */
  public Iterable<Trip> getListTrips(String dateDeparture,String originLat, String originLon, String destinationLat,  String destinationLon  ){
    return tripsProxy.getListTrips(dateDeparture,originLat,originLon,destinationLat,destinationLon);
  }

  /**
   * get trip info
   *
   * @param id of the trip we need
   *
   * @return the trip we want
   */
  public Trip getTripInfo(int id){
   return tripsProxy.getTripInfo(id);
  }

  /**
   * delete trip
   *
   * @param id of the trip we need to delete
   *
   */
  public void deletTrip(int id){
    tripsProxy.deleteTrip(id);
  }

  /**
   * get list of passengers from a specific trip
   *
   * @param id of the trip we want
   *
   * @return list of passengers from the trip
   */
  public Iterable<Passengers> getPassangerList(int id){
    return passengersProxy.getAllPassengersFromTrip(id);
  }
  /**
   * add passenger to trip
   *
   * @param trip_id the trip we want
   * @param user_id the user we add
   *
   */
  public void addPasengerToTrip(int trip_id, int user_id){
    passengersProxy.addUserToTrip(trip_id, user_id);
  }

  /**
   * get passenger status for specific trip
   *
   * @param trip_id the trip we want
   * @param user_id the user we need
   *
   * @return the passenger status
   */
  public String getPassengerStatus(int trip_id, int user_id){
    return passengersProxy.getPassengerStatus(trip_id, user_id);
  }

  /**
   * update passenger status for specific trip
   *
   * @param trip_id the trip we want
   * @param user_id the user we need
   * @param status the status we want to give to the passenger
   */
  public void updatePassengerStatus(int trip_id, int user_id, String status){
    passengersProxy.updatePassengerStatus(trip_id, user_id, status);
  }

  /**
   * remove passenger from specific trip
   *
   * @param trip_id the trip we want
   * @param user_id the user we need
   *
   */
  public void removePassengerFromTrip(int trip_id, int user_id){
    passengersProxy.deletePassengerFromTrip(trip_id, user_id);
  }



}
