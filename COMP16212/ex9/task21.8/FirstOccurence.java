// A pair of a voter with its first occurence time and location
public class FirstOccurence
{
  private final String time;
  private final String location;

  // Create a pairing with the first occurence time and location
  public FirstOccurence(String requiredTime, String requiredLocation)
  {
    time = requiredTime;
    location = requiredLocation;
  } // FirstOccurence

  public String toString()
  {
    return "First occurence: " + time + " " + location;
  } // toString

  public boolean equals(Object other)
  {
    if(other instanceof FirstOccurence)
      return true;
    else
      return false;
  } // equals

  public int hashCode()
  {
    return time.hashCode() * 37 + location.hashCode() * 37;
  } // hashCode
} // class-FirstOccurence
