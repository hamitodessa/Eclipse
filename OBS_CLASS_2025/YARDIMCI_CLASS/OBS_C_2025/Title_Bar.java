package OBS_C_2025;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import OBS_C_2025.Buttont;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;

@SuppressWarnings({ "serial", "unused" })
public class Title_Bar extends javax.swing.JPanel{
	int x ,y ;
	private javaswingdev.GoogleMaterialIcon iconClose;
	public javaswingdev.GoogleMaterialIcon iconMax;
	private javaswingdev.GoogleMaterialIcon iconMinimize;
	public javaswingdev.GoogleMaterialIcon iconRestore;
	public Buttont btnMax;   
	public Buttont btnClose;
	public Buttont btnMin ;
	public Title_Bar(JFrame frame,boolean minimizeButton,boolean maxButton,String titLE,int gen , int yuk) {

		setMinimumSize(new Dimension(0, 18));
		setMaximumSize(new Dimension(0, 18));
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblNewLabel = new JLabel(titLE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel);

		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);

		btnMin = new Buttont();
		btnMin.setToolTipText("");

		iconMinimize = new javaswingdev.GoogleMaterialIcon();
		iconMinimize.setColor1(new java.awt.Color(111, 111, 111));
		iconMinimize.setColor2(new java.awt.Color(215, 215, 215));
		iconMinimize.setIcon(javaswingdev.GoogleMaterialDesignIcon.REMOVE);
		iconMinimize.setSize(17);
		btnMin.setIcon(iconMinimize.toIcon());
		btnMin.setPreferredSize(new Dimension(18, 17));
		if(minimizeButton)
			add(btnMin);

		btnMax = new Buttont();
		btnMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame. getExtendedState() == Frame.MAXIMIZED_BOTH){
					btnMax.setIcon(iconRestore.toIcon() );
					frame.setBounds(0, 0, gen, yuk);
				}
				else 
				{
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					btnMax.setIcon(iconMax.toIcon() );
				}
			}
		});

		iconMax = new javaswingdev.GoogleMaterialIcon();
		iconMax.setColor1(new java.awt.Color(111, 111, 111));
		iconMax.setColor2(new java.awt.Color(215, 215, 215));
		iconMax.setIcon(javaswingdev.GoogleMaterialDesignIcon.CONTENT_COPY);
		iconMax.setSize(17);

		btnMax.setPreferredSize(new Dimension(18, 17));
		iconRestore = new javaswingdev.GoogleMaterialIcon();
		iconRestore.setColor1(new java.awt.Color(111, 111, 111));
		iconRestore.setColor2(new java.awt.Color(215, 215, 215));
		iconRestore.setIcon(javaswingdev.GoogleMaterialDesignIcon.CROP_DIN);
		iconRestore.setSize(17);
		btnMax.setIcon(iconRestore.toIcon());
		if(maxButton)
			add(btnMax);

		btnClose = new Buttont();
		btnClose.setPreferredSize(new Dimension(18, 17));
		iconClose = new javaswingdev.GoogleMaterialIcon();
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
		iconClose.setColor2(new java.awt.Color(215, 215, 215));
		iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
		iconClose.setSize(17);
		btnClose.setIcon(iconClose.toIcon());
		add(btnClose);
	}
}

//Title_Bar tBar = new Title_Bar(null,false,false,"CALISMA DIZINI",1120,425);
//ActionListener btnCLOSED = new ActionListener() {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//    	btnNewButton_5.doClick();
//    }
//};
//tBar.btnClose.addActionListener(btnCLOSED);
////contentPane.add(tBar, BorderLayout.NORTH);

