
public class point {
	int num;
	boolean free;
	point connected;
	point(int num)
	{
		this.num=num;
	free= true;
	connected= null;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public point getConnected() {
		return connected;
	}

	public void setConnected(point connected) {
		this.connected = connected;
	}

	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}

}
