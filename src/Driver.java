import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver {
    public static void main(String[] args){
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Manual(1) or Automatic(0): ");
            int mode = input.nextInt();
            System.out.println("Enter File Name: ");
            String file = input.next();
            Scanner in = new Scanner(new File("src/"+ file));
            System.out.println("Enter PS, FCFS, SJF: ");
            String alg = input.next(); //first line has sched alg
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
                //lists to hold cpu and io bursts
                ArrayList<Integer> cpuBursts = new ArrayList<Integer>();
                ArrayList<Integer> ioBursts = new ArrayList<Integer>();
                //adds the bursts to each list
                int  i = 3;
                while(i < arr.length) {
                    cpuBursts.add(Integer.parseInt(arr[i]));
                    i++;
                    //checks that there is another burst
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
                    scheduler = new FCFS(queue, mode);
                    break;
                case "SJF":
                    scheduler = new SJF(queue, mode);
                    break;
                case "PS":
                    scheduler = new PriorityScheduling(queue, mode);
                    break;
            }
            scheduler.schedule();
        } catch (FileNotFoundException e){
                e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}