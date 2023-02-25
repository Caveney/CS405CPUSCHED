import java.util.List;

public class FCFS extends SchedulingAlgorithm {
    public FCFS(List<PCB> queue){
        super("FCFS", queue);
      }
      
      @Override
      public PCB pickNextProcess(){
          return readyQueue.get(0);
      }
}