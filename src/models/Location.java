package models;

import static com.google.common.base.Objects.toStringHelper;

import java.io.Serializable;

import com.google.common.base.Objects;


@SuppressWarnings( "serial")
public class Location implements Serializable{

  static Long   counter = 0l;
  public Long  id;
  public double latitude;
  public double longitude;
  
  public Location()
  {
  }
  
  public Location (double latitude, double longitude)
  {
    this.id        = counter++;
    this.latitude  = latitude;
    this.longitude = longitude;
  }
  
  @Override  
  public int hashCode()  
  {  
       return Objects.hashCode(this.id, this.longitude, this.latitude);  
    }

    @Override
    public boolean equals(final Object obj)
    {
      if (obj instanceof Location)
      {
        final Location other = (Location) obj;
        return Objects.equal(latitude, other.latitude) 
            && Objects.equal(longitude, other.longitude);
      }
      else
      {
        return false;
      }
    }

  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                           .addValue(latitude)
                   .addValue(longitude)
                           .toString();
  }
}
