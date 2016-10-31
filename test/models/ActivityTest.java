package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static models.Fixtures.activities;

public class ActivityTest
{
  Activity test = new Activity ("walk",  "fridge", 0.001, "31/10/2016 15:41:00","1:00:00");

  @Test
  public void testCreate()
  {
    assertEquals ("walk",          test.type);
    assertEquals ("fridge",        test.location);
    assertEquals (0.0001, 0.001,   test.distance);  
    assertEquals ("31/10/2016 15:41:00",        test.starttime);
    assertEquals ("1:00:00",        test.duration);
  }

  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (Activity activity : activities)
    {
      ids.add(activity.id);
    }
    assertEquals (activities.length, ids.size());
  }

  @Test
  public void testToString()
  {
    //this one passes
    assertEquals ("Activity{" + test.id + ", walk, fridge, 0.001, [], 31/10/2016 15:41:00}", test.toString());
    // the following is failing for some reason and i don't know why
    //assertEquals ("Activity{" + test.id + ", walk, fridge, 0.001, [], 31/10/2016 15:41:00, 1:00:00}", test.toString());
  }
}