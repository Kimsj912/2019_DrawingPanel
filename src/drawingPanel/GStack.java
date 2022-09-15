package drawingPanel;

import java.util.Stack;
import java.util.Vector;

import shape.GShape;

public class GStack extends Stack<Vector<GShape>> {
	private static final long serialVersionUID = 1L;
	private boolean empty;
	public boolean isEmpty() {
		return empty;
	}
	
	private Vector<GShape> shapeVector;

	public GStack() {
		super();
//		this.setSize(10);

	}

	public Vector<GShape> pop() {
		for(Vector<GShape> shapeVector:this) {
			System.out.println(shapeVector);
		}
		Vector<GShape> shapevector = this.get(0);
		this.remove(0);
		System.out.println(shapevector);
		return this.get(0);

	}


	public void setName() {

	}
}
