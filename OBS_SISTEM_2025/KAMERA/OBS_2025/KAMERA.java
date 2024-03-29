package OBS_2025;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class KAMERA extends JInternalFrame 
{
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private Webcam webcam  ;
	public KAMERA() {
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				super.internalFrameClosing(e);
			}
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				webcam.close();
				super.internalFrameClosed(e);
			}
		});
		setClosable(true);
		setTitle("Resim Cek");
		setBounds(100, 100, 450, 300);

		Dimension desktopSize = OBS_MAIN.desktopPane.getSize();
		Dimension jInternalFrameSize =this.getSize();
		this.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height- jInternalFrameSize.height)/2);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		WebcamPanel panel1 = new WebcamPanel(webcam);
		panel1.setFPSDisplayed(true);
		panel1.setDisplayDebugInfo(true);
		panel1.setImageSizeDisplayed(true);
		panel1.setMirrored(true);
		panel.add(panel1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		panel_1.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Resim Al");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//oac.kam_resmi = webcam.getImage();
				Image im = webcam.getImage();
				Image myImg =  fitimage(im,230,175); // im.getScaledInstance(230, 175,Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				BufferedImage bi = new BufferedImage(newImage .getIconWidth(), newImage .getIconHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g = bi.createGraphics();
				newImage.paintIcon(null, g, 0, 0);
				g.setColor(Color.WHITE);
				g.dispose();
				URUN_KART.imagePanel.setImage(bi);
				webcam.close();
				dispose();
			}
		});
		btnNewButton.setBounds(10, 3, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Vazgec");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				webcam.close();
				dispose();
			}
		});
		btnNewButton_1.setBounds(333, 3, 89, 23);
		panel_1.add(btnNewButton_1);
	}
	private Image fitimage(Image img , int w , int h)
	{
		BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0,w,h,null);
		g2.dispose();
		return resizedimage;
	}

}
