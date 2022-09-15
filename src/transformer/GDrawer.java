package transformer;

import java.awt.Graphics2D;

import shape.GPolygon;

public class GDrawer extends GTransformer {

	public GDrawer() {

	}

	@Override
	public void initTransformating(Graphics2D graphics2d, int x, int y) {
		selecting = true;
		this.getgShape().setOrigin(x, y);
	}

	@Override
	public void keepTransformating(Graphics2D graphics2d, int x, int y) {
		selecting = true;
		this.getgShape().setPoint(x, y);
		this.getgShape().draw(graphics2d);

	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().draw(graphics2d);
		selecting = false;
	}

	@Override
	public void drawselectingShape(Graphics2D graphics2d) {
		this.getgShape().draw(graphics2d);

	};

}
