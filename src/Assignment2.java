/*  
 * Description: Assignment 2
 * Author: Rudy Liljeberg
 * Date: 4/1/22
 * Bugs: None that I know of.
 * Reflection: Discuss any problems you had with the lab, and how you would rate 
 * it (e.g., easy, hard, insanely difficult, etc.) 
*/ 

import net.datastructures.PriorityQueue;
import net.datastructures.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import net.datastructures.ArrayQueue;
import net.datastructures.HeapPriorityQueue;

public class Assignment2 {
    
    /** 
     * Calls processInstructions().
     * @param args Not used
     * @throws Exception Not used.
     */
    public static void main(String[] args) throws Exception {
        processInstructions();
    }

    public static void processInstructions() {
        PriorityQueue<Integer, Job> jobQueue = new HeapPriorityQueue<>(); // Queue of jobs to be scheduled.
        Scanner input = null;
        try {
            input = new Scanner(new File("jobs.txt"));     // Initialize Scanner to read from a file.
            while (!jobQueue.isEmpty() || input.hasNextLine()) {                // While there is another line to be read in...
                if (input.hasNextLine()) {
                    String instruction = input.nextLine();         // Set "instruction" to the next instruction
                                                                   // in the queue.
                    String[] instructionArray = instruction.split(" ");

                    if (!CPUScheduler.isInstructionMalformed(instructionArray)) { // If the instruction is not malformed...
                        Job job = CPUScheduler.createProcess(instructionArray);   // Create a new job out of the instruction.
                        jobQueue.insert(job.getPriority(), job);             // Add this job to the jobQueue,
                                                                         // with said job's priority as the key and
                                                                         // the job itself as the value.
                    }
                }
                jobQueue = CPUScheduler.executeTimeSlice(jobQueue);
            }
        } catch (FileNotFoundException e) {                 // If the filename is invalid...
            System.out.printf("Exception: FileNotFound\n"); // Print an exception notice to the user.
        } finally {
            input.close(); // Close Scanner.
        }
    }

    
    /** 
     * Read in the given file line-by-line, 
     * add each line to a queue as a String,
     * then return said queue.
     * @param filename Name of the file to be processed.
     * @return Queue<String> Queue of lines, 
     *                       with each line being a separate String in this queue.
     */
    public static String readTextFileLine(String filename) {
        Scanner input = null;                             // Establish Scanner.
        String line = "";
                                                          // from the file being read in.
        try {
            input = new Scanner(new File(filename));     // Initialize Scanner to read from a file.
            while (input.hasNextLine()) {                // While there is another line to be read in...
                textFileLines.enqueue(input.nextLine()); // Enqueue the current line to the ArrayQueue.
            }
        } catch (FileNotFoundException e) {                 // If the filename is invalid...
            System.out.printf("Exception: FileNotFound\n"); // Print an exception notice to the user.
        } finally {
            input.close(); // Close Scanner.
        }

        return textFileLines; // Returns the ArrayQueue of lines read in from the file.
    }
}
