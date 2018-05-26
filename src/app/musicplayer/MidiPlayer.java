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
	static boolean exception = false;	//���� �߻� ����
	
	// ������
	public MidiPlayer(){
		try{
			seq = MidiSystem.getSequencer();
			seq.open();
			seq.addMetaEventListener(this);
		}catch(Exception e){
			seq = null;
		}
	}
	
	// ������ ��� �޼ҵ� (�޼ҵ� �����ε�)
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
	
	// �÷��̾� ��� ����
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
	
	// meta ������ ó��
	@Override
	public void meta(MetaMessage meta) {
		if (meta.getType() == MidiPlayer.END_OF_TRACK_MESSAGE) {
		      if(seq!=null && seq.isOpen()&&loop)
		    	  seq.start();
		}
	}
	
	
	// ù ������ ���
	private void frontpage() {
		System.out.println("��� ��ȣ:");
		System.out.println("	1. ���");
		System.out.println("	2. �Ͻ����� / �簳");
		System.out.println("	3. ����");
		System.out.println("	4. frontpage");
		System.out.println("	0. ����");
	}
	
	// MidiPlayer�� ���� �޼ҵ�
	public static void midi() {
		MidiPlayer mplay = new MidiPlayer();	//��ü ����
		int app_control = 0;	//�� ��Ʈ�� ��ȣ ����
		
		mplay.frontpage();	//ù ȭ�� ���
		
		while (true) {
			exception = false;
			
			System.out.println();
			System.out.print("��ȣ �Է� >>> ");
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
				System.out.print("midi ���� ��θ� �Է����ּ��� >>> ");
				String midi_path = scan.nextLine();	//��� ����
				Sequence seq = mplay.getSequence(midi_path);
				mplay.play(seq, false);		//��� ����
				
				if (exception == false) {
					System.out.println("���");
				}
				break;
				
			case 2:
				mplay.Pause();	//�Ͻ�����, �簳
				System.out.println("�Ͻ����� / �簳");
				break;
				
			case 3:
				mplay.stop();	//����
				System.out.println("����");
				break;
				
			case 4:
				CommonFn.clear();
				mplay.frontpage();	//ù ȭ�� ���
				break;
				
			case 0:
				System.out.println();
				System.out.println("�����մϴ�...");
				CommonFn.pause();
				mplay.close();	//midi ������ �ݱ�
				return;		//�� ����
				
			default:
				System.out.print("�ش� ��ȣ�� �����ϴ�.");
				break;
			}
		}
	}
	
}
