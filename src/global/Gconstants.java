package global;

import java.awt.Color;
import java.awt.event.KeyEvent;

import shape.GEllipse;
import shape.GHexagon;
import shape.GLine;
import shape.GPentagon;
import shape.GPolygon;
import shape.GRectangle;
import shape.GRightTriangle;
import shape.GShape;
import shape.GTriangle;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotater;
import transformer.GSelect;
import transformer.GTransformer;

public class Gconstants {

	public enum EMainFrame {
		x(200), y(100), w(800), h(600);

		private int value;

		EMainFrame(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum EToolBar {
		rectangle("�簢��", new GRectangle(),"image/rectangle.jpg","image/rectanglePrs.jpg" ),
		ellipse("Ÿ��", new GEllipse(),"image/circle.jpg" ,"image/circlePrs.jpg"),
		Polygon("�ٰ���", new GPolygon(),"image/polygon.jpg","image/polygonPrs.jpg"),
		line("��", new GLine(),"image/line.jpg","image/linePrs.jpg"),
		triangle("�ﰢ��", new GTriangle(),"image/triangle.jpg","image/trianglePrs.jpg"),
		rightTriangle("�����ﰢ��", new GRightTriangle(),"image/rightTriangle.jpg","image/rightTrianglePrs.jpg"),
		pentagon("������", new GPentagon(),"image/pentagon.jpg","image/pentagonPrs.jpg"),
		hexagon("������", new GHexagon(),"image/hexagon.jpg","image/hexagonPrs.jpg"),
		;
		
		private String text;
		private GShape shape;
		private String imageadrNor;
		private String imageadrPrs;

		EToolBar(String text, GShape shape, String imageadrNor , String imageadrPrs) {
			this.text = text;
			this.shape = shape;
			this.imageadrNor = imageadrNor;
			this.imageadrPrs = imageadrPrs;
		}
		
		public String getText() {
			return this.text;
		}

		public GShape getShape() {
			return this.shape;

		}
		public String getImageAdrNor() {
			return this.imageadrNor;
			
		}
		public String getImageAdrPrs() {
			return this.imageadrPrs;
			
		}
		

	}
	
	public enum EColor {

		red("����", "red", Color.red),
		orange("��Ȳ", "orange", Color.orange),
		yellow("���","yellow",Color.yellow),
		green("�ʷ�","green",Color.green),
		blue("�Ķ�","blue",Color.blue),
		purple("����","purple",Color.magenta),
		white("�Ͼ�","white",Color.white),
		gray("ȸ��", "gray", Color.gray),
		black("����","black",Color.black),
		colorChoose("����","chooseC", null),
		
		;

		private String text;
		private String method;
		private Color color;

		EColor(String text, String method , Color color) {
			this.text = text;
			this.method=method;
			this.color = color;
		}

		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
		public Color getColor() {
			return this.color;
		}
	}

	public enum EMemu {
		fileMenu("����"),
		editMenu("����"),
		shapeMenu("����"),
		
		;

		private String text;

		EMemu(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	public enum EFileMemu {

		newItem("���θ����", "nnew",KeyEvent.VK_N,"������ ���θ���ϴ�."),
		openItem("����", "open",KeyEvent.VK_O,"����� ��ǻ�Ϳ� �����ϴ� ������ ���ϴ�."),
		saveItem("����","save",KeyEvent.VK_S, "������ �����մϴ�."),
		saveAsItem("�ٸ� �̸����� ����","saveAs",0, "������ �ٸ��̸����� �����մϴ�."),
		print("����Ʈ","pprint",KeyEvent.VK_P, "���ݲ� �׸� �׸��� ����Ʈ�մϴ�."),
		closeItem("�ݱ�","close",0,"�ش� ���α׷��� �����մϴ�."),
		
		;

		private String text;
		private String method;
		private int keye;
		private String toolTipText;

		EFileMemu(String text, String method, int keye, String toolTipText) {
			this.text = text;
			this.method=method;
			this.keye = keye;
			this.toolTipText = toolTipText;
		}

		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
		public int getKeye() {
			return this.keye;
		}
		public String getToolTipText() {
			return this.toolTipText;
		}
	}

	public enum EEditMemu {
		
//		undoItem("�ǵ�����", "undo",KeyEvent.VK_Z),
//		redoItem("�ٽ� ����", "redo",KeyEvent.VK_Y),
		selectall("��μ���", "selectall", KeyEvent.VK_A, "��� ������ �����մϴ�."),
		delete("�����ϱ�","delete",KeyEvent.VK_D, "���õ� ������ �����մϴ�."),		
		cutItem("�߶󳻱�","cut",KeyEvent.VK_X, "���õ� ������ �߶󳻾� Ŭ�����忡 �����մϴ�."),
		copyItem("�����ϱ�","copy",KeyEvent.VK_C, "���õ� ������ �����Ͽ� Ŭ�����忡 �����մϴ�."),
		pasteItem("�ٿ��ֱ�","paste",KeyEvent.VK_V, "Ŭ�����忡 ����� ������ �ٿ��ֽ��ϴ�"),
		lrflipItem("�¿������","lrflip",0, "���õ� ������ �¿� ���� ��ŵ�ϴ�."),
		udflipItem("���ϵ�����","udflip",0, "���õ� ������ ���� ���� ��ŵ�ϴ�."),
//		group("������","group",KeyEvent.VK_G),
//		ungroup("������ ����","ungroup",KeyEvent.VK_U),		
		toFront("�� ������ ������","toFront",0, "���õ� ������ �� ������ �����ϴ�."),		
		toBack("�� �ڷ� ������","toBack",0, "���õ� ������ �� �ڷ� �����ϴ�."),		
		;

		private String text;
		private String method;
		private int keye;
		private String toolTipText;

		EEditMemu(String text, String method,int keye,String toolTipText) {
			this.text = text;
			this.method=method;
			this.keye = keye;
			this.toolTipText = toolTipText;

		}

		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
		public int getKeye() {
			return this.keye;
		}
		public String getToolTipText() {
			return this.toolTipText;
		}
	}
public enum ETransformer {
		select("�����ϱ�",new GSelect(),"image/select.jpg","image/selectPrs.jpg"),
		mover("�̵��ϱ�", new GMover(),null,null),
		resizer("ũ�⺯��",new GResizer(),null,null),
		rotater("ȸ���ϱ�",new GRotater(),null,null),
		drawer("�׸���",new GDrawer(),null,null),		
		;

		private String text;
		private GTransformer transformer;
		private String imageadrNor;
		private String imageadrPrs;

		ETransformer(String text, GTransformer transformer, String imageadrNor, String imageadrPrs) {
			this.text = text;
			this.transformer=transformer;
			this.imageadrNor = imageadrNor;
			this.imageadrPrs = imageadrPrs;
			
		}

		public String getText() {
			return this.text;
		}
		public GTransformer getMethod() {
			return this.transformer;
		}
		public String getImageAdrNor() {
			return this.imageadrNor;
		}
		public String getImageAdrPrs() {
			return this.imageadrPrs;
		}
		

	}
}
