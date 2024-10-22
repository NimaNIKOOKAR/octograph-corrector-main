import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadFile {
    private Scanner scanner;
    private ArrayList<String> arrayList = new ArrayList<>();

    private String fileName;

    public ReadFile(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }


    public void fileReader() throws FileNotFoundException {

        scanner = new Scanner(new File(getFileName()));
        while (scanner.hasNext()){
            arrayList.add(scanner.next());
        }

    scanner.close();
    }


    public ArrayList<String> GetArrayList(){
        return arrayList;
    }


}
