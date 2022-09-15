package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GLine extends GShape {

	private java.awt.geom.Line2D.Double line;
	private int originx;
	private int originy;
	private int secondx;
	private int secondy;
	private Rectangle frame;

	public GLine() {
		super();
		this.shape = new java.awt.geom.Line2D.Double();
		this.line = (java.awt.geom.Line2D.Double) this.shape;
		this.frame = new Rectangle();
	}
	public GShape newinstance() {
		this.frame = new Rectangle();
		return new GLine();
	}
	@Override
	public void draw(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setColor(this.graphiccolor);
		g2d.setStroke(new BasicStroke(this.strokeThickness));
		g2d.draw(this.line);
//		
//		g2d.setColor(Color.white);
//		g2d.fill(this.frame);
//		g2d.draw(this.frame);
//		if( this.isSelected()) {
//			
//		}
		if(this.isSelected()) {
			float[] dash = new float[] { 10, 5, 5, 5 };
			g2d.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, dash, 0));
			g2d.draw(new Rectangle((int)this.line.getX1(),(int)this.line.getY1(),(int)(this.line.getX2()-this.line.getX1()),(int)(this.line.getY2()-this.line.getY1())));
			g2d.setStroke(new BasicStroke(1));
		}
	}

	public void setOrigin(int x, int y) { // 원점 세팅
		this.px = x;
		this.py = y;
	}

	@Override
	public void setPoint(int x, int y) {
		this.line.setLine(this.px, this.py, x, y);
		this.frame.setBounds(this.px,this.py, x,y);

	}

	@Override
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
