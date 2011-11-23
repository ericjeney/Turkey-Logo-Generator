import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TurkeyImage extends BufferedImage {
	private static BufferedImage background;
	private static BufferedImage sprites;
	
	static {
		try {
			background  = ImageIO.read(new URL("http://www.google.com/logos/2011/thanksgiving11-logo-bg.png"));
			sprites = ImageIO.read(new URL("http://www.google.com/logos/2011/thanksgiving11-logo-final-sheet.png"));
		}catch(Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	private int head, feet, index, middle, ring, pinky;
	
	public TurkeyImage(int head, int feet, int index, int middle, int ring, int pinky) {
		super(416, 230, BufferedImage.TYPE_INT_RGB);
		
		this.head = head;
		this.feet = feet;
		this.index = index;
		this.middle = middle;
		this.ring = ring;
		this.pinky = pinky;
	}
	
	public void drawTurkey() {
		Graphics2D g = createGraphics();
		
		// sprites.getSubimage(x, y, width, height)
		// drawImage(subimage, null, x, y)
		
		// Drawing the constants
		g.drawImage(background, null, 0, 0); // Background
		g.drawImage(sprites.getSubimage(0, 646, 81, 79), null, 169, 118); // Wing
		g.drawImage(sprites.getSubimage(0, 457, 40, 44), null, 105, 104); // Face
		
		g.drawImage(sprites.getSubimage(head * 57, 501, 57, 102), null, 109, 50); // Head
		g.drawImage(sprites.getSubimage(feet * 88, 0, 88, 37), null, 155, 176); // Feet
		
		g.drawImage(sprites.getSubimage(index * 44, 37, 44, 110), null, 136, 21); // Index
		g.drawImage(sprites.getSubimage(middle * 42, 252, 42, 119), null, 159, 7); // Middle
		g.drawImage(sprites.getSubimage(ring * 42, 147, 42, 105), null, 188, 20); // Ring
		g.drawImage(sprites.getSubimage(pinky * 42, 371, 42, 86), null, 216, 48); // Pinky
	}
	
	public static void main(String[] args) {
		TurkeyImage turkey = new TurkeyImage(0,0,0,0,0,0);
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(new JLabel(new ImageIcon(turkey)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
