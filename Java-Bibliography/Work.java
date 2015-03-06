// MODIFY THIS CLASS
// All your code will go in here
import java.lang.Integer;
import java.util.*;
import java.util.logging.*;
import java.lang.String;
import java.util.regex.Pattern;


public class Work{
  // Good for warnings
  private static Logger logger = Logger.getLogger("bibify");

  // Determine how the bibtex files are to be sorted, sort them in
  // place, populate the sortedOrder field of each one.  Valid values
  // for bibstyle are "author" "text" "bibkey" and "bibfile"
  // 
  // See spec Sec. 7
  public static void sortBibtexRecords(ArrayList<BibtexRecord> bibs,
           String bibstyle)
    throws Exception
  {
    // Your code here
   //sort with regard to author
    if (bibstyle.equals("author"))
        Collections.sort(bibs,new AuthorComparator());
      //sort with regard to textOrder
    else if (bibstyle.equals("text"))
        Collections.sort(bibs,new TextComparator());
      //sort with regard to bibkey
    else if (bibstyle.equals("bibkey"))
        Collections.sort(bibs,new BibkeyComparator());
      //sort with regard to bibfile
    else if (bibstyle.equals("bibfile"))
        Collections.sort(bibs,new BibfileComparator());
    else {
      throw new Exception("Wrong bibstyle!");
    }
    
    //provide sortOrder
    for (int i=0;i<bibs.size();i++){
      bibs.get(i).sortedOrder=i+1;
    }
    
  }

  // Determine which keys are cited in the text.  Put the
  // corresponding bibtex records in their own ArrayList.  If a record
  // is cited multiple times, it should only appear once in the return
  // array.  If a key does not appear in the bibliography file, it is
  // helpful but not required to print a warning message using 
  // 
  // logger.warning.("A warning message of your choice");
  // 
  // See spec Sec. 8
  public static 
    ArrayList<BibtexRecord> extractCited(ArrayList<BibtexRecord> bibRecords,
      ArrayList<String> allKeys)
    throws Exception
  {
    // Your code here
    //sort bibRecords with regard to bibkey
    sortBibtexRecords(bibRecords,"bibkey");
    ArrayList<Integer> indexOfRecords =new ArrayList<Integer>();    
    //detect whether a given String in allKeys match bibRecords 
    //by binary search 
    //
    //make an arraylist<String> for all keys in bibRecords
    ArrayList<String> allBibKeys=new ArrayList<String>();
    for(int i=0;i<bibRecords.size();i++){
      allBibKeys.add(bibRecords.get(i).bibkey);
    }
    for (int i=0;i<allKeys.size();i++){
      boolean duplicate=false;
      int index=-1;
      try{
        //check for duplicate
        index=Collections.binarySearch(allBibKeys,allKeys.get(i));
        for (int j=0;j<i;j++){
          if ((new Integer(index)).equals(indexOfRecords.get(j)))
            duplicate=true;
        }
        if (!duplicate)
          indexOfRecords.add(new Integer(index));
      }
      catch(Exception e){
        logger.warning("A warning message of your choice");
      }
    }
      
      //pull out the bibtex records
      ArrayList<BibtexRecord> citedRecords= new ArrayList<BibtexRecord>();
      int index2=-1;
      for (int i=0;i<indexOfRecords.size();i++){
        index2=indexOfRecords.get(i).intValue();
        BibtexRecord b=bibRecords.get(index2);
        citedRecords.add(b);
        b.textOrder=citedRecords.size();
      }
      
    return citedRecords;
  }

  // Determine which citation format to use and return a corresponding
  // formatting object. Valid values for citestyle are "number" and "author"
  // 
  // See spec Sec. 9
  public static CitationFormatter getCitationFormatter(String citestyle)
    throws Exception
  {
    // Your code here
    CitationFormatter citationFormat=null;
    if (citestyle.equals("number")){
      citationFormat=new CiteNumber();
    }
    else if (citestyle.equals("author")){
      citationFormat=new CiteAuthor();
    }
    else {
      throw new Exception("Wrong citestyle!");
    }
    return citationFormat;
  }

  
  // For each bibtex record in argument cited, replace all instances
  // of \cite{bibkey} in argument text by a formatted string produced
  // by running the bibtex record through citeFormat.format()
  // 
  // See spec Sec. 10
  public static 
  String insertCitations(String text, 
    ArrayList<BibtexRecord> cited, 
    CitationFormatter citeFormat){
    // Your code here
    ArrayList<String> newtext=new ArrayList<String>();
    newtext.add(text);
    for(int i=0;i<cited.size();i++){
      BibtexRecord b=cited.get(i);
      String bibKey=b.bibkey;
      String regex="\\cite{"+bibKey+"}";
      String replacement=citeFormat.formatCitation(b);
      newtext.add(newtext.get(i).replaceAll(Pattern.quote(regex),replacement));
    }
    return newtext.get(cited.size());
  }
  
  // For each bibtex record in argument cited, generate a string which
  // starts with how citations are formatted (the results of running
  // citeFormat.format() on a bibtex record) along with the results of
  // running BbibtexFormatter.formatItem() on the record.  This will look like
  // 
  // (1) Wenzhen Jin, Ohki Kambara, Hiroaki Sasakawa, Atsuo Tamura, Shoji Takada. De Novo Design of Foldable Proteins with Smooth Folding Funnel: Automated Negative Design and Experimental Verification. Structure. 2003.
  // (2) Michal Brylinski, Leszek Konieczny, Irena Roterman. Hydrophobic collapse in (in silico) protein folding. Computational Biology and Chemistry. 2006.
  // 
  // or
  // 
  // (Jin, 2003) Wenzhen Jin, Ohki Kambara, Hiroaki Sasakawa, Atsuo Tamura, Shoji Takada. De Novo Design of Foldable Proteins with Smooth Folding Funnel: Automated Negative Design and Experimental Verification. Structure. 2003.
  // (Brylinski, 2006) Michal Brylinski, Leszek Konieczny, Irena Roterman. Hydrophobic collapse in (in silico) protein folding. Computational Biology and Chemistry. 2006.
  // 
  // depending on what argument citeFormat does
  // 
  // Return a string that is the concatenation of all the formatted
  // bibliography entries
  // 
  // See spec Sec. 11
  public static
  String createBibliography(ArrayList<BibtexRecord> cited, 
       CitationFormatter citeFormat){
    // Your code here
    String bibString="";
    for(int i=0;i<cited.size();i++){
      BibtexRecord b=cited.get(i);
      String appendCite=citeFormat.formatCitation(b);
      bibString+=appendCite;
      bibString+=" ";
      String appendBib=BibtexFormatter.formatItem(b);
      bibString+=appendBib;
      bibString+="\n";
    }
    return bibString;
}
}