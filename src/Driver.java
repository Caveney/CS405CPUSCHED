import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver {
    public static void main(String[] args){
        try{
            Scanner in = new Scanner(new File("src/proc.txt"));
            String alg = in.nextLine(); //first line has sched alg
            //while loop to read list of processes
            List<PCB> queue = new ArrayList<>();
            String line;
            int id = 0;
            while(in.hasNextLine()) {
                line = in.nextLine(); //grabs the pieces
                String[] arr = arr[0];
                int arrivalTime = Integer.parseInt(arr[1]);
                int cpuBurst = Integer.parseInt(arr[2]); // needs to be potentially multiple
                int priority = Integer.parseInt(arr[3]); //prob needs to be next int
                //needs io burst and cpu burst lists prob
                PCB pcb = new PCB(name, id++, arrivalTime, cpuBurst, priority);
                queue.add(pcb);
            }

            ScehdulingAlgorithm scheduler = null;
            switch (alg.toUpperCase()) {
                case "FCFS-2":
                    scheduler = new FCFS-2(queue);
                    break;
                case "SJF":
                    scheduler = new SJF(queue);
                    break;
                case "PS":
                    scheduler = new PS(queue);
                    break;
            }
            scheduler.schedule();
        } catch (FileNotFoundException e){
                e.printStackTrace();
        }
    }
}