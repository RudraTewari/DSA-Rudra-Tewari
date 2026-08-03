import java.util.Arrays;
/*
Approach:

1. Sort both the players and trainers arrays in ascending order.
    - Sorting allows us to greedily match players with trainers.

2. Start from the end of both arrays.
    - i points to the strongest (largest requirement) player.
    - j points to the strongest (largest capacity) trainer.

3. While both pointers are valid:
    - If the current trainer can train the current player
        (players[i] <= trainers[j]):
        -> Match them.
        -> Increment the answer.
        -> Move both pointers left (i--, j--).
    - Otherwise:
        -> The strongest remaining trainer cannot train this player.
        -> This player cannot be matched with any weaker trainer either.
        -> Skip this player by moving i--.

4. Continue until either all players or all trainers are processed.

Why this greedy strategy works:
- We always assign the strongest available trainer to the strongest player
    that can still be trained.
- If the strongest trainer cannot train the strongest player, no weaker
    trainer can train that player either, so skipping the player is optimal.
- This leaves smaller trainers available for smaller players, maximizing
    the total number of matches.

Time Complexity:
- Sorting players  : O(n log n)
- Sorting trainers : O(m log m)
- Two-pointer scan : O(n + m)
Overall: O(n log n + m log m)

Space Complexity:
- O(1) extra space (excluding the space used by the sorting algorithm).
*/
class MatchPlayersWithTrainers{
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int match=0;
        int i=players.length-1;
        int j=trainers.length-1;
        while(i>=0&&j>=0){
            if(players[i]<= trainers[j]){
                match++;
                i--;j--;
            }else{
                i--;
            }
        }
        return match;
    }

    // Helper function to display an array
    public static void display(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        // Sample Input
        int[] players = {4, 7, 9};
        int[] trainers = {8, 2, 5, 8};

        System.out.println("Players : ");
        display(players);

        System.out.println("Trainers: ");
        display(trainers);

        // Create Solution object
        MatchPlayersWithTrainers sol = new MatchPlayersWithTrainers();

        // Call the target function
        int result = sol.matchPlayersAndTrainers(players, trainers);

        // Print Output
        System.out.println("Maximum Matching = " + result);
    }

}