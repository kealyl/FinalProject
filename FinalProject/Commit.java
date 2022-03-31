import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commit 
{
	public String searchForTripsAtTime(String timeInput)
	{

		if(timeInput != "hh:mm:ss")
		{
			System.out.print("Error!! Please enter an acceptable time in the form hh:mm:ss - ");
		}
		else
		{
			try
			{
				String[] listOfLines = new String[1700000];
				BufferedReader bf = new BufferedReader(new FileReader("stop_times.txt")); //load data from file
				String line = bf.readLine(); //read entire line as string
				while (line != null) //checking for end of file
				{
					
				}
				double[] array = 
				
			}
			catch (IOException e)
			{
				System.out.print("Error!!");
			}
		}


	}
	public int[] insertionSort(int array[])
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

}
