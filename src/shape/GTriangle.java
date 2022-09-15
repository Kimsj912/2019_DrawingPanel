package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GTriangle extends GShape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.awt.Polygon triangle;

	public GTriangle() {

		this.triangle = new java.awt.Polygon();
		this.shape = this.triangle;
	}
	public GShape newinstance() {
		return new GTriangle();
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
		boolean equilateral = false; // 이거 나중에 shape에 equil넣어서 setequil로 ctrl이 눌리면 이거 get으로 받아서 true false확인할것임.
		if (equilateral) { // 정삼각형이라면
			int w = Math.abs(x - this.px);
			int h = Math.abs(y - this.py);
			int l = w>h ? w:h;

			if (w > h) {
				this.triangle.xpoints[0] = this.px + l / 2;
				this.triangle.ypoints[0] = this.py;

				this.triangle.xpoints[1] = this.px;
				this.triangle.ypoints[1] = (int) (this.py + Math.sqrt(3) * l / 2);

				this.triangle.xpoints[2] = this.px + l;
				this.triangle.ypoints[2] = (int) (this.py + Math.sqrt(3) * l / 2);
			} else {
				this.triangle.xpoints[0] = (int) (this.px + l / Math.sqrt(3));
				this.triangle.ypoints[0] = this.py;

				this.triangle.xpoints[1] = this.px;
				this.triangle.ypoints[1] = this.py + l;

				this.triangle.xpoints[2] = (int) (this.px + 2 * l / Math.sqrt(3));
				this.triangle.ypoints[2] = this.py + l;

			}

		} else {
			this.triangle.xpoints[0] = (x + this.px) / 2;
			this.triangle.ypoints[0] = this.py;

			this.triangle.xpoints[1] = this.px;
			this.triangle.ypoints[1] = y;

			this.triangle.xpoints[2] = x;
			this.triangle.ypoints[2] = y;

		}
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
//		this.shape = this.triangle;
//
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
