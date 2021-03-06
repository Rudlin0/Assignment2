import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import net.datastructures.HeapPriorityQueue;
import net.datastructures.PriorityQueue;

public class CPUScheduler {
    static PriorityQueue<Integer, Job> jobQueue = new HeapPriorityQueue<>(); // Queue of jobs to be scheduled.
    static int maxWaitingTime = 0; // Maximum waiting time for a given Job in time slices, set to 0 by default.

    public CPUScheduler(String filename, int maxWaitingTime) {
        CPUScheduler.maxWaitingTime = maxWaitingTime; // Sets class var. maxWaitingTime to value of
                                                      // arg. maxWaitingTime.
        processInstructions(filename); // Process the instructions from file "filename".
    }

    /**
     * Processes the instructions from file "filename".
     * 
     * @param filename Name of the file to be read in.
     */
    public static void processInstructions(String filename) {
        Scanner input = null; // Initilize Scanner intially to null.
        try {
            input = new Scanner(new File(filename)); // Initialize Scanner to read from a file.
            while (!jobQueue.isEmpty() || input.hasNextLine()) { // While there is another line to be read in...
                if (input.hasNextLine()) { // If there are lines in the target file left
                                           // to read...
                    String[] instructionArray = input.nextLine().split(" "); // Split this instruction into a
                                                                             // String array.

                    if (!isInstructionMalformed(instructionArray)) { // If the instruction is not malformed...
                        Job job = createProcess(instructionArray);   // Create a new job out of the instruction.
                        jobQueue.insert(job.getPriority(), job);     // Add this job to the jobQueue,
                                                                     // with said job's priority as the key and
                                                                     // the job itself as the value.
                    }
                }
                jobQueue = executeTimeSlice(); // Execute one time slice, and set the jobQueue to the result.
            }
        } catch (FileNotFoundException e) {                 // If the filename is invalid...
            System.out.printf("Exception: FileNotFound\n"); // Print an exception notice to the user.
        } finally {
            input.close(); // Close Scanner.
        }
    }

    /**
     * Executes one time slice for the job with the highest priority in the queue at
     * the time.
     * 
     * @return PriorityQueue<Integer, Job> A queue of the jobs yet to be
     *         processed/need more time to process.
     */
    public static PriorityQueue<Integer, Job> executeTimeSlice() {
        Job currentJob = jobQueue.removeMin().getValue(); // Remove the highest priority job
                                                          // in the queue from said queue and
                                                          // set "job" to that entry's value.
        PriorityQueue<Integer, Job> processedJobs = new HeapPriorityQueue<>();
        while (!jobQueue.isEmpty()) { // While there are jobs yet to be processed
                                      // (other than currentJob, of course)...
            int jobPriority = jobQueue.min().getKey(); // Get the priority of this job.
            Job job = jobQueue.removeMin().getValue(); // Get the value of this entry (aka the job itself).
            job.setTimeWaiting(job.getTimeWaiting() + 1); // Increment this job's timeWaiting by 1.
            if (job.getTimeWaiting() > maxWaitingTime) { // If the job's waiting time has exceeded its
                                                         // max waiting time...
                if (job.getPriority() > -20) { // If this job's priority is greater than -20...
                    job.setPriority(job.getPriority() - 1); // Increment this job's priority by 1.
                }

                job.setTimeWaiting(0); // Reset the time waiting for this job to 0.
            }
            processedJobs.insert(jobPriority, job); // Insert this job as the value of an entry
                                                    // in processedJobs, with this job's priority
                                                    // being the key.
        }
        currentJob.setTimeRemaining(currentJob.getTimeRemaining() - 1); // Decrement the timeRemaining for this job
                                                                        // by 1.

        displayTimeSlice(currentJob); // Display currentJob as it stands after having been processed.

        if (currentJob.getTimeRemaining() > 0) { // If there is more yet to do with currentJob...
            processedJobs.insert(currentJob.getPriority(), currentJob); // Insert this job into processedJob,
                                                                        // with currentJob's priority being the key.
        }

        return processedJobs; // Return list of remaining/processed jobs.
    }

    /**
     * Displays info regarding the job currently being executed by
     * executeTimeSlice(),
     * specifically the name, priority #, and time remaining of said job.
     * 
     * @param currentJob Job currently being executed by executeTimeSlice().
     */
    public static void displayTimeSlice(Job currentJob) {
        System.out.printf("%s (priority: %d, time remaining: %d)\n", currentJob.getName(),
                currentJob.getPriority(),
                currentJob.getTimeRemaining());
    }

    /**
     * Creates a new job/process out of a non-malformed instruction.
     * 
     * @param instruction
     * @return Job
     */
    public static Job createProcess(String[] instructionArray) {
        String name = instructionArray[2]; // Name of the instruction always starts at index=2/

        for (int i = 3; i < instructionArray.length - 6; i++) {
            name = name.concat(" " + instructionArray[i]); // Add remaining words to the name until we're past it.
        }

        return new Job(Integer.parseInt(instructionArray[instructionArray.length - 1]),
                Integer.parseInt(instructionArray[instructionArray.length - 4]),
                0,
                name); // Returns a new Job with given parameters.
    }

    /**
     * Checks whether or not a given instruction is properly formatted or not.
     * 
     * @param instruction The instruction in question, given as an array of Strings.
     * @return boolean true = instruction is malformed, and false = instruction is
     *         not malformed.
     */
    public static boolean isInstructionMalformed(String[] instructionArray) {
        int arrayLength = instructionArray.length; // Length of the String array represnting the current
                                                   // instruction.

        try {
            int jobLength = Integer.parseInt(instructionArray[arrayLength - 4]); // Length of this instruction.
            int jobPriority = Integer.parseInt(instructionArray[arrayLength - 1]); // Priority of this instruction.
            if (!instructionArray[0].equals("add") ||
                    !instructionArray[1].equals("job") ||
                    !instructionArray[arrayLength - 6].equals("with") ||
                    !instructionArray[arrayLength - 5].equals("length") ||
                    (jobLength < 1 || jobLength > 100) ||
                    (jobPriority < -20 || jobPriority > 19) ||
                    !instructionArray[arrayLength - 3].equals("and") ||
                    !instructionArray[arrayLength - 2].equals("priority")) { // If the instruction is not properly
                                                                             // formatted and meets the constraints
                                                                             // of an instruction as specified in
                                                                             // the handout...
                return true; // Then the instruction is malformed.
            }
        } catch (NumberFormatException e) { // instructionArray[arrayLength - 4] (jobLength) or
                                            // instructionArray[arrayLength - 1] (jobPriority)
                                            // does not contain just an integer value.
            return true; // The instruction is malformed.
        }

        return false; // Instruction is not malformed.
    }
}
