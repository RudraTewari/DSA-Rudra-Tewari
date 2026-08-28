
/*
    Approach: Recursion + Counting Permutations

    We need to count all numbers in the range [0, 10^n) that contain no repeated digits.

    Instead of generating every number, we count how many valid numbers exist for each
    digit length from 1 to n.

    For a number with unique digits:

        1 digit  -> 9 choices
                    (1 to 9, since the first digit cannot be 0)

        2 digits -> 9 * 9
                    First digit: 9 choices
                    Second digit: 9 choices
                    (0 can now be used, but the first chosen digit cannot be repeated)

        3 digits -> 9 * 9 * 8

        4 digits -> 9 * 9 * 8 * 7

    So, after calculating the count for one digit length, we use that count to calculate
    the next digit length by multiplying it with the remaining available choices.

    1. Handle n == 0:

            if (n == 0) return 1;

        The only number in the range [0, 1) is 0, so the answer is 1.

    2. Start with one-digit numbers:

            return 10 + Solve(n, 2, 9, 9);

        Here, 10 represents:

            1 for number 0
            +
            9 for one-digit numbers from 1 to 9

        The recursive function starts from idx = 2 because the counts for 0-digit
        and 1-digit numbers are already included.

    3. Calculate the count for the current digit length:

        currentCount *= choices;

        For the first recursive call:

            currentCount = 9
            choices = 9

            currentCount = 9 * 9 = 81

        So, there are 81 valid two-digit numbers.

        For the next call:

            currentCount = 81
            choices = 8

        currentCount = 81 * 8 = 648

        So, there are 648 valid three-digit numbers.

    4. Add the current count and move to the next digit length:

            return currentCount
                    + Solve(n, idx + 1, currentCount, choices - 1);

        This ensures that we add the count of every digit length:

            2-digit count
            + 3-digit count
            + 4-digit count
            + ...

    5. Base Case:

            if (idx > n) return 0;

        Since idx represents the digit length currently being processed, we must allow
        idx == n to execute and calculate the count for n-digit numbers.

        Once idx becomes greater than n, all required digit lengths have already been
        processed, so we return 0.

        We return 0 because the recursive results are being added:

            currentCount + 0 = currentCount

    Example: n = 3

        Start with:
            0 and all 1-digit numbers = 10

        Solve(3, 2, 9, 9):

            2-digit count:
            9 * 9 = 81

            3-digit count:
            81 * 8 = 648

        Final answer:
            10 + 81 + 648 = 739

    Thus, recursion efficiently counts valid numbers for each digit length without
    generating every number individually.
*/


public class CountUniqueDigitsinNumber {
    public static int Solve(int n, int idx, int currentCount, int choices){
        if(idx > n) return 0;

        currentCount *= choices;

        return currentCount + Solve(n, idx+1, currentCount, choices-1);
    }
    public int countUniqueDigits(int n){
        if(n==0) return 1;

        return 10 + Solve(n, 2, 9, 9);
    }

    public static void main(String[] args) {
        int n=4;

        CountUniqueDigitsinNumber obj = new CountUniqueDigitsinNumber();

        int ans = obj.countUniqueDigits(n);
        System.out.println(ans);
    }
}
