package INF102.lab3.sumList;

import java.util.List;

public class SumRecursive implements ISum {

    @Override
    public long sum(List<Long> list) {
        // Start sum is 0
        long sum = 0;
        // If there is no more elements in list, return sum
        if(list.isEmpty()){return sum;}
        // Add first element in list to sum, then remove it from list
        sum+=list.get(0);
        list.remove(0);
        // Return sum + sum in list
        return sum+sum(list);
    }
    

}
