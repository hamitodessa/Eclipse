package obs.obs_fihrist.other;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import obs.button.Buttont;
import obs.obs_fihrist.OBS_FIHRIST;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

@SuppressWarnings({ "serial", "unused" })
public class Title_Bar extends javax.swing.JPanel{
	int x ,y ;
	 private javaswingdev.GoogleMaterialIcon iconClose;
	    //private javaswingdev.GoogleMaterialIcon iconMax;
	    private javaswingdev.GoogleMaterialIcon iconMinimize;
	    private javaswingdev.GoogleMaterialIcon iconRestore;
	    
	 public Title_Bar() {
	 	addMouseListener(new MouseAdapter() {
	 		@Override
	 		public void mousePressed(MouseEvent e) {
	 			 x = e.getX(); 
				  y = e.getY(); 
	 		}
	 	});
	 	addMouseMotionListener(new MouseMotionAdapter() {
	 		@Override
	 		public void mouseDragged(MouseEvent e) {
	 			
	 			 int xx = e.getXOnScreen();
				  int yy = e.getYOnScreen(); 
				OBS_FIHRIST.app.setLocation(xx-x,yy-y);
	 		}
	 	});
		 setMinimumSize(new Dimension(0, 18));
		 setMaximumSize(new Dimension(0, 18));
		 setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		 	
		 Component horizontalGlue = Box.createHorizontalGlue();
		 add(horizontalGlue);
		 	
		 	Buttont btnNewButton = new Buttont();
		 	btnNewButton.addActionListener(new ActionListener() {
		 		public void actionPerformed(ActionEvent e) {
		 			OBS_FIHRIST.app.minimized();
		 		}
		 	});
		 	
		 	iconMinimize = new javaswingdev.GoogleMaterialIcon();
		 	iconMinimize.setColor1(new java.awt.Color(111, 111, 111));
		    iconMinimize.setColor2(new java.awt.Color(215, 215, 215));
		    iconMinimize.setIcon(javaswingdev.GoogleMaterialDesignIcon.REMOVE);
		    iconMinimize.setSize(17);
		 	btnNewButton.setIcon(iconMinimize.toIcon());
		 	btnNewButton.setPreferredSize(new Dimension(18, 17));
		 	add(btnNewButton);
		 	
		 	Buttont btnNewButton_1 = new Buttont();
		 	btnNewButton_1.addActionListener(new ActionListener() {
		 		public void actionPerformed(ActionEvent e) {
		 		
		 			OBS_FIHRIST.app.buyult();
		 		}
		 	});
		 	
		 	
		 	btnNewButton_1.setPreferredSize(new Dimension(18, 17));
		 	iconRestore = new javaswingdev.GoogleMaterialIcon();
		 	iconRestore.setColor1(new java.awt.Color(111, 111, 111));
		    iconRestore.setColor2(new java.awt.Color(215, 215, 215));
		    iconRestore.setIcon(javaswingdev.GoogleMaterialDesignIcon.CROP_DIN);
		    iconRestore.setSize(17);
		 	btnNewButton_1.setIcon(iconRestore.toIcon());
		 	add(btnNewButton_1);
		 	
		 	Buttont btnNewButton_2 = new Buttont();
		 	btnNewButton_2.addActionListener(new ActionListener() {
		 		public void actionPerformed(ActionEvent e) {
		 			System.exit(1);
		 		}
		 	});
		 	btnNewButton_2.setPreferredSize(new Dimension(18, 17));
		 	 iconClose = new javaswingdev.GoogleMaterialIcon();
		 	iconClose.setColor1(new java.awt.Color(111, 111, 111));
		 	iconClose.setColor2(new java.awt.Color(215, 215, 215));
		    iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
		    iconClose.setSize(17);
		    btnNewButton_2.setIcon(iconClose.toIcon());
		 	add(btnNewButton_2);
	 }
	

}
