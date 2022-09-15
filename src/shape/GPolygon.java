package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GPolygon extends GShape {
	private java.awt.Polygon polygon;
	private Polygon newPolygon;

	public GPolygon() {

		this.polygon = new java.awt.Polygon();
		this.shape = this.polygon;
	}
	public GShape newinstance() {
		return new GPolygon();
	}
	public void setOrigin(int x, int y) { // 원점 세팅
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);

	}

	public void setPoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints - 1] = x;
		this.polygon.ypoints[this.polygon.npoints - 1] = y;

	}

	public void addPoint(int x, int y) { // 현재점을 고정시키고 하나를 더 추가시켜 ,점을하나더 찍는 함수를 일반화시킨것.
		this.polygon.addPoint(x, y);

	}

	@Override
	public void keepMoving(Graphics2D graphics2d,int x, int y) {
		int dw = x - this.px;
		int dh = y - this.py;

		this.polygon.translate(dw, dh);
		
		this.px = x;
		this.py = y;

	}

	public void finishMoving(Graphics2D graphics2d,int x, int y) {
		java.awt.Polygon newPolygon = new java.awt.Polygon();
		for (int i = 0; i < this.polygon.npoints; i++) {
			newPolygon.addPoint(this.polygon.xpoints[i], this.polygon.ypoints[i]);
		}
		this.polygon = newPolygon;
		this.shape = (java.awt.Polygon)this.polygon;
	}
	@Override
	public void initResizing(Graphics2D graphics2D, int x, int y) {
		setxyPoint(x, y);
		setMinXYPoint(this.shape.getBounds().getMinX(), this.shape.getBounds().getMinY());
		setMaxXYPoint(this.shape.getBounds().getMaxX(), this.shape.getBounds().getMaxY());
		setwhPoint(this.shape.getBounds().width, this.shape.getBounds().height);
		this.newPolygon = new java.awt.Polygon();
		this.newPolygon.npoints = this.polygon.npoints;
		this.newPolygon.xpoints = this.polygon.xpoints;
		this.newPolygon.ypoints = this.polygon.ypoints;
//		for(int i=0;i<=this.polygon.npoints;i++) {
//			newPolygon.xpoints[i] = this.polygon.xpoints[i];
//			newPolygon.ypoints[i] = this.polygon.ypoints[i];
//		}
		this.shape = this.newPolygon;

	}
//	@Override
//	public void finishResizing(Graphics2D graphics2d, int x, int y) {
//		this.polygon = newPolygon;
//		this.shape = (java.awt.Polygon)this.polygon;
//	}
	
	public void initRotating(Graphics2D graphics2d, int x, int y) {
		affineTransform = new AffineTransform();
		setBounds(this.shape.getBounds());
		setxyPoint(x, y);
	}

	
	
//	@Override
	public void finishRotating(Graphics2D graphics2d, int x, int y) {
//		java.awt.Polygon newPolygon = new java.awt.Polygon();
//		for (int i = 0; i < this.polygon.npoints; i++) {
//			newPolygon.addPoint(this.polygon.xpoints[i], this.polygon.ypoints[i]);
//		}
//		this.polygon = newPolygon;
//		this.shape = (java.awt.Polygon)this.polygon;		
	}
}
