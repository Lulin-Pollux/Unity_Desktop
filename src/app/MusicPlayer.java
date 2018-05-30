package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import terminal.CommonFn;
import app.musicplayer.*;

public class MusicPlayer {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static void frontpage_banner() {
		System.out.println();
		System.out.println("*-- Music Player -----------------*");
		System.out.println("|                                 |");
		System.out.println("|       1. MP3 Player             |");
		System.out.println("|       2. Midi Player            |");
		System.out.println("|       0. 앱 종료                |");
		System.out.println("*---------------------------------*");

		System.out.println("\n수행할 작업의 번호를 입력해주세요.");
	}
	
	
	public static void frontpage() {
		int app_control = 0;	//앱 컨트롤 번호 저장
		frontpage_banner();	//배너 띄우기
		
		while (true) {
			System.out.print(">>> ");
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
				MP3_Player.mp3();	//mp3 플레이어 실행
				CommonFn.clear();
				frontpage_banner();	//배너 띄우기
				break;
				
			case 2:
				System.out.println();
				MidiPlayer.midi();	//midi 플레이어 실행
				CommonFn.clear();
				frontpage_banner();	//배너 띄우기
				break;
				
			case 0:
				System.out.println("앱을 종료했습니다.");
				System.out.println();
				return;		//앱 종료
				
			default:
				System.out.print("해당 번호는 없습니다.");
				break;
			}
		}
	}
	
}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
