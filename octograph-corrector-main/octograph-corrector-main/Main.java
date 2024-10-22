import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "/amuhome/n20018114/Bureau/dico.txt";
       // String fileName = "/amuhome/n20018114/Bureau/minidico.txt";
        ReadFile readFile = new ReadFile(fileName);
        readFile.fileReader();

      //  System.out.println(readFile.GetArrayList());



        Dictionary dictionary = new Dictionary(fileName);

        dictionary.setListOfWords(fileName);



       System.out.println(dictionary.isWordAvailable("chambre"));

        System.out.println(dictionary.oneWordTrig("chambre"));

        String word1 = new String("chambre");

        String word2 = new String("calcule");

        System.out.println(dictionary.ListOfChar(word1));

      //  System.out.println(dictionary.CharPlacement(word1));

     //   System.out.println(dictionary.CharPlacement(word2));

        dictionary.setDictionary(fileName);

//        System.out.println(dictionary.getDictionary().get("on>"));
//
//        System.out.println(dictionary.SameTrigList("allocation"));
//
//        System.out.println(dictionary.isWordInDic("agio"));

    //    System.out.println(dictionary.SearchWord("allocation"));

        System.out.println(dictionary.LevenshteinDistance(word1 , word2));



    }
}