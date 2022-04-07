import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalProject {

	public static boolean validTime(String inputTime) //checks if inputTime is a valid time
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

	public static void timeSearch(String inputTime) //search for bus time and print out info about matching stop time
	{
		try
		{
			File inputFile = new File("stop_times.txt");
			Scanner scanner = new Scanner(inputFile);
			scanner.nextLine(); //skips first line of text file
			int i = 0;
			while(scanner.hasNextLine())
			{
				String[] line = scanner.nextLine().trim().split(",");
				int tripID = Integer.parseInt(line[0]);
				String arrival_time = line[1];
				arrival_time = arrival_time.trim();
				String departure_time = line[2];
				int stopID = Integer.parseInt(line[3]);
				int stopSequence = Integer.parseInt(line[4]);
				String stopHeadsign = line[5];
				int pickUpType = Integer.parseInt(line[6]);
				int dropOffType = Integer.parseInt(line[7]);
				String distance = line[line.length-1]; 		

				if(validTime(arrival_time) == true)
				{
					if(validTime(departure_time) == true)
					{
						if(inputTime.equals(arrival_time) || inputTime.equals("0"+arrival_time)) //allows user to input 0 at start 
						{
							i++;
							System.out.print("\nRESULT " + i + "\nTrip ID: " + tripID + "\nArrival Time: " + arrival_time
									+ "\nStop ID: " + stopID + "\nStop Sequence: " + stopSequence + 
									"\nStop Headsign: " + stopHeadsign + "\nPick-Up Type: " + pickUpType +
									"\nDrop-off Type: " + dropOffType + "\nShape Distance Travelled: " + distance);
							System.out.println();
						}
					}
				}
			} 
			if (i == 0) // if no matching times found
			{
				System.out.print("Sorry, there are no buses arriving at this time.\n");
			}
		}

		catch(Exception e)
		{
			System.out.print("Error reading in file.");
		}
	}

	public static String removeFlagstop(String stopName)
	{
		if(stopName.startsWith("FLAGSTOP"))
		{
			String[] temp = stopName.split(" ", 2); // separates first word from rest of string
			return temp[1] + " " + temp[0];
		}
		else
		{
			return stopName;
		}
	}
	public static String removeWBSBNBEB(String stopName)
	{
		if(stopName.startsWith("WB") || stopName.startsWith("SB") || stopName.startsWith("NB") || stopName.startsWith("EB"))
		{
			String[] temp = stopName.split(" ", 2);
			return temp[1] + " " + temp[0];
		}
		else
		{
			return stopName;
		}
		
	}

	public static void ternarySearchTree(String searchWord)
	{
		
		try
		{
			File inputFile = new File("stops.txt");
			Scanner scanner = new Scanner(inputFile);
			ArrayList<busStops> stopsArray = new ArrayList<busStops>();
			TST<busStops> newTST = new TST<busStops>(); // create new TST
			scanner.nextLine(); //skips first line of text file
			while(scanner.hasNextLine())
			{
				String[] line = scanner.nextLine().trim().split(",");
				int stopID = Integer.parseInt(line[0]);
				//int stop_code = Integer.parseInt(line[1]);
				String stop_name = line[2];
				String stopNameUpdate = removeFlagstop(stop_name); //removes 'flagstop' start of from stop_name
				String stopNameFinal = removeWBSBNBEB(stopNameUpdate);
				//System.out.println(stopNameFinal);

				stopsArray.add(new busStops(stopID, stopNameFinal));							
			}

			
			for(int index = 0; index < stopsArray.size(); index++) //adding each stopName to TST
			{
				newTST.put(stopsArray.get(index).stop_name, stopsArray.get(index));
			}
			System.out.print(newTST.size()); //check printing out right size of TST - yes (8757)

			Iterable<String> stops = newTST.keysWithPrefix(searchWord);
			System.out.println(stops);
			//int count = 0;
			for(String stop : stops)
			{
				for(int index = 0; index < stopsArray.size(); index++)
				{
					if(stop.equals(stopsArray.get(index).stop_name))
					{
						System.out.println("\nStop Name: " + stopsArray.get(index).stop_name + 
								"\nStop ID: " + stopsArray.get(index).stop_id);
					}
				}

			}
			



			/*
			if(newTST.keysWithPrefix(searchWord.toUpperCase()) != null)
			{
				System.out.println("Search result: \n" + newTST.keysWithPrefix(searchWord.toUpperCase()));
			}
			else
			{
				System.out.println("Your search was unsuccessful.");
			}
			 */

		}

		catch(Exception e)
		{
			System.out.print("Error reading in file.");
		}		
	}

	public static void shortestPath(int stopID1, int stopID2)
	{
		ArrayList<busStops> stopsArray = new ArrayList<busStops>();
		ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
		try
		{
			/*
			File inputFile = new File("stops.txt");
			Scanner scanner = new Scanner(inputFile);
			scanner.nextLine(); //skips first line of text file
			while(scanner.hasNextLine())
			{
				String[] line = scanner.nextLine().trim().split(",");
				int stopID = Integer.parseInt(line[0]);
				//int stop_code = Integer.parseInt(line[1]);
				String stop_name = line[2];
				stopsArray.add(new busStops(stopID, stop_name));	
			}
			int numberOfEdges = stopsArray.size(); //number of stops
			EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numberOfEdges);
			 */

			File inputFile1 = new File("stop_times.txt");
			Scanner scanner1 = new Scanner(inputFile1);
			scanner1.nextLine(); //skips first line of text file
			while(scanner1.hasNextLine())
			{
				String[] line = scanner1.nextLine().trim().split(",");
				int tripID = Integer.parseInt(line[0]);
				stopTimesArray.add(new StopTimes(tripID));
			}
			for(int i = 0; i < stopTimesArray.size(); i++)
			{
				if(stopTimesArray.get(i).trip_id == stopTimesArray.get(i+1).trip_id) 
				{
					//if 2 consecutive stops have same tripID - add edge

				}
			}
		}
		catch(Exception e)
		{

		}
	}

	public static void main(String[] args)
	{	

		//ArrayList<Stops> stopsArray = new ArrayList<Stops>();
		//TST<String> newTST = new TST<String>(); // create new TST
		boolean finished = false;
		System.out.println("WELCOME TO THE VANCOUVER BUS SYSTEM. ");

		while(finished == false)
		{
			System.out.print("\nType '1' if you wish to find the shortest path between two bus stops.\n"
					+ "Type '2' if you wish to search for a bus stop.\n"
					+ "Type '3' if you wish to find all trips occurring at a given arrival time.\n"
					+ "Type '4' if you would like to exit the system.\n" 
					+ "Please enter your choice here: ");
			Scanner scanner = new Scanner(System.in);

			if(scanner.hasNextInt())
			{
				int choice = scanner.nextInt();
				if(choice==1)
				{
					System.out.print("Please type the stop IDs of the 2 bus stops (separated by a comma with no spaces): ");
					String busStopSearch = scanner.next();
					String[] temp = busStopSearch.split(",");
					int busStop1 = Integer.parseInt(temp[0]);
					int busStop2 = Integer.parseInt(temp[1]);
					System.out.println("Shortest path between Stop " + busStop1 + " & Stop " + busStop2 + ": ");


					//finished = true;
				}
				if(choice==2)
				{
					System.out.print("Please enter your search: ");
					String searchWord = scanner.next();
					searchWord = searchWord.toUpperCase();
					ternarySearchTree(searchWord);
					finished = true;
				}

				if(choice==3)
				{
					boolean over = false;
					while(over == false)
					{
						System.out.print("Please enter an arrival time (in the form hh:mm:ss) to see all available trips: ");
						Scanner scanner2 = new Scanner(System.in);
						String inputTime = scanner2.next();
						if(validTime(inputTime))
						{
							timeSearch(inputTime);
							over = true;
							//finished = true;
						}
						else
						{
							System.out.println("This is not a valid time.\n");
						}
					}
					//finished = true;
				}
				if(choice==4)
				{
					System.out.println("\nGoodbye. Thank you for visiting the Vancouver bus system.");
					finished = true;
				}

			}
			else
			{
				System.out.println();
				System.out.println("Error, invalid choice! - Please enter an integer (1,2,3,4)");
			}
		}
	}
}

