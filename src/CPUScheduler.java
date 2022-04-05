import net.datastructures.HeapPriorityQueue;
import net.datastructures.PriorityQueue;

public class CPUScheduler {
    /** 
     * Executes one time slice for the job with the highest priority in the queue at the time.
     * @param jobsNotYetProcessed A queue of the jobs needing to be processed.
     * @return PriorityQueue<Integer, Job> A queue of the jobs yet to be processed/need more time to process.
     */
    public static PriorityQueue<Integer, Job> executeTimeSlice(PriorityQueue<Integer, Job> jobsNotYetProcessed) {
        Job currentJob = jobsNotYetProcessed.removeMin().getValue(); // Remove the highest priority job
                                                                     // in the queue from said queue and
                                                                     // set "job" to that entry's value.    
        PriorityQueue<Integer, Job> processedJobs = new HeapPriorityQueue<>();
        while(!jobsNotYetProcessed.isEmpty()) {                   // While there are jobs yet to be processed
                                                                  // (other than currentJob, of course)...
            int jobPriority = jobsNotYetProcessed.min().getKey(); // Get the priority of this job.
            Job job = jobsNotYetProcessed.removeMin().getValue(); // Get the value of this entry (aka the job itself).
            job.setTimeWaiting(job.getTimeWaiting() + 1);         // Increment this job's timeWaiting by 1.
            processedJobs.insert(jobPriority, job);               // Insert this job as the value of an entry
                                                                  // in processedJobs, with this job's priority
                                                                  // being the key.
        }
        currentJob.setTimeRemaining(currentJob.getTimeRemaining() - 1); // Decrement the timeRemaining for this job
                                                                        // by 1.

        displayTimeSlice(currentJob);

        if (currentJob.getTimeRemaining() < currentJob.getLength()) {   // If there is more yet to do with currentJob...
            processedJobs.insert(currentJob.getPriority(), currentJob); // Insert this job into processedJob,
                                                                        // with currentJob's priority being the key.
        }

        return processedJobs;
    }

    /**
     * Displays info regarding the job currently being executed by executeTimeSlice(),
     * specifically the name, priority #, and time remaining of said job.
     * @param currentJob Job currently being executed by executeTimeSlice().
     */
    public static void displayTimeSlice(Job currentJob) {
        System.out.printf("%s (priority: %d, time remaining: %d)\n", currentJob.getName(), 
                                                                     currentJob.getPriority(),
                                                                     currentJob.getTimeRemaining());
    }

    
    /** 
     * Creates a new job/process out of a non-malformed instruction.
     * @param instruction
     * @return Job
     */
    public static Job createProcess(String instruction) {
        return null;
    }

    
    /** 
     * @param instruction
     * @return boolean
     */
    public static boolean isInstructionMalformed(String instruction) {
        return false;
    }
}
