import java.io.File;
import java.util.ArrayList;
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

	public static void readFile(String filename) //read in file
	{
		try
		{
			File inputFile = new File(filename);
			Scanner scanner = new Scanner(inputFile);
			ArrayList<String> validArrivalTimes = new ArrayList<String>();
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
					/*
					StopTimes stops = new StopTimes(Integer.parseInt(attributes[0]), attributes[1], attributes[2], Integer.parseInt(attributes[3]),
							Integer.parseInt(attributes[4]), Integer.parseInt(attributes[5]), Integer.parseInt(attributes[6]),
							Integer.parseInt(attributes[7]), Double.parseDouble(attributes[8]));
					System.out.println(stops.arrival_time);
					*/
					//^sort out this constructor


					//ArrayList<Integer> tripIDs = new ArrayList<Integer>();
					String arrival_time = attributes[1];
					
					if(validTime(arrival_time) == true) //checks that times are valid times
					{
						validArrivalTimes.add(arrival_time);//creates arrayList of validTimes
						System.out.println(validArrivalTimes.get(validArrivalTimes.size()-1));
					}
					//create array of trip IDs
					//sort array using insertion sort
					//print out info about stops that match the arrival time of user input
					
					i++;
				}
			} 
			scanner.close();

		}	
		catch(Exception e)
		{
			System.out.print("E");

		}
	}

	public static void main(String[] args)
	{
		/*
		System.out.println("Welcome! This is the Vancouver bus system. \n"
				+ "Type '1' if you wish to find shortest path... \n"
				+ "Type '2' if you wish to search for a bus stop... \n"
				+ "Type '3' if you wish to find all trips at a specific time...\n");
		Scanner scnr = new Scanner(System.in);
		int choice = scnr.nextInt();
		
		if(choice==1)
		{
			
		}
		if(choice==2)
		{
			
		}
		if(choice==3)
		{
			
		}
		else
		{
			System.out.println("Error! - Please insert a valid choice.");
		}
		*/
		
		readFile("stop_times.txt");
	}
}


