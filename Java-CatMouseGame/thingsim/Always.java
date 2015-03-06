// Section 7 of the spec describes the Always class 
package thingsim;

public class Always implements Behavior{
  public String direction;
  public Always(String direction){
    this.direction=direction.toLowerCase();
  }
  public Coords getMove(Room room, Thing me){
    Coords next=null;
    switch (this.direction){
      case "up":next=new Coords(me.position.r-1,me.position.c);
      break;
      case "down":next=new Coords(me.position.r+1,me.position.c);
      break;
      case "left":next=new Coords(me.position.r,me.position.c-1);
      break;
      case "right":next=new Coords(me.position.r,me.position.c+1);
      default:next=me.position;
    }
    return next;
  }
}