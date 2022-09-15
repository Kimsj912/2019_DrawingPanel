package transformer;

import java.awt.Graphics2D;

import shape.GPolygon;
import shape.GShape;

public class GResizer extends GSelect {

	public GResizer() {

	}

	@Override
	public void initTransformating(Graphics2D graphics2d, int x, int y) {
//		this.getgShape().initResizing(graphics2d,x, y);
		for (GShape shape : this.getSelectedShapevector()) {
			shape.initResizing(graphics2d, x, y);
		}

	}

	@Override
	public void keepTransformating(Graphics2D graphics2d, int x, int y) {
//		this.getgShape().keepResize(graphics2d, x,y);
		for (GShape shape : this.getSelectedShapevector()) {
			shape.keepResize(graphics2d, x, y);
		}

	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		for (GShape shape : this.getSelectedShapevector()) {
			shape.finishResizing(graphics2d, x, y);
		}
	}
	public void drawselectingShape(Graphics2D graphics2d) {
		for (GShape shape : this.getSelectedShapevector()) {
			if(shape instanceof GPolygon) {
				setgShape(shape);
			}
		}			
	}
	;

}
