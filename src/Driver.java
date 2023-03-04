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
                int priority = Integer.parseInt(arr[2]);
                ArrayList<Integer> cpuBursts = new ArrayList<Integer>();
                ArrayList<Integer> ioBursts = new ArrayList<Integer>();
                int  i = 3;
                while(i < arr.length) {
                    cpuBursts.add(Integer.parseInt(arr[i]));
                    i++;
                    if(i < arr.length){
                        ioBursts.add(Integer.parseInt(arr[i]));
                        i++;
                    }
                }
                PCB pcb = new PCB(name, id++, arrivalTime, cpuBursts, ioBursts, priority);
                queue.add(pcb);
            }

            SchedulingAlgorithm scheduler = null;
            switch (alg.toUpperCase()) {
                case "FCFS":
                    scheduler = new FCFS(queue);
                    break;
                case "SJF":
                    scheduler = new SJF(queue);
                    break;
                case "PS":
                    scheduler = new PriorityScheduling(queue);
                    break;
            }
            scheduler.schedule();
        } catch (FileNotFoundException e){
                e.printStackTrace();
        }
    }
}