package threads;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * Implements the thread class to creates a painting application.
 * @author Daniel kapit
 *
 */
public class Paint extends JComponent implements Runnable {
	
	int x = 0;
	int y = 0;
	Rectangle r = new Rectangle(this.x, this.y, 20, 20);
	Ellipse2D.Double  el = new Ellipse2D.Double(this.x, this.y, 20, 20);
	Shape s = r;
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	ArrayList<Color> palette = new ArrayList<Color>();
	Color pen = Color.black;
	static boolean click = false;
	String shape = "Rectangle";
	BasicStroke pen_width = new BasicStroke(0.1F);

	/**
	 * Constructs the painting object
	 */
	public Paint() {
		this.palette.add(Color.BLACK);
		setBackground(Color.WHITE);
		addMouseListener(new Mouse());
	}
	
	/**
	 * Overrides the JComponent.paint() method
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		shapes.add(s);
		s = setShape(shape);
		g2.setStroke(pen_width);
		for (Shape a : shapes) {
			g2.setColor(palette.get(shapes.indexOf(a)));
			g2.draw(a);
			g2.fill(a);
		}
		g2.setColor(pen);
		g2.draw(s);
		g2.fill(s);
		palette.add(pen);
	}
	
	/**
	 * Sets the shape of the pen
	 * @param cue the new pen shape
	 * @return the shape to which the pen has been changed
	 */
	public Shape setShape(String cue) {
		if (cue.equalsIgnoreCase("Rectangle"))
			return new Rectangle(this.x-20, this.y-20, 40, 40);
		else if (cue.equalsIgnoreCase("Ellipse"))
			return new Ellipse2D.Double(this.x-20, this.y-20, 40, 40);
		else if (cue.equalsIgnoreCase("Line")) {
			return new Line2D.Double(this.x, this.y, this.x, this.y);
		}
		else 
			return new Line2D.Double(this.x, this.y, 40, 40);
	}
	
	@Override
	/**
	 * What to do when the thread runs; actually implements the painting
	 */
	public void run() {
		while (true) {
			if (click) {
				try {
					if (this.getMousePosition() != null && (this.getMousePosition().x > 0 && 
							this.getMousePosition().y > 0)) {
						this.x = this.getMousePosition().x;
						this.y = this.getMousePosition().y;
						repaint();
						//try {
						//	Thread.sleep(10);
						//} catch (InterruptedException e) {}
					}
				} catch (Exception e) {}	
			}
		}
	}
	
	/**
	 * Creates the mouse listeners allowing the painting to occur
	 * @author Daniel Kapit
	 *
	 */
	class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			click = true;
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			click = false;
			
		}
		
	}
}
