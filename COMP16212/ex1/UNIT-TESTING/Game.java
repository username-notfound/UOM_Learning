public class Game
{

// ----------------------------------------------------------------------
// Part a: the score message

  private String storedTheScoreMessage;

  public String getScoreMessage()
  {
    return storedTheScoreMessage;
  } // getScoreMessage


  public void setScoreMessage(String message)
  {
    storedTheScoreMessage = message;
  } // setScoreMessage

  public String getAuthor()
  {
    return "Shurong Ge";
  } // getAuthor


// ----------------------------------------------------------------------
// Part b: constructor and grid accessors

  // Once their values are set they should not be able to change
  private final int gridSize;
  private final Cell[][] storedGrid;

  public Game(int requiredGridSize)
  {
    gridSize = requiredGridSize;
    storedGrid = new Cell[gridSize][gridSize];
    for (int row = 0; row < gridSize; row++)
      for (int column = 0; column < gridSize; column++)
        storedGrid[row][column] = new Cell();
  } // Game

  public int getGridSize()
  {
    return gridSize;
  } // getGridSize

  public Cell getGridCell(int x, int y)
  {
    return storedGrid[x][y];
  } // getGridCell

// ----------------------------------------------------------------------
// Part c: initial game state

// Part c-1: setInitialGameState method
  private int score;
  private int numOfTrees;

  public void setInitialGameState(int requiredTailX, int requiredTailY,
                                  int requiredLength, int requiredDirection)
  {
    for(int row = 0; row < gridSize; row++)
       for(int column = 0; column < gridSize; column++)
         storedGrid[row][column].setClear();

    placeSnake(requiredTailX,requiredTailY,requiredLength,requiredDirection);
    placeFood();

    // Before eat something, the score is initialised to be 1.
    score = 0;

    // Initially, there are no trees in the grid
    numOfTrees = 0;
    // When we enable the tree, the number of tree should be 1.
    if (treesEnabled)
      plantTrees();
  } // setInitialGameState


// ----------------------------------------------------------------------
// Part c-2 place food


  private int xPosition, yPosition;

  public void placeFood()
  {
    do
    {
      // get a random value for the food X, Y position
      xPosition =  (int) (Math.random() * gridSize);
      yPosition =  (int) (Math.random() * gridSize);
    } while(storedGrid[xPosition][yPosition].getType() != Cell.CLEAR);
    // set food in this random cell
    storedGrid[xPosition][yPosition].setFood();
  } // placeFood

// ----------------------------------------------------------------------
// Part c-3: place snake
  private int currentDirection, tailX, tailY, headX, headY, length;
  public void placeSnake(int requiredTailX, int requiredTailY,
                         int requiredLength, int requiredDirection)
  {
    tailX = requiredTailX;
    tailY = requiredTailY;
    length = requiredLength;
    currentDirection = requiredDirection;

    // tail
    int outDirection = Direction.opposite(requiredDirection);
    storedGrid[requiredTailX][requiredTailY].setSnakeTail(outDirection,
                                                          requiredDirection);

    // body
    int headPositionX = tailX + Direction.xDelta(requiredDirection);
    int headPositionY = tailY + Direction.yDelta(requiredDirection);
    storedGrid[headPositionX][headPositionY].setSnakeBody(outDirection,requiredDirection);
    do
    {
      headPositionX = headPositionX +  Direction.xDelta(requiredDirection);
      headPositionY = headPositionY +  Direction.yDelta(requiredDirection);
      storedGrid[headPositionX][headPositionY].setSnakeBody(outDirection,requiredDirection);
      length++;
      requiredLength--;
    } while(requiredLength - 2 > 0);

    // head
    headX = headPositionX;
    headY = headPositionY;
    storedGrid[headX][headY].setSnakeHead(outDirection,requiredDirection);
  } // placeSnake

// ----------------------------------------------------------------------
// Part d: set snake direction

  private int directionChanged;
  public void setSnakeDirection(int requiredDirection)
  {
    directionChanged = requiredDirection;
    // To warn players that can not face straight back along its body.
    if (directionChanged == storedGrid[headX][headY].getSnakeInDirection())
      setScoreMessage("Do you wanna eat yourself?");
    else
    {
      storedGrid[headX][headY].setSnakeOutDirection(directionChanged);
      currentDirection = directionChanged;
    }
  } // setSnakeDirection

// ----------------------------------------------------------------------
// Part e: snake movement

// Part e-1: move method
  private int newHeadX;
  private int newHeadY;
  private int outDirection = Direction.opposite(currentDirection);
  public void move(int moveValue)
  {
    if (!storedGrid[headX][headY].isSnakeBloody())
    {
      // compute the new head
      newHeadX = headX +
              Direction.xDelta(storedGrid[headX][headY].getSnakeOutDirection());
      newHeadY = headY +
              Direction.yDelta(storedGrid[headX][headY].getSnakeOutDirection());

      boolean okToMove = ifCrashes();

      if (okToMove)
        {
          int newCellType = storedGrid[newHeadX][newHeadY].getType();
          moveHead();

          if (newCellType ==  Cell.FOOD)
            eatFood(moveValue);
          else
            moveTail();
         }
    }
  } // move


// ----------------------------------------------------------------------
// Part e-2: move the snake head

  public void moveHead()
  {
    int outDirectionOfHead = Direction.opposite(currentDirection);
    // set the old head become a part of its body
    storedGrid[headX][headY].setSnakeBody();
    // set the cell of new head become its head
    storedGrid[newHeadX][newHeadY].setSnakeHead(outDirectionOfHead,currentDirection);
    // Update the head position
    headX = newHeadX;
    headY = newHeadY;
  } // moveHead

// ----------------------------------------------------------------------
// Part e-3: move the snake tail
  private int newTailX;
  private int newTailY;
  public void moveTail()
  {
    // calculate the new tail
    newTailX = tailX +
               Direction.xDelta(storedGrid[tailX][tailY].getSnakeOutDirection());
    newTailY = tailY +
               Direction.yDelta(storedGrid[tailX][tailY].getSnakeOutDirection());
    int outDirectionOfTail = storedGrid[tailX][tailY].getSnakeOutDirection();
    // set the old tail clear
    storedGrid[tailX][tailY].setClear();
    // Update the tail position
    tailX = newTailX;
    tailY = newTailY;
    storedGrid[tailX][tailY].setSnakeTail();
  } // moveTail

// ----------------------------------------------------------------------
// Part e-4: check for and deal with crashes
   public boolean ifCrashes()
   {
    if (newHeadX >= gridSize || newHeadX < 0
        || newHeadY >= gridSize || newHeadY < 0)
    {
      // if the snake died
      if (reduceCurrent())
      {
        storedGrid[headX][headY].setSnakeBloody(true);
        setScoreMessage("Your snake is out of side!");
      }
      return false;
    } // if the snake crash out off size
    else if (storedGrid[newHeadX][newHeadY].isSnakeType())
    {
      if (reduceCurrent())
      {
      storedGrid[headX][headY].setSnakeBloody(true);
      storedGrid[newHeadX][newHeadY].setSnakeBloody(true);
      setScoreMessage("You can not eat yourself!");
      }
      return false;
    } // if the snake eat itself
    else if (storedGrid[newHeadX][newHeadY].getType() == Cell.TREE)
    {
      if (reduceCurrent())
      {
        storedGrid[headX][headY].setSnakeBloody(true);
        setScoreMessage("Your snake hit the tree and died :( ");
      }
      return false;
    } // if the snake meet a tree
    else
    {
        resetCountdown();
        return true;
    }
   } // ifItIsBloody

// ----------------------------------------------------------------------
// Part e-5: eat the food


  public void eatFood(int moveValue)
  {
    length++;
    int newScore = moveValue * ((length / (gridSize * gridSize / 36 + 1)) + 1);

    if (treesEnabled)
      {
        // if tree enable, the score will increased by the number of trees
        newScore = newScore * numOfTrees;
        score = newScore + score;
        setScoreMessage("raw score:" + newScore
                        + ", the number of trees: " + numOfTrees
                        + ", actual score: " + score );
        plantTrees();
      } // have trees
    else
      {
        score = newScore + score;
        setScoreMessage("Fantastic! You ate a piece of food " +
                       "and got " + newScore + " !");
      } // not have tree
    placeFood();

  } //eatFood

  public int getScore()
  {
    return score;
  } // getScore


// ----------------------------------------------------------------------
// Part f: cheat
  public void cheat()
  {
    int lostScore = (score + 1) / 2;
    score -= lostScore;
    setScoreMessage("You lost half score! In other words, your score for now"
                    + " is " + score + " !");

    resetCountdown();

    for (int row = 0; row < gridSize; row++)
      for (int column = 0; column < gridSize; column++)
        if (storedGrid[row][column].isSnakeType())
          storedGrid[row][column].setSnakeBloody(false);

  } // cheat


// ----------------------------------------------------------------------
// Part g: trees

  private boolean treesEnabled;

  public void toggleTrees()
  {

    if (treesEnabled)
      {
        treesEnabled = false;
        for (int row = 0; row < gridSize; row++ )
          for (int column = 0; column < gridSize; column++)
            {
              if (storedGrid[row][column].getType() == Cell.TREE)
                storedGrid[row][column].setClear();
            }
      } // trees enabled
    else
      {
        treesEnabled = true;
        plantTrees();
      } // trees not enabled

  } // toggleTrees

  private int treeX;
  private int treeY;

  public void plantTrees()
  {
    // when enable the tree, it will increase one every time after we eat the food
    numOfTrees++;
    do
    {
      treeX =  (int) (Math.random() * gridSize);
      treeY =  (int) (Math.random() * gridSize);
    } while(storedGrid[treeX][treeY].getType() != Cell.CLEAR);
    storedGrid[treeX][treeY].setTree();
  } // placeTrees

// ----------------------------------------------------------------------
// Part h: crash countdown
  private final int crashStartValue = 5;
  private int crashCurrentValue;

  private void resetCountdown()
  {
    crashCurrentValue = crashStartValue;
    if (crashCurrentValue < crashStartValue)
      setScoreMessage("You have "
                     + crashCurrentValue + "times to escape death!");
  } // resetCountdown

  private boolean reduceCurrent()
  {
    crashCurrentValue--;
    if (crashCurrentValue >= 0)
      {
        setScoreMessage("You only have " + crashCurrentValue
                        + " times to escape death, please move quickly!");
        return false;
      }
    else
      crashCurrentValue = crashStartValue;
      return true;
  } // reduceCurrent

// ----------------------------------------------------------------------
// Part i: optional extras

  private void trails()
  {
    // storedGrid[tailX][tailY].setOther(50);
  } // trails

  private int directTreeX;
  private int directTreeY;
  private void burnTress()
  {
    directTreeX = headX +
              Direction.xDelta(storedGrid[headX][headY].getSnakeOutDirection());
    directTreeY = headY +
              Direction.yDelta(storedGrid[headX][headY].getSnakeOutDirection());

    if (storedGrid[directTreeX][directTreeY].getType() == Cell.TREE)
      {
        storedGrid[directTreeX][directTreeY].setClear();
        // reduce the number of tree (so the score will changed)
        numOfTrees--;
      }
  } // burnTress

  private void moveFood()
  {

  } // moveFood

  public String optionalExtras()
  {
    return "  No optional extras defined\n";
  } // optionalExtras


  public void optionalExtraInterface(char c)
  {
    switch (c)
    {
      case 'b': burnTress();
      case 'g': trails();
      case 'm': moveFood();
    }
  } // optionalExtraInterface

} // class Game
