import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Dictionary {
    private HashMap<String , ArrayList<String>> dictionary = new HashMap<>();

    private ArrayList<String> listOfWords = new ArrayList<>();


    private ReadFile readFile;

    private String fileName;




    public Dictionary(String fileName){
      this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }


    public HashMap<String, ArrayList<String>> getDictionary(){
        return dictionary;
    }


    public ArrayList<String> getListOfWords(){
        return listOfWords;
    }

// setListOfWords Works right
    public void setListOfWords(String fileName) throws FileNotFoundException {
        ReadFile l_readFile = new ReadFile(fileName);
        ArrayList<String> arrayList = new ArrayList<>();
        l_readFile.fileReader();
        arrayList = l_readFile.GetArrayList();


        for (int i = 0; i < arrayList.size(); i++) {
            this.listOfWords.add(i ,  arrayList.get(i));

        }
    }

    // Char placements Works
    public List<Integer> CharPlacement(String word){
        List<Integer> chars = new ArrayList<>();

        chars = word.codePoints().boxed().toList();

        return chars;
    }



    // ListOfChar Works right
    public ArrayList<Character> ListOfChar(String word){
        ArrayList<Character> chars = new ArrayList<>();

        for(Character character : word.toCharArray()){
            chars.add(character);
        }
        return chars;
    }





    public int LevenshteinDistance(String left , String right ){

        int[][] distance = new int[left.length() + 1][right.length() + 1];


        for( int i = 0; i < left.length() + 1; i++ ) {
            distance[ i ][ 0 ] = i;
        }

        for(int j = 0; j < right.length() + 1; j++) {
            distance[ 0 ][ j ] = j;
        }

        for( int i = 1; i < left.length() + 1; i++ ) {
            for( int j = 1; j < right.length() + 1; j++ ) {
                int d1 = distance[ i - 1 ][ j ] + 1;
                int d2 = distance[ i ][ j - 1 ] + 1;
                int d3 = distance[ i - 1 ][ j - 1 ];
                if ( left.charAt(i - 1) != right.charAt(j - 1)) {
                    d3 += 1;
                }
                distance[ i ][ j ] = Math.min( Math.min( d1, d2 ), d3 );
            }
        }
        return distance[ left.length() ][ right.length() ];


    }



  public void setDictionary(String fileName) throws IOException {
        List<String> words = Files.lines(Path.of("/amuhome/n20018114/Bureau/dico.txt")).toList();
        setListOfWords(fileName);
        for(String word : words) {//getListOfWords()){
            for (String trig : oneWordTrig(word)){
                 if (!dictionary.containsKey(trig)) {
                     dictionary.put(trig, new ArrayList<>());
                 }
                 dictionary.get(trig).add(word);
            }
        }
    }





  public  ArrayList<String> SameTrigList(String word)  throws FileNotFoundException {
        ArrayList<String> sameTrigsWords = new ArrayList<>();
      for (Map.Entry<String, ArrayList<String>> set : this.dictionary.entrySet()){
          for(String trig : oneWordTrig(word)){
              if (set.getKey().equals(trig))
                   sameTrigsWords.addAll(set.getValue());
      }
      }
      return sameTrigsWords;
  }






  public ArrayList<Word> SearchWord(String word) throws FileNotFoundException {
        ArrayList<String> sameTrigsWords = new ArrayList<>();
        sameTrigsWords = SameTrigList(word);
       // HashMap<Integer , ArrayList<String>> setclosestWords= new HashMap<Integer, ArrayList<String>>();
      ArrayList<Word> closestWords =new  ArrayList<Word>();

      for (String closeWord : sameTrigsWords){
          int distance = 0;
          distance = LevenshteinDistance(word , closeWord);
          closestWords.add(new Word(distance , word));
      }

      return closestWords ;

  }




  public ArrayList<String> getClosestWords(String word) throws FileNotFoundException {
        ArrayList<Word> closestWords = new ArrayList<>();
        closestWords = SearchWord(word);

        ArrayList<String> words = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < closestWords.size() ; j ++ ){

            }
        }

       return null;
  }





// oneWordTrig Works
  public List<String> oneWordTrig(String word) throws FileNotFoundException {
        ArrayList<String> listOfTrigs = new ArrayList<>();

        String TrigWord = "<" + word + ">";
        TrigWord.substring(0,3);


        String[] Trigramms = TrigWord.split("(?<=\\G.{" + 3 + "})");

  return Arrays.asList(Trigramms);
  }




   // isWordAvailable Works rigth
  public boolean isWordAvailable(String word) throws IOException {
        ReadFile newReadFile = new ReadFile(getFileName());
        newReadFile.fileReader();
      ArrayList<String> listOfWords = new  ArrayList<String>(newReadFile.GetArrayList());

      for (String availableWords : listOfWords)
          if (word.equals(availableWords))
              return true;
      return false;


  }

  public boolean isWordInDic(String word) throws IOException {
        setDictionary(getFileName());
       for(String trig : oneWordTrig(word))
          if (getDictionary().get(trig).contains(word))
              return true;
          else
              return false;
      return false;
  }


}
