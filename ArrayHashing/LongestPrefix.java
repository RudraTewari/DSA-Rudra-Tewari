class LongestPrefix {
    
    // public String longestCommonPrefix(String[] strs)
    // {
    //     if(strs==null || strs.length==0) return "";
    //     String prefix=strs[0];
    //     for(int i=1;i<strs.length;i++)
    //     {
    //         while(!strs[i].startsWith(prefix))
    //         {
    //             prefix=prefix.substring(0,prefix.length()-1);
    //             if(prefix.isEmpty()) return "";
    //         }
    //     }
    //     return prefix;
    // }
    
    public String longestCommonPrefix(String[] strs) {
        String prefix= strs[0];
        for(int i=0;i< prefix.length();i++)
        {
            char ch = prefix.charAt(i);
            for(int j=1;j<strs.length;j++)
            {
                if(i>=strs[j].length() || ch != strs[j].charAt(i)){
                    return prefix.substring(0,i);
                }
            }
        }
        return prefix;
    }
    public static void main(String[] args){
        String[] strs={"flower","flow","flight"};

        LongestPrefix obj = new LongestPrefix();
        String ans = obj.longestCommonPrefix(strs);

        System.out.println(ans);

    }
}
