package Backtracking;

import java.util.Arrays;
/*
    Approach:
    ---------
    We need to find the maximum number of building transfer requests
    that can be accepted such that, after processing all selected
    requests, every building has the same number of incoming and
    outgoing employees.

    We use Backtracking to consider every request in two possible ways:
    
        1. Include the current request
        2. Exclude the current request

    ---------------------------------------------------------------
    1. Track the net employee change for every building
    ---------------------------------------------------------------

    We maintain an array `resultant[]` where:

        resultant[i] = net change in employees at building i

    For a request [from, to]:

        from building loses an employee
        to building gains an employee

    Therefore:

        resultant[from]--
        resultant[to]++

    Example:

        request = [0, 1]

        resultant[0]--
        resultant[1]++

    ---------------------------------------------------------------
    2. Include the current request
    ---------------------------------------------------------------

    First, we choose to accept the current request:

        resultant[fromBuilding]--;
        resultant[toBuilding]++;

    Since we have selected one additional request, we pass:

        count + 1

    to the recursive call.

        Solve(resultant, idx + 1, count + 1, requests);

    Notice that we use `count + 1` instead of `++count` or `count++`.

    `count + 1` creates the value needed by the next recursive call
    without modifying `count` in the current recursive state.

    ---------------------------------------------------------------
    3. Backtrack
    ---------------------------------------------------------------

    After exploring the "include" branch, we must undo its changes
    before exploring the "exclude" branch.

        resultant[fromBuilding]++;
        resultant[toBuilding]--;

    This restores `resultant[]` to exactly the state it was in
    before selecting the request.

    This is the most important part of backtracking:

        Make a choice
             ↓
        Explore the choice
             ↓
        Undo the choice

    ---------------------------------------------------------------
    4. Exclude the current request
    ---------------------------------------------------------------

    Now we explore the second possibility: don't accept the request.

    Since the request is not selected:

        - resultant[] is not changed
        - count is not increased

    So we simply call:

        Solve(resultant, idx + 1, count, requests);

    ---------------------------------------------------------------
    5. Base Case
    ---------------------------------------------------------------

    When:

        idx >= requests.length

    it means that we have considered every request.

        if (idx >= requests.length)

    Now we need to check whether the selected requests form a valid
    configuration.

    A valid configuration requires every building to have a net change
    of zero.

        resultant[i] == 0

    for every building.

    We check this using:

        boolean isZero =
            Arrays.stream(resultant)
                  .allMatch(val -> val == 0);

    If every value is zero, the selected requests are valid.

    We then update the maximum number of accepted requests:

        maxCount = Math.max(maxCount, count);

    ---------------------------------------------------------------
    6. Why does resultant[i] == 0 mean a valid configuration?
    ---------------------------------------------------------------

    Suppose a building has:

        resultant[i] = -2

    It means 2 more employees leave this building than enter it.

    If:

        resultant[i] = +2

    it means 2 more employees enter than leave.

    For the building to remain balanced:

        incoming == outgoing

    Therefore:

        resultant[i] = 0

    must hold for every building.

    ---------------------------------------------------------------
    7. Overall Backtracking Structure
    ---------------------------------------------------------------

    For every request, we create two branches:

                       Current Request
                       /              \
                  Include            Exclude
                    |                   |
                count + 1             count
                    |                   |
                recurse              recurse
                    |
                 backtrack

    This allows us to examine every possible subset of requests.

    ---------------------------------------------------------------
    8. Initialization
    ---------------------------------------------------------------

    We create the resultant array:

        int[] resultant = new int[n];

    Java initializes all elements to 0.

    We start from:

        idx = 0
        count = 0

    So:

        Solve(resultant, 0, 0, requests);

    Initially, no requests have been selected and every building
    has a net change of zero.

    ---------------------------------------------------------------
    9. Why Backtracking is Necessary
    ---------------------------------------------------------------

    `resultant[]` is shared between recursive calls.

    Therefore, when we include a request, we modify the array.

    Before trying the exclude branch, we must undo those modifications.

    Example:

        Before selecting [0, 1]:

            resultant = [0, 0, 0]

        Include [0, 1]:

            resultant = [-1, +1, 0]

        After recursive exploration, backtrack:

            resultant = [0, 0, 0]

    Now the exclude branch starts with the correct state.

    ---------------------------------------------------------------
    10. Complexity
    ---------------------------------------------------------------

    Each request has 2 choices:

        Include
        Exclude

    For `m` requests, there are:

        2^m

    possible subsets.

    At every leaf/base case, we check all `n` buildings using
    `Arrays.stream(resultant).allMatch(...)`.

    Therefore:

        Time Complexity: O(n * 2^m)

    where:
        n = number of buildings
        m = number of requests

    The recursion depth is `m`, and the resultant array requires `O(n)`
    space.

        Auxiliary Space: O(m + n)

    ---------------------------------------------------------------
    Main Idea:
    ---------------------------------------------------------------

    The key idea is to treat every request as a binary decision:

        "Should I take this request or not?"

    We recursively explore both choices, maintain the net employee
    change using `resultant[]`, undo every modification while
    backtracking, and whenever all buildings become balanced
    (`resultant[i] == 0`), update the maximum number of selected
    requests.
*/
class Solution{
    static int maxCount;
    public static void Solve(int[] resultant, int idx, int count, int[][] requests){
        if(idx >= requests.length){
            boolean isZero = Arrays.stream(resultant).allMatch(val -> val == 0);
            if(isZero){
                maxCount = Math.max(maxCount, count);
            }
            return;
        }

        int fromBuilding = requests[idx][0];
        int toBuiding = requests[idx][1];

        //Include a Request
        resultant[fromBuilding]--;
        resultant[toBuiding]++;
        Solve(resultant, idx+1, count+1, requests);

        //Backtrack
        resultant[fromBuilding]++;
        resultant[toBuiding]--;

        //Exclude a Request
        Solve(resultant, idx+1, count, requests);
    }
    public int maximumRequests(int n, int[][] requests){
        maxCount = Integer.MIN_VALUE;
        int[] resultant = new int[n];
        Solve(resultant, 0, 0, requests);
        return maxCount;
    }
}
public class MaximumTransferRequestAchieved {
    public static void main(String[] args) {
        int[][] requests = {
            {0,1},{1,0},{0,1},{1,2},{2,0},{3,4}
        };

        int n=5;

        Solution obj = new Solution();
        int ans = obj.maximumRequests(n, requests);
        System.out.println(ans);
    }
}
