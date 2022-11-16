import java.awt.Color;
public class LotteryTestC
{

  public static void main(String args[])
  {
    // Create one lottery GUI
    SpeedController speedController
      = new SpeedController(SpeedController.HALF_SPEED);
    LotteryGUI gui = new LotteryGUI("TV Studio", speedController);
    // Create a game with a small machine and a rack
    Game game1 = new Game("Lott O'Luck Larry", 49,
                          "Slippery's Mile", 7);
    gui.addGame(game1);

    Worker worker1 = new TraineeWorker("Jim", 0);

    MagicBall ball1 = new MagicBall(1, Color.red);
    MagicBall ball2 = new MagicBall(20, Color.pink);
    MagicBall ball3 = new MagicBall(99, Color.lightGray);
    game1.machineAddBall(ball1);
    game1.machineAddBall(ball2);
    game1.machineAddBall(ball3);
    game1.rackSortBalls();
    speedController.delay(40);
  } // main
} // LotteryTestC
