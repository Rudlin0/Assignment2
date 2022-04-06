/*  
 * Description: Assignment 2
 * Author: Rudy Liljeberg
 * Date: 4/1/22
 * Bugs: None that I know of.
 * Reflection: Discuss any problems you had with the lab, and how you would rate 
 * it (e.g., easy, hard, insanely difficult, etc.) 
*/

public class Assignment2 {
    
    /** 
     * Calls processInstructions().
     * @param args Not used
     * @throws Exception Not used.
     */
    public static void main(String[] args) throws Exception {
        new CPUScheduler("jobs.txt", 2);
    }
}
