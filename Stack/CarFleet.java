import java.util.Arrays;
/*
    Car Fleet Logic:

    1. Combine position and speed into Pair objects:
    Each car is represented as (position, speed).

    2. Sort cars in descending order of position:
    - Process cars from closest to target → farthest.
    - This ensures when we process a car, we already know the fate of cars ahead.

    3. For each car, compute time to reach target:
    time = (target - position) / speed
    - Use double to avoid precision loss.

    4. Maintain a stack (or just track last fleet time):
    - Each value represents the time taken by a fleet to reach the target.

    5. For each car:
    a) If no fleet exists yet || If current car's time > last fleet time:
        → Create first fleet (push time)
        → Current car is slower
        → Cannot catch the fleet ahead
        → Forms a new fleet (push time)

    c) If current car's time <= last fleet time:
        → Current car is faster or equal
        → Will catch the fleet ahead
        → Becomes part of that fleet (do nothing)

    6. Final answer:
    - Number of fleets = number of elements in stack = top + 1

    Key Insight:
    - Fleets are determined by comparing times, not positions.
    - A faster car behind merges into a slower fleet ahead.
    - Stack (or variable) stores increasing fleet times.
*/
class Pair{
    int first,second;
    Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
}
class CarFleet{
    public int carFleet(int target,int[] position,int[] speed)
    {
        Pair[] arr = new Pair[position.length];
        for(int i=0;i<position.length;i++)
        {
            arr[i] = new Pair(position[i],speed[i]);
        }
        Arrays.sort(arr,(a,b)-> Integer.compare(b.first,a.first));

        double[] stack = new double[position.length];
        int top = -1;
        for(Pair pair : arr)
        {
            double reachingTime = (double)(target - pair.first)/pair.second;
            if(top==-1 || stack[top] < reachingTime)
                stack[++top] = reachingTime;
        }
        return top+1;
    }
    public static void main(String[] args)
    {
        int[] position={0,2,4};
        int[] speed = {4,2,1};
        int target=100;

        CarFleet obj = new CarFleet();
        int ans = obj.carFleet(target,position,speed);
        System.out.println(ans);
    }
}