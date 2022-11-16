
public class TestMoodyGroup
{
  public static void main(String[] args)
  {
    MoodyGroup<Teenager> teen = new MoodyGroup<Teenager>();
    teen.addMoodyPerson(new Teenager("T1"));
    teen.addMoodyPerson(new Teenager("T2"));
    teen.addMoodyPerson(new Teenager("T3"));


    for (int count = 1; count <= teen.getSize(); count++)
    {
      teen.setHappy(false);
      System.out.printf("%s%n%n", teen);
    } // for

    for (int count = 1; count <= teen.getSize(); count++)
    {
      teen.setHappy(true);
      System.out.printf("%s%n%n", teen);
    } // for

    MoodyGroup<MoodyPerson> anyother = new MoodyGroup<MoodyPerson>();
    anyother.addMoodyPerson(new Worker("W1"));
    anyother.addMoodyPerson(new Teenager("T1"));

    System.out.printf("%s%n%n", anyother);

    for (int count = 1; count <= anyother.getSize(); count++)
    {
      anyother.setHappy(false);
      System.out.printf("%s%n%n", anyother);
    } // for

    for (int count = 1; count <= anyother.getSize(); count++)
    {
      anyother.setHappy(true);
      System.out.printf("%s%n%n", anyother);
    } // for

  } // main
}
