package GUNLUK;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class Aylik_Gorunum extends JPanel{
	
	int gridRow = 5;
	int gridColumn = 7;
    
    public Aylik_Gorunum(int sayi, int sayi2){
    	//System.out.println(sayi +"=="+ sayi2);
    	if ((sayi + sayi2 ) > 37) 
    		{
    		gridRow = 6;
    		}
    	else if ((sayi + sayi2 ) <= 30) 
		{
		gridRow = 4;
		}
    	setPreferredSize( new Dimension(600, 400) );
        setBorder(new LineBorder(new Color(0, 191, 255)));
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
					String twoLines =  "<html><p style=text-align:center;> <font color = #303A68 > <b> " + qweString + " </b> <br> </p></html>";
					
            		 button = new JButton( twoLines );
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
							} catch (ClassNotFoundException | SQLException e) 
                             {
								e.printStackTrace();
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