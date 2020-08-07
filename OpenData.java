/**
 * Program that support 3 different types of data queries and show the school name, enrollment number, with or without shared space, and % free lunch of the schools that meet the requirement based on user input
 *
 * @author Yaojia Ju
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenData {

	//the main method
	public static void main(String[] args) throws FileNotFoundException {
		String answer = displayMessage1();
		String fileName = "5.csv";
		String[][] processedData = processData(fileName);

		//call the function of finding schools with common space
		System.out.println();
		String[][] result1 = searchDataCommonSpace(answer,processedData);
		displayMessage2(answer, result1);


		//call the function of finding schools with the number of enrollment in certain range
		System.out.println();
		String[] answer3 = displayMessage3();
		System.out.println();
		String[][]result2 = searchDataEnrollment(answer3,processedData);
		displayMessage5(answer3, result2);

		//call the function of finding schools with the percentage of FreeLunch in certain range
		System.out.println();
		String[] answer4 = displayMessage4();
		System.out.println();
		String[][]result3 = searchDataFreeLunch(answer4,processedData);
		displayMessage6(answer4, result3);

		
	}

	/**
	 * Display messages regarding the information of schools that have enrollment number within the required range to user
	 * @param answer the response from user, as a string array
	 * @param result the processed data, as a two-dimensional array
	 */

	public static void displayMessage5(String[] answer, String[][] result) {
		
		System.out.print("Found " + result.length + " high schools that have enrollment number within the range of " + answer[0] + "-" + answer[1]+ "   ");
		

		int end;
		if (result.length >=10) end=10;
		else end = result.length;
		int initial = 1;
			for (int i=0; i<result.length && result.length - initial>=0; i++) {
				System.out.println("Showing results " + (initial) + "-" + (end) + " :");
				for (int x=(initial-1); x<=(end-1); x++) {
					System.out.printf("%-85s,%-15s,%-20s",result[x][0],result[x][2],result[x][3]);
					System.out.println();
				}

				initial += 10;
				if (result.length - initial< 10 && result.length - initial>=0) {
					end = result.length;
					System.out.println("...hit enter to see the next " + (result.length - initial+1) + "...");
				}

				else if (result.length - initial>= 10){
					end = initial + 9;
					System.out.println("...hit enter to see the next 10...");
				}
			


				Scanner scn = new Scanner(System.in);
				String response = scn.nextLine();
				if (response.contentEquals("")) continue;
				else break;	
			}
	}

	/**
	 * Display messages regarding the information of schools that have the percentage of free lunch within the required range to user
	 * @param answer the response from user, as a string array
	 * @param result the processed data, as a two-dimensional array
	 */

	public static void displayMessage6(String[] answer, String[][] result) {
		
		System.out.print("Found " + result.length + " high schools that have the percentage of free lunch within the range of " + answer[0] + "-" + answer[1]+ "   ");
		

		int end;
		if (result.length >=10) end=10;
		else end = result.length;
		int initial = 1;
			for (int i=0; i<result.length && result.length - initial>=0; i++) {
				System.out.println("Showing results " + (initial) + "-" + (end) + " :");
				for (int x=(initial-1); x<=(end-1); x++) {
					System.out.printf("%-85s,%-15s,%-20s",result[x][0],result[x][1],result[x][3]);
					System.out.println();
				}

				initial += 10;
				if (result.length - initial< 10 && result.length - initial>=0) {
					end = result.length;
					System.out.println("...hit enter to see the next " + (result.length - initial+1) + "...");
				}

				else if (result.length - initial>= 10){
					end = initial + 9;
					System.out.println("...hit enter to see the next 10...");
				}
			


				Scanner scn = new Scanner(System.in);
				String response = scn.nextLine();
				if (response.contentEquals("")) continue;
				else break;	
			}
	}
 	
 	/**
	 * Display messages regarding the information of schools that with or without the shared space to user
	 * @param answer the response from user, as a string array
	 * @param result the processed data, as a two-dimensional array
	 */

	public static void displayMessage2(String answer, String[][] result) {
		if (answer.equals ("yes")) {
			System.out.print("Found " + result.length + " high schools with common space.   ");
		}

		else if (answer.equals ("no")) {
			System.out.print("Found " + result.length + " high schools without common space.   ");
		}

		int end;
		if (result.length >=10) end=10;
		else end = result.length;
		int initial = 1;
		for (int i=0; i<result.length && result.length - initial>0; i++) {
			System.out.println("Showing results " + (initial) + "-" + (end) + " :");
			for (int x=(initial-1); x<=(end-1); x++) {
				System.out.printf("%-85s,%-15s,%-20s",result[x][0],result[x][1],result[x][2]);
				System.out.println();
			}

			initial += 10;
			if (result.length - initial< 10 && result.length - initial>=0) {
				end = result.length;
				System.out.println("...hit enter to see the next " + (result.length - initial+1) + "...");
			}

			else if (result.length - initial>= 10){
				end = initial + 9;
				System.out.println("...hit enter to see the next 10...");
			}

			Scanner scn = new Scanner(System.in);
			String response = scn.nextLine();
			if (response.contentEquals("")) continue;
			else break;
				
			
				
		}


	}


	/**
	 * Search for the schools that have or don't have common space
	 * @param answer the response from user, as a string array
	 * @param dataset the processed data, as a two-dimensional array
	 * @return a two dimensional array that store the information of schools that meet the requirement
	 */

	public static String[][] searchDataCommonSpace(String answer, String[][] dataset) {

		int n = 0;
		String[][] result = new String[1][1];
		if (answer.equals("yes")) {
			for (String[] number : dataset) {
				if (number[3].equals("Yes")) {
					n++;
				}
			}

			//create a new array to store the data needed to be displayed
			result = new String[n][dataset[0].length];

			int m = 0;

			for (int i = 0; i<dataset.length; i++) {
				if (dataset[i][3].equals("Yes")) {
					result[m] = dataset[i];
					m++;
				}
			}
		}
		else if (answer.equals("no")) {
			for (String[] number : dataset) {
				if (number[3].equals("No")) {
					n++;
				}
			}

			//create a new array to store the data needed to be displayed
			result = new String[n][dataset[0].length];

			int m = 0;

			for (int i = 0; i<dataset.length; i++) {
				if (dataset[i][3].equals("No")) {
					result[m] = dataset[i];
					m++;
				}
			}
		}
		return result;
	}


	/**
	 * Search for the schools that have enrollment number within a required range
	 * @param answer2 the response from user, as a string array
	 * @param dataset the processed data, as a two-dimensional array
	 * @return a two dimensional array that store the information of schools that meet the requirement
	 */


	public static String[][]searchDataEnrollment(String[] answer2, String[][] dataset) {

		int n = 0;

		String[][] result = new String[1][1];
		

		for (String[] number : dataset) {
			int num1 = Integer.parseInt(number[1]);
			int range1 = Integer.parseInt(answer2[0]);
			int range2 = Integer.parseInt(answer2[1]);
			if (num1>=range1 && num1<=range2) {
				n++;
			}
		}

		//create a new array to store the data needed to be displayed
		result = new String[n][dataset[0].length];

		int m = 0;

		for (int i = 0; i<dataset.length; i++) {
			int num1 = Integer.parseInt(dataset[i][1]);
			int range1 = Integer.parseInt(answer2[0]);
			int range2 = Integer.parseInt(answer2[1]);
			if (num1>=range1 && num1<=range2) {
				result[m] = dataset[i];
				m++;
			}
		}
		
		return result;

	}

	/**
	 * Search for the schools that have the percentage of free lunch within a required range
	 * @param answer3 the response from user, as a string array
	 * @param dataset the processed data, as a two-dimensional array
	 * @return a two dimensional array that store the information of schools that meet the requirement
	 */

	public static String[][]searchDataFreeLunch(String[] answer3, String[][] dataset) {

		int n = 0;

		String[][] result = new String[1][1];
		

		for (String[] number : dataset) {
			String[] stripSign = number[1].split("%");
			String newChar = stripSign[0];
			int num1 = Integer.parseInt(newChar);
			int range1 = Integer.parseInt(answer3[0]);
			int range2 = Integer.parseInt(answer3[1]);
			if (num1>=range1 && num1<=range2) {
				n++;
			}
		}

		//create a new array to store the data needed to be displayed
		result = new String[n][dataset[0].length];

		int m = 0;

		for (int i = 0; i<dataset.length; i++) {
			String[] stripSign = dataset[i][1].split("%");
			String newChar = stripSign[0];
			int num1 = Integer.parseInt(newChar);
			int range1 = Integer.parseInt(answer3[0]);
			int range2 = Integer.parseInt(answer3[1]);
			if (num1>=range1 && num1<=range2) {
				result[m] = dataset[i];
				m++;
			}
		}
		
		return result;

	}
	

	/**
	 * Input the data from the file and convert it into a two dimensional array
	 * @param fileName the name of the file to be opened
	 * @return a two dimensional array that store the information of all the schools
	 */
	

	//create a function to read data from the file and convert to a multidimensional array
	public static String[][] processData(String fileName) throws FileNotFoundException {
		//create a new Scanner to read data from the file
		Scanner read = new Scanner(new File(fileName));
		//use a while loop to read data in each line
		int count = -1;

		boolean sign = false;

		String dataIn = "";

		while (read.hasNextLine()) {
			String line = read.nextLine();
			count++;
			if (sign) {
				dataIn += (line + "\n");
			}
			sign = true;
		}

		//split the data
		String[] processed = dataIn.split("\n");
		int test = processed[0].split(",").length;

		//create a multidimensional array to store data
		String[][] data = new String[count][test];

		int linenum = 0;

		for (String[] number: data) {

			String[] items = processed[linenum].split(",");

			if (linenum<count) {
				linenum++;
			}

			else
				break;

			for (int i=0; i<number.length; i++) {
				number[i] = items[i];
			}
		}
		

		return data;
		
	}


	/**
	 * Display the message that ask user for a requiremnt regarding shared space to perform data queries
	 * @return the response from the user, a String
	 */
	

	public static String displayMessage1() {
		System.out.println("Welcome to the 2013-2014 High School School Quality Results Finder App.");
		//the URL used is https://data.cityofnewyork.us/Education/2013-2014-School-Quality-Reports-Results-for-High-/bm9v-cvch
		System.out.println("This app mines data from https://data.cityofnewyork.us/Education/2013-2014-School-Quality-Reports-Results-for-High-/bm9v-cvch");
		//the three columns used are School Name, Enrollment, Shared Space, and % Free Lunch
		System.out.println("We show you the school name, the number of enrollment of each school, and percentage of free lunch.");
		System.out.println();	
		System.out.print("Please enter 'yes' to see the information of schools with common space or 'no' to see the one without common space: ");
		Scanner scn = new Scanner(System.in);
		String response = scn.nextLine().toLowerCase();
		System.out.println();
		return response;
	}

	/**
	 * Display the message that ask user for a requiremnt regarding enrollment to perform data queries
	 * @return the response from the user, a String
	 */
	
	public static String[] displayMessage3() {
		System.out.println("Welcome to the 2013-2014 High School School Quality Results Finder App.");
		//the URL used is https://data.cityofnewyork.us/Education/2013-2014-School-Quality-Reports-Results-for-High-/bm9v-cvch
		System.out.println("This app mines data from https://data.cityofnewyork.us/Education/2013-2014-School-Quality-Reports-Results-for-High-/bm9v-cvch");
		//the three columns used are School Name, Enrollment, Shared Space, and % Free Lunch
		System.out.println("We show you the school name, the percentage of free lunch, and whether the school has common space or not.");
		System.out.println();
		System.out.print("Please enter the range of enrollment to see the information of schools that meets the requirement.(e.g.: 80-200): ");
		Scanner scn = new Scanner(System.in);
		String[] response = scn.nextLine().split("-");
		System.out.println();
		return response;
	}

	/**
	 * Display the message that ask user for a requiremnt regarding percentage of freelunch to perform data queries
	 * @return the response from the user, a String
	 */

	public static String[] displayMessage4() {
		System.out.println("Welcome to the 2013-2014 High School School Quality Results Finder App.");
		//the URL used is https://data.cityofnewyork.us/Education/2013-2014-School-Quality-Reports-Results-for-High-/bm9v-cvch
		System.out.println("This app mines data from https://data.cityofnewyork.us/Education/2013-2014-School-Quality-Reports-Results-for-High-/bm9v-cvch");
		//the three columns used are School Name, Enrollment, Shared Space, and % Free Lunch
		System.out.println("We show you the school name, the number of enrollment of each school, and whether the school has common space or not.");
		System.out.println();
		System.out.print("Please enter the range of percentage of freelunch to see the information of schools that meets the requirement.(e.g.: enter 60-80 for 60% to 80%): ");
		Scanner scn = new Scanner(System.in);
		String[] response = scn.nextLine().split("-");
		System.out.println();
		return response;
	}

}