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
				System.out.print("Sorry, there are no buses departing at this time.");
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

		catch(Exception e)
		{
			System.out.print("Error reading in file.");
		}
	}

	public static void ternarySearchTree(String searchWord)
	{
		try
		{
			File inputFile = new File("stops.txt");
			Scanner scanner = new Scanner(inputFile);
			scanner.nextLine(); //skips first line of text file
			TST<String> newTST = new TST<String>(); // create new TST
			ArrayList<String> stopNames = new ArrayList<>();
			int i = 0;
			while(scanner.hasNextLine())
			{
				String[] line = scanner.nextLine().trim().split(",");
				String stop_name = line[2];

				//moving keyword flagstops WB,NB,SB,EB to end of word
				String[] splitStopName = stop_name.split(" ");
				String flagstop = splitStopName[0]; //might need to trim
				if(flagstop.equalsIgnoreCase("WB") || flagstop.equalsIgnoreCase("NB") || flagstop.equalsIgnoreCase("SB")
						|| flagstop.equalsIgnoreCase("EB"))
				{
					flagstop = splitStopName[splitStopName.length-1]; //put flagstop to end of stopName
					stop_name = splitStopName.toString(); //prints new layout of stop_name
				}
				//stopNames.add(stop_name);
				//newTST.put(stop_name, i);
				i++;
			}
			/*
			boolean searchSuccessful = newTST.search(searchWord); //returns true if finds item
			if(searchSuccessful)
			{
				//print out info about stop

			}
			 */

		}

		catch(Exception e)
		{
			System.out.print("Error reading in file.");
		}		
	}

	public static void main(String[] args)
	{	
		boolean finished = false;
		System.out.println("Welcome! This is the Vancouver bus system. ");
		
		while(finished == false)
		{
			System.out.print("Type '1' if you wish to find the shortest path between two bus stops.\n"
					+ "Type '2' if you wish to search for a bus stop.\n"
					+ "Type '3' if you wish to find all trips occurring at a given arrival time.\n"
					+ "Type '4' if you would like to exit the system.\n" 
					+ "Please enter your choice here: ");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

			if(choice==1)
			{

				finished = true;
			}
			if(choice==2)
			{
				/*
			System.out.print("Please enter your search: ");
			String searchWord = scanner.next();
			ternarySearchTree(searchWord);

			ArrayList<String> words = new ArrayList<>();
			words.add("hello");
			words.add("There");
			words.add("how");
			words.add("are");
			words.add("you");
			TST newTST = new TST(words);
			System.out.print("Please enter your search: ");
			Scanner scanner = new Scanner(System.in);
			String searchWord = scanner.next();
			boolean searchSuccessful = newTST.search(searchWord); //returns true if finds item
			if(searchSuccessful)
			{
				System.out.println("Search successful");
			}
			else
			{
				System.out.println("Search unsuccessful");
			}
				 */
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
						finished = true;
					}
					else
					{
						System.out.println("This is not a valid time.");
					}
				}
				
			}
			if(choice==4)
			{
				System.out.println("Goodbye. Thank you for visiting the Vancouver bus system.");
				finished = true;
			}
			else
			{
				System.out.println();
				System.out.println("Error! - Please enter a valid choice.");
			}

		}
	}
}

