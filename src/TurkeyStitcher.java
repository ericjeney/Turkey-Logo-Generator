import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class TurkeyStitcher implements Runnable {
	public void run() {
		int stitched = 0;
		int tiles = 0;
		
		while(stitched < TurkeyController.totalImages) {
			BufferedImage tile = new BufferedImage(4160, 9200, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = tile.createGraphics();
			
			int x = -416;
			int y = 0;
			int tempStitched = 0;

			while(tempStitched < 400 && stitched < TurkeyController.totalImages) {
				TurkeyImage image = TurkeyController.stitchQueue.poll();
				if(image == null) continue;
				
				x += 416;
				if(x >= 4160) {
					x = 0;
					y += 230;
				}
				
				graphics.drawImage(image, null, x, y);
				
				stitched++;
				tempStitched++;
			}
			
			try {
				File outputFile = new File("C:\\Users\\Eric\\Desktop\\Turkeys\\" + tiles + ".gif");
				ImageIO.write(tile, "gif", outputFile);
				System.out.println("Printed tile: " + tiles);
				
				tiles++;
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
