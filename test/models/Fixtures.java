package models;

public class Fixtures
{
  public static User[] users =
  {
    new User ("marge", "simpson", "marge@simpson.com",  "secret"),
    new User ("lisa",  "simpson", "lisa@simpson.com",   "secret"),
    new User ("bart",  "simpson", "bart@simpson.com",   "secret"),
    new User ("maggie","simpson", "maggie@simpson.com", "secret")
  };

  public static Activity[] activities =
  {
    new Activity ("walk",  "fridge", 0.001, "31/10/2016 15:41:00", "1:00:00"),
    new Activity ("walk",  "bar",    1.0, "31/10/2016 15:41:00", "1:00:00"),
    new Activity ("run",   "work",   2.2, "31/10/2016 15:41:00", "1:00:00"),
    new Activity ("walk",  "shop",   2.5, "31/10/2016 15:41:00", "1:00:00"),
    new Activity ("cycle", "school", 4.5, "31/10/2016 15:41:00", "1:00:00")
  };

  public static Location[]locations =
  {
    new Location(23.3, 33.3),
    new Location(34.4, 45.2),  
    new Location(25.3, 34.3),
    new Location(44.4, 23.3)       
  };
}