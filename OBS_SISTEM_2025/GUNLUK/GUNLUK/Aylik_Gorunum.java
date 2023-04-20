package GUNLUK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class Aylik_Gorunum extends JPanel{
	
	int gridRow = 5;
	int gridColumn = 7;
	private static final String INITIAL_TEXT = "Nothing Pressed";
    private static final String ADDED_TEXT = " was Pressed";
    
    public Aylik_Gorunum(int sayi, int sayi2){
    	if ((sayi + sayi2 ) > 35) 
    		{
    		gridRow = 6;
    		}
    	else if ((sayi + sayi2 ) <= 29) 
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
            		button = new JButton();
            	}
            	else {
					
            		if (  gunler > sayi2)
            		{
            			button = new JButton();
            		}
            		else {
						
            		
					String qweString = Integer.toString(gunler) ;
					String twoLines =  "<p style=text-align:center;> <font color = blue > <b> " + qweString + " </b> <br> </p> <p style=text-align:left;> <font color = black > " +" Deneme Lines" 
							+ "<br>" +  "<font color = red > " +" abc  </p>";
					
            		 button = new JButton("<html>" + twoLines + "</html>");
            		 button.setName(Integer.toString(gunler));
            		 button.setActionCommand("" + Integer.toString(gunler) + "");
                     button.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent ae)
                         {
                             JButton but = (JButton) ae.getSource();
                      Gunluk.aYLIK();
                             System.out.println(but.getActionCommand() + ADDED_TEXT);
                         }
                     });
                     gunler +=1 ;
            		}
				}
                
               
               add(button);
            }
       
        }
     
 
        
        
        //JButton btngiris = new JButton("Giris");
		//btngiris.setBounds(10, 30, 110, 23);
		
        
	
        //???
    }

}
