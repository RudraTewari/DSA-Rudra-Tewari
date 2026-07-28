import java.util.*;

class Job {
        int difficulty;
        int profit;

        Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }
// Class name chosen according to the problem
public class MostProfitAssigningWork {
    
    /*
     * LeetCode Style Function
     * Problem: Most Profit Assigning Work
     */
    public static int binarySearch(int n, Job[] jobs,int ability){
        int low=0,high=n-1;
        int mid=0,ans=-1;

        while(low<=high){
            mid=(low+high)>>1;
            if(ability >= jobs[mid].difficulty){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    } 
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        int n= difficulty.length;
        Job[] jobs = new Job[n];
        for(int i=0;i<n;i++){
            jobs[i] = new Job(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs,(a,b)->a.difficulty-b.difficulty);
        int[] bestProfit = new int[n];
        bestProfit[0]=jobs[0].profit;
        for(int i=1;i<n;i++){
            bestProfit[i] = Math.max(bestProfit[i-1], jobs[i].profit);
        }
        
        int totalProfit=0;
        for(int ability : worker){
            int idx = binarySearch(n,jobs,ability);
            if(idx != -1){
                totalProfit+=jobs[idx].profit;
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {

        MostProfitAssigningWork solution = new MostProfitAssigningWork();

        // Sample Input
        int[] difficulty = {2, 4, 6, 8, 10};
        int[] profit = {10, 20, 30, 40, 50};
        int[] worker = {4, 5, 6, 7};

        // Function Call
        int result = solution.maxProfitAssignment(difficulty, profit, worker);

        // Output
        System.out.println("Maximum Profit: " + result);

        /*
         * Expected Output:
         * Maximum Profit: 100
         */
    }
}