package transformer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import shape.GRectangle;
import shape.GShape;

public class GSelect extends GTransformer {
	private static final long serialVersionUID = 1L;
	private java.awt.Rectangle rectangle;
	private Vector<GShape> selectedshapes;
	private int px;
	private int py;
	private Rectangle shape;
	private GShape groupingRect;

	public GSelect() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;
		this.selectedshapes = new Vector<GShape>();
		this.setSelectingShape(this.shape);
	}

	@Override
	public void initTransformating(Graphics2D graphics2d, int x, int y) {
		selecting = true;
		this.px = x;
		this.py = y;
	}

	@Override
	public void keepTransformating(Graphics2D graphics2d, int x, int y) {
		selecting = true;
		this.rectangle.setBounds(Math.min(this.px, x), Math.min(this.py, y), Math.abs(x - this.px),
				Math.abs(y - this.py));
		drawselectingShape(graphics2d);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		selecting = false;

	}

	public GTransformer setTransformer(GTransformer method) {
		return method;
	}

	@Override
	public void drawselectingShape(Graphics2D graphics2d) {
		graphics2d.setStroke(new BasicStroke(1));
		graphics2d.setColor(new Color(204, 255, 255, 50));
		graphics2d.fill(this.shape);
		graphics2d.setColor(new Color(0, 153, 153));
		graphics2d.draw(this.shape);

	}

	public void contains(Vector<GShape> shapeVector, GShape currentShape) { //여기만 지나면 값이 모두 초기화 됨.... 하......
		for (GShape shape : shapeVector) {
			if (this.shape.contains(shape.getShape().getBounds())/*|| currentShape.equals(shape)*/) {
				shape.setSelected(true);
				selectedshapes.add(shape);
			}
		}
	}

	public boolean isSelected(GShape currentShape) {
		for (GShape shape : this.selectedshapes) {
			if (shape.isSelected() & currentShape.equals(shape)) {
				return true;
			}
		}
		return false;
	}

	public GShape groupingRect(Vector<GShape> shapeVector) {
		this.groupingRect = new GRectangle();
		int minx = 0, miny = 0, maxx = 0, maxy = 0;
		for (GShape shape : shapeVector) {
			minx = Math.min(shape.getMin().x, minx);
			miny = Math.min(shape.getMin().y, miny);
			maxx = Math.max(shape.getMax().x, maxx);
			maxy = Math.max(shape.getMax().y, maxy);
		}
		this.groupingRect.setOrigin(minx, miny);
		this.groupingRect.setPoint(maxx, maxy);
		return this.groupingRect;
	}

}
