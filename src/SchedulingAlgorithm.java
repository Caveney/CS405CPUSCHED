import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class SchedulingAlgorithm {
    protected String name; // scheduling algorithm name
    protected List<PCB> allProcs; // the initial list of processes
    protected List<PCB> readyQueue; // ready queue of ready processes
    protected List<PCB> finishedProcs; // list of terminated processes
    protected List<PCB> ioQueue; //queue of IO
    protected PCB curProcess; // current selected process by the scheduler
    protected int systemTime = 0; // system time or simulation time steps
    protected int manualMode;
    int cpuRun = 0;
    int ioWait = 0;

    public SchedulingAlgorithm(String name, List<PCB> queue, int manualMode) {
        this.name = name;
        this.allProcs = queue;
        this.manualMode = manualMode;
        this.readyQueue = new ArrayList<>();
        this.finishedProcs = new ArrayList<>();
        this.ioQueue = new ArrayList<>();
    }

    public void schedule() throws IOException {
        System.out.println(name + "\n");
        Scanner in = new Scanner(System.in);
        FileOut out = new FileOut();
        out.createFile("data.txt");
        int t = 0;
        //runs until all queues are empty
        while (!allProcs.isEmpty() || !readyQueue.isEmpty() || !ioQueue.isEmpty()) {
            System.out.println(systemTime);
            for (PCB proc : allProcs)
                if (proc.getArrivalTime() == systemTime){
                    readyQueue.add(proc);
                    proc.setState("READY");
                    out.addLine("New Process added to the ready queue: " + proc.toString());
                }
            allProcs.removeAll(readyQueue);
            //when both queues are empty systemTime++
            if(readyQueue.isEmpty() && ioQueue.isEmpty()){
                systemTime++;
            }else {
                //checks
                if (t == 0){
                    curProcess = pickNextProcess();
                    t++;
                } else if (curProcess.getCurBurst() == "CPU") {
                    curProcess = pickNextProcess();
                }else{
                    curProcess = ioQueue.get(0);
                }

                print();
                if (curProcess.getStartTime() < 0)
                    curProcess.setStartTime(systemTime);
                //checks to see if its a cpu burst
                if (curProcess.getCurBurst() == "CPU") {
                    cpuRun += 1;
                    CPU.execute(curProcess, 1);
                    //checks to see current io burst has hit 0
                    if (curProcess.getCurCPU() == 0) {
                        //grabs new CPU burst if it exists
                        out.addLine("Process dispatched to the CPU: " + curProcess.toString());
                        curProcess.curCPUBurst();
                        //flags to the pcb that next burst is IO
                        if (curProcess.getCurIO() != 0) {
                            curProcess.flipCurBurst();
                            ioQueue.add(curProcess); //moves proccess to ioqueue
                            curProcess.setState("Terminated");
                            out.addLine("Process is terminated: " + curProcess.toString());
                            readyQueue.remove(curProcess);
                        }
                    }
                }
                //checks to see if its an IO burst
                if (curProcess.getCurBurst() == "IO") {
                    IO.execute(curProcess, 1);
                    //checks to see current io burst has hit 0
                    if (curProcess.getCurIO() == 0) {
                        out.addLine("Process dispatched to the IO: " + curProcess.toString());
                        //grabs new io burst if it exists
                        curProcess.curIOBurst();
                        //flags that the pcb that next burst is cpu
                        curProcess.flipCurBurst();
                        readyQueue.add(0, curProcess);
                        ioQueue.remove(curProcess);
                        out.addLine("Process dispatched to the the Ready Queue: " + curProcess.toString());

                    }
                }
                for (PCB proc : readyQueue)
                    if (proc != curProcess) proc.increaseWaitingTime(1);
                systemTime += 1;
                if (curProcess.getCpuBurst().size() == 0) {
                    curProcess.setFinishTime(systemTime);
                    readyQueue.remove(curProcess);
                    finishedProcs.add(curProcess);
                    double cpuUtil = (double) cpuRun/(double) systemTime;
                    System.out.println("Process " + curProcess.getName() + " terminated at " + systemTime
                            + ", startTime=" + curProcess.getStartTime()
                            + ", turnaroundTime = " + curProcess.getTurnAroundTime()
                            + ", waitingTime = " + curProcess.getWaitingTime()
                            + ", CPU Utilization = " + (cpuUtil)
                            + ", IO Wait Time = " + ioWait);
                    out.addLine("Process " + curProcess.getName() + " terminated at " + systemTime
                            + ", startTime=" + curProcess.getStartTime()
                            + ", turnaroundTime = " + curProcess.getTurnAroundTime()
                            + ", waitingTime = " + curProcess.getWaitingTime()
                            + ", CPU Utilization = " + (cpuUtil)
                            + ", IO Wait Time = " + ioWait);
                }
            }
                System.out.println();
            //manual through it
            if(manualMode == 1){
                System.out.println("Hit Right Arrow Key to move forward");
                in.nextLine();
            }
            }
        }
    public abstract PCB pickNextProcess();

    public void print() {
        if(curProcess.getState() == "RUNNING"){
            System.out.println("╭―――――――――――――╮");
            System.out.println("│ CPU 0: Running      │");
            System.out.println("╰―――――――――――――╯");
        }else {
            System.out.println("╭―――――――――――――╮");
            System.out.println("│ CPU 0: Idle         │");
            System.out.println("╰―――――――――――――╯");
        }
        if(curProcess.getState() == "WAITING"){
            System.out.println("┌──────────────┐");
            System.out.println("│I/O 0:Running │");
            System.out.println("└──────────────┘");
        }else {
            System.out.println("┌──────────────┐");
            System.out.println("│I/O 0:Idle    │");
            System.out.println("└──────────────┘");
        }

        System.out.println(curProcess.toString());
        System.out.print("Ready queue: [");
        for(PCB proc : readyQueue)
            System.out.print(proc.getName() + ": Priority" + proc.getPriority() + ", ");
        System.out.println("]");
        System.out.print("IO Queue: [");
        for(PCB proc : ioQueue)
            System.out.print(proc.getName() + ": " + proc.getState());
        System.out.println("]");
    }




}
