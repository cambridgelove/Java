// Section 5 of the spec describes the Thing class
package thingsim;
import java.io.PrintWriter;
public class Thing{
  //static fields and methods
  public static int totalThings;
  public static PrintWriter log;
  public static void startLogging(PrintWriter p){
    log=p;
  }
  public static void stopLogging(){
    log=null;
  }
  
  //fields of instances
  public String name;
  public String shortName;
  public final int id;
  public Coords position;
  public Behavior behavior;
  public Status status;
  
  //constructors
  //public constructor used by subclasses
  public Thing(String name, String shortName){
    this();
    this.name=name;
    this.shortName=shortName;
  }
  //private constructor used internally
  private Thing(){
    this.id=totalThings;
    totalThings++;
  }
  
  //methods of instances
  //how things move
  public Coords getMove(Room r){
    return behavior.getMove(r,this);
  }
  
  //print out target of things
  public void say(String msg){
    log.print(this.id+" "+this.name+": "+msg+"\n");
  }
  
 //print out things' words to others
  public void sayTo(Thing other, String msg){
    log.print(this.id+" "+this.name+" to "+other.id+" "+other.name+": "+msg+"\n");
  }
  
  //check if the position is occupied
  public boolean occupies(Coords c){
    return (this.position.equals(c));
  }
  
  //is the thing in the room?
  public boolean inBounds(Coords c,Room room){
    return room.inside(c);  
  }
  
  //message printed to show the state of things
  public String toString(){
    if (this.status==Status.DEAD)
      return "rip";
    else if (this.status==Status.SAFE)
      return this.shortName+"ok";
    else return this.shortName;    
  } 
}