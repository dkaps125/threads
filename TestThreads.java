package threads;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A class that provides a frame for a drawing application. Includes a JMenuBar which can
 * change pen color, shape, and width.
 * @author Daniel Kapit
 *
 */
public class TestThreads extends JFrame {
	
	static Paint p = new Paint();
	
	/**
	 * Constructs the frame for the painting application
	 */
	public TestThreads() {
		super("Threads");
		setSize(1000, 900);
		setTitle("Threads");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Paint());
		setBackground(java.awt.Color.WHITE);
		validate();
		add(p);
		JMenuBar menubar = new JMenuBar();
		menubar.add(createOptionsMenu());
		menubar.add(createColorMenu());
		menubar.add(createPenMenu());
		setJMenuBar(menubar);
	}
	
	/**
	 * Creates a menu which provides options for changing pen width
	 * @return a menu that gives options for changing pen width
	 */
	public JMenu createPenMenu() {
		JMenu menu = new JMenu("Pen width...");
		menu.add(createWidthItem(5.0F));
		menu.add(createWidthItem(4.0F));
		menu.add(createWidthItem(3.0F));
		menu.add(createWidthItem(2.0F));
		menu.add(createWidthItem(1.0F));
		menu.add(createWidthItem(0.5F));
		menu.add(createWidthItem(0.25F));
		return menu;
	}
	
	/**
	 * Creates a menu item that, when clicked, changes the pen width
	 * @param s a width value for the pen
	 * @return a menu item that changes the pen width
	 */
	public JMenuItem createWidthItem(final float s) {
		JMenuItem item = new JMenuItem(s +"");
		class Width implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				p.pen_width = new BasicStroke(s);
			}
		}
		item.addActionListener(new Width());
		return item;
	}
	
	/**
	 * Creates a menu item that changes the pen color
	 * @param n a pen color change option
	 * @return an item that changes the pen color
	 */
	public JMenuItem createColorItem(final Color n) {
		JMenuItem item = new JMenuItem(n.toString());
		class ChangeColor implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				p.pen = n;				
			}
		}
		item.addActionListener(new ChangeColor());
		return item;
	}
	
	/**
	 * Creates the menu that provides basic application options
	 * @return the basic option menu
	 */
	public JMenu createOptionsMenu() {
		JMenu menu = new JMenu("Options");
		menu.add(createClearItem());
		menu.add(createShapeMenu());
		return menu;
	}
	
	/**
	 * Creates an item that clears the drawing panel
	 * @return a menu item that clears the drawing panel
	 */
	public JMenuItem createClearItem() {
		JMenuItem item = new JMenuItem("Clear");
		class Clear implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				p.shapes.clear();
				p.palette.clear();
				p.palette.add(p.pen);
				p.repaint();
			}
		}
		item.addActionListener(new Clear());
		return item;
	}
	
	/**
	 * Creates a menu that allows the user to change the pen shape
	 * @return a menu that allows the user to change the pen shape
	 */
	public JMenu createShapeMenu() {
		JMenu menu = new JMenu("Shapes...");
		menu.add(createShapeItem("Ellipse"));
		menu.add(createShapeItem("Rectangle"));
		menu.add(createShapeItem("Line"));
		return menu;
	}
	
	/**
	 * Creates a menu item that, when clicked, changes the pen shape
	 * @param shape the shape for the item to display
	 * @return a menu item that changes the pen shape
	 */
	public JMenuItem createShapeItem(final String shape) {
		JMenuItem item = new JMenuItem(shape);
		class Shape implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				p.shape = shape;
			}
		}
		item.addActionListener(new Shape());
		return item;
	}
	
	/**
	 * Creates a menu that contains items which change the pen color
	 * @return a menu that contains color options for the pen
	 */
	public JMenu createColorMenu() {
		JMenu menu = new JMenu("Color...");
		menu.add(createColorItem(Color.BLACK));
		menu.add(createColorItem(Color.BLUE));
		menu.add(createColorItem(Color.RED));
		menu.add(createColorItem(Color.YELLOW));
		menu.add(createColorItem(Color.GREEN));
		menu.add(createColorItem(Color.CYAN));
		menu.add(createColorItem(Color.GRAY));
		menu.add(createColorItem(Color.WHITE));
		return menu;
	}
	
	public static void main(String args[]) { 
		TestThreads test = new TestThreads();
		Thread t = new Thread(p);
		test.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		t.start();
    }
}
