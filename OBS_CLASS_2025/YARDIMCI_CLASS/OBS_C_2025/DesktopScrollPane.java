package OBS_C_2025;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class DesktopScrollPane extends ScrollPaneWin11 {

	private JDesktopPane desktopPane;
	private InternalFrameComponentListener componentListener;

	public DesktopScrollPane(JDesktopPane desktopPane) {
		componentListener = new InternalFrameComponentListener();
		this.desktopPane = desktopPane;
		desktopPane.addContainerListener(new ContainerListener() {
			@Override
			public void componentAdded(ContainerEvent e) {
				onComponentAdded(e);
			}
			@Override
			public void componentRemoved(ContainerEvent e) {
				onComponentRemoted(e);
			}
		});
		setViewportView(desktopPane);
		// set some defaults
		// setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(ScrollPaneWin11.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneWin11.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	private void onComponentRemoted(ContainerEvent event) {
		Component removedComponent = event.getChild();
		if (removedComponent instanceof JInternalFrame)
			removedComponent.removeComponentListener(componentListener);

	}

	private void onComponentAdded(ContainerEvent event) {
		Component addedComponent = event.getChild();
		if (addedComponent instanceof JInternalFrame)
		{
			addedComponent.addComponentListener(componentListener);
			resizeDesktop();
		}
	}
	/**
	 * returns all internal frames placed upon the desktop
	 *
	 * @return a JInternalFrame array containing references to the internal frames
	 */
	public JInternalFrame[] getAllFrames() {
		return desktopPane.getAllFrames();
	}

	/**
	 * sets the preferred size of the desktop
	 *
	 * @param dim a Dimension object representing the desired preferred size
	 */
	public void setDesktopSize(Dimension dim) {
		desktopPane.setPreferredSize(dim);
		desktopPane.revalidate();
	}
	/**
	 * resizes the desktop based upon the locations of its
	 * internal frames. This updates the desktop scrollbars in real-time.
	 */
	public void resizeDesktop() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				// has to go through all the internal frames now and make sure none
				// off screen, and if so, add those scroll bars!
				Rectangle viewPort = getViewport().getViewRect();
				int maxX = viewPort.width + viewPort.x, maxY = viewPort.height + viewPort.y;
				int minX = viewPort.x, minY = viewPort.y;
				// determine the min/max extents of all internal frames
				JInternalFrame frame = null;
				JInternalFrame[] frames = getAllFrames();
				for (int i=0; i < frames.length; i++) {
					frame = frames[i];
					if (frame.getX() < minX) { // get minimum X
						minX = frame.getX();
					}
					if ((frame.getX() + frame.getWidth()) > maxX)
					{
						maxX = frame.getX() + frame.getWidth();
					}
					if (frame.getY() < minY) { // get minimum Y
						minY = frame.getY();
					}
					if ((frame.getY() + frame.getHeight()) > maxY)
					{
						maxY = frame.getY() + frame.getHeight();
					}
				}
				// Don't count with frames that get off screen from the left side ant the top
				if (minX < 0) minX = 0;
				if (minY < 0) minY = 0;

				setVisible(false); // don't update the viewport
				// while we move everything (otherwise desktop looks 'bouncy')

				if (minX != 0 || minY != 0) {
					// have to scroll it to the right or up the amount that it's off screen...
					// before scroll, move every component to the right / down by that amount
					for (int i=0; i < frames.length; i++) {
						frame = frames[i];
						frame.setLocation(frame.getX()-minX, frame.getY()-minY);
					}
					// have to scroll (set the viewport) to the right or up the amount
					// that it's off screen...
					JViewport view = getViewport();
					view.setViewSize(new Dimension((maxX-minX),(maxY-minY)));
					view.setViewPosition(new Point((viewPort.x-minX),(viewPort.y-minY)));
					setViewport(view);
				}
				// resize the desktop
				setDesktopSize(new Dimension(maxX-minX, maxY-minY));
				setVisible(true); // update the viewport again
			}
		});
	}

	private class InternalFrameComponentListener implements ComponentListener
	{
		@Override
		public void componentResized(ComponentEvent e) {
			resizeDesktop();
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			resizeDesktop();
		}

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			
		}
	}
}
