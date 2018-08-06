package by.pinchuk.deadlock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunDeadlock {
	
	public static void main(String[] args) {
		 Demo demo1 = new Demo("demo1");
		 Demo demo2 = new Demo("demo2");
			Logger logger = LogManager.getLogger();
		
		 new Thread(new Runnable() {
			 public void run() {
				 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						logger.log(Level.INFO, e);
					}
				 demo1.firstOperation(demo2);
			 }}).start();
		 
		 new Thread(new Runnable() {
			 public void run() {
				 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						logger.log(Level.INFO, e);
					}
				 demo2.firstOperation(demo1);
			 }}).start();

	}

}
