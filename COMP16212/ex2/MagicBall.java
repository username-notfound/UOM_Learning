import java.awt.Color;
public class MagicBall extends Ball
{

  // Constructor
  public MagicBall(int requiredValue, Color requiredColour)
  {
    super(requiredValue, requiredColour);
  } // Ball


  public MagicBallImage makeImage()
  {
    return new MagicBallImage(this);
  } // makeImage

  private int currentState = 1;
  private static final int NORMAL = 1;
  private static final int INVISIBLE = 2;
  private static final int FLASHING = 3;
  private static final int COUNTING = 4;

  public void doMagic(int spellNumber)
  {
    if (spellNumber == 1)
      switch (currentState)
      {
        case NORMAL : currentState = INVISIBLE;
                      getImage().update();
                      break;
        case INVISIBLE : currentState = FLASHING;
                         getImage().update();
                         break;
        case FLASHING : currentState = COUNTING;
                        getImage().update();
                        break;
        case COUNTING : currentState = NORMAL;
                        getImage().update();
                        break;
      } // switch

    else if  (spellNumber == 2)
    {
      currentState = 1;
    }
  } // doMagic

  public boolean isVisible()
  {
    if (currentState != 2)
      return true;
    else
      return false;
  } // isVisible

  public boolean isFlashing()
  {
    if (currentState == 3 || currentState == 4)
      return true;
    else
      return false;
  } // isFlashing

  private int count = super.getValue();

  public int getValue()
  {
    if (currentState == 4)
    {
      if (count == 99)
      {
        count = 0;
      }
      else
        count++;
      return count;
    }
    else
      return super.getValue();
  } // getValue

} // MagicBall
