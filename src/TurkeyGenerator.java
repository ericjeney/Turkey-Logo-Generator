import java.io.File;

import javax.imageio.ImageIO;


public class TurkeyGenerator implements Runnable {
	public void run() {
		while(true) {
			TurkeyImage turkey = TurkeyController.turkeyQueue.poll();
			
			if(turkey == null) {
				try {
					Thread.sleep(500);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}else {
				turkey.drawTurkey();
				
				int i = TurkeyController.counter.getAndIncrement();
				
				TurkeyController.stitchQueue.add(turkey);
			}
		}
	}
}
