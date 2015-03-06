// Playing 4 rounds

// Initial

//   0M  
//    ---
//   1M  
//    ---
//    ---
//    ---
//   2C  
//   3XXX
//   5M  
//   4( )
//   6C  
//    ---
//   7M  
//   8C  
//    ---

// Critters can't move past one another, get stuck on the brick/hole,
// one always cat, limitations of RowColSearch
import java.io.*;
import thingsim.*;

public class Crunch{
  public static void main(String args[])
    throws FileNotFoundException
  {
    Game game = new Game();
    game.gamelog = new PrintWriter(System.out);
    Thing.startLogging(game.gamelog);
    game.rules = CatMouseRules.rules;

    game.room = new Room(15,1,new Thing[]{
	Standard.stillMouse(0,0),
	Standard.mouse(2,0),
	Standard.cat(6,0),
	Standard.brick(7,0),
	Standard.hole(9,0),
	Standard.mouse(8,0),
	Standard.cat(10,0),
	Standard.mouse(12,0),
	Standard.alwaysCat(13,0,"down")
      });
    
    game.play(4);
    Thing.stopLogging();
  }
}
