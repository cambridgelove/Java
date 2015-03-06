public class CiteNumber implements CitationFormatter{
  
  public String formatCitation(BibtexRecord b){
    return String.format("(%s)",b.sortedOrder);
  }
  
}