import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Switch {
	node [][] sw;
	int rows;
	int rows1;
	int cols;
	int cols1;
	int [] input;
	int [] in;
	int [] out;
	Switch(int rows,int cols,int [] input) throws IOException
	{
		this.rows=rows;
		this.rows1=rows;
		this.cols=cols;
		this.input = new int [input.length];
	    this.in = new int [input.length];
	    this.out = new int [input.length];
		for(int i=0;i<input.length;i++)
		{
			this.input[i]=input[i];
			this.in[i]=-1;
			this.out[i]=-1;
		}
		sw = new node[rows][cols];

		int x=0;
		int nn=0;
		for(int i =0 ;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				
				sw[i][j]= new node(x,nn);
			}
			nn++;
			x=x+2;
		}
		/*
		for(int i =0 ;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{		
				sw[i][j].print();
			}
		}
		*/
		// type par=0 cross = 1 k d al7aga ally 3lyha aldoor
		connect( 0, 0, rows);	
	//    run(input);
		forward(0,0);
		print();
	}
	void forward(int row , int col)
	{
	
        if(sw[row][col].getInput1().isFree())
		{
			if(sw[row][col].getType()==-1)
			{
				sw[row][col].setType(0);
				sw[row][col].getInput1().setFree(false);
				sw[row][col].getOutput1().setFree(false);
				
				int target = input[row*2];
			
				int innerin= sw[row][col].getOutput1().getConnected().getNum();
			
				backword(target, cols-1-col, innerin);
				
			}
			else if(sw[row][col].getType()==0)
			{
				sw[row][col].getInput1().setFree(false);
				sw[row][col].getOutput1().setFree(false);
				int target = input[row*2];
	    		int innerin= sw[row][col].getOutput1().getConnected().getNum();
				
				backword(target, cols-1-col, innerin);
				// call function back
			}
			else if(sw[row][col].getType()==1)
			{
				sw[row][col].getInput1().setFree(false);
				sw[row][col].getOutput2().setFree(false);
				int target = input[(row*2)];
				int innerin= sw[row][col].getOutput2().getConnected().getNum();
			    
				backword(target, cols-1-col, innerin);
				// call function back
			}
			else
			{
				System.out.println("SomeThing Wrong with The Type");
			}
		}
		else if(sw[row][col].getInput2().isFree())
		{
			if(sw[row][col].getType()==-1)
			{
				sw[row][col].setType(0);
				sw[row][col].getInput2().setFree(false);
				sw[row][col].getOutput2().setFree(false);
				int target = input[(row*2)+1];
				int innerin= sw[row][col].getOutput2().getConnected().getNum();
				
				
				backword(target, cols-1-col, innerin);
				// call function back
			}
			else if(sw[row][col].getType()==0)
			{
				sw[row][col].getInput2().setFree(false);
				sw[row][col].getOutput2().setFree(false);
				int target = input[(row*2)+1];
				int innerin= sw[row][col].getOutput2().getConnected().getNum();
				
				backword(target, cols-1-col, innerin);
				// call function back
			}
			else if(sw[row][col].getType()==1)
			{
				sw[row][col].getInput2().setFree(false);
				sw[row][col].getOutput1().setFree(false);
				int target = input[(row*2)+1];
				int innerin= sw[row][col].getOutput1().getConnected().getNum();
					
				
				backword(target, cols-1-col, innerin);
				// call function back
			}
			else
			{
				System.out.println("SomeThing Wrong with The Type");
			}
		}
		else
		{
			// choose random or go to next level if there are no choices
			int newIndex = -1;
			for(int i=0;i<input.length;i++)
			{
				
				if(input[i]!=-1)
				{
					newIndex = i;
					break;
				}
			}
		
			if(newIndex==-1)
			{
				
				if((col+1)!=((cols-1)/2))
				{
					input=out;
					
					input = new int[out.length];
					for(int i=0;i<input.length;i++)
					{
						input[i]=out[i];
						in[i]=-1;
						out[i]=-1;
					}
					for(int i=0;i<input.length;i++)
					{
						System.out.println(input[i]);
						
					}
					forward(0, col+1);		
				}
				else
				{
					
					System.out.println("**********Out*******");
					for(int i=0;i<input.length;i++)
					{
						System.out.print(out[i]+" ");
					}
					System.out.println();
					System.out.println("**********In******");
					for(int i=0;i<input.length;i++)
					{
						System.out.print(in[i]+" ");
					}
					
					System.out.println();
					for(int i=0;i<in.length;i++)
					{
						if(in[i]!=out[i])
						{
							if((i/2)==(i-1)/2)
							{
								if(in[i]!=-1)
								{
									sw[i/2][(cols-1)/2].setType(1);
								}
							}
							else
							{
								sw[i/2][(cols-1)/2].setType(1);
							}
							
						}
						else
						{
							
							if((i/2)==(i-1)/2)
							{
								if(in[i]!=-1)
								{
									sw[i/2][(cols-1)/2].setType(0);
								}
							}
							else
							{
								sw[i/2][(cols-1)/2].setType(0);
							}
						}
					//	i++;
					}
				}	
			}
			else
			{
				row = newIndex/2;
				rows=(rows1*3)/2;
				// hna hn3ml nfs alklam ally 3mlnah tany
				forward(row, col);
			}
		}
	}
	void print() throws IOException
	{
		FileWriter fr = new FileWriter("switch.txt");
		BufferedWriter br = new BufferedWriter(fr);
		System.out.println("SWITCHES AFTER CONFIGURATION:-");
		for(int i =0;i<rows1; i++)
		{
			for (int j=0;j<cols;j++)
			{
			      if(sw[i][j].getType()==0)
			      {
			    	//  System.out.print("bar ");
			    	  System.out.print("= ");
			    	  br.write("= ");
			    	  br.flush();
			      }
			      else if(sw[i][j].getType()==1)
			      {
			    	//  System.out.print("cross ");
			    	  System.out.print("x ");
			    	  br.write("x ");
			    	  br.flush();
			      }
			      else
			      {
			    	  System.out.print("ERROR ");
			    	  br.write("ERROR ");
			    	  br.flush();
			      }
			}
			br.newLine();
			br.flush();
			 
			System.out.println(" ");
		}
		br.close();
	}
	void backword(int target, int col, int innerin)
	{
		int outinner;
	  int row  = target/2;
	//	System.out.println("Calling back "+ row+" "+ col);
	  if(sw[row][col].getType()==-1)
	  {
	//		System.out.println("target = "+target);
		  if(target%2==0) // ana foo2
		  {
			if(innerin<rows) //gyly mn foo2
			{
				sw[row][col].setType(0);
				
				for(int i=0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
					}
				}
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput1().getConnected().getNum();
				sw[row][col].getOutput1().setFree(false);
				sw[row][col].getInput1().setFree(false);
				if(sw[row][col].getOutput2().isFree())
				{
					sw[row][col].getOutput2().setFree(false);
					sw[row][col].getInput2().setFree(false);
					outinner = sw[row][col].getInput2().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getOutput2().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput2().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech1 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{
							
                          
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech2 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
			}
			else // ana foo2 w gyly 7aga mn t7t
			{
				for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
				
				sw[row][col].setType(1);
				
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput1().getConnected().getNum();
				// 3awz a call al foreword
				sw[row][col].getInput1().setFree(false);
				sw[row][col].getOutput1().setFree(false);
				
				if(sw[row][col].getOutput2().isFree())
				{
					sw[row][col].getInput2().setFree(false);
					sw[row][col].getOutput2().setFree(false);
					
				    outinner = sw[row][col].getInput2().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getOutput2().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput2().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							
							else
							{
								System.out.println("tech3 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								forward(btar/2,cols-col-1);
							}
							
							else
							{
								System.out.println("tech4 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}	
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
				
			}
		  }
		  else // ana ally t7t
		  {
			if(innerin<rows) // gyly mn foo2
			{
				sw[row][col].setType(1);
				sw[row][col].getOutput2().setFree(false);
				sw[row][col].getInput1().setFree(false);
				
				for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput1().getConnected().getNum();
				
				if(sw[row][col].getOutput1().isFree())
				{
					sw[row][col].getOutput1().setFree(false);
					sw[row][col].getInput2().setFree(false);
					outinner = sw[row][col].getInput2().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getOutput1().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						input[btar]=-1;
						innerin = sw[btar/2][cols-col-1].getOutput2().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech5 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech6 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
				
				
				
				
			}
		    else // gyly mn t7t w ana t7t
			{
		    	sw[row][col].setType(0);
		    	for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
				
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput2().getConnected().getNum();
				
				
				
				sw[row][col].getOutput2().setFree(false);
				sw[row][col].getInput2().setFree(false);
				if(sw[row][col].getOutput1().isFree())
				{
					sw[row][col].getOutput1().setFree(false);
					sw[row][col].getInput1().setFree(false);
					outinner = sw[row][col].getInput1().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getInput1().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput1().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech7 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{

							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech8 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
				
				
				
				
			}
		  }
	  }
	  
	  
	  else if(sw[row][col].getType()==0)
	  {
		  if(target%2==0)
		  {
			if(innerin<rows)
			{
				for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput1().getConnected().getNum();
				// 3awz a call al foreword
				
				sw[row][col].getOutput1().setFree(false);
				sw[row][col].getInput1().setFree(false);
				if(sw[row][col].getOutput2().isFree())
				{
					sw[row][col].getOutput2().setFree(false);
					sw[row][col].getInput2().setFree(false);
					outinner = sw[row][col].getInput2().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getInput2().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput2().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech9 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{

							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput2().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech10 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
				
				
				
				
			}
			else
			{
				System.out.println("I'm in a par node and you req it to be cross");
		
			}
		  }
		  else // ana ally t7t
		  {
			if(innerin<rows) // gyly mn foo2
			{
				System.out.println("I'm in a par node and you req it to be cross");
			}
		    else // gyly mn t7t w ana t7t
			{
				
		    	for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
		    	
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput2().getConnected().getNum();
		
				sw[row][col].getOutput2().setFree(false);
				sw[row][col].getInput2().setFree(false);
				if(sw[row][col].getOutput1().isFree())
				{
					sw[row][col].getOutput1().setFree(false);
					sw[row][col].getInput1().setFree(false);
					outinner = sw[row][col].getInput1().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getInput1().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput1().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech11 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{

							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech12 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
				
				
				
				

				
				
			}
		  }
	  }
	  else if(sw[row][col].getType()==1)
	  {

		  if(target%2==0)
		  {
			if(innerin<rows)
			{
				System.out.println("I'm cross and you req it to be par");
			}
			else
			{
				for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
				
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput2().getConnected().getNum();
				
				
				sw[row][col].getInput2().setFree(false);
				sw[row][col].getOutput1().setFree(false);
				
				if(sw[row][col].getOutput2().isFree())
				{
					sw[row][col].getInput1().setFree(false);
					sw[row][col].getOutput2().setFree(false);
					outinner = sw[row][col].getInput1().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getInput2().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput1().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech13 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{

							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech14 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}	
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}
				
				
				// 3awz a call al foreword
			}
		  }
		  else // ana ally t7t
		  {
			if(innerin<rows) // gyly mn foo2
			{
				sw[row][col].setType(1);
				
				for(int i = 0;i<input.length;i++)
				{
					if(input[i]==target)
					{
						input[i]=-1;
						break;
					}
				}
				
				in[innerin]=innerin;
				out[innerin]=sw[row][col].getInput1().getConnected().getNum();
				
				
				
				sw[row][col].getInput2().setFree(false);
				sw[row][col].getOutput1().setFree(false);
				
				if(sw[row][col].getOutput2().isFree())
				{
					sw[row][col].getInput1().setFree(false);
					sw[row][col].getOutput2().setFree(false);
					outinner = sw[row][col].getInput1().getConnected().getNum();
					int btar =-1;
					for(int i=0;i<input.length;i++)
					{
						if(input[i]==sw[row][col].getInput2().getNum())
						{
							btar=i;
						}
					}
					if(btar==-1)
					{
						System.out.println("that's not working good ");
						forward(0,cols-col-1);
						
					}
					else // kda ana hb3t 7aga wara
					{
						innerin = sw[btar/2][cols-col-1].getOutput2().getConnected().getNum();
						out[innerin]=outinner;
						in[innerin]=innerin;
						input[btar]=-1;
						if(btar%2==0)
						{
							if(sw[btar/2][cols-col-1].getType()==0||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(0);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput1().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech15 is bad");
								forward(0,cols-col-1);
							}
						}
						else
						{

							if(sw[btar/2][cols-col-1].getType()==1||sw[btar/2][cols-col-1].getType()==-1)
							{
								sw[btar/2][cols-col-1].setType(1);
								sw[btar/2][cols-col-1].getOutput1().setFree(false);
								sw[btar/2][cols-col-1].getInput2().setFree(false);
								
								forward(btar/2,cols-col-1);
							}
							else
							{
								System.out.println("tech16 is bad");
								forward(0,cols-col-1);
							}
						}
					
					}	
				}
				else // ana l2yt ally m3aya mlyan
				{
					forward(0,cols-col-1);
				}		
			}
		    else // gyly mn t7t w ana t7t
			{
		    	System.out.println("I'm cross and you req it to be par");
			}
		  }
	   
	  }
	  else
	  {
		  System.out.println("Error in Type of node");
	  }
	  
	}
	
	void inoutsort()
	{
		for(int i=0;i<in.length-1;i++)
		{
			if(in[i]>in[i+1])
			{
				int temp = in[i];
				in[i]= in[i+1];
				in[i+1] = temp;
				
				int temp1 = out[i];
				out[i]= out[i+1];
				out[i+1] = temp1;
				i=-1;
			}
		}
	}

	
   void connect(int col,int first,int last)
   {
	   int first1 = first;
	   if(first1>0){first1=first1/2;}
	   int row = last-first;
	 if(col<cols/2)
	 {
		 System.out.println("I'm in col: "+col );
		 boolean even=true;
		 for(int i=first;i<last;i++)
		 {
			 System.out.println("row"+ i);
				sw[i][col].getInput1().setFree(true);
				sw[i][col].getInput2().setFree(true);
			    if(even)
				{
			      System.out.println("   connected first to: "+sw[(i/2)+first1][col+1].getInput1().getNum());
				  sw[i][col].getOutput1().setConnected(sw[(i/2)+first1][col+1].getInput1());
				  sw[(i/2)+first1][col+1].getInput1().setConnected(sw[i][col].getOutput1());
		          System.out.println("   connected sec to "+sw[first1+(i/2)+(row/2)][col+1].getInput1().getNum());
				  sw[i][col].getOutput2().setConnected(sw[first1+(i/2)+(row/2)][col+1].getInput1());
				  sw[first1+(i/2)+(row/2)][col+1].getInput1().setConnected(sw[i][col].getOutput2());
				  even=false;		 
				}
				else
				{
					  System.out.println("   connected first to: "+sw[first1+(i/2)][col+1].getInput2().getNum());	
			      sw[i][col].getOutput1().setConnected(sw[first1+(i/2)][col+1].getInput2());
			      sw[first1+(i/2)][col+1].getInput2().setConnected(sw[i][col].getOutput1());
			      
			      System.out.println("   connected sec to "+sw[first1+(i/2)+(row/2)][col+1].getInput2().getNum());
				  sw[i][col].getOutput2().setConnected(sw[first1+(i/2)+(row/2)][col+1].getInput2());
				  sw[first1+(i/2)+(row/2)][col+1].getInput2().setConnected(sw[i][col].getOutput2());
				  even=true;
				}
		 }
		 connect( (cols-1)-col, first, last);
	 }
	 else
	 {
		 boolean even=true;
		 System.out.println("I'm in col: "+col );
		 for(int i=first;i<last;i++)
		 {
		     System.out.println("row "+ i);	 
		  
				sw[i][col].getOutput1().setFree(true);
				sw[i][col].getOutput2().setFree(true);
				if(even)
				{
					 System.out.println("   connected first to: "+sw[first1+(i/2)][col-1].getOutput1().getNum());
				  sw[i][col].getInput1().setConnected(sw[first1+(i/2)][col-1].getOutput1());
				  sw[first1+(i/2)][col-1].getOutput1().setConnected(sw[i][col].getInput1());
				   System.out.println("   connected sec to "+sw[first1+(i/2)+(row/2)][col-1].getOutput1().getNum());
				   
				  sw[i][col].getInput2().setConnected(sw[first1+(i/2)+(row/2)][col-1].getOutput1());
				  sw[first1+(i/2)+(row/2)][col-1].getOutput1().setConnected(sw[i][col].getInput2());
				  even=false;		 
				}
				else
				{
					  System.out.println("   connected first to: "+sw[first1+(i/2)][col-1].getOutput2().getNum());
			      sw[i][col].getInput1().setConnected(sw[first1+(i/2)][col-1].getOutput2());
			      sw[first1+(i/2)][col-1].getOutput2().setConnected(sw[i][col].getInput1());

			      System.out.println("   connected sec to "+sw[first1+(i/2)+(row/2)][col-1].getOutput2().getNum());

				  sw[i][col].getInput2().setConnected(sw[first1+(i/2)+(row/2)][col-1].getOutput2());
				  sw[first1+(i/2)+(row/2)][col-1].getOutput2().setConnected(sw[i][col].getInput2());
				  even=true;
				}
		 } 
		 System.out.println("last = "+last+" first = "+ first);
		 if(!((cols-1)-col+1==((cols-1)/2)||(cols-1)-col+1==((cols-1)/2)))
		 {
			 System.out.println("calling col: "+((cols-1)-col+1)+" from: "+first+" to "+(((last-first)/2)+first));
		 connect((cols-1)-col+1, first, ((last-first)/2)+first);
		 System.out.println("calling col: "+((cols-1)-col+1)+" from: "+( ((last-first)/2)+first+1)+" to "+last);
		 connect((cols-1)-col+1, ((last-first)/2)+first, last);
		 }
	 }
	 
	 
	 
   }
}
