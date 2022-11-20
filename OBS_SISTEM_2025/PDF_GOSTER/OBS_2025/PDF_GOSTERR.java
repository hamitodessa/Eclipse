package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.BorderLayout;

public class PDF_GOSTERR extends JInternalFrame {

	public JPanel panel = new JPanel();
	 public JScrollPane jscpn = new  JScrollPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PDF_GOSTERR frame = new PDF_GOSTERR();
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
	public PDF_GOSTERR() {
		setClosable(true);
		setTitle("PDF GOSTER");
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setBounds(0, 0, 1000, 600);
		
		
	 jscpn = new  JScrollPane();
		 
		 panel = new JPanel();
		 jscpn.add(panel);
		getContentPane().add(jscpn, BorderLayout.CENTER);
		// System.out.println("fdsfdsfsd");
		
		pdf_goster();
      
	}

	public void pdf_goster()
	{
		 try {
			 String file = "C:\\qwerty.pdf" ;
	           SwingController control=new SwingController();
	            SwingViewBuilder factry=new SwingViewBuilder(control);
	            panel =factry.buildViewerPanel();
	            ComponentKeyBinding.install(control, panel);
	      
	            control.getDocumentViewController().setAnnotationCallback(
	                    new org.icepdf.ri.common.MyAnnotationCallback(
	                    control.getDocumentViewController()));
	                   control.openDocument(file);
	     jscpn.setViewportView(panel); 
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this,"Cannot Load Pdf");
	        }
	}
}
