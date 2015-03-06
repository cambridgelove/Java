public class CiteAuthor implements CitationFormatter{
  public String formatCitation(BibtexRecord b){
    return String.format("(%s, %s)",b.firstAuthorLastName(),b.year());
  }
}