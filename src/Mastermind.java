import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	public static Random rand;
	public static Scanner sc;
	
	// options
	public static int pieceCount = 4;
	public static boolean repeating = false;
	public static int guesses = 13;
	
	//
	public static char[] pieces = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	public static ArrayList<Character> code = new ArrayList<>();
	public static char[][] record = new char[guesses][pieceCount];
	public static int[][] things = new int[guesses][2];
	public static boolean codeGuessed = false;
	public static String left = "Colour", right = "Position";
	public static int currentGuess = 0;
	
	public static void main(String[] args)
	{
		System.out.println("mastermind");
		init();

		while(!codeGuessed && currentGuess < guesses)
		{
			display();
			guess();
		}
		display();
		if(currentGuess < guesses) System.out.println("OMG YOUR SO SECY");
		else System.out.println("ew");
	}
	
	public static void init()
	{
		rand = new Random();
		sc = new Scanner(System.in);
		
		options();
		restartGame();
	}
	
	public static void options()
	{
		// dont caya
	}
	
	public static void restartGame()
	{
		for(int i = 0; i < pieceCount; i++)
		{
			int ind = rand.nextInt(pieces.length);
			do
			{
				ind = rand.nextInt(pieces.length);
			}
			while(!repeating && code.contains(pieces[ind]));
			code.add(pieces[ind]);
			
		}
	}
	
	public static void display()
	{
		System.out.printf("%-" + (left.length()) + "s | %-" + (pieceCount * 4) + "s| %-" + right.length() + "s\n", left, "", right);

		for(int i = 0; i < guesses; i++)
		{
			System.out.printf("%" + (left.length()) + "s | ", things[i][0]);
			
			for(int p = 0; p < pieceCount; p++)
			{
				System.out.printf("[%c] ", record[i][p]);
			}
			
			System.out.printf("| %-" + (right.length()) + "s\n", things[i][1]);
		}
	}
	
	public static void guess()
	{
		int a = guesses-currentGuess-1;
		
		System.out.print("Aight guess bro: ");
		String guess = sc.nextLine().toLowerCase();
		
		// validate
		ArrayList<Character> b = new ArrayList<>();
		char[] c = guess.toCharArray();
		for(int i = 0; i < pieceCount; i++)
		{
			for(int j = 0; j < pieceCount; j++)
			{
				if(c[i] == code.get(j) && !b.contains(c[i]))
				{
					if(i == j) things[a][1]++;
					else things[a][0]++;
					
					b.add(c[i]);
					break;
				}
			}
			record[a][i] = c[i];
		}
		
		if(things[a][1] == pieceCount) codeGuessed = true;
		currentGuess++;
	}
}
