package GUNLUK;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Aylik_Gorunum extends JPanel{

	int gridRow = 5;
	int gridColumn = 7;

	public Aylik_Gorunum(int sayi, int sayi2){
		if ((sayi + sayi2 ) > 37)
			gridRow = 6;
		else if ((sayi + sayi2 ) <= 30)
			gridRow = 4;
		setPreferredSize( new Dimension(600, 400) );
		setLayout(new GridLayout(gridRow, gridColumn, 2, 2));
		JButton button  ;
		int gunler = 1 ;
		for (int i = 0; i < gridRow; i++)
		{
			for (int j = 0; j < gridColumn; j++)
			{
				if (i == 0 && j < sayi -1   )
				{
					button = new JButton("");
					button.setEnabled(false);
				}
				else {
					if (  gunler > sayi2)
					{
						button = new JButton("");
						button.setEnabled(false);
					}
					else {
						String qweString = Integer.toString(gunler) ;
						//String twoLines =  "<html><p style=text-align:center;> <font color = #303A68 > <b> " + qweString + " </b> <br> </p></html>";
						String twoLines =  "<html><p style=text-align:center;>  <b> " + qweString + " </b> <br> </p></html>";
						button = new JButton( twoLines );
						button.setToolTipText(twoLines);
						button.setName(Integer.toString(gunler));
						button.setActionCommand("" + Integer.toString(gunler) + "");
						button.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent ae)
							{
								JButton but = (JButton) ae.getSource();
								try {
									if (! but.getName().equals(""))
									{
										setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
										Gunluk.aYLIK(but.getActionCommand());
										setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
									}
								} catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
						});
						button.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getClickCount() == 2) {
									try {
										Gunluk.ay_gorev_ac();
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
							}
						});
						gunler +=1 ;
					}
				}
				add(button);
			}
		}
	}
}
