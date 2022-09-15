package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GPentagon extends GShape {
	private java.awt.Polygon pentagon;

	public GPentagon() {

		this.pentagon = new java.awt.Polygon();
		this.shape = this.pentagon;
	}
	public GShape newinstance() {
		return new GPentagon();
	}
	public void setOrigin(int x, int y) { // 원점 세팅
		this.px = x;
		this.py = y;
		this.pentagon.addPoint(x, y);
		this.pentagon.addPoint(x, y);
		this.pentagon.addPoint(x, y);
		this.pentagon.addPoint(x, y);
		this.pentagon.addPoint(x, y);
		this.pentagon.npoints = 5;

	}

	public void setPoint(int x, int y) {

		
		int height = (int) Math.abs(y - this.py);
		int width = (int) Math.abs(x - this.px);

		boolean equilateral = false; // 이거 나중에 shape에 equil넣어서 setequil로 ctrl이 눌리면 이거 get으로 받아서 true false확인할것임.

		if (equilateral) {
			if (width > height) {
				height = width;

			} else {
				width = height;

			}
		}
		if(x<this.px) {
			width = -width;
		} 
		
		if(y<this.py) {
			height= -height;
		}
		
		int m1X = this.px + width / 5;
		int m2X = this.px + width / 2;
		int m3X = this.px + 4 * width / 5;
		
		this.pentagon.xpoints[0] = m2X;
		this.pentagon.xpoints[1] = this.px+width;
		this.pentagon.xpoints[2] = m3X;
		this.pentagon.xpoints[3] = m1X;
		this.pentagon.xpoints[4] = this.px;
		
		int mY = this.py+height/3;
		
		this.pentagon.ypoints[0] = this.py;
		this.pentagon.ypoints[1] = mY;
		this.pentagon.ypoints[2] = this.py+height;
		this.pentagon.ypoints[3] = this.py+height;
		this.pentagon.ypoints[4] = mY;
	}

	public void addPoint(int x, int y) { // 현재점을 고정시키고 하나를 더 추가시켜 ,점을하나더 찍는 함수를 일반화시킨것.

	}

//	@Override
//	public void keepMoving(Graphics2D graphics2d, int x, int y) {
//		int dw = x - this.px;
//		int dh = y - this.py;
//
//		for (int i = 0; i < this.pentagon.npoints; i++) {
//			this.pentagon.xpoints[i] += dw;
//			this.pentagon.ypoints[i] += dh;
//		}
//
//		this.px = x;
//		this.py = y;
//
//		java.awt.Polygon newPolygon = new java.awt.Polygon();
//		for (int i = 0; i < this.pentagon.npoints; i++) {
//			newPolygon.addPoint(this.pentagon.xpoints[i], this.pentagon.ypoints[i]);
//			
//		}
//		this.pentagon = newPolygon;
//		this.shape = this.pentagon;
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
