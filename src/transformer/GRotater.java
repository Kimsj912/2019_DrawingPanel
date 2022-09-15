package transformer;

import java.awt.Graphics2D;

import shape.GPolygon;

public class GRotater extends GSelect {

	public GRotater() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransformating(Graphics2D graphics2d, int x, int y) {
		if (!(this.getgShape() instanceof GPolygon)) {
			this.getgShape().setOrigin(x, y);
		}
		this.getgShape().initRotating(graphics2d, x, y);
	}

	@Override
	public void keepTransformating(Graphics2D graphics2d, int x, int y) {
		this.getgShape().keepRotate(graphics2d, x, y);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().finishRotating(graphics2d, x, y);
	}

}
