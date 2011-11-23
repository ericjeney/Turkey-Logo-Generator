import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TurkeyController implements Runnable {
	public static ConcurrentLinkedQueue<TurkeyImage> turkeyQueue = new ConcurrentLinkedQueue<TurkeyImage>();
	public static ConcurrentLinkedQueue<TurkeyImage> stitchQueue = new ConcurrentLinkedQueue<TurkeyImage>();
	
	public static AtomicInteger counter = new AtomicInteger(0);
	
	public static int totalImages = 12*12*12*12*12*11;
	
	public void run() {
		BufferedImage turkey = null;
		
		for(int i = 0; i < 2; i++) {
			TurkeyGenerator generator = new TurkeyGenerator();
			new Thread(generator).start();
		}
		
		for(int head = 0; head < 12; head++) {
			for(int feet = 0; feet < 12; feet++) {
				for(int index = 0; index < 12; index++) {
					for(int middle = 0; middle < 12; middle++) {
						for(int ring = 0; ring < 12; ring++) {
							for(int pinky = 0; pinky < 11; pinky++) {
								while((turkeyQueue.size() + stitchQueue.size()) > 2000) {
									try { Thread.sleep(500); }catch(Exception ex) {}
								}
								
								turkeyQueue.add(new TurkeyImage(head, feet, index, middle, ring, pinky));
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(new TurkeyController()).start();
		Thread stitcher = new Thread(new TurkeyStitcher());
		stitcher.start();
		
		long time = System.currentTimeMillis();
		
		try {
			Thread.sleep(5000);
			
			while(!turkeyQueue.isEmpty() || !stitchQueue.isEmpty()) {
				System.out.println("Completed: " + counter.get() + " in " + (System.currentTimeMillis() - time) + " ms");
				Thread.sleep(5000);
			}
			
			while(stitcher.isAlive()) {
				Thread.sleep(1000);
			}
		}catch(Exception ex) {}
		
		System.out.println("Done in " + (System.currentTimeMillis() - time) + " ms");
		System.exit(0);
	}
}
