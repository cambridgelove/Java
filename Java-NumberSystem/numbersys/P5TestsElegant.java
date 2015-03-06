package numbersys;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import static numbersys.ElegantNumberConvert.*;
  
public class P5TestsElegant {
  // Tests for Problem 1.
  @Test
  public void toInt_1(){
    String s="00123_10";
    assertEquals(toInt(s),123);
  }
  
  @Test
  public void toInt_2(){
    String s="0100101_2";
    assertEquals(toInt(s),37);
  }
  
  @Test
  public void toInt_3(){
    String s="0AF146_16";
    assertEquals(toInt(s),717126);
  }
  
  @Test
  public void toInt_4(){
    String s="356_7";
    assertEquals(toInt(s),188);
  }
  
  @Test
  public void toInt_5(){
    String s="D24643_024";
    assertEquals(toInt(s),104236515);
  }
  
   @Test
  public void toInt_6(){
    String s="7342_008";
    assertEquals(toInt(s),3810);
  }
   
   @Test
   public void toInt_7(){
     String s="123_";
     try {
       toInt(s);
     }
     catch(InvalidBaseException e){}
     catch(Exception o){
       fail();
     }
   }
   
  //Tests for problem 2
  @Test
   public void toBase_1(){
     int decimal=222;
     int base=10;
     assertEquals(toBase(decimal,base),"222_10");
   }
  
   @Test
   public void toBase_2(){
     int decimal=4;
     int base=7;
     assertEquals(toBase(decimal,base),"4_7");
   }
   
   @Test
   public void toBase_3(){
     int decimal=8;
     int base=16;
     assertEquals(toBase(decimal,base),"8_16");
   }
   
   @Test
   public void toBase_4(){
     int decimal=123;
     int base=10;
     assertEquals(toBase(decimal,base),"123_10");
   }

   @Test
   public void toBase_5(){
     int decimal=28023;
     int base=4;
     assertEquals(toBase(decimal,base),"12311313_4");
   }
   
   @Test
   public void toBase_6(){
     int decimal=280;
     int base=9;
     assertEquals(toBase(decimal,base),"341_9");
   }
   
   @Test
   public void toBase_7(){
     int decimal=123;
     int base=0;
     try{
       toBase(decimal,base);
     }
     catch (InvalidBaseException e){}
     catch (Exception o){
       fail();
     }
   }
  // Main function, don't monkey with this
  public static void main(String args[]){
   org.junit.runner.JUnitCore.main("numbersys.P5TestsElegant.java");
  }

}
