package models;

import static com.google.common.base.Objects.toStringHelper;

import java.io.Serializable;
import java.util.*;

import com.google.common.base.Objects;

@SuppressWarnings( "serial")
public class Activity implements Serializable{

  static Long   counter = 0l;
  
  public Long   id;
  
  public String type;
  public String location;
  public double distance;
  public String starttime;
  public String duration;
  
  public List<Location> route = new ArrayList<>();
 
  public Activity()
  {
  }
  
  public Activity(String type, String location, double distance, String starttime, String duration)
  {
    this.id        = counter++;
    this.type      = type;
    this.location  = location;
    this.distance  = distance;
    this.starttime  = starttime;
    this.duration = duration;
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Activity)
    {
      final Activity other = (Activity) obj;
      return Objects.equal(type, other.type) 
          && Objects.equal(location,  other.location)
          && Objects.equal(distance,  other.distance)
          && Objects.equal(starttime, other.starttime)
          && Objects.equal(duration, other.duration)
          && Objects.equal(route,     other.route);        
    }
    else
    {
      return false;
    }
  }
  
  //getID
  public Long getId()
  {
    return id;
  }

  //setID
  public void setId(Long id)
  {
    this.id = id;
  }
  
  //getType
  public String getType()
  {
    return type;
  }
  
  //setType
  public void setType(String type)
  {
    this.type = type;
  }
  
  //getLocation
  public String getLocation()
  {
    return location;
  }
  
  //setLocation
  public void setLocation(String location)
  {
    this.location = location;
  }
  
    // getDistance
  public double getDistance()
  {
    return distance;
  }

//setDistance
  public void setDistance(double distance)
  {
    this.distance = distance;
  }
  
  // getstarttime
  public String getStarttime()
  {
    return starttime;
  }

//setDistance
  public void setStarttime(String starttime)
  {
    this.starttime = starttime;
  }
  
  //getstarttime
   public String getDuration()
   {
     return duration;
   }
  
  //setDistance
   public void setDuration(String duration)
   {
     this.duration = duration;
   }
  
  public List<Location> getRoute()
  {
    return route;
  }

  public void setRoute(List<Location> route)
  {
    this.route = route;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(type)
                               .addValue(location)
                               .addValue(distance)
                               .addValue(route)
                               .addValue(starttime)
                               .toString();
  }
  
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.type, this.location, this.distance, this.starttime);  
  } 
}