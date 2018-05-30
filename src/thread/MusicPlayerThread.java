package thread;

import app.MusicPlayer;
import gui.MainFrame;

public class MusicPlayerThread extends Thread {
	
	public void run() {
		MusicPlayer.frontpage();
		
		MainFrame.musicPlayer_run = false;	//음악 플레이어 종료 표시
		MainFrame.console_used = false;		//콘솔 사용 중지 표시
		return;
	}
}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
