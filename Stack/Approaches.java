/*A monotonic stack is a stack where elements are kept in a specific sorted order (either increasing or decreasing).
There are two types:


Monotonic Increasing Stack
Elements increase from top → bottom
Top always has the smallest element

Monotonic Decreasing Stack
Elements decrease from top → bottom
Top always has the largest element

⚙️ Core Idea
Instead of storing everything, we:
👉 Remove useless elements
👉 Keep only those that can help in future
This is what makes it powerful and efficient (O(n)).

🔥 Why Do We Use It?
Monotonic stacks are used to efficiently answer questions like:

Next greater element
Previous smaller element
Nearest larger/smaller value
Range/span problems


Instead of brute force (O(n²)), we solve them in O(n).

⚡ How It Works (Simple Rule)
At every step:
while(stack not empty AND top is useless)    pop()push(current)
The definition of "useless" depends on the problem.

📌 Example (Next Greater Element)
Array : [4, 5, 2, 10]

Goal:
Find next greater element for each
Process:

Start from right
Maintain monotonic decreasing stack

Step-by-step:
10 → stack empty → push2 → 10 > 2 → next greater = 10 → push5  → pop 2 → 10 > 5 → next greater = 10 → push4  → 5 > 4 → next greater = 5 → push

🧩 Key Insight
When we pop an element:
👉 It means we found something better
Example:
Current = 6 
Stack top = 3 → useless → pop

Why?
Because 6 is closer and bigger than 3 → 3 will never be useful again.

🧱 Two Ways to Traverse
DirectionUse CaseLeft → RightPrevious elementsRight → LeftNext elements

🧭 How to Identify Monotonic Stack Problems
Look for these clues:
“Next greater/smaller”
“Nearest element”
“Span”
“First element on left/right”
“Remove unnecessary elements”



🧮 Template (Generic)
Next Greater (Right side)

for (int i = n-1; i >= 0; i--) {    
        while (!stack.isEmpty() && stack.peek() <= arr[i]) {        
            stack.pop();    
        }    
        ans[i] = stack.isEmpty() ? -1 : stack.peek();   
        stack.push(arr[i]);
    }

Previous Smaller (Left side)
for (int i = 0; i < n; i++) {    
        while (!stack.isEmpty() && stack.peek() >= arr[i]) {        
            stack.pop();    
        }    
        ans[i] = stack.isEmpty() ? -1 : stack.peek();    
        stack.push(arr[i]);
    }

 */