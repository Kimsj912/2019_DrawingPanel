package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable {

	private final int w = 10;
	private final int h = 10;
	private final int dw = w / 2;
	private final int dh = h / 2;

	public enum EAnchors {// 10State Return
		NW, NN, NE, EE, SE, SS, SW, WW, RR
	}

	private Vector<Ellipse2D> anchors;
	private Rectangle r;

	@SuppressWarnings("unused")
	public GAnchors() {
		this.anchors = new Vector<Ellipse2D>();
		for (EAnchors eAnchor : EAnchors.values()) {
			this.anchors.add(new Ellipse2D.Double(0, 0, w, h));
		}
	}

	public EAnchors onShape(int x, int y) {
		for (int i = 0; i < EAnchors.values().length; i++) {
			if (this.anchors.get(i).contains(x, y)) {
				return EAnchors.values()[i];
			}
		}
		return null;
	}

	public void draw(Graphics2D g2) {
		for (Shape s : this.anchors) {
			g2.setColor(Color.white);
			g2.fill(s);
			g2.setColor(Color.black);
			g2.draw(s);
		}

	}

	public void fillrect(Graphics2D g2, Rectangle bounds) {
		float[] dash = new float[] { 10, 5, 5, 5 };
		g2.setColor(Color.BLACK.darkGray);
		g2.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, dash, 0));
		g2.draw(bounds);
		g2.setStroke(new BasicStroke(1));
	}

	public void setBoundingRect(Rectangle r) {
		this.r = r;
		for (EAnchors eAnchor : EAnchors.values()) {
			int x = 0, y = 0;
			switch (eAnchor) {
			case NW:
				x = r.x;
				y = r.y;
				break;
			case NN:
				x = r.x + r.width / 2;
				y = r.y;
				break;
			case NE:
				x = r.x + r.width;
				y = r.y;
				break;
			case EE:
				x = r.x + r.width;
				y = r.y + r.height / 2;
				break;
			case SE:
				x = r.x + r.width;
				y = r.y + r.height;
				break;
			case SS:
				x = r.x + r.width / 2;
				y = r.y + r.height;
				break;
			case SW:
				x = r.x;
				y = r.y + r.height;
				break;
			case WW:
				x = r.x;
				y = r.y + r.height / 2;
				break;
			case RR:
				x = r.x + r.width / 2;
				y = r.y - 30;
				break;
			}
			x -= dw;
			y -= dh;
			this.anchors.get(eAnchor.ordinal()).setFrame(x, y, w, h);
		}
	}

	public Rectangle getBoundingRect() {
		return this.r;
	}

}
