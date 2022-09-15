package drawingPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import bottompanel.GBottomPanel;
import global.Gconstants.EToolBar;
import global.Gconstants.ETransformer;
import menu.GPopUpRightClickMenu;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;
import shape.GShape.EOnState;
import toolbarLeft.GToolBar;
import toolbarTop.GHomePanel;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotater;
import transformer.GSelect;
import transformer.GTransformer;;

public class GDrawingPanel extends JPanel {

	@Serial
	private static final long serialVersionUID = 1L;

	private MouseHandler mousehandler;
	private GShape currentTool;
	private Vector<GShape> shapeVector;

	public Object getShapeVector() {
		return this.shapeVector;
	}

	public void setShapeVector(Object object) {
		this.shapeVector = (Vector<GShape>) object;
	}

	private GShape currentShape;

	public boolean iscurrentShape() {
		if (this.currentShape != null) {
			return true;
		}
		return false;
	}

	private enum EActionState {
		eReady, eTransforming, epolyTransforming
	};

	private EActionState eActionState;

	public void setcurrentTool(EToolBar currentTool) {// 초기선택이 왼쪽이라 변경시 오류가 생길 수 있음.
		this.currentTool = currentTool.getShape();
	}

	private GTransformer transformer;

	private Vector<GShape> selectedShape;

	/////////// color
	public ELineFill elineFill;

	public enum ELineFill {
		line, fill;
	}

	public Color graphicLinecolor;

	public Color getstrokecolor() {
		return graphicLinecolor;
	}

	public Color graphicFillColor;

	public Color getfillcolor() {
		return graphicFillColor;
	}

	public void setStrokeColor(Color color) {
		this.graphicLinecolor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		this.gToolBar.setLineColorpane(this.graphicLinecolor);
		this.ghomepanel.setLineColorpane(this.graphicLinecolor);
		for (GShape shape : this.shapeVector) {
			if (shape.isSelected()) {
				shape.setColor(this.graphicLinecolor);
			}
		}
		repaint();
	}

	public void setFillColor(Color color) {
		this.graphicFillColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		this.gToolBar.setFillColorpane(this.graphicFillColor);
		this.ghomepanel.setFillColorpane(this.graphicFillColor);
		for (GShape shape : this.shapeVector) {
			if (shape.isSelected()) {
				shape.setfillColor(this.graphicFillColor);
			}
		}
		repaint();
	}

	////////// Stroke
	private float strokeThickness;

	public float getstrokeThickness() {
		return this.strokeThickness;
	}

	public void setStrokeThickness(int f) {
		this.strokeThickness = f;
		this.currentShape.setStrokeThickness(strokeThickness);
		for (GShape shape : this.shapeVector) {
			if (shape.isSelected()) {
				shape.setStrokeThickness(this.strokeThickness);
			}
		}
		repaint();
	}

	// associate
	private GToolBar gToolBar;
	private GBottomPanel bottompanel;

	private GHomePanel ghomepanel;

	private GClipBoard gClipBoard;

	private GPopUpRightClickMenu gPopUpRightClickMenu;

	private boolean popupon;

	public boolean updated;

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	public boolean isUpdated() {
		return updated;
	}

	public void associate(GToolBar gToolBar, GBottomPanel bottompanel, GHomePanel ghomepanel) {
		this.gToolBar = gToolBar;
		this.bottompanel = bottompanel;
		this.ghomepanel = ghomepanel;
	}

	public GDrawingPanel() {
		this.eActionState = this.eActionState.eReady;

		this.elineFill = ELineFill.line;
		this.graphicFillColor = Color.white;
		this.graphicLinecolor = Color.black;
		this.setBackground(graphicFillColor);
		this.setForeground(graphicLinecolor);

		this.mousehandler = new MouseHandler();
		this.addMouseListener(this.mousehandler);
		this.addMouseMotionListener(this.mousehandler);


		this.shapeVector = new Vector<GShape>();
		this.currentShape = new GRectangle();
		this.selectedShape = new Vector<GShape>();
		this.gClipBoard = new GClipBoard();
//		this.gStack = new GStack();

		this.transformer = new GDrawer();
		this.gPopUpRightClickMenu = new GPopUpRightClickMenu(this.mousehandler);
		this.updated = false;
	}

	public void initialize() {
		this.currentShape.setColor(graphicLinecolor);
		this.currentShape.setfillColor(graphicFillColor);
		this.setStrokeThickness(1);
		this.currentShape.setStrokeThickness(strokeThickness);
		this.transformer.setgShape(currentShape);

	}

	public void undo() {
//		if (!this.gStack.isEmpty()) {
//			Vector<GShape> undoshapeVector = this.gStack.pop();
//			System.out.println(undoshapeVector+"undoshvec");
////			System.out.println(this.shapeVector);
////			this.shapeVector.clear();
//			for (int i = undoshapeVector.size() - 1; i >= 0; i--) {
//				this.shapeVector.add(i, undoshapeVector.get(i));
//
//			}
//
////			this.shapeVector=undoshapeVector;
////			System.out.println(this.shapeVector);
//		} else {
////			System.out.println(this.gStack);
//		}
////		this.shapeVector=this.gStack.peek()
////		System.out.println(undoshapeVector);
//////		this.shapeVector.addAll(undoshapeVector);
////		for(GShape shape:undoshapeVector) {
////			this.shapeVector.add(shape);
////		}
//		repaint();
	}

	public void redo() {

	}

	public void lrflip() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		if (!this.selectedShape.isEmpty()) {
			for (GShape shape : this.selectedShape) {
				shape.initflip(g2, true);
				shape.keepflip(g2, true, this);
			}
			repaint();
		}
		setUpdated(true);

	}

	public void udflip() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		if (!this.selectedShape.isEmpty()) {
			for (GShape shape : this.selectedShape) {
				shape.initflip(g2, false);
				shape.keepflip(g2, false, this);
			}
			repaint();
		}
		setUpdated(true);

	}

	public void delete() {
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			if (shapeVector.get(i).isSelected()) {
				// 나중에 여기에다가 ctrl+z할때 추가하면 좋을 듯.
				this.shapeVector.remove(i);
				if (this.shapeVector.equals(this.transformer.getgShape())) {
					this.transformer.setgShape(null);
				}
			}
		}
		repaint();
		setUpdated(true);

	}

	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		Vector<GShape> midshapes = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			if (shapeVector.get(i).isSelected()) {
				midshapes.add(shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		for (int i = midshapes.size() - 1; i >= 0; i--) {
			if (midshapes.get(i).isSelected()) {
				selectedShapes.add(midshapes.get(i));
			}
		}
		this.gClipBoard.setContents(selectedShapes);
		this.repaint();
		setUpdated(true);

	}

	public void copy() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			if (shapeVector.get(i).isSelected()) {
				selectedShapes.add(shapeVector.get(i));
			}
		}
		this.gClipBoard.setContents(selectedShapes);
		this.repaint();
		setUpdated(true);

	}

	public void paste() {
		Vector<GShape> clipedshapes = this.gClipBoard.getContents();
		Vector<GShape> redundancyShapes = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			for (int j = clipedshapes.size() - 1; j >= 0; j--) {
				GShape svs = shapeVector.get(i);
				GShape css = clipedshapes.get(j);
				if (svs.getMin().equals(css.getMin()) & svs.getMax().equals(css.getMax())
						& svs.getClass().equals(css.getClass())) {
					redundancyShapes.add(clipedshapes.get(j));
					clipedshapes.remove(j);
				}
			}

		}
		for (int i = redundancyShapes.size() - 1; i >= 0; i--) {
			GShape gshape = redundancyShapes.get(i);
			gshape.initMoving((Graphics2D) (this.getGraphics()), gshape.getMin().x, gshape.getMin().y);
			gshape.keepMoving((Graphics2D) (this.getGraphics()), gshape.getMin().x + 10, gshape.getMin().y + 10);
			clipedshapes.add(gshape);
		}
		this.shapeVector.addAll(clipedshapes);
		this.gClipBoard.setContents(clipedshapes);
		this.repaint();
		setUpdated(true);

	}

	public void ungroup() {
		// TODO Auto-generated method stub

	}

	public void group() {
		// TODO Auto-generated method stub
//		this.shapeVector.add(((GSelect)this.transformer).groupingRect(this.shapeVector));

	}

	public void selectall() {
		this.gToolBar.doclickon(ETransformer.select);
		this.selectedShape.clear();
		for (GShape shape : this.shapeVector) {
			shape.setSelected(true);
			this.selectedShape.add(shape);
		}
		this.transformer.setSelectedShapevector(this.selectedShape);
		repaint();
		setUpdated(true);

	}

	public void toFront() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			if (shapeVector.get(i).isSelected()) {
				selectedShapes.add(shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		for (int i = selectedShapes.size() - 1; i >= 0; i--) {
			this.shapeVector.add(selectedShapes.get(i));
		}
		repaint();
		setUpdated(true);

	}

	public void toBack() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			if (shapeVector.get(i).isSelected()) {
				selectedShapes.add(shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		Vector<GShape> shapeVectorCp = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			shapeVectorCp.add(shapeVector.get(i));
		}
		this.shapeVector.clear();
		for (int i = selectedShapes.size() - 1; i >= 0; i--) {
			this.shapeVector.add(selectedShapes.get(i));
		}
		for (int i = shapeVectorCp.size() - 1; i >= 0; i--) {
			this.shapeVector.add(shapeVectorCp.get(i));
		}
		repaint();
		setUpdated(true);

	}

	//////// toolbar

	public void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (GShape shape : this.shapeVector) {
			shape.draw(graphics);
		}
		if (this.transformer.selecting) {
			this.transformer.drawselectingShape((Graphics2D) graphics);
		}

	}

	public void clearall() {
		this.shapeVector.clear();
		this.transformer.setgShape(null);
		this.repaint();
		setUpdated(true);

	}

	private void clearSelected() {
		for (GShape shape : this.shapeVector) {
			shape.setSelected(false);
		}
	}

	private EOnState onShape(int x, int y) { // 모둔 도형을 물어봐야겠지
		this.selectedShape = new Vector<GShape>();
		for (int i = shapeVector.size() - 1; i >= 0; i--) {
			EOnState eOnstate = shapeVector.get(i).onShape(x, y);
			if (eOnstate != EOnState.eOutShape) {
				this.currentShape = shapeVector.get(i);
				this.selectedShape.add(shapeVector.get(i));
				return eOnstate;
			}
		}
		return EOnState.eOutShape;

	}

	private void defineActionState(int x, int y) {
		EOnState eOnstate = onShape(x, y);
		if (this.transformer instanceof GSelect) {
			if (!((GSelect) this.transformer).isSelected(this.currentShape)) {
				this.clearSelected();
				this.currentShape.setSelected(true);
			}
			switch (eOnstate) {
			case eOnShape:
				this.transformer = new GSelect().setTransformer(ETransformer.mover.getMethod());
				this.transformer.setSelectedShapevector(selectedShape);
				this.ghomepanel.setProperties(this.currentShape);
				break;
			case eOnResize:
				this.transformer = new GSelect().setTransformer(ETransformer.resizer.getMethod());
				this.transformer.setSelectedShapevector(selectedShape);
				this.ghomepanel.setProperties(this.currentShape);
				break;
			case eOnRotate:
				this.transformer = new GSelect().setTransformer(ETransformer.rotater.getMethod());
				this.transformer.setSelectedShapevector(selectedShape);
				this.ghomepanel.setProperties(this.currentShape);

				break;
			case eOutShape:
				if (!shapeVector.isEmpty()) {
					this.clearSelected();
				}
				this.transformer = new GSelect();
				break;

			default:
				// exception
				this.eActionState = null;
				break;
			}

		}

	}

	public void setTransformer(GTransformer eTransformer) {
		if (eTransformer instanceof GDrawer) {
			this.clearSelected();
		}
		this.transformer = eTransformer;

	}

	///////// drawing
	private void initTransforming(int x, int y) {
		defineActionState(x, y);
		viewTool(this.transformer);
		if (this.transformer instanceof GDrawer) {
			this.currentShape = this.currentTool.newinstance();
			this.currentShape.setColor(this.graphicLinecolor);
			this.currentShape.setfillColor(this.graphicFillColor);
			this.currentShape.setStrokeThickness(this.strokeThickness);
		}
		this.transformer.setgShape(currentShape);
		this.transformer.initTransformating((Graphics2D) this.getGraphics(), x, y);

	}

	private void keepTransforming(int x, int y) {
		repaint();
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		this.transformer.keepTransformating(graphics2d, x, y);
	}

	private void finishTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		this.transformer.finishTransforming(graphics2d, x, y);
		if (this.transformer instanceof GDrawer) {
			this.shapeVector.add(this.currentShape);
			this.updated = true;

		} else if (this.transformer instanceof GSelect) {
			((GSelect) this.transformer).contains(this.shapeVector, this.currentShape); /////////////////// 스트로크두께의 범인
		}

		repaint();

	}

	private void continueTransforming(int x, int y) {
		this.currentShape.addPoint(x, y);
	}
//////////

	public void viewMousePoint(int x, int y) {
		this.bottompanel.viewMousePoint(x, y);

	}

	public void viewTool(GTransformer transformer) {
		this.bottompanel.viewTool(transformer);
	}

//////

	public void showpopupMenus(MouseEvent e) {
		gPopUpRightClickMenu.show(this, e.getX(), e.getY());
		this.popupon = true;
	}

	public void controlpopupMenus(Component component) {
		if (component.getName() != null) {
			try {
				this.getClass().getMethod(component.getName()).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			popupon = false;
		}
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) { // 응용 이벤트야 이런게
			if (e.getClickCount() == 1) {
				mouse1clicked(e);// 여기에 첫점이면 init하고 2번째 점이면 다른데에다가
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		private void mouse1clicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (eActionState == EActionState.eReady) {
					if (currentTool instanceof GPolygon & transformer instanceof GDrawer) {
						initTransforming(e.getX(), e.getY());
						eActionState = EActionState.eTransforming;
					}
				} else if (eActionState == EActionState.eTransforming) {
					if (currentTool instanceof GPolygon & transformer instanceof GDrawer) {
						continueTransforming(e.getX(), e.getY());
					}
				}
			}

		}

		private void mouse2Clicked(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				if (currentTool instanceof GPolygon & transformer instanceof GDrawer) {
					finishTransforming(e.getX(), e.getY());
					eActionState = EActionState.eReady;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			viewMousePoint(e.getX(), e.getY());
			if (eActionState == EActionState.eTransforming) {
				if (currentTool instanceof GPolygon & transformer instanceof GDrawer) {
					keepTransforming(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) { // 여기서 무브 로테 리사 뭐할지 나눠야해요.
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (popupon) {
					controlpopupMenus(e.getComponent());
				}
				if (eActionState == EActionState.eReady) {
					if (!(currentTool instanceof GPolygon & transformer instanceof GDrawer)) {
						initTransforming(e.getX(), e.getY());
						eActionState = EActionState.eTransforming;
					}
				}
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				if(transformer instanceof GSelect) {
					showpopupMenus(e);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) { // moving을 ready로 바꿔.
			if (eActionState == EActionState.eTransforming) {
				if (!(currentTool instanceof GPolygon & transformer instanceof GDrawer)) {
					finishTransforming(e.getX(), e.getY());
					eActionState = EActionState.eReady;

				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) { // moving일때 뭘 할지 해라.
			viewMousePoint(e.getX(), e.getY());
			if (eActionState == EActionState.eTransforming) {
				if (!(currentTool instanceof GPolygon & transformer instanceof GDrawer)) {
					keepTransforming(e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

	}

}
