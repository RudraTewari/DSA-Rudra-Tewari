import java.util.Arrays;

class RescueBoats{

    // this works for some test cases but fails for some.
 /* public int rescueBoats(int[] people, int limit)
    {
        int originaLimit;

        boolean[] isRescue = new boolean[people.length];
        Arrays.fill(isRescue,false);

        int boats=0;
        for(int i=0; i<people.length; i++)
        {
            originaLimit=limit;
            if(people[i] <= originaLimit && isRescue[i]==false)
            {
                boats++;
                isRescue[i]=true;
                originaLimit -= people[i];
            }
            for(int j=i+1; j<people.length; j++)
            {
                if(people[j] <= originaLimit && isRescue[j] == false)
                {
                    isRescue[j]= true;
                    originaLimit -= people[j];
                    break;
                }
            }
        }
        return boats;
    }*/

/* Here we use two pointer we can observe 
the largest value never exceeds limit so if people[lb] +  people[ub] not less than limit then 
value at ub can definetly solely fill the boat so using this logic we solve it. */
    public int rescueBoats(int[] people,int limit)
    {
        Arrays.sort(people);

        int lb=0,ub=people.length-1;
        int boats=0;
        while(lb<=ub)
        {
            int sum = people[lb]+people[ub];
            if(sum <= limit)
            {
                boats++;
                lb++;
                ub--;
            }
            else{
                boats++;
                ub--;
            }
        }
        return boats;
    }
    public static void main(String[] args)
    {
        int[] people={3,1,7};
        int limit=7;

        RescueBoats obj = new RescueBoats();
        int ans = obj.rescueBoats(people,limit);
        System.out.println(ans);
    }
}