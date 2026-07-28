import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
/*
    APPROACH: Stock Span Problem using Monotonic Stack

    Goal:
    For each incoming stock price, find the number of consecutive days 
    (including today) the price has been less than or equal to today's price.

    Key Idea:
    Use a stack to store pairs of (price, span), where:
    - price = stock price of that day
    - span  = number of consecutive days the price was <= this price

    Algorithm:
    1. Initialize an empty stack of Pair (price, span).

    2. For each new price:
    a. Start with count = 1 (today itself).
    
    b. While stack is not empty AND current price >= top price:
        - Pop the top element.
        - Add its span to count.
        (This allows us to "jump" multiple days at once instead of checking one by one.)
    
    c. Push a new Pair(current price, count) onto the stack.
    
    d. Return count.

    Why it works:
    - The stack maintains a decreasing order of prices.
    - When a higher price comes, we collapse all smaller/equal prices
    and accumulate their spans.
*/
class Pair{
    int first,second;
    Pair(int first,int second)
    {
        this.first = first;
        this.second=second;
    }
}
class StockSpan{
    private Deque<Pair> stack;
    public StockSpan()
    {
        stack = new ArrayDeque<>();
    }
    public int next(int prices){
        int count = 1;

        while(!stack.isEmpty() && prices >= stack.peek().first)
            count += stack.pop().second;
        
        stack.push(new Pair(prices,count));
        return count;
    }
    public static void main(String[] args)
    {
        StockSpan obj = new StockSpan();
        List<Integer> list = new ArrayList<>();
        list.add(obj.next(100));
        list.add(obj.next(80));
        list.add(obj.next(60));
        list.add(obj.next(70));
        list.add(obj.next(60));
        list.add(obj.next(75));
        list.add(obj.next(85));

        for(int val : list)
        {
            System.out.print(val+" ");
        }
        System.out.println();
    }
}