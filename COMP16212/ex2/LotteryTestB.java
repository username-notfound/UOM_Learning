public class LotteryTestB
{
  public static void main(String[] args)
  {
    SpeedController speedController
      = new SpeedController(SpeedController.HALF_SPEED);

    LotteryGUI gui = new LotteryGUI("TV Studio", speedController);

    TVHost tvHost = new TVHost("Terry Bill Woah B'Gorne");
    gui.addPerson(tvHost);
    speedController.delay(40);

    Worker worker1 = new TraineeWorker("Jim", 0);
    gui.addPerson(worker1);
    speedController.delay(40);

    DramaticGame game = new DramaticGame("Lott O'Luck Larry", 49,
                          "Slippery's Mile", 7);
    gui.addGame(game);
    speedController.delay(40);

    worker1.fillMachine(game);

    for (int count = 1; count <= game.getRackSize(); count ++)
    {
      game.ejectBall();
    } // for
  } // main
} // LotteryTestB
