package thread;

import gui.MainFrame;
import terminal.Shell;

public class ShellThread extends Thread {
	
	public void run() {
		Shell.shell();
		
		MainFrame.shell_run = false;	//Shell 종료 표시
		MainFrame.console_used = false;		//콘솔 사용 중지 표시
		return;
	}
}
