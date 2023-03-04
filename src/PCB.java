import java.util.ArrayList;

public class PCB {

    //rep of each process
    private String name;
    private int id;
    private int arrivalTime;
    private ArrayList<Integer> cpuBurst;
    private ArrayList<Integer> ioBurst;
    private int curCPU;
    private int curIO;

    private int priority;

    //stats of process exec
    private int startTime, finishTime, turnAroundTime, waitingTime;

    //constructors
    public PCB(String name, int id, int arrivalTime, ArrayList<Integer> cpuBurst, ArrayList<Integer> ioBurst, int priority){
        super();
        this.name = name;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
        this.curCPU = cpuBurst.get(0);
        this.ioBurst = ioBurst;
        this.curIO = ioBurst.get(0);
        this.priority = priority;
        this.startTime = -1;
        this.finishTime = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getCpuBurst() {
        return cpuBurst;
    }

    public void setCpuBurst(ArrayList<Integer> cpuBurst) {
        this.cpuBurst = cpuBurst;
    }

    public ArrayList<Integer> getIoBurst() {
        return ioBurst;
    }

    public void setIoBurst(ArrayList<Integer> ioBurst) {
        this.ioBurst = ioBurst;
    }

    public int getCurCPU() {
        return curCPU;
    }

    public void setCurCPU(int curCPU) {
        this.curCPU = curCPU;
    }

    public int getCurIO() {
        return curIO;
    }

    public void setCurIO(int curIO) {
        this.curIO = curIO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
        this.turnAroundTime = finishTime - arrivalTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void increaseWaitingTime(int burst){
        //Increase the waitingTime with variable burst
        this.waitingTime += burst;
    }

    public void curCPUBurst(){
        cpuBurst.remove(0);
        this.curCPU = cpuBurst.get(0);
    }

    public void curIOBurst(){
        ioBurst.remove(0);
        this.curIO = ioBurst.get(0);
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Process " +
                "[name= '" + name + '\'' +
                ", id= " + id +
                ", arrivalTime= " + arrivalTime +
                ", cpuBurst= " + cpuBurst +
                ", ioBurst= " + ioBurst +
                ", priority= " + priority + "]";
    }
}
