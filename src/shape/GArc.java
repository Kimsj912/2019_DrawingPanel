//package shape;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.geom.Arc2D;
//
//public class GArc extends GShape {
//	private Arc2D arc; // �̰� ������ �������̶����. �ߴ� ���� �����̿��ִ°� ã��. ���� �����ϱ� �� ���ٱ��� ������淡 �̷��� ���̺귯������ ��������
//											// ��!��� �������ذž�.
//
//	public GArc() {
//		super();
//		this.shape = new java.awt.geom.AffineTr
//		this.arc = (java.awt.geom.Arc2D) this.shape;
//	}
//	public GShape newinstance() {
//		return new GArc();
//	}
//	public void setOrigin(int x, int y) { // ���� ����
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
