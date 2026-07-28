import java.util.*;
class Pair{
    String first;
    int second;
    Pair(String first,int second){
        this.first=first;
        this.second=second;
    }
}
class TimeMap {
    private Map<String,List<Pair>> timeMap;
    public TimeMap() {
        timeMap=new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!timeMap.containsKey(key))
            timeMap.put(key,new ArrayList<>());
            
        timeMap.get(key).add(new Pair(value,timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!timeMap.containsKey(key)){
            return "";
        }
        int low=0;
        int high = timeMap.get(key).size()-1;
        String ans="";
        int mid=0;
        while(low<=high){
            mid=low+(high-low)/2;
            if(timeMap.get(key).get(mid).second <= timestamp){
                ans=timeMap.get(key).get(mid).first;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        
        TimeMap obj = new TimeMap();

        obj.set("foo","bar",1);

        System.out.println(obj.get("foo",1));
        System.out.println(obj.get("foo",3));


        obj.set("foo","bar2",4);

        System.out.println(obj.get("foo",4));
        System.out.println(obj.get("foo",5));       
        
    }
}

