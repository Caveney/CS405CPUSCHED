import java.util.ArrayList;
import java.util.List;

public abstract class SchedulingAlgorithm {
  protected String name; //scheduling algorithm name
  protected List<PCB> allProcs; //initial list of process
  protected List<PCB> readyQueue; //ready queue of ready process
  protected List<PCB> finishedProcs; //list of terminated process
  protected PCB curProcess;//current process
  protected int systemTime; //system time or simulation time steps
  
  public SchedulingAlgorithm(String name, List<PCB> queue){
    this.name = name;
    this.allProcs = queue;
    this.readyQueue = new ArrayList<>();
    this.finishedProcs = new ArrayList<>();
    }
    
    public void schedule(){
        System.out.println(name+"\n");
        
        while(allProcs.isEmpty() || !readyQueue.isEmpty()){
          
          System.out.println(systemTime);
          
          for(PCB proc : allProcs)
              if(proc.getArrivalTime() == systemTime) readyQueue.add(proc);
           allProcs.removeAll(readyQueue);
           
          curProcess = pickNextProcss();
          
          print();
          
          if(curProcess.getStartTime() < 0)
              curProcess.setStartTime(systemTime);
          CPU.execute(curProcess, 1);
          
          for(PCB proc : readyQueue)
              if(proc != curProcess) proc.increaseWaitingTime(1);
          
          systemTime += 1;
          
          if(curProcess.getCpuBurst() == 0){
              curProcess.setFinishTime(systemTime);
              readyQueue.remove(curProcess);
              finishedProcs.add(curProcess);
              
              System.out.println("Process " + name + "terminated at " + systemTime +
                                  ", startTime =" curProcess.getStartTime() + 
                                  ", turnaroundTime = " + curProcess.geTurnaroundTime()
                                  + ", waitingTime = " + curProcess.getWaitingTime());
                                  
          }
          System.out.println();
          
    }
    
    public abstract PCB pickNextProcess();
    
    public void print(){
    
      System.out.println("CPU: " + curProcess.toString());
      System.out.println("Ready queue: {");
      for(PCB proc : readyQueue)
          System.out.println(proc.getName() + " ");
       System.out.println("}");
     
    
    
    }
}
