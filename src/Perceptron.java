
import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    double alpha;
    double t;
    List<ObjectData>training;
    List<ObjectData>testing;
    ObjectData weight;
    double threshold;
    double accuracy;

    public Perceptron(File data,double alpha,double t){
        training = new ArrayList<>();
        testing = new ArrayList<>();
        this.alpha=alpha;
        this.t=t;
        accuracy=0;
        DataReader dr = new DataReader(data);
        for(int i=0;i<dr.data.size();i++){
            if(i%4==0) {
                testing.add(dr.data.get(i));
            }else {
                training.add(dr.data.get(i));
            }
        }
        weight = testing.get(0);
        weight.DataVector.replaceAll(d->(d*0)+1);
        threshold=1;
        do{
            accuracy=0;
            for (int i = 0; i < training.size(); i++) {
                if (train(training.get(i)) == training.get(i).y)
                    accuracy += 1;
            }
            accuracy = accuracy / training.size();
        }while(accuracy<t);
    }
    public int train(ObjectData objectData){
        if(objectData.DataVector.size()!=weight.DataVector.size())
            throw new IllegalArgumentException();
        double d=0;
        for(int i=0;i<weight.DataVector.size();i++){
            d+=weight.DataVector.get(i)*objectData.DataVector.get(i);
        }
        int r = d>=threshold ? 1 : 0;
        if(r!=objectData.y){
            for(int i=0;i<weight.DataVector.size();i++){
                weight.DataVector.set(i,weight.DataVector.get(i)+(objectData.y-r)*alpha*objectData.DataVector.get(i));
            }
            threshold=threshold-(objectData.y-r)*alpha;
        }
        return r;
    }
    public double test(ObjectData objectData){
        double y=0;
        for(int i=0;i<weight.DataVector.size();i++){
            y+=objectData.DataVector.get(i)*weight.DataVector.get(i);
        }
        if(y>= threshold)
            return 1;
        return 0;
    }
    public void showWieght(){
        System.out.println(weight.DataVector +" prog="+threshold);
    }
}