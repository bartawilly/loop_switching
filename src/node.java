
public class node {
    point input1;
    point input2;
    point output1;
    point output2;
    int num;
	int type=-1;
    node(int num,int nn)
    {
    	this.num=nn;
    	 input1 = new point(num);
    	 input2= new point(num+1);
    	 output1 = new point(num);
    	 output2 = new point(num+1);
    }
    
    public point getInput1() {
		return input1;
	}
	public void setInput1(point input1) {
		this.input1 = input1;
	}
	public point getInput2() {
		return input2;
	}
	public void setInput2(point input2) {
		this.input2 = input2;
	}
	public point getOutput1() {
		return output1;
	}
	public void setOutput1(point output1) {
		this.output1 = output1;
	}
	public point getOutput2() {
		return output2;
	}
	public void setOutput2(point output2) {
		this.output2 = output2;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void print()
	{
	  System.out.println("my num: "+ num+ " input1: "+input1.getNum()+" input2: "+input2.getNum()+" output1: "+output1.getNum()+" output2: "+output2.getNum());
	}

    
	
}
