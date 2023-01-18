import java.util.ArrayList;
import java.util.List;
/**
 *	Population - Implements different sorting methods to organize a set
 * of data based on categorical and quantitative variables like state,
 * city, population, and designation 
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Amy Zheng
 *	@since	January 9, 2023
 */
public class Population {
	
	// List of cities
	private ArrayList<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/** Method loadCities     read in files from DATA_FILE and initialize
	 * 						  the data to a City object
	 * */
	public void loadCities(){
		java.util.Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]"); //use tabs and new lines as delimiter
		cities = new ArrayList<City>();
		while(input.hasNext()){
			String state = input.next();
			String name = input.next();
			String designation = input.next();
			int population = input.nextInt();
			City city = new City(state, name, designation, population);
			cities.add(city);
		}
		input.close();
	}
	
	
	/** Method getStateName      prompts the user for a state name until valid
	 * 
	 * @return   stateName 		 the valid state name
	 * */ 
	public String getStateName(){
		System.out.println();
		String stateName = Prompt.getString("Enter state name (ie. Alabama) ");
		boolean isState = false;

		for (int i = 0; i < cities.size(); i++){
			if (stateName.equals(cities.get(i).getState())){
				isState = true;
			}
		}
		
		while (!isState){
			System.out.println("ERROR: " + stateName + " is not valid");
			stateName = Prompt.getString("Enter state name (ie. Alabama) ");
			for (int i = 0; i < cities.size(); i++){
				if (stateName.equals(cities.get(i).getState())){
					isState = true;
				}
			}	
		}
		
		System.out.println();
		return stateName;
	}
	
	/** Method getCityName       prompts the user for a city name until valid
	 * 
	 * @return  cityName  	     the valid city name
	 * */ 
	public String getCityName(){
		System.out.println();
		String cityName = Prompt.getString("Enter city name ");
		boolean isCity = false;

		for (int i = 0; i < cities.size(); i++){
			if (cityName.equals(cities.get(i).getName())){
				isCity = true;
			}
		}
		
		while (!isCity){
			System.out.println("ERROR: " + cityName + " is not valid");
			cityName = Prompt.getString("Enter city name ");
			for (int i = 0; i < cities.size(); i++){
				if (cityName.equals(cities.get(i).getName())){
					isCity = true;
				}
			}	
		}
		
		System.out.println();
		return cityName;
	}
	
	/** Method answerPrompt1       lists the least populous cities in USA 
	 * 								with Selection Sort
	*/
	public void answerPrompt1(){
		long startMillisec = System.currentTimeMillis();
		SortMethods sort = new SortMethods();
		sort.selectionSort(cities);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec;
		
		for (int i = 0; i < 50; i++){
			System.out.println(cities.get(i));
		}
		
		System.out.println();
		System.out.println("Elapsed Time " + time + " milliseconds");
	}
	
	/** Method answerPrompt2       lists the most populous cities in USA 
	 * 								with Merge Sort
	*/
	public void answerPrompt2(){
		SortMethods sort = new SortMethods();
		long startMillisec = System.currentTimeMillis();
		sort.mergeSort(cities, 0, cities.size() - 1);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec;
		
		for (int i = 0; i < 50; i++){
			System.out.println(cities.get(i));
		}
		
		System.out.println();
		System.out.println("Elapsed Time " + time + " milliseconds");
	}
	
	/** Method answerPrompt3       lists the first fifty cities in USA 
	* 								sorted by name with insertion Sort
	*/
	public void answerPrompt3(){
		SortMethods sort = new SortMethods();
		
		long startMillisec = System.currentTimeMillis();
		sort.insertionSort(cities);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec;
		
		for (int i = 0; i < 50; i++){
			System.out.println(cities.get(i));
		}
		
		System.out.println();
		System.out.println("Elapsed Time " + time + " milliseconds");
	}
	
	/** Method answerPrompt4        lists the last fifty cities in USA 
	 * 								sorted by name with Merge Sort
	*/
	public void answerPrompt4(){
		SortMethods sort = new SortMethods();
		long startMillisec = System.currentTimeMillis();
		sort.mergeSort2(cities, 0, cities.size() - 1);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec;
		
		for (int i = 0; i < 50; i++){
			System.out.println(cities.get(i));
		}
		
		System.out.println();
		System.out.println("Elapsed Time " + time + " milliseconds");
	}
	
	/** Method answerPrompt5       lists the fifty most populous cities in the named state
	 * 								with Selection Sort
	*/
	public void answerPrompt5(String stateName){
		ArrayList<City> stateList = new ArrayList<City>();
		for (int j = 0; j < cities.size(); j++){
			//adds City to list only if City matches user's city
			if (cities.get(j).getState().equals(stateName)){
				stateList.add(cities.get(j));
			}
			else;
		}
		SortMethods sort = new SortMethods();
		
		//long startMillisec = System.currentTimeMillis();
		sort.selectionSortMost(stateList);
		//long endMillisec = System.currentTimeMillis();
		//long time = endMillisec - startMillisec;
		
		for (int i = 0; i < 50; i++){
			System.out.println(stateList.get(i));
		}
	}
	
	/** Method answerPrompt6       lists all cities matching a name
	 * 								sorted by population with Selection Sort
	*/
	public void answerPrompt6(String cityName){
		ArrayList<City> cityList = new ArrayList<City>();
		for (int j = 0; j < cities.size(); j++){
			//adds City to list only if City matches user's city
			if (cities.get(j).getName().equals(cityName)){
				cityList.add(cities.get(j));
			}
			else;
		}
		SortMethods sort = new SortMethods();
		
		sort.selectionSortMost(cityList);

		
		if (cityList.size() >= 50){
			for (int i = 0; i < 50; i++){
				System.out.println(cityList.get(i));
			}
		}
		
		else {
			for (int k = 0; k < cityList.size(); k++){
				System.out.println(cityList.get(k));
			}
		}
		//System.out.println("Elapsed Time " + time + " milliseconds");
	}
	

	public static void main (String[] args){
		Population pop = new Population();
		pop.printIntroduction();
		pop.printMenu();
		int choice = Prompt.getInt("Enter selection ");
		
		//begin to initiate user's desired choice from 1-6 or 9
		if (choice == 1){
			System.out.println();
			System.out.println("Fifty least populous cities");
			pop.loadCities();
			pop.answerPrompt1();
		}
		else if (choice == 2){
			System.out.println();
			System.out.println("Fifty most populous cities");
			pop.loadCities();
			pop.answerPrompt2();
		}
		else if (choice == 3){
			System.out.println();
			System.out.println("Fifty cities sorted by name");
			pop.loadCities();
			pop.answerPrompt3();
		}
		else if (choice == 4) {
			System.out.println();
			System.out.println("Fifty cities sorted by name descending");
			pop.loadCities();
			pop.answerPrompt4();
		}
		else if (choice == 5){
			//System.out.println();
			pop.loadCities();
			String stateN = pop.getStateName();
			System.out.println("Fifty most populous cities in " + stateN);
			pop.answerPrompt5(stateN);
			
		}
		else if (choice == 6){
			pop.loadCities();
			String cityN = pop.getCityName();
			System.out.println("City " + cityN + " by population");
			pop.answerPrompt6(cityN);
		}
		
		else if (choice == 9){
			System.out.println("Thanks for using population!");
		}
	}
	
}
