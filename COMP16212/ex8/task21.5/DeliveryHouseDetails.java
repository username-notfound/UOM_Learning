public class DeliveryHouseDetails implements Comparable<DeliveryHouseDetails>
{

  private int houseNumber;
  private String personName;

  // Constructor
  public DeliveryHouseDetails(int requiredHouseNumber, String requiredPersonName)
  {
    houseNumber = requiredHouseNumber;
    personName = requiredPersonName;
  }

  // Accesor method to get house number
  public int getHouseNumber()
  {
    return houseNumber;
  } // getHouseNumber

  // Accesor method to get the name of person
  public String getPersonName()
  {
    return personName;
  } // getPersonNames

  public int compareTo(DeliveryHouseDetails other)
  {
    int thisNumber = houseNumber;
    int otherNumber = other.getHouseNumber();

    if (thisNumber % 2 != 0 && otherNumber % 2 != 0)
    {
        return thisNumber - otherNumber;
    } // if both house numbers are odd
    else if (thisNumber % 2 == 0 && otherNumber % 2 == 0)
    {
        return otherNumber - thisNumber;
    } // else if both house numbers are even
    else if (thisNumber % 2 != 0)
    {
        return -1;
    } // else if this house number is odd
    else
    {
        return 1;
    } // else
  } // compareTo

  public boolean equals(Object other)
  {
    if (other instanceof DeliveryHouseDetails)
      return houseNumber == ((DeliveryHouseDetails)other).houseNumber;
    else
      return super.equals(other);
  } // equals
} // class DeliveryHouseDetails
