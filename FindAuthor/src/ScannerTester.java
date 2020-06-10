import java.io.*;
import java.io.BufferedReader;

//import java.util.Scanner;
/**
 * Write a description of class ScannerTester here.
 *
 * @author Shounak Ghosh
 * @version 05/17/2018
 */
public class ScannerTester
{
	/**
	 * Main tester method
	 *
	 * @param str array of String objects
	 */
	public static void main(String[] str) throws FileNotFoundException
	{
		int count = 0;
		BufferedReader reader = new BufferedReader(new FileReader("mystery1.txt"));
		Scanner scanner = new Scanner(reader);

		while (scanner.hasNextToken())
		{
			if(count < 50)
			{
				System.out.println(scanner.nextToken());
				//count++;
			}
			else 
			{
			    break;
			}
			
		}
	}
}
