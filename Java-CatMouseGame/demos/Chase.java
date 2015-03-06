// Playing 4 rounds

// Initial

//   1C  
//   0M  
//    ---
//    ---
//   2( )
//    ---
//   3M  
// 
// Cat chases mouse which is beaten to hole by another mouse
import java.io.*;
import thingsim.*;

public class Chase{
  public static void main(String args[])
    throws FileNotFoundException
  {
    Game game = new Game();
    game.gamelog = new PrintWriter(System.out);
    Thing.startLogging(game.gamelog);
    game.rules = CatMouseRules.rules;
    game.room = new Room(7,1,new Thing[]{
	Standard.mouse(1,0),
	Standard.cat(0,0),
	Standard.hole(4,0),
	Standard.mouse(6,0)
      });
    game.play(4);
    Thing.stopLogging();
  }
}
