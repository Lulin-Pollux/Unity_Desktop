package thread;

import app.MusicPlayer;
import gui.MainFrame;

public class MusicPlayerThread extends Thread {
	
	public void run() {
		MusicPlayer.frontpage();
		
		MainFrame.musicPlayer_run = false;	//���� �÷��̾� ���� ǥ��
		MainFrame.console_used = false;		//�ܼ� ��� ���� ǥ��
		return;
	}
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
