package robot;

import java.util.*;
import java.lang.Math.*;

public class RobotBeliefState{

  /**
     This is the class representing the robot's belief state. Some of
     the methods contain dummy code, however. Functioning versions of
     these methods can be found in the final subclass
     SolutionRobotBeliefState

  */


  protected double[][][] beliefMatrix;
  protected double[][] positionBeliefMatrix;
  protected double[] orientationBeliefMatrix;

  protected double maxPositionProbability;
  protected double maxOrientationProbability;

  protected double[][][] workMatrix;

  protected String statusString;

  public WorldMap map;             // Accurate map of the world

  protected ProbActionToggler probActionToggler;


  // Set up constants

    public RobotBeliefState(WorldMap m, ProbActionToggler probActionToggler1){

    // Set map
    map= m;
    statusString= "Student's code"; // string telling the demonstrator code is ok
    initializeMatrices();
    probActionToggler= probActionToggler1;
  }

  public void initializeMatrices(){

    // Initialize matrices
    beliefMatrix=
      new double[RunRobot.SIZE][RunRobot.SIZE][RunRobot.SIZE];
    workMatrix=
      new double[RunRobot.SIZE][RunRobot.SIZE][RunRobot.SIZE];
    positionBeliefMatrix=
      new double[RunRobot.SIZE][RunRobot.SIZE];
    orientationBeliefMatrix=
      new double[RunRobot.SIZE];

    /*
	        ************** Dummy code follows **************

    // The following code does not work. In its current state, it initializes
       the probability of any given pose to 1.

       The method should actually set the probability distribution
       stored in beliefMatrix[][][] to a flat distribution over poses
       corresponding to *unoccupied* squares.  (If (i,j) is an
       occupied square, then beliefMatrix[i][j][k] should be zero for all k.)

    */
  int unoccupiedSquare = (int)Math.pow(RunRobot.SIZE, 3);
    for(int i= 0;i < RunRobot.SIZE; i++)
      for(int j= 0;j < RunRobot.SIZE; j++)
	      for(int k= 0;k < RunRobot.SIZE; k++)
        {
          if (map.isOccupied(i,j))
            unoccupiedSquare--;
            beliefMatrix[i][j][k]= 0;
        }

    for(int i= 0;i < RunRobot.SIZE; i++)
      for(int j= 0;j < RunRobot.SIZE; j++)
        for(int k= 0;k < RunRobot.SIZE; k++)
        {
          if (!map.isOccupied(i,j))
            beliefMatrix[i][j][k] = 1 / unoccupiedSquare;
        }

   // beliefMatrix[i][j][k]= 1;  // Dummy: set probabilites to 1 (nonsense value)

    /* End of dummy code */

    updateMaxProbabilities(); // Update member variables used by public access
                              // functions. (Do not change this line.)
  }


  public double getPoseProbability(Pose pose){
    /**
	Return the probability that the robot is currently in Pose pose
    */
    return beliefMatrix[pose.x][pose.y][pose.theta];
  }

  public double getPositionProbability(int x, int y){
    /**
	Return the probability that the robot is currently in position (x,y)
    */

    return positionBeliefMatrix[x][y];
  }

  public double getOrientationProbability(int t){
    /**
	Return the probability that the robot currently has orientation theta
    */
    return orientationBeliefMatrix[t];
  }

  protected void fixWorkMatrix(Observation o){

  }

  public void updateProbabilityOnObservation(Observation o){

    /**
	Revise beliefMatrix by conditionalizing on Observation o
    */

    /*

	        ************** Dummy code follows **************

    // The following code does not work. In its current state, it sets
       the probability of any given pose to 1.


      The method should actually revise the probability distribution
      stored in beliefMatrix[][][] by conditionalizing on the observation o.

    */
    Pose pose = new Pose();
    double sumOfBeliefMatrix = 0.0;
    for(int x= 0;x < RunRobot.SIZE; x++)
      for(int y= 0;y < RunRobot.SIZE; y++)
	      for(int t= 0;t < RunRobot.SIZE; t++)
        {
          pose.x = x;
          pose.y = y;
          pose.theta = t;
          workMatrix[x][y][t] = map.getObservationProbability(pose,o);
          sumOfBeliefMatrix += workMatrix[x][y][t];
        }

      for(int x= 0;x < RunRobot.SIZE; x++)
        for(int y= 0;y < RunRobot.SIZE; y++)
      	  for(int t= 0;t < RunRobot.SIZE; t++)
          {
            pose.x = x;
            pose.y = y;
            pose.theta = t;
            workMatrix[x][y][t] = map.getObservationProbability(pose,o);
            beliefMatrix[x][y][t] = workMatrix[x][y][t] / sumOfBeliefMatrix;
          }
    //beliefMatrix[x][y][t]= 1; // Dummy: set probabilites to 1 (nonsense value)

    /* End of dummy code */

    updateMaxProbabilities();  // Update member variables used by public access
                               // functions. (Do not change this line.)
  }


  public void updateProbabilityOnAction(Action a){

    /**
	Revise beliefMatrix by conditionalizing on the knowledge that
	Action a has been performed. Assume deterministic actions for
	the moment.
    */

    /*
       ************** Dummy code follows **************

       The following code does not work. In its current state, it sets
       the probability of any given pose to 1.

       The method should actually revise the probability distribution
       stored in beliefMatrix[][][] by conditionalizing on the action a
    */

    Pose tempPose = new Pose();  // Work variable

    if(!probActionToggler.probActions())
    {
      for(int x= 0;x < RunRobot.SIZE; x++)
        for(int y= 0;y < RunRobot.SIZE; y++)
  	      for(int t= 0;t < RunRobot.SIZE; t++)
          {
            workMatrix[x][y][t] = 0;
          }
    for(int i= 0;i < RunRobot.SIZE; i++)
      for(int j= 0;j < RunRobot.SIZE; j++)
        for(int t= 0;t < RunRobot.SIZE; t++)
        {
          tempPose.x = i;
          tempPose.y = j;
          tempPose.theta = t;
          map.fillPoseOnAction(tempPose,i,j,t,a);
          workMatrix[i][j][t] += beliefMatrix[i][j][t];
        }
      }  // if
      else
      {
        
      } // else

      for(int i= 0;i < RunRobot.SIZE; i++)
        for(int j= 0;j < RunRobot.SIZE; j++)
          for(int t= 0;t < RunRobot.SIZE; t++)
          {
            beliefMatrix[i][j][t] = workMatrix[i][j][t];
          }

    //beliefMatrix[x][y][t]= 1; // Dummy: set probabilites to 1 (nonsense value)

    /* End of dummy code */

    updateMaxProbabilities();  // Update member variables used by public access
                               // functions. (Do not change this line.)
  }

  public double getMaxPositionProbability(){
    return maxPositionProbability;
  }

  public double getMaxOrientationProbability(){
    return maxOrientationProbability;
  }

  protected void updateMaxProbabilities(){

    double temp;
    maxPositionProbability= 0;
    for(int x= 0; x< RunRobot.SIZE; x++)
      for(int y= 0; y< RunRobot.SIZE; y++){
	temp= 0;
	for(int k= 0; k< RunRobot.SIZE; k++)
	  temp+=beliefMatrix[x][y][k];
	positionBeliefMatrix[x][y]= temp;
	if(positionBeliefMatrix[x][y]>maxPositionProbability)
	  maxPositionProbability= positionBeliefMatrix[x][y];
      }

    maxOrientationProbability= 0;
    for(int t= 0; t< RunRobot.SIZE; t++){
	temp= 0;
	for(int i= 0; i< RunRobot.SIZE; i++)
	  for(int j= 0; j< RunRobot.SIZE; j++)
	    temp+=beliefMatrix[i][j][t];
	orientationBeliefMatrix[t]= temp;
	if(orientationBeliefMatrix[t]>maxOrientationProbability)
	  maxOrientationProbability= orientationBeliefMatrix[t];
    }
  }

}
