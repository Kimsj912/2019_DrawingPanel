//package shape;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.geom.Arc2D;
//
//public class GArc extends GShape {
//	private Arc2D arc; // 이걸 렉시컬 스코핑이라고함. 야는 가장 가까이에있는걸 찾아. 가장 가까우니까 걍 렉텐글을 갖고오길래 이렇게 라이브러리에서 가져오는
//											// 얘!라고 지정해준거야.
//
//	public GArc() {
//		super();
//		this.shape = new java.awt.geom.AffineTr
//		this.arc = (java.awt.geom.Arc2D) this.shape;
//	}
//	public GShape newinstance() {
//		return new GArc();
//	}
//	public void setOrigin(int x, int y) { // 원점 세팅
//		this.arc.setBounds(x, y, 0, 0);
//	}
//
//	public void setPoint(int x, int y) {
//		this.arc.setSize(x - this.arc.x, y - this.arc.y);
//	}
//
//	public void addPoint(int x, int y) {
//		
//	}
//
//
//	@Override
//	public void keepMoving(Graphics2D graphics2d,int x, int y) {
//		int dw = x - px;
//		int dh = y - py;
//		
//		this.arc.setLocation(this.arc.x + dw, this.arc.y + dh);
//
//		this.px = x;
//		this.py = y;
//
//	}
//	public void finishMoving(Graphics2D graphics2d,int x, int y) {
//	}
//}
