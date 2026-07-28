import java.util.Map;
import java.util.HashMap;

/*
    Approach & Intuition:

    1. Problem Understanding:
    - We have 3 types of garbage: 'M', 'P', 'G'
    - Each piece of garbage takes 1 unit time to pick
    - travel[i] represents time to move from house i → i+1
    - Each truck starts from house 0 and only travels as far as needed

    2. Key Idea:
    Total Time =
        (Total garbage count)
        + (Travel time for M truck)
        + (Travel time for P truck)
        + (Travel time for G truck)

    3. Step-by-step Strategy:

    (a) Count total garbage:
        - Sum up lengths of all strings in garbage[]
        - This gives total pickup time

    (b) Find last occurrence of each type:
        - Traverse garbage[]
        - Track last index where 'M', 'P', and 'G' appear
        - This tells how far each truck must travel

    (c) Compute prefix sum of travel[]:
        - prefix[i] = total travel time from house 0 to house (i+1)
        - Helps us quickly get travel cost to any index

    (d) Add travel cost:
        - If last occurrence of a type is at index i:
                travel needed = prefix[i - 1]
        - Because to reach house i, we travel till (i-1)

        - If i == 0 → no travel needed

    4. Why prefix sum?
    - Avoid recomputing travel again and again
    - Gives O(1) access to travel cost

    5. Time Complexity:
    - O(n + total characters) → linear
    - Efficient for constraints up to 10^5

    6. Space Complexity:
    - O(n) for prefix array (can be optimized to O(1))

    7. Key Insight:
    - Trucks don’t need to visit all houses
    - Each truck only travels till its last required house
*/
class GarbageCollect{
    public int garbageCollection(String[] garbage,int[] travel)
    {
        int totalGarbage=0;
        for(int i=0;i<garbage.length;i++)
        {
            totalGarbage += garbage[i].length();
        }

        int lastM=0,lastG=0,lastP=0;
        for(int i=0;i<garbage.length;i++)
        {
            String curr = garbage[i];
            for(char ch : curr.toCharArray())
            {
                if(ch=='M') lastM=i;
                if (ch=='G') lastG=i;
                if(ch=='P') lastP=i;
            }
            
        }
        int[] travelPrefix = new int[travel.length];
        travelPrefix[0] = travel[0];
        for(int i=1;i<travel.length;i++)
        {
            travelPrefix[i] = travelPrefix[i-1]+travel[i];
        }

        int totalTime = totalGarbage;

        if (lastM > 0) totalTime += travelPrefix[lastM - 1];
        if (lastG > 0) totalTime += travelPrefix[lastG - 1];
        if (lastP > 0) totalTime += travelPrefix[lastP - 1];
        return totalTime;
    }
    public static void main(String[] args)
    {
        String[] garbage = {"G","P","GP","GG"};
        int[] travel = {2,4,3};
        GarbageCollect obj = new GarbageCollect();
        int ans = obj.garbageCollection(garbage,travel);
        System.out.println(ans);
    }
}