import java.util.Arrays;

class AsteroidCollision{
    public int[] asteroidCollision(int[] asteroids)
    {
        int[] stack = new int[asteroids.length];
        int top = -1;

        for(int asteroid : asteroids)
        {
            while(top != -1 && asteroid < 0 && stack[top] > 0)
            {
                int diff = asteroid + stack[top];
                
                if(diff < 0)
                {
                    if(top!=-1)  
                        top--;
                }else if(diff > 0)
                {
                    asteroid = 0;
                    break;
                }else{
                    if(top!=-1) 
                        top--;
                    asteroid = 0;
                    break;
                }
            }
            if(asteroid != 0)
                if(top < asteroids.length-1) 
                    stack[++top] = asteroid;            
        }
        return Arrays.copyOf(stack,top+1);
    }
    public static void main(String[] args)
    {
        int[] nums = {5,10,-5};
        AsteroidCollision obj = new AsteroidCollision();
        int[] ans = obj.asteroidCollision(nums);
        for(int i=0;i<ans.length;i++)
        {
            System.out.print(ans[i] + " ");
        }
    }
}