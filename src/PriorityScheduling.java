import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class PriorityScheduling extends SchedulingAlgorithm{

    public PriorityScheduling(List<PCB> queue, int manualMode){
        super("Priority Scheduling",queue, manualMode);
    }
   
    @Override
    public PCB pickNextProcess(){
        Collections.sort(readyQueue, new Comparator<PCB>(){
        @Override
        public int compare(PCB o1, PCB o2){return o1.getPriority() - o2.getPriority();}
        });
        return readyQueue.get(0);
    }
}