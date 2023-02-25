import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF extends SchedulingAlgorithm{

    public SJF(List<PCB> queue){
        super("SJF", queue);
    }
    
    @Override
    public PCB pickNextProcess(){
        Collections.sort(readyQueue, new Comparator<PCB>(){
            @Override
            public int compare(PCB o1, PCB o2){
                return o1.getCpuBurst() - o2.getCpuBurst();
            }
           }};
           return readyQueue.get(0);
   }
}
