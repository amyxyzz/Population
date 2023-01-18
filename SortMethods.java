import java.util.ArrayList;
import java.util.List;

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Amy Zheng
 *	@since	January 9, 2023
 */
public class SortMethods {
	
	/**
	 *	Swaps two City objects in array list arr
	 *	@param arr		array list of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(ArrayList<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order for least populous cities
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(ArrayList<City> arr) {
		//System.out.println(arr.get(0).getPopulation());
		for (int n = arr.size(); n > 1; n--){
			int max = 0;
			for (int i = 1; i < n; i++){
				if (arr.get(i).compareTo(arr.get(max)) > 0){
					max = i;
				}
			}
			swap(arr, max, n-1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order for city names
	 *	@param arr		array list of City objects to sort
	 */
	public void insertionSort(ArrayList<City> arr) {
		for (int n = 1; n < arr.size(); n++){
			String key = arr.get(n).getName();
			int index = n;
			
			while (index > 0 && key.compareTo(arr.get(index - 1).getName()) < 0){
				index--;
			}
			
			City temp = arr.get(n);
			
			for (int i = n; i > index; i--) {
				arr.set(i, arr.get(i - 1));
			}
			
			arr.set(index, temp);
		}
		
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order for most populous cities
	 *	@param arr		array list of City objects to sort
	 */
	public void selectionSortMost(ArrayList<City> arr){
		//System.out.println(arr.get(0).getPopulation());
		for (int n = arr.size(); n > 1; n--){
			int max = 0;
			for (int i = 1; i < n; i++){
				if (arr.get(i).compareTo(arr.get(max)) < 0){
					max = i;
				}
			}
			swap(arr, max, n-1);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order for most populous cities
	 *	@param arr		array list of City objects to sort
	 *  @param start    starting index of the array list
	 *  @param end      ending index of the array list
	 */

    public static void mergeSort(ArrayList<City> cities, int start, int end) {
        if (cities == null || start >= end) {
            return;
        }
        
        int middle = start + (end - start) / 2;
        mergeSort(cities, start, middle);
        mergeSort(cities, middle + 1, end);
        
        ArrayList<City> temp = new ArrayList<City>();
        int i = start;
        int j = middle + 1;
        int k = 0;
        
        while (i <= middle && j <= end) {
            if (cities.get(i).getPopulation() > cities.get(j).getPopulation()) {
                temp.add(cities.get(i++));
            } else {
                temp.add(cities.get(j++));
            }
        }
        while (i <= middle) {
            temp.add(cities.get(i++));
        }
        while (j <= end) {
            temp.add(cities.get(j++));
        }
        
        for (i = start, k = 0; i <= end; i++, k++) {
            cities.set(i, temp.get(k));
        }
    }
    
	/**
	 *	Merge Sort algorithm - in descending order for city names
	 *	@param arr		array list of City objects to sort
	 *  @param start    starting index of the array list
	 *  @param end      ending index of the array list
	 */    
        public static void mergeSort2(ArrayList<City> cities, int start, int end) {
        if (cities == null || start >= end) {
            return;
        }
        
        int middle = start + (end - start) / 2;
        mergeSort2(cities, start, middle);
        mergeSort2(cities, middle + 1, end);
        
        ArrayList<City> temp = new ArrayList<City>();
        int i = start;
        int j = middle + 1;
        int k = 0;
        
        while (i <= middle && j <= end) {
            if (cities.get(i).getName().compareTo(cities.get(j).getName()) > 0) {
                temp.add(cities.get(i++));
            } else {
                temp.add(cities.get(j++));
            }
        }
        while (i <= middle) {
            temp.add(cities.get(i++));
        }
        while (j <= end) {
            temp.add(cities.get(j++));
        }
        
        for (i = start, k = 0; i <= end; i++, k++) {
            cities.set(i, temp.get(k));
        }
    }
}
	



