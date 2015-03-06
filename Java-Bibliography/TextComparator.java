import java.util.Comparator;

public class TextComparator implements Comparator<BibtexRecord>{
  public int compare(BibtexRecord b1,BibtexRecord b2){
    return b1.textOrder.compareTo(b2.textOrder);
  }
}