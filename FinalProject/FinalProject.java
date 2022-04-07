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
				//System.out.println(stopNameFinal); //prints out stop names correctly

				stopsArray.add(new busStops(stopID, stopNameFinal)); //adding stops to array						
			}

			for(int index = 0; index < stopsArray.size(); index++) //adding each stopName to TST
			{
				newTST.put(stopsArray.get(index).stop_name, stopsArray.get(index));
			}
			System.out.print("TST size: " + newTST.size()); //check printing out right size of TST - yes (8757)

			Iterable<String> stops = newTST.keysWithPrefix(searchWord);
			int counter = 0;
			for (String stop : stops) 
			{
				counter++;
				if(counter == 1) 
				{
					System.out.println("Bus stop Search Successful:");
				}
				else if(counter == 0) 
				{
					System.out.print("Sorry. We could not find any bus stops matching your search.");
				}
				busStops stopOutput =  newTST.get(stop);
				System.out.println("\nStop Name: " + stopOutput.stop_name + "\nStop ID: " + stopOutput.stop_id + "\n");
			}
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
		ArrayList<Transfers> transfersArray = new ArrayList<Transfers>();
		try
		{

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
			int numberOfVertices = stopsArray.size(); //number of stops
			System.out.println("No. of Vertices: " + numberOfVertices); //8757 as expected
			EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numberOfVertices); 

			//12479 edges found by trial and error		


			File inputFile1 = new File("stop_times.txt");
			Scanner scanner1 = new Scanner(inputFile1);
			scanner1.nextLine(); //skips first line of text file
			while(scanner1.hasNextLine())
			{
				String[] line = scanner1.nextLine().trim().split(",");
				int tripID = Integer.parseInt(line[0]);
				int stopID = Integer.parseInt(line[3]);
				stopTimesArray.add(new StopTimes(tripID, stopID));
			}

			//setting edges from stop_times.txt
			for(int i = 0; i < stopTimesArray.size(); i++)
			{

				if(stopTimesArray.get(i).trip_id == stopTimesArray.get(i+1).trip_id) 
				{
					//if 2 consecutive stops have same tripID - add edge (cost 1 as comes from stop_times.txt)
					DirectedEdge edge = new DirectedEdge(stopTimesArray.get(i).stop_id, stopTimesArray.get(i+1).stop_id, 1); 
					graph.addEdge(edge);
				}
			}
			
			File inputFile2 = new File("transfers.txt");
			Scanner scanner2 = new Scanner(inputFile1);
			scanner2.nextLine(); //skips first line of text file
			while(scanner2.hasNextLine())
			{
				String[] line = scanner2.nextLine().trim().split(",");
				int from_stopID = Integer.parseInt(line[0]);
				int to_stopID = Integer.parseInt(line[1]);
				int transfer_type = Integer.parseInt(line[2]);
				int min_transfer_time = Integer.parseInt(line[3]);
				transfersArray.add(new Transfers(from_stopID, to_stopID, transfer_type, min_transfer_time));
			}
			//setting edges from transfers.txt
			for(int i = 0; i < transfersArray.size(); i++)
			{
				if(transfersArray.get(i).transfer_type == 0)
				{
					DirectedEdge edge = new DirectedEdge(transfersArray.get(i).from_stopID, transfersArray.get(i).to_stopID, 2); 
					graph.addEdge(edge);
				}
				else if(transfersArray.get(i).transfer_type == 2)
				{
					DirectedEdge edge = new DirectedEdge(transfersArray.get(i).from_stopID, transfersArray.get(i).to_stopID, (transfersArray.get(i).min_transfer_time / 100));
					graph.addEdge(edge);
				}
			}
			DijkstraSP SP = new DijkstraSP(graph, stopID1);
			SP.pathTo(stopID2); // should return shortest path from user input stop 1 to stop 2	
			//graph.E();
			//graph.V();
		}

		catch(Exception e)
		{
			System.out.print("Error reading in file.");
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
					shortestPath(busStop1, busStop2);
					//finished = true;
				}
				if(choice==2)
				{
					System.out.print("Please enter your search: ");
					String searchWord = scanner.next();
					searchWord = searchWord.toUpperCase();
					ternarySearchTree(searchWord);
					//finished = true;
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

