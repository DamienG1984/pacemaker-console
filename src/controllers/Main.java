package controllers;

import java.util.Collection;

import com.google.common.base.Optional;

import java.io.File;

import java.util.*;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Activity;
import models.User;
//import utils.BinarySerializer;
import utils.JSONSerializer;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
  public PacemakerAPI paceApi;
  
  @Command(description="Get all users details")
  public void ListUsers ()
  {
    Collection<User> users = paceApi.getUsers();
    //System.out.println(users);
    List<User> usrLst = new ArrayList<>(users);
    //pass user details to user table method to be printed.
      paceApi.usrtbl(usrLst);
    
  }
  
  @Command(description="Create a new User")
  public void createUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName, 
                          @Param(name="email")      String email,     @Param(name="password")  String password)
  {
    paceApi.createUser(firstName, lastName, email, password);
    System.out.println("User Created");
  }
  
  @Command(description="Get a Users detail")
  public void ListUser (@Param(name="email") String email)
  {
    User user = paceApi.getUserByEmail(email);
    if (user != null) {
      System.out.println("User Found");
      List<User> usrLst = Arrays.asList(user);
      //pass user details to user table method to be printed.
        paceApi.usrtbl(usrLst);
    }
    else
    {
      System.out.println("The User email entered does not exist please try again.");
    }
  }
  
  @Command(description="Get a Users detail By ID")
  public void ListUser (@Param(name="id") Long id)
  {
    User user = paceApi.getUser(id);
    
    if (user != null) {
      System.out.println("User Found");
      List<User> usrLst = Arrays.asList(user);
    //pass user details to user table method to be printed.
      paceApi.usrtbl(usrLst);
    }
    else
    {
      System.out.println("The User Id entered does not exist please try again.");
    }
    
  }
  
  @Command(description = "List User Activities")
  public void listActivities(@Param(name = "user id") Long id)
  {
    Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
    if (user.isPresent())
    {
      Map<Long, Activity> activity = paceApi.getUserActivity(user.get().id);
      List<Activity> actLst = new ArrayList<>(activity.values());
      paceApi.actLst(actLst);
       // System.out.println(activity);
       
    }
  }
  
  @Command(description = "List a users activities sortBy: type, location, distance, starttime, duration")
  @SuppressWarnings( "static-access")
  public void listActivities(@Param(name = "user id") Long id,
      @Param(name = "sortBy: type, location, distance, starttime, duration") String sortBy)
  {
    Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
    if (user.isPresent())
    {
    Map<Long, Activity> activity = paceApi.getUserActivity(user.get().id);
    List<Activity> actLst = new ArrayList<>(activity.values());

      if (sortBy.equals ("type"))
        {
          Collections.sort(actLst, paceApi.srtType);
          paceApi.actLst(actLst);
        }
      else
         if (sortBy.equals ("location"))
         { 
           Collections.sort(actLst, paceApi.srtLocation);
           paceApi.actLst(actLst);
         }
         else
           if (sortBy.equals ("distance"))
           {
             Collections.sort(actLst, paceApi.srtLocation);
             paceApi.actLst(actLst);
           }
           else
             if (sortBy.equals ("starttime"))
             {
               Collections.sort(actLst, paceApi.srtStarttime);
               paceApi.actLst(actLst);
             }
             else
               if (sortBy.equals ("duration"))
               {
                 Collections.sort(actLst, paceApi.srtDuration);
                 paceApi.actLst(actLst);
               }
           else
             System.out.println("Not a valid Sort By Option, Please use type, location, distance, starttime or duration");
    }
  }
  
  @Command(description="Delete a User")
  public void deleteUser (@Param(name="id") Long id)
  {
    Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
    if (user.isPresent())
    {
      System.out.println("User ID:" + id + "has been removed");
      paceApi.deleteUser(user.get().id);
    }
  }
  
  @Command(description="Add an activity")
  public void addActivity (@Param(name="user-id")  Long   id,       @Param(name="type") String type, 
                           @Param(name="location") String location, @Param(name="distance") double distance,
                           @Param(name="starttime") String starttime, @Param(name="duration") String duration)
  {
    Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
    if (user.isPresent())
    {
      paceApi.createActivity(id, type, location, distance, starttime, duration);
    }
  }
  
  @Command(description="Add Location to an activity")
  public void addLocation (@Param(name="activity-id")  Long  id,   
                           @Param(name="latitude")     float latitude, @Param(name="longitude") float longitude)
  {
    Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
    if (activity.isPresent())
    {
      paceApi.addLocation(activity.get().id, latitude, longitude);
    }
  }

  public Main() throws Exception
  {
  //XML Default
    File  datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);
    
    paceApi = new PacemakerAPI(serializer);
    if (datastore.isFile())
    {
      paceApi.load();
    }
  }
  
  //XML FILE 
  public void XML() throws Exception
  {
    File datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);

    paceApi = new PacemakerAPI(serializer);
    if (datastore.isFile())
    {
      paceApi.load();
    }
  }

  // JSON FIILE
  public void JSON() throws Exception
  {
    File datastore = new File("datastore.JSON");
    Serializer serializer = new JSONSerializer(datastore);

    paceApi = new PacemakerAPI(serializer);
    if (datastore.isFile())
    {
      paceApi.load();
    }
  }
  
  @Command(description = "Change file format")
  public void changeFileFormat(@Param(name = "file format: XML, JSON") String format) throws Exception
  {
    if (format.equals("JSON"))
    {
      JSON();
      System.out.println("File Format Changed");
    }
    else
      if (format.equals("XML"))
      {
        XML();
        System.out.println("File Format Changed");
      }
      else
        System.out.println("File Format not Found. Please try again");
  }
  
  
  @Command(description = "Load Data")
  public void load() throws Exception
  {
    paceApi.load();
    System.out.println("File has been loaded");
  }
  
  @Command(description = "Save Data")
  public void store() throws Exception
  {
    paceApi.store();
    System.out.println("File has been saved");
  }

  
  public static void main(String[] args) throws Exception
  {
    Main main = new Main();

    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions", main);
    shell.commandLoop();
    
    main.paceApi.store();
  }
}
