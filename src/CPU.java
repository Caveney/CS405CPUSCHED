public class CPU {
    public static void execute(PCB process, int cpuBurst){
        //reduce cpu burst length
        process.setState("RUNNING");
        process.setCurCPU(process.getCurCPU() - cpuBurst);
    }
}