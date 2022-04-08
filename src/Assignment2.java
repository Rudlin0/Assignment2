/*  
 * Description: Assignment 2
 * Author: Rudy Liljeberg
 * Date: 4/1/22
 * Bugs: None that I know of.
 * Reflection: This Assignment was very enjoyable to design and (for the most part) implement.
 *             Writing processes out in pseudocode beforehand decreased my debug time exponentially.
 *             Looking forward to the next one!
 */

public class Assignment2 {

    /**
     * Calls processInstructions().
     * 
     * @param args Not used
     * @throws Exception Not used.
     */
    public static void main(String[] args) throws Exception {
        new CPUScheduler("jobs.txt", 2); // Calls new instance of CPUScheduler.
    }
}