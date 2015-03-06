import java.util.Comparator;

public class BibfileComparator implements Comparator<BibtexRecord>{
  public int compare(BibtexRecord b1,BibtexRecord b2){
    return b1.bibfileOrder.compareTo(b2.bibfileOrder);
  }
}