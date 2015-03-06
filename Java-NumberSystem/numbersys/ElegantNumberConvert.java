package numbersys;
public class ElegantNumberConvert{
  // This maps characters to numerals
  public static char digitMap[] = {
    '0','1','2','3','4','5','6','7','8','9',
    'A','B','C','D','E','F','G','H','I','J',
    'K','L','M','N','O','P','Q','R','S','T',
    'U','V','W','X','Y','Z'
  };
  // Convert a character to its decimal value according to the above
  // map - locate it in the above array and return its index.
  public static int char2int(char c){
    int i=0;
    while(digitMap[i] != c){
      i++;
    }
    return i;
  }
    
  // Convert a String of arbitrary base into java's primitive int
  public static int toInt(String s) throws InvalidBaseException
  {
    // Determine the base which is at the end of the string after an
    // underscore and in decimal format as in 123ABC_16. 
    int offset = s.indexOf('_');
    //try to convert number after "_" to an int
    //if there is nothing after "_", throws InvailidBaseException
    int base=0;
    try {
      base = Integer.parseInt(s.substring(offset+1));
    }
    catch (Exception o){
      throw new InvalidBaseException();
    }
    //if base is small than 2, throw InvalidBaseException
    if (base<2)
      throw new InvalidBaseException();
    // This assumes that all the characters to be read in are smaller
    // than the base that was read.  Not sure what will happen if
    // that's not true.
    int sum=0;
    for(int i=0; i<offset; i++){
      char digit = s.charAt(i);
      int decimal = char2int(digit);
      sum = sum*base + decimal;
    }
    return sum;
  }

  // Convert a primitive java int into a String of arbitrary base
  public static String toBase(int decimal, int base) throws InvalidBaseException
  {
    String result = "";
    int current = decimal;
    int quotient = decimal;
    int remainder = 0;
    
    //check if the base is bigger than 1
    //otherwise throw InvalidBaseException
    if (base<2)
      throw new InvalidBaseException();
    do{
      quotient = current / base;
      remainder = current % base;
      char digit = digitMap[remainder];
      result = digit + result;
      current = quotient;
    } while(current > 0);
    result =result + "_" + base;
    return result;
  }
  
  // A simple main to allow interactive testing
  public static void main(String args[])throws InvalidBaseException
  {
    String num = args[0];
    int newBase = Integer.parseInt(args[1]);
    int decimal = toInt(num);
    String numNewBase = toBase(decimal, newBase);
    System.out.printf("%15s: %s\n%15s: %d\n%15s: %d\n%15s: %s\n",
        "Original",num,
        "Decimal",decimal,
        "Desired Base",newBase,
        "New Rep",numNewBase);
  }
    

}
