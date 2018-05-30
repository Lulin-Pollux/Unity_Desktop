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
				System.out.println(e);
			}
		}
	}
}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
