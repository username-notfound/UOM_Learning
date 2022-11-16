public class GameMethodTest
{

  public static void main (String [] args)
  {
    String string;
    int integer;
    Cell cell;

    Game game = new Game(18);

    string = game.getScoreMessage();
    game.setScoreMessage("");
    string = game.getAuthor();
    integer = game.getGridSize();
    cell = game.getGridCell(10, 10);
    game.setInitialGameState(0, 0, 5, Direction.EAST);
    game.setSnakeDirection(Direction.SOUTH);
    game.move(100);
    integer = game.getScore();
    game.cheat();
    game.toggleTrees();
    string = game.optionalExtras();
    game.optionalExtraInterface('g');

  } // main

} // class Game
