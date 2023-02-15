public class PCB {

    //rep of each process
    private String name;
    private int id;
    private int arrivalTime;
    private int cpuBurst;
    private int priority;

    //stats of process exec
    private int startTime, finishTime, turnAroundTime, waitingTime;

    //constructors
    public PCB(String name, int id, int arrivalTime, int cpuBurst, int priority){
        super();
        this.name = name;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
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

    public int getCpuBurst() {
        return cpuBurst;
    }

    public void setCpuBurst(int cpuBurst) {
        this.cpuBurst = cpuBurst;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Process " +
                "[name='" + name + '\'' +
                ", id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", cpuBurst=" + cpuBurst +
                ", priority=" + priority + "]";
    }
}
