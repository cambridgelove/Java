// Section 8 of the spec describes the Always class 
package thingsim;
public class RowColSearch implements Behavior{
  public int targetId;
  public String targetName;
  
  //constructor
  public RowColSearch(String targetName){
    this.targetId=-1;
    this.targetName=targetName;
  }
  
  //selct the target for things
  private void selectTarget(Room room, Thing me){
    int distance = Integer.MAX_VALUE;
    for (int i=0;i<room.thingCount();i++){
      Thing temp=room.getThingByIndex(i);
      //compare name
      if (this.targetName.equals(temp.name)){
        //search for the closest one
        if (me.position.distance(temp.position)<distance){
          this.targetId=i;
          distance=me.position.distance(temp.position);
        }
      } 
    }
    me.say("Targetting "+this.targetId);
  }
  
  //return the desired position
  public Coords getMove(Room room, Thing me){
    //check if the thing has selected its target
    if (this.targetId<0){
      this.selectTarget(room,me);
      //if there is no target for the thing, return its original position
      if (this.targetId<0){
        me.say("I don't know where to go!");
        return me.position;
      }
      
    }
    //if the thing has selected its target
    //first try to get to the same row as its target
    //then try to get to the same column
    Thing tar=room.getThingById(this.targetId);
    Coords next=null;
    if (tar.position.r==me.position.r){
      if (tar.position.c>me.position.c)
        next=new Coords(me.position.r,me.position.c+1);
      else if (tar.position.c<me.position.c)
        next=new Coords(me.position.r,me.position.c-1);
      else next=me.position;
    }
    else if (tar.position.r>me.position.r)
      next=new Coords(me.position.r+1,me.position.c);
    else
      next=new Coords(me.position.r-1,me.position.c);
    return next;
  }
  
}