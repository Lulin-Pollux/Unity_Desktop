package thread;

import gui.MainFrame;
import terminal.Shell;

public class ShellThread extends Thread {
	
	public void run() {
		Shell.shell();
		
		MainFrame.shell_run = false;	//Shell ���� ǥ��
		MainFrame.console_used = false;		//�ܼ� ��� ���� ǥ��
		return;
	}
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
