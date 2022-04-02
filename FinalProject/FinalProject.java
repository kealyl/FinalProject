import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalProject {

	public static boolean validTime(String inputTime) 
	{
		String timeFormat = "((\\s?)[0-9]|[01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(timeFormat);
		if(inputTime == null) 
		{
			return false;
		}
		Matcher matcher = pattern.matcher(inputTime);
		return matcher.matches();       
	}

	public static int[] insertionSort(int array[])
	{
		int temp = 0;
		for(int i=1; i < array.length; i++)
		{
			for(int j=i; j > 0; j--)
			{
				if(array[j] < array[j-1])
				{
					temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
		}
		return array;
	}

	public static void readFile(String filename, String inputTime) //read in file
	{
		try
		{
			File inputFile = new File(filename);
			Scanner scanner = new Scanner(inputFile);
			ArrayList<String> validArrivalTimes = new ArrayList<String>();
			ArrayList<Integer> tripIDs = new ArrayList<Integer>();
			
			int i = 0 ;
			while(scanner.hasNextLine())
			{
				if(i == 0) //ignores first line of file
				{
					i++;
				}
				else
				{
					String[] attributes = scanner.nextLine().trim().split(",");
					
					StopTimes stops = new StopTimes(Integer.parseInt(attributes[0]), attributes[1], attributes[2], Integer.parseInt(attributes[3]),
							Integer.parseInt(attributes[4]), Integer.parseInt(attributes[5]), Integer.parseInt(attributes[6]),
							Integer.parseInt(attributes[7]), Double.parseDouble(attributes[8]));
					//System.out.println(stops.arrival_time);
					
					//^sort out this constructor call

					//String arrival_time = attributes[1];
					int count = 0;
					if(validTime(stops.arrival_time) == true) //checks that times are valid times
					{
						validArrivalTimes.add(stops.arrival_time);//creates arrayList of validTimes
						//System.out.println(validArrivalTimes.get(validArrivalTimes.size()-1)); //print most recent item added to arraylist
						tripIDs.add(stops.trip_id); //create arraylist of trips IDs
						if(inputTime == stops.arrival_time)
						{
							count++;
							String printDetails = "Result " + count + "\nTrip ID: " + stops.trip_id + 
									"\nArrival Time: " + stops.arrival_time; //add in other details here 
							System.out.println(printDetails);
						}
						
					}
					i++;
					//create array of trip IDs
					//sort array using insertion sort

				} 
				//converting trip ID array list to array & sorting using insertionSort
				/*
				int[] tripIDArray = new int [tripIDs.size()];
				Iterator<Integer> iterator = tripIDs.iterator();
				for(int j=0; iterator.hasNext(); j++)
				{
					tripIDArray[j] = iterator.next();
				}
				int[] sortedTripIDs = insertionSort(tripIDArray);
				*/
			}
			scanner.close();
		}	
		catch(Exception e)
		{
			System.out.print("Error reading in file.");
		}
	}

	public static void tripSearch(String inputTime)
	{
		if(validTime(inputTime) == true)
		{
			readFile("stop_times.txt", inputTime);
			//if inputTime == stops.arrival_time > print out all info about stops

		}
		else
		{
			System.out.println("Error - Invalid Time! Please enter a time in the form 'hh:mm:ss'.");
		}
	}

	public static void main(String[] args)
	{
		
		System.out.print("Welcome! This is the Vancouver bus system. \n"
				+ "Type '1' if you wish to find the shortest path between two bus stops.\n"
				+ "Type '2' if you wish to search for a bus stop.\n"
				+ "Type '3' if you wish to find all trips occurring at a given arrival time.\n"
				+ "Type '4' if you would like to exit the system.\n" 
				+ "Please enter your choice here: ");
		Scanner scnr = new Scanner(System.in);
		int choice = scnr.nextInt();

		if(choice==1)
		{

		}
		if(choice==2)
		{
			System.out.print("Please enter an arrival time to see all available trips: ");
			String inputTime = scnr.next();
			tripSearch(inputTime);
		}
		if(choice==3)
		{

		}
		if(choice==4)
		{
			System.out.println("Goodbye. Thank you for visiting the Vancouver bus system.");
		}
		else
		{
			System.out.println("Error! - Please enter a valid choice, 1,2,3 or 4.");
		}
		 


	}
}


