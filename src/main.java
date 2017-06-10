import java.io.IOException;
import java.util.Scanner;

public class main {
	public static void main(String [] args) throws IOException
	{
		double input;
		System.out.println("Enter number of inputs(4, 8, 16, 32, ...) :- ");
		Scanner s = new Scanner(System.in);
		input = s.nextDouble();
		int stages =(int) (2.0*((Math.log(input))/Math.log(2)))-1;
		int rows=(int)(input/2.0);
		System.out.println("Enter input/output sequence:-");
	    int [] x = new int [(int)input];
	    for (int i=0;i<(int)input;i++)
	    {
	    	x[i]=s.nextInt();
	    }
		Switch sw = new Switch(rows,stages,x);
	
	}
}
