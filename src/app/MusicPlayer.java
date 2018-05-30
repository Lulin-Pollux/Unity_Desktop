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
		System.out.println("|       0. �� ����                |");
		System.out.println("*---------------------------------*");

		System.out.println("\n������ �۾��� ��ȣ�� �Է����ּ���.");
	}
	
	
	public static void frontpage() {
		int app_control = 0;	//�� ��Ʈ�� ��ȣ ����
		frontpage_banner();	//��� ����
		
		while (true) {
			System.out.print(">>> ");
			try {
				app_control = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("������ �Է����ּ���.");
				scan.nextLine();
				continue;
			}
			
			switch (app_control) {
			case 1:
				System.out.println();
				MP3_Player.mp3();	//mp3 �÷��̾� ����
				CommonFn.clear();
				frontpage_banner();	//��� ����
				break;
				
			case 2:
				System.out.println();
				MidiPlayer.midi();	//midi �÷��̾� ����
				CommonFn.clear();
				frontpage_banner();	//��� ����
				break;
				
			case 0:
				System.out.println("���� �����߽��ϴ�.");
				System.out.println();
				return;		//�� ����
				
			default:
				System.out.print("�ش� ��ȣ�� �����ϴ�.");
				break;
			}
		}
	}
	
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
