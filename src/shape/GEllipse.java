package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GEllipse extends GShape {
	private java.awt.geom.Ellipse2D.Float Ellipse; // 이걸 렉시컬 스코핑이라고함. 야는 가장 가까이에있는걸 찾아. 가장 가까우니까 걍 렉텐글을 갖고오길래 이렇게
													// 라이브러리에서 가져오는

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
	public void setOrigin(int x, int y) { // 원점 세팅
		this.originx = x;
		this.originy = y;

		this.Ellipse.setFrame(x, y, 0, 0);
	}

	public void setPoint(int x, int y) {
		boolean equilateral = false ; //이거 나중에 shape에 equil넣어서 setequil로 ctrl이 눌리면 이거 get으로 받아서 true false확인할것임.
		int startX = (int) Math.min(x, this.originx);
		int startY = (int) Math.min(y, this.originy);
		int width = (int) Math.abs(x - this.originx);
		int height = (int) Math.abs(y - this.originy);
		
		if (equilateral) { // 원이라면
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
