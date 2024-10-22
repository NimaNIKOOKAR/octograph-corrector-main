public class Word {

    private int distance;
    private String value;


    public Word(int distance , String value){
        this.distance = distance;
        this.value = value;
    }
    public void setDistance(int distance){
        this.distance = distance;

    }
    public void setValue(String value){
        this.value = value;
    }
    public int getDistance(){
        return distance;
    }
    public String getValue(){
        return value;
    }
}
