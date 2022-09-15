package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GRectangle extends GShape {
	private java.awt.Rectangle rectangle; // �̰� ������ �������̶����. �ߴ� ���� �����̿��ִ°� ã��. ���� �����ϱ� �� ���ٱ��� ������淡 �̷��� ���̺귯������ ��������
											// ��!��� �������ذž�.

	public GRectangle() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;
	}
	public GShape newinstance() {
		return new GRectangle();
	}
	public void setOrigin(int x, int y) { // ���� ����
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
