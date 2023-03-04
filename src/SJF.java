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
                int o1time = 0;
                int o2time = 0;
                for(int i = 0; o1.getCpuBurst().size() < i; i++){
                    o1time =+ o1.getCpuBurst().get(i) + o1.getIoBurst().get(i);
                }
                for(int i = 0; o2.getCpuBurst().size() < i; i++){
                    o2time =+ o2.getCpuBurst().get(i) + o2.getIoBurst().get(i);
                }
                return o1time   - o2time;
            }
           });
           return readyQueue.get(0);
   }
}
