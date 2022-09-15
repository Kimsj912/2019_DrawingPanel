package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.Vector;

import shape.GShape;

public abstract class GTransformer {

	public GTransformer() {
		this.setgShape(null);
	}
	private Shape selectingShape;
	public Shape getSelectingShape() {return selectingShape;}
	public void setSelectingShape(Shape selectingShape) {this.selectingShape = selectingShape;}
	
	private GShape gShape;
	public GShape getgShape() {return this.gShape;	}
	public void setgShape(GShape gshape) {	this.gShape = gshape;}

	private Vector<GShape> selectedShapevector;
	public Vector<GShape> getSelectedShapevector() {return selectedShapevector;}
	public void setSelectedShapevector(Vector<GShape> selectedShapevector) {this.selectedShapevector = selectedShapevector;}
	public void drawselectingShape(Graphics2D graphics2d) {graphics2d.draw(selectingShape); };
	
	private Point WidthHeight;
	public Point getWidthHeight() {	return WidthHeight;}
	public void setWidthHeight(Point widthHeight) {	WidthHeight = this.gShape.getwhPoint();}

	
	private Point xy;
	public boolean selecting;
	
	public Point getXy() {return xy;}
	public void setXy(Point xy) {this.xy = this.gShape.getxyPoint();}


	public abstract void initTransformating(Graphics2D graphics2d, int x, int y);
	public abstract void keepTransformating(Graphics2D graphics2d, int x, int y);
	public abstract void finishTransforming(Graphics2D graphics2d, int x, int y);


}
