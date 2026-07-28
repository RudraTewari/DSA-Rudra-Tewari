class SimplifyPath{
/*
    Approach to Simplify Unix Path:

    1. Split the input path using "/":
    - This breaks the path into components (directories, ".", "..", or empty strings).
    - Example: "/a/./b/../c/" → ["", "a", ".", "b", "..", "c", ""]

    2. Use a stack to simulate directory navigation:
    - The stack will store valid directory names.
    - "top" represents the current top of the stack.

    3. Traverse each component:
    a) If the string is "" or ".":
        - Ignore it.
        - "" occurs due to multiple slashes "//"
        - "." means current directory (no change)

    b) If the string is "..":
        - Move to parent directory.
        - Pop from stack if it is not empty (top != -1)

    c) Otherwise:
        - It is a valid directory name.
        - Push it onto the stack.

    4. Build the final simplified path:
    - Start from the top of the stack.
    - Keep prepending "/" + directory to the result string.
    - This reconstructs the canonical path.

    5. Edge Case:
    - If stack is empty, return "/"
    - This represents the root directory.

    Key Insight:
    - Stack simulates real folder navigation:
    push → go inside a folder
    pop  → go back to parent
*/
    public String simplfyPath(String path)
    {
        String[] arr = path.split("/");
        String[] stack = new String[arr.length];
        int top=-1;
        for(String str : arr){
            if(str.equals("") || str.equals(".")) 
                continue;
            else if(str.equals("..")){
                if (top != -1){
                    // System.out.println("Popping" +"->"+ stack[top]);
                    top--;                    
                }
            }
            else{
                    stack[++top] = str.trim();
                    // System.out.println(" -> push " + str);
                }
        }
        
        String ans = new String("");
        while(top!=-1){
            ans = "/"+stack[top]+ans;
            top--;
        }
        return ans.length() ==0 ? "/" : ans;
    }
    public static void main(String[] args)
    {
        String path = "/.../a/../b/c/../d/./";
        SimplifyPath obj = new SimplifyPath();
        String ans = obj.simplfyPath(path);
        System.out.println(ans);
    }
}