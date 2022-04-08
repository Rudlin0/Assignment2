public class Job {
    private int priority = 0,       // Priority of this Job.
            length = 1,             // Length of this Job.
            timeWaiting = 0,        // # of time slices this Job has waited to execute.
            timeRemaining = length; // Time remaining to execute this Job.
    private String name = "";       // Name of this Job.

    public Job(int priority, int length, int timeWaiting, String name) {
        this.priority = priority;
        this.length = length;
        this.timeWaiting = timeWaiting;
        this.name = name;
        this.timeRemaining = length;
    }

    /**
     * Returns this Job's priority value.
     * 
     * @return int
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority value of this Job.
     * 
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns this Job's length value.
     * 
     * @return int
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length value of this Job.
     * 
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns this Job's timeWaiting value.
     * 
     * @return int
     */
    public int getTimeWaiting() {
        return timeWaiting;
    }

    /**
     * Sets the timeWaiting value of this Job.
     * 
     * @param timeWaiting
     */
    public void setTimeWaiting(int timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    /**
     * Returns this Job's timeRemaining value.
     * 
     * @return int
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Sets the timeRemaining value of this Job.
     * 
     * @param timeRemaining
     */
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    /**
     * Returns this Job's name as a String.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name String value of this Job.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
