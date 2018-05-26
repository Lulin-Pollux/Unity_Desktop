package app.musicplayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import terminal.CommonFn;

public class MidiPlayer implements MetaEventListener {

	private static Scanner scan = new Scanner(System.in);
	public final static int END_OF_TRACK_MESSAGE = 47;
	Sequencer seq;
	boolean loop;
	boolean pause;
	static boolean exception = false;	//예외 발생 유무
	
	// 생성자
	public MidiPlayer(){
		try{
			seq = MidiSystem.getSequencer();
			seq.open();
			seq.addMetaEventListener(this);
		}catch(Exception e){
			seq = null;
		}
	}
	
	// 시퀸스 얻는 메소드 (메소드 오버로딩)
	public Sequence getSequence(String filename){
		try{
			return getSequence(new FileInputStream(filename));
		}catch(Exception e){
			System.out.println(e);
			exception = true;
			return null;
		}
	}
	public Sequence getSequence(InputStream is){
		try {
	      if (!is.markSupported()) {
	        is = new BufferedInputStream(is);
	      }
	      Sequence s = MidiSystem.getSequence(is);
	      is.close();
	      return s;
	    } catch (Exception e) {
	      e.printStackTrace();
	      exception = true;
	      return null;
	    }
	}
	public Sequencer getSequencer(){
		return seq;
	}
	
	// 플레이어 기능 모음
	//-----------------------------------------------------
	
	public void play(Sequence sq, boolean loop){
      if(sq!=null && seq!=null && seq.isOpen()){
    	  try{
	    	  seq.setSequence(sq);
	    	  seq.start();
	    	  this.loop = loop;
	    	  pause = false;
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }
      }
	}
	public void stop(){
		if(seq!=null && seq.isOpen()){
			seq.stop();
			seq.setMicrosecondPosition(0);
		}
	}
	public void close(){
		if(seq!=null && seq.isOpen())
			seq.close();
	}
	public void Pause(){
		try{
			if(pause == true)
				seq.start();
			else
				seq.stop();
			pause = !pause;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//-----------------------------------------------------
	
	// meta 데이터 처리
	@Override
	public void meta(MetaMessage meta) {
		if (meta.getType() == MidiPlayer.END_OF_TRACK_MESSAGE) {
		      if(seq!=null && seq.isOpen()&&loop)
		    	  seq.start();
		}
	}
	
	
	// 첫 페이지 배너
	private void frontpage() {
		System.out.println("명령 번호:");
		System.out.println("	1. 재생");
		System.out.println("	2. 일시정지 / 재개");
		System.out.println("	3. 정지");
		System.out.println("	4. frontpage");
		System.out.println("	0. 종료");
	}
	
	// MidiPlayer의 메인 메소드
	public static void midi() {
		MidiPlayer mplay = new MidiPlayer();	//객체 생성
		int app_control = 0;	//앱 컨트롤 번호 저장
		
		mplay.frontpage();	//첫 화면 띄움
		
		while (true) {
			exception = false;
			
			System.out.println();
			System.out.print("번호 입력 >>> ");
			try {
				app_control = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("정수를 입력해주세요.");
				scan.nextLine();
				continue;
			}
			
			switch (app_control) {
			case 1:
				System.out.println();
				System.out.print("midi 파일 경로를 입력해주세요 >>> ");
				String midi_path = scan.nextLine();	//경로 저장
				Sequence seq = mplay.getSequence(midi_path);
				mplay.play(seq, false);		//재생 시작
				
				if (exception == false) {
					System.out.println("재생");
				}
				break;
				
			case 2:
				mplay.Pause();	//일시정지, 재개
				System.out.println("일시정지 / 재개");
				break;
				
			case 3:
				mplay.stop();	//정지
				System.out.println("정지");
				break;
				
			case 4:
				CommonFn.clear();
				mplay.frontpage();	//첫 화면 띄움
				break;
				
			case 0:
				System.out.println();
				System.out.println("종료합니다...");
				CommonFn.pause();
				mplay.close();	//midi 시퀸스 닫기
				return;		//앱 종료
				
			default:
				System.out.print("해당 번호는 없습니다.");
				break;
			}
		}
	}
	
}
