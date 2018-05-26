package app.musicplayer;

import java.io.FileInputStream;
import java.util.Scanner;

import javazoom.jl.player.Player;
import terminal.CommonFn;

public class MP3_Player {

	private static Scanner scan = new Scanner(System.in);
	
	private static void mp3_player() {
		System.out.print("MP3 파일 경로를 입력해주세요>>> ");
		String mp3_path = scan.nextLine();
		
		try {
			FileInputStream fis = new FileInputStream(mp3_path);
			Player playMp3 = new Player(fis);
			
			System.out.println();
			System.out.println("Play!");
			System.out.println("중간에 정지하려면 Ctrl + c(종료)를 눌러주세요. \n");
			playMp3.play();
			playMp3.close();
			System.out.println("Stop!");
			return;
			
		} catch (Exception e) {
			System.out.println(e);
			CommonFn.pause();
			scan.nextLine();
			return;
		}
	}
	
	public static void mp3() {
		while (true) {
			mp3_player();
			
			System.out.print("다시 시작할까요? (Y/N) ");
			while (true) {
				System.out.print(">>> ");
				String input1 = scan.nextLine();
				
				switch (input1) {
				case "y": case "Y":
					System.out.println();
					break;
					
				case "n": case "N":
					System.out.println();
					System.out.println("종료합니다...");
					CommonFn.pause();
					return;
					
				default:
					System.out.print("다시 입력해주세요. ");
					continue;
				}
				break;
			}
		}
	}
	
}
