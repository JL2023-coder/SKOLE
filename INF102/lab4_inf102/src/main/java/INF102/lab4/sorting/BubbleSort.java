package INF102.lab4.sorting;

import java.util.List;

public class BubbleSort implements ISort {
    boolean swapped;

    @Override
    public <T extends Comparable<T>> void sort(List<T> list) {
        for(int i = 0; i < list.size() - 1; i++){
            swapped = false;
            if(list.get(i).compareTo(list.get(i+1))>0){
                swap(list, i, i+1);
            }
            for(int j = 0; j < list.size() - i - 1; j++){
                if(list.get(j).compareTo(list.get(j+1))>0){
                    swap(list, j, j+1);
                }
            }
            // breaks if no swapped has occured, list is already sorted
            if(swapped == false){
                return;
            }
        }
    }
    // swaps to elems in list
    private <T> void swap(List<T> list, int index1, int index2) {
        T firstElem = list.get(index1);
        T secondElem = list.get(index2);
        list.set(index1, secondElem); 
        list.set(index2, firstElem);
        // a swap has occured
        swapped = true;
    }
}
