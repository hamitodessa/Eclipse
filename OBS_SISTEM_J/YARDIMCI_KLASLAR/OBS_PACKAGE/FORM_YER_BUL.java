package OBS_PACKAGE;

import javax.swing.JInternalFrame;

public class FORM_YER_BUL {
	
	public  int   yer_bul_x(String form)
	{
		int result = 0;
		for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
        {   
        JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
        String tit = frame.getTitle();
        if (tit.equals(form) )
        	{
        	result = frame.getBounds().x;
         	}
        }
		return result;
	}
	public  int   yer_bul_y(String form)
	{
		int result = 0;
		for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
        {   
        JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
        String tit = frame.getTitle();
        if (tit.equals(form) )
        	{
        	result = frame.getBounds().y;
         	}
        }
		return result;
	}
}
