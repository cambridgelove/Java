import java.util.Comparator;
public class BibkeyComparator implements Comparator<BibtexRecord>{
  public int compare(BibtexRecord b1,BibtexRecord b2){
    return b1.bibkey.compareTo(b2.bibkey);
  }
}