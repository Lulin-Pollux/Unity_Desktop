package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

import gui.MainFrame;

public class TimerThread extends Thread {
	
	public void run() {
		Date date;
		
		while (true) {
			date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a (z Z)");
			MainFrame.lblDate.setText(df.format(date).toString());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
