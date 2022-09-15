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
		rectangle("사각형", new GRectangle(),"image/rectangle.jpg","image/rectanglePrs.jpg" ),
		ellipse("타원", new GEllipse(),"image/circle.jpg" ,"image/circlePrs.jpg"),
		Polygon("다각형", new GPolygon(),"image/polygon.jpg","image/polygonPrs.jpg"),
		line("선", new GLine(),"image/line.jpg","image/linePrs.jpg"),
		triangle("삼각형", new GTriangle(),"image/triangle.jpg","image/trianglePrs.jpg"),
		rightTriangle("직각삼각형", new GRightTriangle(),"image/rightTriangle.jpg","image/rightTrianglePrs.jpg"),
		pentagon("오각형", new GPentagon(),"image/pentagon.jpg","image/pentagonPrs.jpg"),
		hexagon("육각형", new GHexagon(),"image/hexagon.jpg","image/hexagonPrs.jpg"),
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

		red("빨강", "red", Color.red),
		orange("주황", "orange", Color.orange),
		yellow("노랑","yellow",Color.yellow),
		green("초록","green",Color.green),
		blue("파랑","blue",Color.blue),
		purple("보라","purple",Color.magenta),
		white("하양","white",Color.white),
		gray("회색", "gray", Color.gray),
		black("검정","black",Color.black),
		colorChoose("선택","chooseC", null),
		
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
		fileMenu("파일"),
		editMenu("편집"),
		shapeMenu("도형"),
		
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

		newItem("새로만들기", "nnew",KeyEvent.VK_N,"파일을 새로만듭니다."),
		openItem("열기", "open",KeyEvent.VK_O,"당신의 컴퓨터에 존재하는 파일을 엽니다."),
		saveItem("저장","save",KeyEvent.VK_S, "파일을 저장합니다."),
		saveAsItem("다른 이름으로 저장","saveAs",0, "파일을 다른이름으로 저장합니다."),
		print("프린트","pprint",KeyEvent.VK_P, "지금껏 그린 그림을 프린트합니다."),
		closeItem("닫기","close",0,"해당 프로그램을 종료합니다."),
		
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
		
//		undoItem("되돌리기", "undo",KeyEvent.VK_Z),
//		redoItem("다시 실행", "redo",KeyEvent.VK_Y),
		selectall("모두선택", "selectall", KeyEvent.VK_A, "모든 도형을 선택합니다."),
		delete("삭제하기","delete",KeyEvent.VK_D, "선택된 도형을 삭제합니다."),		
		cutItem("잘라내기","cut",KeyEvent.VK_X, "선택된 도형을 잘라내어 클립보드에 저장합니다."),
		copyItem("복사하기","copy",KeyEvent.VK_C, "선택된 도형을 복사하여 클립보드에 저장합니다."),
		pasteItem("붙여넣기","paste",KeyEvent.VK_V, "클립보드에 저장된 도형을 붙여넣습니다"),
		lrflipItem("좌우뒤집기","lrflip",0, "선택된 도형을 좌우 반전 시킵니다."),
		udflipItem("상하뒤집기","udflip",0, "선택된 도형을 상하 반전 시킵니다."),
//		group("모으기","group",KeyEvent.VK_G),
//		ungroup("모으기 해제","ungroup",KeyEvent.VK_U),		
		toFront("맨 앞으로 보내기","toFront",0, "선택된 도형을 맨 앞으로 보냅니다."),		
		toBack("맨 뒤로 보내기","toBack",0, "선택된 도형을 맨 뒤로 보냅니다."),		
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
		select("선택하기",new GSelect(),"image/select.jpg","image/selectPrs.jpg"),
		mover("이동하기", new GMover(),null,null),
		resizer("크기변경",new GResizer(),null,null),
		rotater("회전하기",new GRotater(),null,null),
		drawer("그리기",new GDrawer(),null,null),		
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
