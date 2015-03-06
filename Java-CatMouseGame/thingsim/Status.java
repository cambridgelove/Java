// Section 6 of the spec describes the Status class 
package thingsim;
public enum Status{
  ACTIVE,//a Thing can move
  DEAD,
  INERT,//doesn't move, used for Brick and Hole
  SAFE;//Thing finds refuge and won't move anymore
}