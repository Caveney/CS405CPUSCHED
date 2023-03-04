public class IO {
    public static void execute(PCB process, int ioBurst) {
        //reduce io burst length
        process.setState("WAITING");
        process.setCurIO(process.getCurIO() - ioBurst);
    }
}
