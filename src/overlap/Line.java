package overlap;

public class Line {

	public int start;
	public int end;
	public Line(int start, int end){
		this.start = start;
		this.end = end;
	}
	public String toString(){
		String ret = "start = ";
		ret+= String.valueOf(this.start);
		ret+= " end = ";
		ret+= String.valueOf(this.end);
		return ret;
	}
}
