public class CPU {
    public static void execute(PCB process, int cpuBurst){
        //reduce cpu burst length
        process.setCurCPU(process.getCurCPU() - cpuBurst);
    }
}