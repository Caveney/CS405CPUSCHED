public class IO {
    public static void execute(PCB process, int ioBurst) {
        //reduce cpu burst length
        process.setCurIO(process.getCurIO() - ioBurst);
    }
}
