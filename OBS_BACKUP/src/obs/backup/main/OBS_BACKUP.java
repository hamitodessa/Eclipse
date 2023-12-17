package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import obs.backup.gorev.gOREV_TAKIP;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class OBS_BACKUP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Hamit.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_BACKUP frame = new OBS_BACKUP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OBS_BACKUP() {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		JPanel container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(10, 1, 0, 0));
		


	JButton btnNewButton = new JButton("New button");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				gOREV_TAKIP x = new gOREV_TAKIP("hamit");
				//x.setBounds(0, 0, 400, 100);
				
				container.add(x);
				gOREV_TAKIP x2 = new gOREV_TAKIP("aden");
				//x2.setBounds(0, 110, 400, 100);
				container.add(x2);
//			for (int i = 0; i < 15; i++) {
//				gOREV_TAKIP x = new gOREV_TAKIP(String.valueOf(i + 1));
//		          x.setLocation(0, 45 *i);
//		         container.add(x);
//		      }
				pack();
			}
		});
		splitPane.setLeftComponent(btnNewButton);
	}
	public static void okuma(String isim)
	{
		System.out.println(isim);
	}
	@Override
	   public Dimension getPreferredSize() {
	      Dimension superSz = super.getPreferredSize();
	      if (isPreferredSizeSet()) {
	         return superSz;
	      }
	      return new Dimension(800, 600);
	   }

}
