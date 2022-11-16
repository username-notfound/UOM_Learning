public class MoodyGroup<MoodyPersonType extends MoodyPerson>
{
  private static final int INITIAL_ARRAY_SIZE = 2, ARRAY_RESIZE_FACTOR = 2;
  private MoodyPerson[] moodyperson = new MoodyPerson[INITIAL_ARRAY_SIZE];
  private int noOfMoodyPerson = 0;

  // Constructor
  public MoodyGroup()
  {
  }

  public void addMoodyPerson(MoodyPersonType newMoodyPerson)
  {
    if(noOfMoodyPerson == moodyperson.length)
    {
      MoodyPerson[] biggerArray
              = new MoodyPerson[moodyperson.length * ARRAY_RESIZE_FACTOR];
      for(int index = 0; index < moodyperson.length; index++)
        biggerArray[index] = moodyperson[index];
      moodyperson = biggerArray;
    } // if
    moodyperson[noOfMoodyPerson] = newMoodyPerson;
    noOfMoodyPerson++;
  } // addMoodyPerson

  public int getSize()
  {
    return noOfMoodyPerson;
  } // getSizeForTeenager

  private int nextToSetHappy = 0;

  public void setHappy(boolean newHappiness)
  {
    if(noOfMoodyPerson > 0)
    {
      moodyperson[nextToSetHappy].setHappy(newHappiness);
      nextToSetHappy = (nextToSetHappy + 1) % noOfMoodyPerson;
    }
  } // setHappy

  @Override
  public String toString()
  {
    String result = noOfMoodyPerson == 0 ? "" : "" + moodyperson[0];
    for (int index = 1; index < noOfMoodyPerson; index++)
      result += String.format("%n%s", moodyperson[index]);

    return result;
  } // toString
} // ModyGroup
