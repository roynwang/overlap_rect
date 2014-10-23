package overlap;

public class Rect {
	int x;
	int y;
	int width;
	int height;

	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Line getXLine() {
		return new Line(this.x, this.x + this.width);
	}

	public Line getYLine() {
		return new Line(this.y, this.y + this.height);
	}

	public String toString(){
		String ret = "x:";
		ret+=String.valueOf(this.x) ;
		ret+=" y:";
		ret+=String.valueOf(this.y);
		ret+=" width:";
		ret+=String.valueOf(this.width);
		ret+=" height:";
		ret+=String.valueOf(this.height);
		return ret;
	}
}
