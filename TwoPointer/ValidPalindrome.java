class ValidPalindrome{
    public boolean isAlNum(char c)
    {
        if(c>='A' && c<='Z') return true;
        else if(c>='a' && c<='z') return true;
        else if(c>='0' && c<='9') return true;

        return false;

    }
    public boolean isPalindrome(String s)
    {
        s.trim();
        StringBuilder ans= new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            if(isAlNum(s.charAt(i)))
            {
                ans.append(Character.toLowerCase(s.charAt(i)));
            }           
        }
        int lb=0,ub=ans.length()-1;
        while(lb<ub)
        {
            if(ans.charAt(lb) != ans.charAt(ub))
                return false;
            
            lb++;
            ub--;
        }
        return true;

    }
    public static void main(String[] args)
    {
        String s = "a canal: Panama";
        ValidPalindrome obj = new ValidPalindrome(); 
        boolean ans = obj.isPalindrome(s);
        System.out.println(ans);
    }
}