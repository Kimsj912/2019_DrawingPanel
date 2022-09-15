package transformer;

import java.awt.Graphics2D;

import shape.GShape;

public class GMover extends GSelect {

	private int px, py;
	
	
	public GMover() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransformating(Graphics2D graphics2d, int x, int y) {
//		this.getgShape().initMoving(graphics2d,x, y);
		for(GShape shape:this.getSelectedShapevector()) {
			shape.initMoving(graphics2d, x, y);
		}
		
	}

	@Override
	public void keepTransformating(Graphics2D graphics2d, int x, int y) {
//		this.getgShape().keepMoving(graphics2d,x, y);
		for(GShape shape:this.getSelectedShapevector()) {
			shape.keepMoving(graphics2d, x, y);
		}
		
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		for(GShape shape:this.getSelectedShapevector()) {
			shape.finishMoving(graphics2d, x, y);
		}

//		this.getgShape().finishMoving(graphics2d,x, y);
	}

}
