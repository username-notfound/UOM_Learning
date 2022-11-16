
/*
 *  A dramatic game,
    a ball will be picked at random, then, starting with the first ball
    in the machine, each ball will be flashed in turn, until the one
    to be ejected is reached. And the chosen ball will be flashed
    and ejected as before.
 */

public class DramaticGame extends Game
{

  // Constructor
  public DramaticGame(String dramaticMachineName, int dramaticMachineSize,
                      String dramaticRackName, int dramaticRackSize)
  {
    super(dramaticMachineName, dramaticMachineSize,
          dramaticRackName,dramaticRackSize);
  } // Constructor

  public DramaticMachine makeMachine(String dramaticMachineName,
                                     int dramaticMachineSize)
  {
    return new DramaticMachine(dramaticMachineName, dramaticMachineSize);
  } // makeMachine

} // DramaticGame
