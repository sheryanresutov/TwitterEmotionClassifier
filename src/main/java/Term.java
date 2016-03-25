import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Term {

    String term;
    List<Integer> emotionValueList =  new ArrayList<>(Collections.nCopies(6,0));
    List<Integer> tCounts = new ArrayList<>(Collections.nCopies(6, 0));
    List<Integer> dCounts = new ArrayList<>(Collections.nCopies(6, 0));
    List<Double> tfIdfs = new ArrayList<>();

    private void computeTfIDf(List<Integer> emotionCounts){
        double tf, idf;
        for(int i=0;i<6;i++){
            tf = tCounts.get(i); //i have to somehow normalize or just remove completely
            if(dCounts.get(i) != 0) {
                idf = Math.log(emotionCounts.get(i) / dCounts.get(i));
            }
            else{
                idf = 1; //what can i do here?
            }
            tfIdfs.add(tf*idf);
        }
    }

    public void computeEmotionValues(List<Integer> emotionCounts){
        computeTfIDf(emotionCounts);
        for(int i=1;i<7;i++){
            emotionValueList.set(tfIdfs.indexOf(Collections.max(tfIdfs)), i);
            tfIdfs.set(tfIdfs.indexOf(Collections.max(tfIdfs)),-1.0);
        }
    }
}
