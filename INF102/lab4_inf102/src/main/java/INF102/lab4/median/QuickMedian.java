package INF102.lab4.median;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class QuickMedian implements IMedian {
    // k-th element, the median
    int k;

    @Override
    public <T extends Comparable<T>> T median(List<T> list) {
        List<T> listCopy = new ArrayList<>(list); // Method should not alter list
        k = list.size()/2; 
        // recursive method
        return splitList(listCopy);
    }

    private <T extends Comparable<T>> T splitList(List<T> list){
        // if k is 0, return smalles number in list
        if(k == 0){
            return Collections.min(list);
        }
        // if list only contains two elems, and k is not 0
        // return the largest
        else if(list.size() == 2) {
            return Collections.max(list);
        }
            List<T> lesserElems = new ArrayList<>();
            List<T> greaterElems = new ArrayList<>();
            // pivot point for checking vals
            // could be random
            T pivot = list.get(list.size()/2);
            for(int i = 0; i < list.size(); i++){
                if((list.get(i).compareTo(pivot))<0){
                    lesserElems.add(list.get(i));
                }
                else if((list.get(i).compareTo(pivot))>0){
                    greaterElems.add(list.get(i));
                }
            }
            if(k < lesserElems.size()) {
                return splitList(lesserElems);
            }
            else if (k > lesserElems.size()){
                k = k - lesserElems.size() - 1; 
                return splitList(greaterElems);
            }
            else{
                return pivot;
            }
    }
}