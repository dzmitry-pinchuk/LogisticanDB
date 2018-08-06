package by.pinchuk.deadlock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
	private String name;

	public Demo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	private static Logger logger = LogManager.getLogger();
	
	public synchronized void firstOperation(Demo demo) {
		System.out.println(this.name + " try get " + demo.getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.log(Level.INFO, e);
		}
		demo.secondOperation(this);
	}
	
	public synchronized void secondOperation(Demo demo) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.log(Level.INFO, e);
		}
		System.out.println(this.name + " got " + demo.getName());
	}

}
