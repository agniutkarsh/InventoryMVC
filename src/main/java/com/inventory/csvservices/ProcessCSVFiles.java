package com.inventory.csvservices;

public class ProcessCSVFiles {
	public void initialteThreadClass() {

		CSVHandlerThread csvhandler = new CSVHandlerThread();
		Thread t1 = new Thread(csvhandler);
		t1.start();
	} 
}