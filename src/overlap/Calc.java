package overlap;

import java.util.ArrayList;
import java.util.List;

public class Calc {
	private int MINX = 0;   //start point x
	private int MINY = 0;   //start point y
	private int MAXX = 100; //end point x
	private int MAXY = 100; //end point y;
	
	public ArrayList<ArrayList<Rect>> selectOverlap(Node root) {
		if(root == null){
			return null;
		}
		ArrayList<ArrayList<Rect>> ret = new ArrayList<ArrayList<Rect>>();
		//go through left
		ArrayList<ArrayList<Rect>> tmp = this.selectOverlap(root.left);
		if (tmp != null) {
			ret.addAll(tmp);
		}
		//go through right
		tmp = this.selectOverlap(root.right);
		if (tmp != null){
			ret.addAll(tmp);
		}
		if(root.left == null && root.right == null && root.rects.size()>1){
			System.out.println(root.start + ":" + root.end + ":" +root.rects.size());
			ret.add(root.rects);
			for(Rect rect : root.rects){
				System.out.println(rect.toString());
			}
		}
		return ret;
	}

	public Node calcX(int start, int end, List<Rect> input) {
		// init the root
		Node root = new Node(start, end, null);
		// get overlap according to X
		for (Rect rect : input) {
			root.addLine(rect, rect.getXLine());
		}
		return root;
	}

	public Node calcY(int start, int end, List<Rect> input) {
		Node root = new Node(start, end, null);

		for (Rect rect : input) {
			root.addLine(rect, rect.getYLine());
		}
		return root;
	}
	
	public ArrayList<ArrayList<Rect>> execute(List<Rect> input){
		Node xr = calcX(this.MINX,this.MAXX, input);
		ArrayList<ArrayList<Rect>> xoverlap = this.selectOverlap(xr);
		ArrayList<ArrayList<Rect>> ret = new ArrayList<ArrayList<Rect>>();
		for(ArrayList<Rect> xset : xoverlap){
			Node yr = calcY(this.MINY,this.MAXY, xset);
			ret.addAll(this.selectOverlap(yr));
		}
		return ret;
	}

	public static void main(String[] args) {
		ArrayList<Rect> input = new ArrayList<Rect>();
		input.add(new Rect(0, 0, 20, 20));
		input.add(new Rect(10, 10, 20, 20));
		input.add(new Rect(18, 18, 1, 1));
		input.add(new Rect(20, 20, 1, 1));
		Calc cal = new Calc();
		ArrayList<ArrayList<Rect>> results = cal.execute(input);
//		Node node = cal.calcX(0,cal.MAXX, input); 
//		ArrayList<ArrayList<Rect>> results = cal.selectOverlap(node);
		
		for(ArrayList<Rect> ar : results){
			System.out.println("*********duplicate*********");
			for(Rect rect : ar){
				System.out.println(rect.toString());
			}
			System.out.println("***************************");
		}
		
	}

}
