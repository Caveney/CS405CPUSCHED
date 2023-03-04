import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF extends SchedulingAlgorithm{

    public SJF(List<PCB> queue, int manualMode){
        super("SJF", queue, manualMode);
    }
    
    @Override
    public PCB pickNextProcess(){
        Collections.sort(readyQueue, new Comparator<PCB>(){
            @Override
            public int compare(PCB o1, PCB o2){

                return o1.getCpuBurst().get(0)  - o2.getCpuBurst().get(0);
            }
           });
           return readyQueue.get(0);
   }
}
