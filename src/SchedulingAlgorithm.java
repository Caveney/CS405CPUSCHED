import java.util.ArrayList;
import java.util.List;

public abstract class SchedulingAlgorithm {
    protected String name; // scheduling algorithm name
    protected List<PCB> allProcs; // the initial list of processes
    protected List<PCB> readyQueue; // ready queue of ready processes
    protected List<PCB> finishedProcs; // list of terminated processes
    protected PCB curProcess; // current selected process by the scheduler
    protected int systemTime; // system time or simulation time steps

    public SchedulingAlgorithm(String name, List<PCB> queue) {
        this.name = name;
        this.allProcs = queue;
        this.readyQueue = new ArrayList<>();
        this.finishedProcs = new ArrayList<>();
    }

    public void schedule() {
        System.out.println(name + "\n");
        while (!allProcs.isEmpty() || !readyQueue.isEmpty()) {
            System.out.println(systemTime);
            for (PCB proc : allProcs)
                if (proc.getArrivalTime() == systemTime) readyQueue.add(proc);
            allProcs.removeAll(readyQueue);
            curProcess = pickNextProcess();
            print();
            if (curProcess.getStartTime() < 0)
                curProcess.setStartTime(systemTime);
            CPU.execute(curProcess, 1);
            for (PCB proc : readyQueue)
                if (proc != curProcess) proc.increaseWaitingTime(1);
            systemTime += 1;
            if (curProcess.getCurCPU() == 0) {
                curProcess.curCPUBurst();
                if (curProcess.getCpuBurst().size() == 0) {
                    curProcess.setFinishTime(systemTime);
                    readyQueue.remove(curProcess);
                    finishedProcs.add(curProcess);
                    System.out.println("Process " + curProcess.getName() + " terminated at " + systemTime
                            + ", startTime=" + curProcess.getStartTime()
                            + ", turnaroundTime = " + curProcess.getTurnAroundTime()
                            + ", waitingTime = " + curProcess.getWaitingTime());
                }
                while (curProcess.getCurIO() >= 0) {
                    IO.execute(curProcess, 1);
                    if (curProcess.getCurIO() == 0) {
                        curProcess.curIOBurst();
                        if (curProcess.getIoBurst().size() == 0) {
                            curProcess.setFinishTime(systemTime);
                            readyQueue.remove(curProcess);
                            finishedProcs.add(curProcess);
                            System.out.println("Process " + curProcess.getName() + " terminated at " + systemTime
                                    + ", startTime=" + curProcess.getStartTime()
                                    + ", turnaroundTime = " + curProcess.getTurnAroundTime()
                                    + ", waitingTime = " + curProcess.getWaitingTime());
                        }

                    }
                }
                System.out.println();
            }
        }
    }
    public abstract PCB pickNextProcess();

    public void print() {
        System.out.println("CPU: " + curProcess.toString());
        System.out.print("Ready queue: [");
        for(PCB proc : readyQueue)
            System.out.print(proc.getName() + " ");
        System.out.println("]");
    }




}
