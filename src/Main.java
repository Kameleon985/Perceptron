import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[]args){
        File file = new File("iris.csv");
        Perceptron perceptron;
        perceptron = new Perceptron(file,0.1,1);
        perceptron.showWieght();
        System.out.println("Dokładność przydziału: "+perceptron.accuracy);

        for(ObjectData o:perceptron.testing){
            String xd = o.y+" "+perceptron.test(o);
            if(o.y!=perceptron.test(o))
                xd+=" =============BŁĄD===========";
            System.out.println(xd);
        }
        while(true){
            Scanner in = new Scanner(System.in);
            String[]tmp=new String[perceptron.weight.DataVector.size()+1];
            for(int i=0;i<tmp.length;i++){
                tmp[i]= String.valueOf(in.nextDouble());
            }
            ObjectData od = new ObjectData(tmp);
            System.out.println(perceptron.test(od));
            perceptron.train(od);
            //5.0;3.3;1.4;0.2;0
            //7.0;3.2;4.7;1.4;1
            perceptron.showWieght();
        }
    }
}
