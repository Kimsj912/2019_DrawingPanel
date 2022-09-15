package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import drawingPanel.GDrawingPanel;
import shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable { // ÁÂÇ¥¶û ±×¸²±×¸®´Â°É ºÐ¸®ÇÑ°Ü.

	public enum EOnState {
		eOnShape, eOnResize, eOnRotate, eOutShape// onshpaeÀº ¹«ºùÀÌ¶û °°Àº°Å¾ß.

	}

	protected java.awt.Shape shape;

	public java.awt.Shape getShape() {
//		this.strokeThickness = (float) 1.0;
		return shape;
		
	}

	protected AffineTransform affineTransform;
	protected GAnchors anchors;
	private boolean selected;
	protected EAnchors eAnchors;

	protected float strokeThickness ;

	public float getStrokeThickness() {
		return strokeThickness;
	}

	public void setStrokeThickness(float strokeThickness) {
		if (0 < strokeThickness) {
			this.strokeThickness = strokeThickness;
		}
	}

	protected int px;
	protected int py;

	protected int fixx;
	protected int fixy;

	public void setxyPoint(int px, int py) {
		this.fixx = px;
		this.fixy = py;
	}

	public Point getxyPoint() {
		return new Point(this.fixx, this.fixy);
	}

	protected double fixMinx;
	protected double fixMiny;

	public void setMinXYPoint(double d, double e) {
		this.fixMinx = d;
		this.fixMiny = e;
	}

	protected double fixMaxx;
	protected double fixMaxy;

	public void setMaxXYPoint(double px, double py) {
		this.fixMaxx = px;
		this.fixMaxy = py;
	}

	protected double w;
	protected double h;
	protected Color graphiccolor;
	public void setColor(Color graphiccolor) {this.graphiccolor = graphiccolor;}
	public Color getGraphiccolor() {return graphiccolor;}

	private Color graphicFillColor;
	public void setfillColor(Color graphicFillColor) {this.graphicFillColor = graphicFillColor;}
	public Color getGraphicFillColor() {return graphicFillColor;}

	public void setwhPoint(double w, double h) {
		this.w = w;
		this.h = h;
	}

	public Point getwhPoint() {
		return new Point(this.shape.getBounds().width, this.shape.getBounds().height);
	}

	private Rectangle bounds;

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	abstract public void setOrigin(int x, int y);

	abstract public void setPoint(int x, int y);

	abstract public void addPoint(int x, int y);


	public Point getMax() {
		return new Point((int) this.shape.getBounds().getMaxX(), (int) this.shape.getBounds().getMaxY());
	}

	public Point getMin() {
		return new Point((int) this.shape.getBounds().getMinX(), (int) this.shape.getBounds().getMinY());

	}

	public GShape() {
		setSelected(false);
		this.anchors = new GAnchors();
		this.strokeThickness = 1;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public GShape clone() {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream ObjectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (GShape) ObjectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

	abstract public GShape newinstance();

	public void draw(Graphics graphics) {
		Graphics2D g2D = (Graphics2D) graphics;

		g2D.setColor(this.graphicFillColor);
		g2D.fill(shape);
		g2D.setColor(this.graphiccolor);
		g2D.setStroke(new BasicStroke(this.strokeThickness));
		g2D.draw(this.shape);
		if(shape instanceof GLine) {
			System.out.println(this.shape.getBounds());
		}
		if (isSelected()) {
			anchordraw(g2D);
			
		}

	}

	public void anchordraw(Graphics2D g2D) {
		
		g2D.setColor(Color.black);
		g2D.setStroke(new BasicStroke(1));
		this.anchors.setBoundingRect(this.shape.getBounds());
		this.anchors.fillrect(g2D, this.shape.getBounds());
		this.anchors.draw(g2D);

	}

	public EOnState onShape(int x, int y) { // ¾î´À ¾ÞÄ¿À§¿¡ ÀÖ´ÂÁö È®ÀÎÇÏ°í ¾î´À ¾ÞÄ¿ÀÎÁö µ¹·ÁÁà.
		this.eAnchors = this.anchors.onShape(x, y);
		if (eAnchors == EAnchors.RR) { // rotate
			return EOnState.eOnRotate;
		} else if (eAnchors == null) {
			if (this.shape.contains(x, y)) {
				return EOnState.eOnShape;
			} else {
				return EOnState.eOutShape;
			}
		} else { // resize
			return EOnState.eOnResize;
		}

	}

//////////////////moving
	public void initMoving(Graphics2D graphics2D, int x, int y) {
		this.px = x;
		this.py = y;

		if (!isSelected()) {
			this.selected = true;
			anchordraw(graphics2D);
		}
	}

	public void keepMoving(Graphics2D graphics2d, int x, int y) {
		this.affineTransform = new AffineTransform();
		int dw = x - px;
		int dh = y - py;

		this.affineTransform.translate(dw, dh);
		this.shape = this.affineTransform.createTransformedShape(this.shape);

		this.px = x;
		this.py = y;
	}
	abstract public void finishMoving(Graphics2D graphics2d, int x, int y);

	////////////////// resize
	public void initResizing(Graphics2D graphics2D, int x, int y) {
		setxyPoint(x, y);
		setMinXYPoint(this.shape.getBounds().getMinX(), this.shape.getBounds().getMinY());
		setMaxXYPoint(this.shape.getBounds().getMaxX(), this.shape.getBounds().getMaxY());
		setwhPoint(this.shape.getBounds().width, this.shape.getBounds().height);
	}

	public void keepResize(Graphics2D graphics2d, int x, int y) {
//		draw(graphics2d);
//		anchordraw(graphics2d);
		this.affineTransform = new AffineTransform();

		boolean xIsSmallthanMinX = x<=this.fixMinx ? true: false; 
		boolean yIsSmallthanMinY = y<=this.fixMiny ? true: false; 
		boolean xIsBiggerthanMaxX = x>=this.fixMaxx ? true: false; 
		boolean yIsBiggerthanMaxY = y>=this.fixMaxy ? true: false; 
		
		switch (this.eAnchors) {
		case NW: // ºÏ¼­¾ÞÄ¿
			if(yIsBiggerthanMaxY) {
				this.eAnchors = EAnchors.SW;
			} else if(xIsBiggerthanMaxX) {
				this.eAnchors = EAnchors.NE;
			}
			break;
		case NN: // ºÏºÏ¾ÞÄ¿
			if(yIsBiggerthanMaxY) {
				this.eAnchors = EAnchors.SS;
			}
			break;
		case NE: // ºÏµ¿¾ÞÄ¿
			if(yIsBiggerthanMaxY) {
				this.eAnchors = EAnchors.SE;
			} else if(xIsSmallthanMinX) {
				this.eAnchors = EAnchors.NW;
			}
			break;
		case EE: // µ¿µ¿¾ÞÄ¿
			if(xIsSmallthanMinX) {
				this.eAnchors = EAnchors.WW;
			}
			break;
		case SE: // ³²µ¿¾ÞÄ¿
			if(xIsSmallthanMinX) {
				this.eAnchors = EAnchors.SW;
			} else if(yIsSmallthanMinY) {
				this.eAnchors = EAnchors.NE;
			}
			break;
		case SS: // ³²³²¾ÞÄ¿
			if(yIsSmallthanMinY) {
				this.eAnchors = EAnchors.NN;
			}
			break;
		case SW: // ³²¼­¾ÞÄ¿
			if(xIsBiggerthanMaxX) {
				this.eAnchors = EAnchors.SE;
			} else if(yIsSmallthanMinY) {
				this.eAnchors = EAnchors.NW;
			} 
			break;
		case WW: // ¼­¼­¾ÞÄ¿
			if(xIsBiggerthanMaxX) {
				this.eAnchors = EAnchors.EE;
			}
			break;
		default:
			break;
		}
		
		
		
		switch (this.eAnchors) {
		case NW: // ºÏ¼­¾ÞÄ¿
			double nwRateX = Math.abs(this.fixMaxx - x) / Math.abs(this.fixMaxx - this.fixMinx);
			double nwRateY = Math.abs(this.fixMaxy - y) / Math.abs(this.fixMaxy - this.fixMiny);
			this.affineTransform.translate((this.fixMaxx * (1 - nwRateX)), -(this.fixMaxy * (nwRateY - 1)));
			this.affineTransform.scale(nwRateX, nwRateY);
			break;
		case NN: // ºÏºÏ¾ÞÄ¿
			double nnRateX = 1;
			double nnRateY = Math.abs(this.fixMaxy - y) / Math.abs(this.fixMaxy - this.fixMiny);
			this.affineTransform.translate(-(this.fixMaxx * (nnRateX - 1)), -(this.fixMaxy * (nnRateY - 1)));
			this.affineTransform.scale(nnRateX, nnRateY);
			break;
		case NE: // ºÏµ¿¾ÞÄ¿
			double neRateX = Math.abs(x - this.fixMinx) / Math.abs(this.fixMinx - this.fixMaxx);
			double neRateY = Math.abs(this.fixMaxy - y) / Math.abs(this.fixMaxy - this.fixMiny);
			this.affineTransform.translate(-(this.fixMinx * (neRateX - 1)), -(this.fixMaxy * (neRateY - 1)));
			this.affineTransform.scale(neRateX, neRateY);
			break;
		case EE: // µ¿µ¿¾ÞÄ¿
			double eeRateX = Math.abs(x - this.fixMinx) / Math.abs(this.fixMinx - this.fixMaxx);
			double eeRateY = 1;
			this.affineTransform.translate(-(this.fixMinx * (eeRateX - 1)), -(this.fixMiny * (eeRateY - 1)));
			this.affineTransform.scale(eeRateX, eeRateY);
			break;
		case SE: // ³²µ¿¾ÞÄ¿
			double seRateX = Math.abs(x - this.fixMinx) / Math.abs(this.fixMinx - this.fixMaxx);
			double seRateY = Math.abs(y - this.fixMiny) / Math.abs(this.fixMiny - this.fixMaxy);
			this.affineTransform.translate(-(this.fixMinx * (seRateX - 1)), -(this.fixMiny * (seRateY - 1)));
			this.affineTransform.scale(seRateX, seRateY);
			break;
		case SS: // ³²³²¾ÞÄ¿
			double ssRateX = 1;
			double ssRateY = Math.abs(y - this.fixMiny) / Math.abs(this.fixMiny - this.fixMaxy);
			this.affineTransform.translate(-(this.fixMinx * (ssRateX - 1)), -(this.fixMiny * (ssRateY - 1)));
			this.affineTransform.scale(ssRateX, ssRateY);
			break;
		case SW: // ³²¼­¾ÞÄ¿
			double swRateX = Math.abs(this.fixMaxx - x) / Math.abs(this.fixMaxx - this.fixMinx);
			double swRateY = Math.abs(y - this.fixMiny) / Math.abs(this.fixMiny - this.fixMaxy);
			this.affineTransform.translate((this.fixMaxx * (1 - swRateX)), -(this.fixMiny * (swRateY - 1)));
			this.affineTransform.scale(swRateX, swRateY);
			break;
		case WW: // ¼­¼­¾ÞÄ¿
			double wwRateX = Math.abs(this.fixMaxx - x) / Math.abs(this.fixMaxx - this.fixMinx);
			double wwRateY = 1;
			this.affineTransform.translate((this.fixMaxx * (1 - wwRateX)), (this.fixMaxy * (1 - wwRateY)));
			this.affineTransform.scale(wwRateX, wwRateY);
			break;
		default:
			break;
		}
		shape = affineTransform.createTransformedShape(shape);
//		draw(graphics2d);
//		
		anchors.setBoundingRect(affineTransform.createTransformedShape(anchors.getBoundingRect()).getBounds());
		anchordraw(graphics2d);

		setMinXYPoint(this.shape.getBounds().getMinX(), this.shape.getBounds().getMinY());
		setMaxXYPoint(this.shape.getBounds().getMaxX(), this.shape.getBounds().getMaxY());
		setwhPoint(this.shape.getBounds().width, this.shape.getBounds().height);
	}
//	abstract public void finishResizing(Graphics2D graphics2d, int x, int y);
	public void finishResizing(Graphics2D graphics2d, int x, int y) {};

	///////////////// rotating
	public void initRotating(Graphics2D graphics2d, int x, int y) {
		affineTransform = new AffineTransform();
		setBounds(this.shape.getBounds());
		setxyPoint(x, y);
	}

	public void keepRotate(Graphics2D graphics2d, int x, int y) {

		Point2D center = new Point((int) getBounds().getCenterX(), (int) getBounds().getCenterY());
		Point2D dragStart = getxyPoint();
		Point2D nowPoint = new Point(x, y);

		double rotationAngle = computeRotationAngle(center, dragStart, nowPoint);
		if (rotationAngle != 0) {
			this.affineTransform.setToRotation(Math.toRadians(rotationAngle), center.getX(), center.getY());
			shape = affineTransform.createTransformedShape(shape);
		}
		setxyPoint((int) nowPoint.getX(), (int) nowPoint.getY());
	}

	public double computeRotationAngle(Point2D center, Point2D previous, Point2D current) { // Giggle Giggle
		double startAngle = Math
				.toDegrees(Math.atan2(center.getX() - previous.getX(), center.getY() - previous.getY()));
		double endAngle = Math.toDegrees(Math.atan2(center.getX() - current.getX(), center.getY() - current.getY()));
		double rotationAngle = startAngle - endAngle;
		if (rotationAngle < 0) {
			rotationAngle += 360;
		}
		return rotationAngle;
	}
	abstract public void finishRotating(Graphics2D graphics2d, int x, int y);

	public void initflip(Graphics2D g2, boolean lr) {
		setMinXYPoint(this.shape.getBounds().getMinX(), this.shape.getBounds().getMinY());
		setMaxXYPoint(this.shape.getBounds().getMaxX(), this.shape.getBounds().getMaxY());
		setwhPoint(this.shape.getBounds().width, this.shape.getBounds().height);
	}

	public void keepflip(Graphics2D g2, boolean lr, GDrawingPanel gdp) {
		this.affineTransform = new AffineTransform();

		if (lr) {// ÁÂ¿ì
			double ssRateX = -1;
			double ssRateY = 1;
			this.affineTransform.translate(-(this.fixMinx * (ssRateX - 1) - this.getwhPoint().x),
					-(this.fixMiny * (ssRateY - 1)));
			this.affineTransform.scale(ssRateX, ssRateY);
		} else {// »óÇÏ
			double ssRateX = 1;
			double ssRateY = -1;
			this.affineTransform.translate(-(this.fixMinx * (ssRateX - 1)),
					-(this.fixMiny * (ssRateY - 1) - this.getwhPoint().y));
			this.affineTransform.scale(ssRateX, ssRateY);
		}
		shape = affineTransform.createTransformedShape(shape);
		draw(g2);
		anchors.setBoundingRect(affineTransform.createTransformedShape(anchors.getBoundingRect()).getBounds());
		anchordraw(g2);

	}

}