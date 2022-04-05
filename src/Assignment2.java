import net.datastructures.PriorityQueue;
import net.datastructures.Queue;
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
        Queue<String> instructionQueue = readTextFile("jobs.txt");
        PriorityQueue<Integer, Job> jobQueue = new HeapPriorityQueue<>(); // Queue of jobs to be scheduled.
        while (!instructionQueue.isEmpty()) {                  // While there are instructions to be processed...
            String instruction = instructionQueue.dequeue();         // Set "instruction" to the next instruction
                                                                     // in the queue.
            if (!CPUScheduler.isInstructionMalformed(instruction)) { // If the instruction is not malformed...
                Job job = CPUScheduler.createProcess(instruction);   // Create a new job out of the instruction.
                jobQueue.insert(job.getPriority(), job);             // Add this job to the jobQueue,
                                                                     // with said job's priority as the key and
                                                                     // the job itself as the value.
            }
        }
        while(!jobQueue.isEmpty()) {                 // While there are jobs left to execute...
            CPUScheduler.executeTimeSlice(jobQueue); // Execute one time slice with
                                                     // the current queue of job entries.
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
    public static Queue<String> readTextFile(String filename) {
        return null;
    }
}
