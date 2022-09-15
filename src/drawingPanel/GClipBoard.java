package drawingPanel;

import java.util.Vector;
import shape.GShape;

public class GClipBoard {
	private Vector<GShape> shapes;
	public GClipBoard() {
		this.shapes = new Vector<GShape>();
	}

	public void setContents(Vector<GShape> shapes) { // ������������ �˷����¡. �ƴϐ� 2������̷���Ƽ�.. ��... �׷����� �巴�ٿ����� �ؼ� 4~5�������� �ٽü����Ҽ�����.
		this.shapes.clear();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			this.shapes.add(shapes.get(i));
		}
	}

	public Vector<GShape> getContents() { // ������������ �˷����¡. �ƴϐ� 2������̷���Ƽ�.. ��... �׷����� �巴�ٿ����� �ؼ� 4~5�������� �ٽ� �����Ҽ�����.
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
