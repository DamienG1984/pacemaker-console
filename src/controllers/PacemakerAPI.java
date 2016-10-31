package controllers;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Activity;
import models.Location;
import models.User;
import utils.Serializer;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.impl.CollectionASCIITableAware;
import com.bethecoder.ascii_table.spec.IASCIITableAware;
import com.google.common.base.Optional;

import org.joda.time.*;
import org.joda.time.format.*;


public class PacemakerAPI
{
  private Map<Long, User>     userIndex       = new HashMap<>();
  private Map<String, User>   emailIndex      = new HashMap<>();
  private Map<Long, Activity> activitiesIndex = new HashMap<>();
      
  private Serializer serializer;
  
  public PacemakerAPI(Serializer serializer)
  {
    this.serializer = serializer;
  }

  public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
  
  public  void deleteUsers() 
  {
    userIndex.clear();
    emailIndex.clear();
  }
  
  public User createUser(String firstName, String lastName, String email, String password) 
  {
    User user = new User (firstName, lastName, email, password);
    userIndex.put(user.id, user);
    emailIndex.put(email, user);
    return user;
  }
  
  public User getUserByEmail(String email) 
  {
    return emailIndex.get(email);
  }
  
  @SuppressWarnings( {"rawtypes", "unchecked" })
  public void usrtbl(List usr) 
  {
  /*  String[][] users = { 
        {user.firstName, user.lastName, user.password, user.email}};
 
     
    String [] header = {"FirstName", "LastName",  "Password", "Email", 
        
        };
    
    ASCIITable.getInstance().printTable(header, users);
    */
    IASCIITableAware asciiTableAware = new CollectionASCIITableAware<Activity>(usr,"id","firstName","lastName","password","email"); 
    ASCIITable.getInstance().printTable(asciiTableAware);
  }
  
  @SuppressWarnings( {"rawtypes", "unchecked" })
  public void actLst(List actLst) 
  {          
    //System.out.println(actLst);
    IASCIITableAware asciiTableAware = new CollectionASCIITableAware<Activity>(actLst,"id","type","location","distance","starttime","duration","route"); 
    ASCIITable.getInstance().printTable(asciiTableAware);
  }
  
  public User getUser(Long id) 
  {
    return userIndex.get(id);
  }

  public void deleteUser(Long id) 
  {
    User user = userIndex.remove(id);
    emailIndex.remove(user.email);
  }
  
  public Activity createActivity(Long id, String type, String location, double distance, String starttime, String duration)
  {
    Activity activity = null;
    Optional<User> user = Optional.fromNullable(userIndex.get(id));
    if (user.isPresent())
    {
      activity = new Activity (type, location, distance, this.Starttime(starttime), this.Duration(duration));
      user.get().activities.put(activity.id, activity);
      activitiesIndex.put(activity.id, activity);
    }
    return activity;
  }
  
  public String Starttime (String starttime)
  {
    DateTime date = DateTime.parse(starttime,DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
    return date.toString();
  }
  
  public String Duration (String duration)
  {
    LocalTime time = LocalTime.parse(duration);
    return time.toString();
  }
  
  public Activity getActivity (Long id)
  {
    return activitiesIndex.get(id);
  }
  
  public Map<Long, Activity> getUserActivity(Long id)
  {
    return userIndex.get(id).activities;
  }
  
  public void addLocation (Long id, double latitude, double longitude)
  {
    Optional<Activity> activity = Optional.fromNullable(activitiesIndex.get(id));
    if (activity.isPresent())
    {
      activity.get().route.add(new Location(latitude, longitude));
    }
  }
  
  public static Comparator<Activity> srtType = new Comparator<Activity>()
  {
    @Override
    public int compare(Activity act1, Activity act2)
    {
      return act2.type.compareTo(act1.type);
    }
  };
  
  public static Comparator<Activity> srtLocation = new Comparator<Activity>()
  {
    @Override
    public int compare(Activity act1, Activity act2)
    {
      return act2.location.compareTo(act1.location);
    }
  };
  
  public static Comparator<Activity> srtDistance = new Comparator<Activity>()
  {
    @Override
    public int compare(Activity act1, Activity act2)
    {
      return Double.compare(act2.distance,act1.distance);
    }
  };
  
  public static Comparator<Activity> srtStarttime = new Comparator<Activity>()
      {
        @Override
        public int compare(Activity act1, Activity act2)
        {
          return act2.starttime.compareTo(act1.starttime);
        }
      };
  
  public static Comparator<Activity> srtDuration = new Comparator<Activity>()
      {
        @Override
        public int compare(Activity act1, Activity act2)
        {
          return act2.duration.compareTo(act1.duration);
        }
      };

 @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    activitiesIndex = (Map<Long, Activity>) serializer.pop();
    emailIndex      = (Map<String, User>)   serializer.pop();
    userIndex       = (Map<Long, User>)     serializer.pop();
  }

  public void store() throws Exception
  {
    serializer.push(userIndex);
    serializer.push(emailIndex);
    serializer.push(activitiesIndex);
    serializer.write(); 
  }
  
}