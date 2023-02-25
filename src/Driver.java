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
                String[] arr = line.split(",\s*");
                String name = arr[0];
                int arrivalTime = Integer.parseInt(arr[1]);
                int cpuBurst = Integer.parseInt(arr[2]); // needs to be potentially multiple
                int priority = Integer.parseInt(arr[3]); //prob needs to be next int
                //needs io burst and cpu burst lists prob
                PCB pcb = new PCB(name, id++, arrivalTime, cpuBurst, priority);
                queue.add(pcb);
            }

            SchedulingAlgorithm scheduler = switch (alg.toUpperCase()) {
                case "FCFS2.java" -> new FCFS(queue);
                case "SJF.java" -> new SJF(queue);
                case "PS" -> new PriorityScheduling(queue);
                default -> null;
            };
            scheduler.schedule();
        } catch (FileNotFoundException e){
                e.printStackTrace();
        }
    }
}