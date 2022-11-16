import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;

public class FirstOccurenceMap
{
  private final Map<String, String> firstOccurenceMap =
                                              new HashMap<String, String>();

  // Empty constructor
  public FirstOccurenceMap()
  {
  } // constructor

  // to ger the origional time and location
  public String getTimeAndLocation(String voterName)
  {
    return firstOccurenceMap.get(voterName);
  }

  public boolean isFirstTime(String voterIdentification,
                             String timeAndLocation)
  {
    String voter = firstOccurenceMap.get(voterIdentification);
    if (voter == null)
    {
      firstOccurenceMap.put(voterIdentification, timeAndLocation);      
      return true;
    }
    else
    {
      return false;
    }
  } // isFirstTime

} // FirstOccurenceMap
