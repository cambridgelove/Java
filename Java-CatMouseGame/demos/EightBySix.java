// Playing 5 rounds

// Initial

//    ---   ---   --- 10M     --- 11M  
//    ---  8M     ---   ---  0XXX   ---
//    ---  2XXX  3( )   ---  1XXX  5( )
//    ---   ---   ---   ---   ---   ---
//   6C     ---   ---   ---   ---   ---
//    ---   ---   ---  7C     ---   ---
//    ---   ---   ---   ---   --- 12M  
//    ---   ---  4( )   ---   ---  9M  

// Large room with lots going on
import java.io.*;
import thingsim.*;

public class EightBySix {
  public static void main(String args[])
    throws FileNotFoundException
  {
    Game game = new Game();
    game.gamelog = new PrintWriter(System.out);
    Thing.startLogging(game.gamelog);
    game.rules = CatMouseRules.rules;
    game.room = new Room(8,6,new Thing[]{
	Standard.brick(1,4),
	Standard.brick(2,4),
	Standard.brick(2,1),
	Standard.hole(2,2),
	Standard.hole(7,2),
	Standard.hole(2,5),
	Standard.cat(4,0),	
	Standard.cat(5,3),
	Standard.mouse(1,1),
	Standard.mouse(7,5),
	Standard.mouse(0,3),
	Standard.stillMouse(0,5),
	Standard.alwaysMouse(6,5,"up")
      });
    game.play(5);
    Thing.stopLogging();
   }
}