package Backtracking;

/*
Approach: Backtracking / Try All Possible Cookie Distributions

1. We distribute the cookies one by one.
    The parameter `idx` represents the index of the current cookie
    that we need to distribute.

2. For every cookie, try giving it to each of the `k` children:

        for (int i = 0; i < k; i++) {
            children[i] += cookie;              // Choose
            findDistribution(idx + 1, children, cookies, k); // Explore
            children[i] -= cookie;              // Backtrack
        }

    This generates all possible ways to distribute the cookies.

3. Base Case:
    When all cookies have been distributed:

        if (idx == cookies.length)

    calculate the maximum number of cookies received by any child:

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            max = Math.max(max, children[i]);
        }

4. The unfairness of one distribution is the maximum number of cookies
    received by any child.

    We want the minimum unfairness among all possible distributions:

        result = Math.min(result, max);

5. Backtracking is important because after trying one child, we must
    remove the current cookie before trying the next child:

       children[i] += cookie;   // Give cookie
        ...
       children[i] -= cookie;   // Remove cookie

6. Recursion Tree Idea:

    For each cookie:
        -> Give to Child 1
        -> Give to Child 2
        -> Give to Child 3
        ...
        -> Give to Child k

    Therefore, each of the `n` cookies has `k` possible choices,
    resulting in approximately k^n possible distributions.

Time Complexity:
    O(k^n * (n + k))

    - k^n possible distributions
    - O(k) to calculate the maximum at each complete distribution

Space Complexity:
    O(n)

    - Recursion stack can go as deep as the number of cookies
    - The `children` array is used in-place and is not recreated
*/
public class CookieDistribution {

    static int result;
    
    static void findDistribution(int idx, int[] children, int[] cookies, int k){
        if(idx==cookies.length){
            int max=Integer.MIN_VALUE;
            for (int i = 0; i < k; i++) {
                max = Math.max(max,children[i]);
            }
            result = Math.min(result, max);
            return;
        }
        int cookie= cookies[idx];
        for(int i=0; i< k;i++){
            children[i] += cookie;
            findDistribution(idx+1, children, cookies, k);
            children[i] -= cookie;
        }
    }
    public static void main(String[] args) {
        int[] cookies = {8,15,10,20,8};
        int k=2;
        
        int[] children = new int[k];
        result=Integer.MAX_VALUE;
        findDistribution(0, children, cookies, k);
        System.out.println(result);
    }
}
