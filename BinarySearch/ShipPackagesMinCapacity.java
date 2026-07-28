/*
    INTUITIVE APPROACH — Capacity to Ship Packages Within D Days

    🧠 Problem Understanding:
    We are given an array of package weights and a fixed number of days.
    We must ship all packages in order, and each day we can carry up to a certain "capacity".

    👉 Goal:
    Find the MINIMUM capacity such that all packages can be shipped within given days.

    ------------------------------------------------------------

    💡 Key Intuition:

    Think of capacity as your "answer".
    - If capacity is TOO SMALL → you need more days ❌
    - If capacity is LARGE ENOUGH → you can finish within given days ✅

    So the problem becomes:
    👉 "What is the smallest capacity that works?"

    ------------------------------------------------------------

    🔍 Observations:

    1. Minimum possible capacity:
    = max(weights)
    Why? Because you must at least carry the heaviest package.

    2. Maximum possible capacity:
    = sum(weights)
    Why? Because you can ship everything in one day.

    👉 So search space:
    [max(weights) → sum(weights)]

    ------------------------------------------------------------

    ⚙️ Strategy (Binary Search on Answer):

    We try a capacity (mid) and check:
    👉 Can we ship all packages within 'days'?

    This is done using canDo().

    ------------------------------------------------------------

    📦 How canDo() works:

    Simulate the shipping process:

    - Start with day = 1 and load = 0
    - Add packages one by one
    - If adding a package exceeds capacity:
        → move to next day
        → reset load
    - Continue

    At the end:
    - If totalDays <= given days → capacity is valid ✅
    - Else → capacity too small ❌

    ------------------------------------------------------------

    📈 Behavior of function:

    capacity ↑  → easier to ship → fewer days needed

    So pattern looks like:
    F F F F T T T T
            ↑
    answer (minimum valid capacity)

    ------------------------------------------------------------

    🔁 Binary Search Decision:

    If canDo(mid) == true:
        → this capacity works
        → try smaller (move left)
    Else:
        → too small
        → increase capacity (move right)

    ------------------------------------------------------------

    🎯 Final Goal:

    Find the FIRST TRUE (minimum valid capacity)

    ------------------------------------------------------------

    🧠 Mental Model:

    Think like this:
    "I am guessing a capacity. If it works, I try smaller.
    If it fails, I increase it."

    ------------------------------------------------------------

    🔥 Pattern Recognition:

    This problem is identical to:
    - Allocate Books
    - Painter’s Partition

    All follow:
    👉 "Minimize the maximum load"

    ------------------------------------------------------------

    ⚡ One-line takeaway:

    Binary search the minimum capacity where shipping becomes possible.
*/
class ShipPackagesMinCapacity{
    public static boolean canDo(int capacity,int[] weights,int days)
    {
        int totalDays=1;
        int currentLoad=0;
        for(int weight : weights){
            // if adding this exceeds capacity → new day
            if(currentLoad+weight > capacity){
                totalDays++;
                currentLoad=0;
            }
            currentLoad+=weight;
        }
        return totalDays <= days;
    }
    public int shipWithinDays(int[] weights,int days){
        int start=Integer.MIN_VALUE; 
        int end=0,ans=0;
        for(int val : weights){
            start=Math.max(start,val);  // Atleast we have to carry the heaviest weight
            end+=val;  // Sum of total Weight
        }
        int mid=0;
        while(start <= end){
            mid=start+(end-start)/2;

            if(canDo(mid,weights,days)){
                ans=mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        int[] weights = {1,2,3,1,1};
        int days = 4;

        ShipPackagesMinCapacity obj = new ShipPackagesMinCapacity();
        int ans = obj.shipWithinDays(weights,days);
        System.out.println(ans);
    }
}