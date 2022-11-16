public class TestRelativeDates
{
  public static void main(String[] args)
  {
    try
    {
      Date referenceDate = new Date(28,12,1754);
      for (int count = 0; count <= 731; count++)
        {
          System.out.println("Date: " + referenceDate);
          System.out.println("Add the month: " + referenceDate.addMonth());
          System.out.println("Add the year: " + referenceDate.addYear());
          System.out.println("Subtract the day: "
                             + referenceDate.subtractDay());
          System.out.println("Subtract the month: " +
                              referenceDate.subtractMonth());
          System.out.println("Subtract the year: " +
                                referenceDate.subtractYear());
          System.out.println(" ");
          referenceDate = referenceDate.addDay();

        }
    }
    catch (Exception exception)
    {
      System.err.println(exception);
    } // catch

  } // main
} // class TestRelativeDates
