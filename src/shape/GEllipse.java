package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GEllipse extends GShape {
	private java.awt.geom.Ellipse2D.Float Ellipse; // �̰� ������ �������̶����. �ߴ� ���� �����̿��ִ°� ã��. ���� �����ϱ� �� ���ٱ��� ������淡 �̷���
													// ���̺귯������ ��������

	private int originx;
	private int originy;

	public GEllipse() {
		super();
		this.shape = new java.awt.geom.Ellipse2D.Float();
		this.Ellipse = (java.awt.geom.Ellipse2D.Float) this.shape;
	}
	public GShape newinstance() {
		return new GEllipse();
	}
	public void setOrigin(int x, int y) { // ���� ����
		this.originx = x;
		this.originy = y;

		this.Ellipse.setFrame(x, y, 0, 0);
	}

	public void setPoint(int x, int y) {
		boolean equilateral = false ; //�̰� ���߿� shape�� equil�־ setequil�� ctrl�� ������ �̰� get���� �޾Ƽ� true falseȮ���Ұ���.
		int startX = (int) Math.min(x, this.originx);
		int startY = (int) Math.min(y, this.originy);
		int width = (int) Math.abs(x - this.originx);
		int height = (int) Math.abs(y - this.originy);
		
		if (equilateral) { // ���̶��
			if(width>height) {
				this.Ellipse.setFrame(startX, startY, width, width);

			}else {
				this.Ellipse.setFrame(startX, startY, height, height);

			}
		} else {
			this.Ellipse.setFrame(startX, startY, width, height);
			
		}
//		setequil(false);

	}

	public void addPoint(int x, int y) {

	}

	
	@Override
	public void finishMoving(Graphics2D graphics2d, int x, int y) {
		// TODO Auto-generated method stub
		
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
