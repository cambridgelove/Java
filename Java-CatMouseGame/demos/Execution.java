// Playing 5 rounds

// Initial

//   0C  
//    ---
//   1M  
//   2M--
//    ---

// One mouse, one cat, no holes

import java.io.*;
import thingsim.*;

public class Execution{
  public static void main(String args[])
    throws FileNotFoundException
  {
    Game game = new Game();
    game.gamelog = new PrintWriter(System.out);
    Thing.startLogging(game.gamelog);
    game.rules = CatMouseRules.rules;

    game.room = new Room(5,1,new Thing[]{
	Standard.alwaysCat(0,0,"down"),
	// Standard.cat(0,0),
	Standard.mouse(2,0),
	Standard.mouse(3,0)
      });
    game.play(5);
    Thing.stopLogging();
  }
}
