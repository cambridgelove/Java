import java.util.Comparator;
public class AuthorComparator implements Comparator<BibtexRecord>{
  public int compare(BibtexRecord b1,BibtexRecord b2){
    return b1.firstAuthorLastName().compareTo(b2.firstAuthorLastName());
  }
}