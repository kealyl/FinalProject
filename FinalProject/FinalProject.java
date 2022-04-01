//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.ResolverStyle;
//import java.util.ArrayList;
//import java.util.List;
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

	public static void readFile(String filename) //read in file
	{
		try
		{
			File inputFile = new File(filename);
			Scanner scanner = new Scanner(inputFile);
			while(scanner.hasNextLine())
			{
				String[] attributes = scanner.nextLine().trim().split(",");
				StopTimes stops = new StopTimes(Integer.parseInt(attributes[0]), attributes[1], attributes[2], Integer.parseInt(attributes[3]),
						Integer.parseInt(attributes[4]), Integer.parseInt(attributes[5]), Integer.parseInt(attributes[6]),
						Integer.parseInt(attributes[7]), Double.parseDouble(attributes[8]));
				System.out.println(stops.arrival_time);						
			}
			scanner.close();
		}	
		catch(Exception e)
		{
			System.out.print("E");

		}
	}


	/*
	public static void searchForTripsAtTime(String inputTime)
	{
		//check if valid time
		DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
				.withResolverStyle(ResolverStyle.STRICT);
		LocalTime.parse(inputTime, strictTimeFormatter);

		if(inputTime != "hh:mm:ss")
		{
			System.out.print("Error!! Please enter an acceptable time in the form hh:mm:ss - ");
		}


		else
		{
			try
			{
				List<String> listOfStrings = new ArrayList<String>(); //list that holds strings of a file
				BufferedReader bf = new BufferedReader(new FileReader("stop_times.txt")); //load data from file
				String line = bf.readLine(); //read entire line as string
				int[] tripIDArray;
				while (line != null) //checking for end of file
				{
					listOfStrings.add(line);					
					for(int i=0; i < listOfStrings.size(); i++)
					{
						String[] attributes = line.split(",");
						int trip_id = Integer.parseInt(attributes[0]);
						String arrival_time = attributes[1];
						String departure_time = attributes[2];
						int stop_id = Integer.parseInt(attributes[3]);
						int stop_sequence = Integer.parseInt(attributes[4]);
						int stop_headsign = Integer.parseInt(attributes[5]); //?? not sure what type
						int pickup_type = Integer.parseInt(attributes[6]);
						int drop_off_type = Integer.parseInt(attributes[7]);
						double shape_dist_travelled = Double.parseDouble(attributes[8]);

						tripIDArray = new int[listOfStrings.size()];
						for(int j=0; j < listOfStrings.size(); j++)
						{
							tripIDArray[j] = trip_id;
						}		
						insertionSort(tripIDArray);

						if(departure_time == inputTime)
						{
							System.out.println("Trip ID: " + trip_id + "\nArrival Time: " + arrival_time + 
									"\nDeparture Time: " + departure_time + "\nStop ID: " + stop_id + "\nStop Sequence: " 
									+ stop_sequence + "\nStop Headsign: " + stop_headsign + "\nPick-up type: " + 
									pickup_type + "\nDrop-off type: " + drop_off_type + "Shape Distance Travelled: " + 
									shape_dist_travelled);
							System.out.println();
						}
					}

				}
				bf.close();
			}
			catch (IOException e)
			{
				System.out.print("Error!!");
			}
		}
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
	*/

	public static void main(String[] args)
	{
		/*
		System.out.print("Please insert a time: ");
		Scanner input = new Scanner(System.in);
		String inputTime = input.next();
		searchForTripsAtTime(inputTime);
		input.close();
		*/
		readFile("stop_times.txt");
	}
}


