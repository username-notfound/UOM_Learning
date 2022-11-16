import java.awt.Color;
public class LotteryTestD
{
  public static void main(String[] args)
  {
    //This should simply create one lottery GUI,
    SpeedController speedController
      = new SpeedController(SpeedController.HALF_SPEED);
    LotteryGUI gui = new LotteryGUI("TV Studio", speedController);

    // and then create two magic workers, and insert them into the GUI.
    MagicWorker worker1 = new MagicWorker("Jim");
    MagicWorker worker2 = new MagicWorker("Merlin");
    gui.addPerson(worker1);
    gui.addPerson(worker2);

    //Then it should create two games and insert them into the GUI.
    Game game1 = new Game("Lott O'Luck Larry", 49,
                          "Slippery's Mile", 7);
    gui.addGame(game1);
    Game game2 = new Game("Second Time Lucky", 34,
                          "Oooz OK Lose", 6);
    gui.addGame(game2);

    worker1.fillMachine(game1);
    worker2.fillMachine(game2);

    for (int count = 1; count <= game1.getRackSize(); count ++)
    {
      game1.ejectBall();
    } // for

    game1.rackSortBalls();
    speedController.delay(40);

    for (int count = 1; count <= game2.getRackSize(); count ++)
    {
      game2.ejectBall();
    } // for

    game2.rackSortBalls();
    speedController.delay(40);
  } // main
} // LotteryTestD
