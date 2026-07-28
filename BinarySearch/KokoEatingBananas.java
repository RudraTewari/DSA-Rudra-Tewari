import java.util.Arrays;
/*
    INTUITIVE APPROACH — Koko Eating Bananas (Minimum Eating Speed)

    🧠 Problem Understanding:
    We are given piles of bananas and total hours (h).
    Koko can eat at a fixed speed (bananas/hour), and in each hour she picks ONE pile.

    👉 Goal:
    Find the MINIMUM eating speed such that all bananas are eaten within h hours.

-----------------------------------------------------------------

    💡 Key Intuition:

    Think of "speed" as your answer.
    - If speed is TOO SLOW → takes more hours ❌
    - If speed is FAST ENOUGH → finishes within h hours ✅

    So the problem becomes:
    👉 "What is the smallest speed that works?"

----------------------------------------------------------------

    🔍 Observations:

    1. Minimum possible speed:
    = 1
    Why? She must eat at least 1 banana/hour.

    2. Maximum possible speed:
    = max(piles)
    Why? She can finish the largest pile in 1 hour.

    👉 So search space:
    [1 → max(piles)]

-----------------------------------------------------------------

    ⚙️ Strategy (Binary Search on Answer):

    We try a speed (mid) and check:
    👉 Can Koko finish within h hours?

    This is done using canDo().

-----------------------------------------------------------------

    🍌 How canDo() works:

    For each pile:
    - Time required = ceil(pile / speed)
    (because partial hours count as full)

    Add time for all piles:
    - totalHours = sum of all required hours

    At the end:
    - If totalHours <= h → speed is valid ✅
    - Else → speed too slow ❌

-----------------------------------------------------------------

    📈 Behavior of function:

    speed ↑ → faster eating → fewer hours needed

    So pattern looks like:
    F F F F T T T T
            ↑
    answer (minimum valid speed)

-----------------------------------------------------------------

    🔁 Binary Search Decision:

    If canDo(mid) == true:
        → this speed works
        → try smaller (move left)
    Else:
        → too slow
        → increase speed (move right)

----------------------------------------------------------------

    🎯 Final Goal:

    Find the FIRST TRUE (minimum valid speed)

----------------------------------------------------------------

    🧠 Mental Model:

    Think like this:
    "I am guessing a speed. If it works, I try slower.
    If it fails, I must increase speed."

----------------------------------------------------------------

    🔥 Important Notes:

    - ceil(pile / speed) is critical (cannot use normal division)
    - Avoid overflow: prefer long if constraints are large
    - No need to sort the array (max can be found in O(n))

-----------------------------------------------------------------

    ⚡ One-line takeaway:

    Binary search the minimum speed at which finishing within h hours becomes possible.
*/
class KokoEatingBananas{
    public static boolean canDo(int speed,int[] piles,int h){
        int totalHours=0;
        for(int pile : piles){
            totalHours += (int)Math.ceil((double)pile/speed);
        }

        if(totalHours <= h){
            return true;
        }else{
            return false;
        }
    }
    public int minEatingSpeed(int[] piles,int h)
    {
        Arrays.sort(piles);
        int start=1,end=piles[piles.length-1];
        // System.out.println(end);
        int ans=-1;
        int mid=0; 
        while(start <= end){
            mid=start+(end-start)/2;
            // System.out.println(start + " " +mid+" "+end);
            if(canDo(mid,piles,h)==true)
            {
                ans=mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        int[] piles={3,6,7,11};
        int h=8;

        KokoEatingBananas obj = new KokoEatingBananas();
        int ans = obj.minEatingSpeed(piles,h);
        System.out.println(ans);
    }
}