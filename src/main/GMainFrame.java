package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bottompanel.GBottomPanel;
import drawingPanel.GDrawingPanel;
import drawingPanel.GLeftRuler;
import drawingPanel.GTopRuler;
import global.Gconstants.EMainFrame;
import menu.GMenuBar;
import toolbarLeft.GToolBar;
import toolbarTop.GHomePanel;

public class GMainFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private GMenuBar menubar;
	private GToolBar toolbar;
	private GDrawingPanel drawingpanel;
	private GBottomPanel bottompanel;

	private GHomePanel homepanel;

	private GLeftRuler leftRuler;

	private GTopRuler topRuler;
	
	public GMainFrame() {
		super("Graphics");
		//attribute >> ���� ��� �� , �Ӽ����� 
		this.setLocation(EMainFrame.x.getValue(),EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(),EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		//component >> �ڽĵ�, �ִ°� ��ӹ޾Ƽ� �����, �̹� ���̺귯���� ��õ���� Ŭ������ ��������־�. �ڹ� ��Ÿ�� ���̺귯�� 
		
		JPanel homemenu = new JPanel();
		homemenu.setLayout(new BorderLayout());
		this.menubar= new GMenuBar();
		homemenu.add(this.menubar, BorderLayout.NORTH);

		this.homepanel = new GHomePanel();
		homemenu.add(this.homepanel, BorderLayout.CENTER);
		this.add(homemenu, BorderLayout.NORTH);
		
		this.toolbar=new GToolBar();
		this.add(this.toolbar, BorderLayout.WEST);
	
		JPanel dpSET = new JPanel();
		dpSET.setLayout(new BorderLayout());
		
		this.drawingpanel=new GDrawingPanel();
		dpSET.add(this.drawingpanel, BorderLayout.CENTER);
		
		this.bottompanel = new GBottomPanel();
		dpSET.add(this.bottompanel, BorderLayout.SOUTH);
		
		this.add(dpSET, BorderLayout.CENTER);
		
	}

	public void initialize() {
		
		//Association
		this.menubar.associate(this.drawingpanel, this.toolbar);
		this.homepanel.associate(this.drawingpanel, this.toolbar);
		this.menubar.associate(this.drawingpanel, this.toolbar);
		this.toolbar.associate(this.drawingpanel, this.menubar);
		this.drawingpanel.associate(this.toolbar, this.bottompanel,this.homepanel);
		this.bottompanel.associate(this.drawingpanel);

		//initialize
		this.menubar.initialize();
		this.homepanel.initialize();
		this.toolbar.initialize();
		this.drawingpanel.initialize();
		this.bottompanel.initialize();
		
	}

}
