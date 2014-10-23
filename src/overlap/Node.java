package overlap;

import java.util.ArrayList;

public class Node {
	public int start;
	public int end;
	public int mid;
	public ArrayList<Rect> rects;
	public Node left; // left child
	public Node right; // right child

	public Node(int start, int end, ArrayList<Rect> rects) {
		this.start = start;
		this.end = end;
		this.mid = (this.start + this.end) >> 1;
		this.rects = new ArrayList<Rect>();
		if (rects != null) {
			this.rects.addAll(rects);
		}
		this.left = null;
		this.right = null;
		// System.out.println("Created " + this.start + " : " + this.end + "=> "
		// + this.rects.size());
	}

	public void appendRect(Rect rect){
		boolean found = false;
		for (Rect item : this.rects) {
			if (item.equals(rect)) {
				found = true;
				break;
			}
		}
		if (!found) {
			this.rects.add(rect);
			System.out.println("adding " + rect.toString() + " to " + this.start + "<->" + this.end);
			if(this.left!= null){
				this.left.appendRect(rect);
			}
			if(this.right!=null){
				this.right.appendRect(rect);
			}
		}
	}
	
	public void addLine(Rect rect, Line line) {
		if (line.start == line.end)
			return;
		int low = line.start < this.start ? this.start : line.start;
		int high = line.end > this.end ? this.end : line.end;
		if (this.start == low && this.end == high) {
			//add
			this.appendRect(rect);
			return;
		}

		if (low <= this.mid) {
			if (this.left == null) {
			
				this.left = new Node(this.start, this.mid, this.rects);
			}
			this.left.addLine(rect, new Line(low, high));
		}

		if (high > this.mid) {
			if (this.right == null) {
				this.right = new Node(this.mid, this.end, this.rects);

			}
			this.right.addLine(rect, new Line(low, high));
		}
	}
}
