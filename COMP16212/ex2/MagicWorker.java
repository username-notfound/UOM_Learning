import java.util.ArrayList;
import java.awt.Color;

public class MagicWorker extends Worker
{
  public MagicWorker(String name)
  {
    super(name);
  } // Constructor

  public MagicWorkerImage makeImage()
  {
    return new MagicWorkerImage(this);
  } // makeImage

  private ArrayList<String> magicBalls = new ArrayList<String>();

  public void doMagic(int spellNumber)
  {
    for (int index = 0; index < magicBalls.getSize(); index++)
    {
      magicBalls.get(index).doMagic(spellNumber);
    }
  } // doMagic

  public Ball makeNewBall(int number, Color colour)
  {
    return new MagicBall(number, colour);
  } // makeNewBall

} // MagicWorker
