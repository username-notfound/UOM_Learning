public class LotteryTestA
{
  public static void main(String[] args)
  {
    SpeedController speedController
      = new SpeedController(SpeedController.HALF_SPEED);

    LotteryGUI gui = new LotteryGUI("TV Studio", speedController);

    TVHost tvHost = new TVHost("Terry Bill Woah B'Gorne");
    gui.addPerson(tvHost);
    speedController.delay(40);

    AudienceMember audienceMember1 = new AudienceMember("Ivana Di Yowt");
    gui.addPerson(audienceMember1);
    speedController.delay(40);

    Punter punter1 = new Punter("Ian Arushfa Rishly Ving");
    gui.addPerson(punter1);
    speedController.delay(40);

    Psychic psychic = new Psychic("Miss T. Peg de Gowt");
    gui.addPerson(psychic);
    speedController.delay(40);

    Director director = new Director("Sir Lance Earl Otto");
    gui.addPerson(director);
    speedController.delay(40);

    CleverPunter cleverPunter1 = new CleverPunter("Wendy Athinkile-Win");
    gui.addPerson(cleverPunter1);
    speedController.delay(40);

    Worker worker1 = new TraineeWorker("Jim", 0);
    gui.addPerson(worker1);
    speedController.delay(40);

    Worker worker2 = new Worker("Merlin");
    gui.addPerson(worker2);
    speedController.delay(40);
  } // main
} // LottertTestA
