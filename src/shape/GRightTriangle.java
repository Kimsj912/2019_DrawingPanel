package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GRightTriangle extends GShape {
	private java.awt.Polygon triangle;

	public GRightTriangle() {

		this.triangle = new java.awt.Polygon();
		this.shape = this.triangle;
	}
	public GShape newinstance() {
		return new GRightTriangle();
	}
	public void setOrigin(int x, int y) { // 원점 세팅
		this.px = x;
		this.py = y;
		this.triangle.addPoint(x, y);
		this.triangle.addPoint(x, y);
		this.triangle.addPoint(x, y);
		this.triangle.npoints = 3;
		

	}

	public void setPoint(int x, int y) {
		this.triangle.xpoints[0] = this.px;
		this.triangle.ypoints[0] = this.py;
		
		this.triangle.xpoints[1] = this.px;
		this.triangle.ypoints[1] = y;
		
		this.triangle.xpoints[2] = x ;
		this.triangle.ypoints[2] = y;

	}

	public void addPoint(int x, int y) { // 현재점을 고정시키고 하나를 더 추가시켜 ,점을하나더 찍는 함수를 일반화시킨것.
		
	}

//	@Override
//	public void keepMoving(Graphics2D graphics2d, int x, int y) {
//		int dw = x - this.px;
//		int dh = y - this.py;
//
//		for (int i = 0; i < this.triangle.npoints; i++) {
//			this.triangle.xpoints[i] += dw;
//			this.triangle.ypoints[i] += dh;
//		}
//
//		this.px = x;
//		this.py = y;
//
//		java.awt.Polygon newPolygon = new java.awt.Polygon();
//		for (int i = 0; i < this.triangle.npoints; i++) {
//			newPolygon.addPoint(this.triangle.xpoints[i], this.triangle.ypoints[i]);
//			
//		}
//		this.triangle = newPolygon;
//		this.shape =  this.triangle;
//	}

	@Override
	public void finishMoving(Graphics2D graphics2d, int x, int y) {
		
	}
	@Override
	public void finishResizing(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finishRotating(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
