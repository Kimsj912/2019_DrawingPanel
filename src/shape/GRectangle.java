package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GRectangle extends GShape {
	private java.awt.Rectangle rectangle; // 이걸 렉시컬 스코핑이라고함. 야는 가장 가까이에있는걸 찾아. 가장 가까우니까 걍 렉텐글을 갖고오길래 이렇게 라이브러리에서 가져오는
											// 얘!라고 지정해준거야.

	public GRectangle() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;
	}
	public GShape newinstance() {
		return new GRectangle();
	}
	public void setOrigin(int x, int y) { // 원점 세팅
		this.px=x;
		this.py=y;
	}

	public void setPoint(int x, int y) {
		this.rectangle.setBounds(Math.min(this.px, x),Math.min(this.py, y),Math.abs(x-this.px),Math.abs(y-this.py));
	}

	public void addPoint(int x, int y) {
		
	}


	
	public void finishMoving(Graphics2D graphics2d,int x, int y) {
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
