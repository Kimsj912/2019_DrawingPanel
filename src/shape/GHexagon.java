package shape;

import java.awt.Graphics2D;

public class GHexagon extends GShape {
	private java.awt.Polygon hexagon;

	public GHexagon() {

		this.hexagon = new java.awt.Polygon();
		this.shape = this.hexagon;
	}
	public GShape newinstance() {
		return new GHexagon();
	}
	public void setOrigin(int x, int y) { // 원점 세팅
		this.px = x;
		this.py = y;
		this.hexagon.addPoint(x, y);
		this.hexagon.addPoint(x, y);
		this.hexagon.addPoint(x, y);
		this.hexagon.addPoint(x, y);
		this.hexagon.addPoint(x, y);
		this.hexagon.addPoint(x, y);
		this.hexagon.npoints = 6;

	}

	public void setPoint(int x, int y) {
		int mY = (this.py + y) / 2;
		int m1X = (3 * this.px + x) / 4;
		int m2X = (this.px + 3 * x) / 4;

		this.hexagon.xpoints[0] = m1X;
		this.hexagon.ypoints[0] = this.py;

		this.hexagon.xpoints[1] = m2X;
		this.hexagon.ypoints[1] = this.py;

		this.hexagon.xpoints[2] = x;
		this.hexagon.ypoints[2] = mY;

		this.hexagon.xpoints[3] = m2X;
		this.hexagon.ypoints[3] = y;

		this.hexagon.xpoints[4] = m1X;
		this.hexagon.ypoints[4] = y;

		this.hexagon.xpoints[5] = this.px;
		this.hexagon.ypoints[5] = mY;

	}

	public void addPoint(int x, int y) { // 현재점을 고정시키고 하나를 더 추가시켜 ,점을하나더 찍는 함수를 일반화시킨것.

	}


//	@Override
//	public void keepMoving(Graphics2D graphics2d, int x, int y) {
//		int dw = x - this.px;
//		int dh = y - this.py;
//
//		for (int i = 0; i < this.hexagon.npoints; i++) {
//			this.hexagon.xpoints[i] += dw;
//			this.hexagon.ypoints[i] += dh;
//		}
//
//		this.px = x;
//		this.py = y;
//		
//		java.awt.Polygon newPolygon = new java.awt.Polygon();
//		for (int i = 0; i < this.hexagon.npoints; i++) {
//			newPolygon.addPoint(this.hexagon.xpoints[i], this.hexagon.ypoints[i]);
//			
//		}
//		this.hexagon = newPolygon;
//		this.shape = this.hexagon;		
//	}
//
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
