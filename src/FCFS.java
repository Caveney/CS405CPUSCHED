import java.util.List;

public class FCFS extends SchedulingAlgorithm {
    public FCFS(List<PCB> queue, int manualMode){
        super("FCFS", queue, manualMode);
      }
      
      @Override
      public PCB pickNextProcess(){
          return readyQueue.get(0);
      }
}
