package drawingPanel;

import java.util.Vector;
import shape.GShape;

public class GClipBoard {
	private Vector<GShape> shapes;
	public GClipBoard() {
		this.shapes = new Vector<GShape>();
	}

	public void setContents(Vector<GShape> shapes) { // 이전에들어갔던걸 알려줘야징. 아니먄 2ㅊ원어레이로잡아서.. 어... 그러려면 드럽다운으로 해서 4~5개가지는 다시선택할수있지.
		this.shapes.clear();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			this.shapes.add(shapes.get(i));
		}
	}

	public Vector<GShape> getContents() { // 이전에들어갔던걸 알려줘야징. 아니먄 2ㅊ원어레이로잡아서.. 어... 그러려면 드럽다운으로 해서 4~5개가지는 다시 선택할수있지.
		Vector<GShape> clonedShapes = new Vector<GShape>();
//		for (GShape shape : this.shapes) {
//			GShape clonedshape = shape.clone();
//			clonedShapes.add(clonedshape);
//		}
		for (int i = shapes.size() - 1; i >= 0; i--) {
			GShape shape = this.shapes.get(i);
			GShape clonedshape = shape.clone();
			clonedShapes.add(clonedshape);
			}
		return clonedShapes;
	}

}
