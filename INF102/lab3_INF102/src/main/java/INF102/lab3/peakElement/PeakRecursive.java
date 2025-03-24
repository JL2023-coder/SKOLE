package INF102.lab3.peakElement;

import java.util.List;

public class PeakRecursive implements IPeak {

    @Override
    public int peakElement(List<Integer> numbers) {
        // If list only contains one int
        if(numbers.size()==1){
            return numbers.get(0);
        }
        // If first int is larger then the second
        if(numbers.get(0)>=numbers.get(1)){
            return numbers.get(0);
        }
        // If last int is larger then the last-1
        if(numbers.get(numbers.size()-1)>=numbers.get(numbers.size()-2)){
            return numbers.get(numbers.size()-1);
        }

        // If int in middel is peak elem
        return findPeakElement(numbers, 1);
        }

    private int findPeakElement(List<Integer> numbers, int i){
        // If elem is peak elem
        if(numbers.get(i)>=numbers.get(i-1) && numbers.get(i)>=numbers.get(i+1)){
            return numbers.get(i);
        }
        // Recursive call
        return findPeakElement(numbers, i+1);
    }
}
