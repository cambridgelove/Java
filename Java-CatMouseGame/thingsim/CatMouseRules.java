// See section 9 an 9.2 of the spec to complete this file
package thingsim;
public class CatMouseRules{
  public static Rule rules[] = {
    
    new Rule(10,"Cat move to Hole, not allowed, high in priority to avoid killing safe mice"){
      public boolean predicate(Thing x, String action, Thing y){
        return
          x instanceof Cat &&
          y instanceof Hole &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        x.say("Pesky hole is in my way");
        // position of cat is unchanged 
      }
    },
    
    new Rule(9,"Mouse move to Mouse, not allowed"){
      public boolean predicate(Thing x, String action, Thing y){
        return
          x instanceof Mouse &&
          y instanceof Mouse &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
 // YOUR CODE HERE
        y.sayTo(x,"This is my spot,find your own!");
        //position of mouse x is unchanged
      }
    },
    
    new Rule(8,"Mouse move to Hole, becomes safe"){
      public boolean predicate(Thing x, String action, Thing y){
        return
   // YOUR CODE HERE
          x instanceof Mouse &&
          y instanceof Hole &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        x.position = y.position;
        x.status = Status.SAFE;
        x.say("Safe at last");
      }
    },
    
    new Rule(7,"Cat move to Mouse, Mouse dies"){
      public boolean predicate(Thing x, String action, Thing y){
        return 
          x instanceof Cat && 
          y instanceof Mouse &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        Cat c = (Cat) x;
        Mouse m = (Mouse) y;
        if(m.status == Status.ACTIVE){
   // YOUR CODE HERE
          m.say("Farewell cruel world");
          m.status=Status.DEAD;
          c.sayTo(m,"Dinner is served");
          //cat's kills inclements
          c.kills++;
          //cat move to the mouse's position
          c.position=m.position; 
        }    
      }
    },
    
    new Rule(0,"Default things can't occupy the same space"){
      public boolean predicate(Thing x, String action, Thing y){
        return 
          x instanceof Thing && 
          y instanceof Thing &&
          action.equals("move to");
      }
      public void apply(Thing x, Thing y){
        x.say("I can't move there");
        y.say("Ha ha");
      }
    }    
  };
}