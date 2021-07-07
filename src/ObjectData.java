import java.util.ArrayList;
import java.util.List;

public class ObjectData {

    public List<Double>DataVector;
    public double y;

    public ObjectData(String[]tab){

        DataVector=new ArrayList<>();
        y=Double.parseDouble(tab[tab.length-1]);
        for(int i=0;i<tab.length-1;i++){
            DataVector.add(Double.parseDouble(tab[i]));
        }
    }
    public String toString(){
        String s="";
        for(Double d:DataVector){
            s+=d+";";
        }
        s+=";y="+y;
        return s;
    }
}
