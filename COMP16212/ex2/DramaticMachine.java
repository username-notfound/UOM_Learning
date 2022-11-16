
/*
 *  A dramatic machine,
    a ball will be picked at random, then, starting with the first ball
    in the machine, each ball will be flashed in turn, until the one
    to be ejected is reached. And the chosen ball will be flashed
    and ejected as before.
 */

public class DramaticMachine extends Machine
{
  public DramaticMachine(String name, int size)
  {
    super(name,size);
  } // Constructor

  // Returns the name of type of ballContainer
  public String getType()
  {
    return "Dramatic machine";
  } // getType

  public Ball ejectBall()
  {
    if (getNoOfBalls() <= 0)
      return null;
    else
    {
      // Pick a ball in random
      int ejectedBallIndex = (int) (Math.random() * getNoOfBalls());

      for(int index = 0; index <= ejectedBallIndex; index++)
      {
        Ball otherBall = getBall(index);
        otherBall.flash(4, 5);
      } // for

      Ball ejectedBall = getBall(ejectedBallIndex);
      swapBalls(ejectedBallIndex, getNoOfBalls() - 1);
      removeBall();

      return ejectedBall;
    } //else
  } // ejectBall
} // DramaticMachine
