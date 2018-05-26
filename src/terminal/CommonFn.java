package terminal;

/* ���������� ���� ����ϴ� �Լ����� ���⿡ ����Ѵ�. */
import java.io.IOException;
import java.util.Scanner;

public class CommonFn {
	
	private static Scanner scan = new Scanner(System.in);
	
	/* �Լ� ��� */
	//----------------------------------------------------------------------------
	public static void clear() {		// �ܼ�â �����ϰ� ���ִ� �޼ҵ� (���ڰ� ���������� ����)
		for (int i=0; i<=80; i++) {
			System.out.println();
		}
	}
	
	public static void pause() {	// Enter Ű�� ���� ������ ������Ű��
		System.out.print("����Ϸ��� EnterŰ�� �������� . . . ");
	    try {
	      System.in.read();
	      scan.nextLine();
	    } catch (IOException e) {
	    	e.printStackTrace();	//���� ���� ���
	    }
	}
	//----------------------------------------------------------------------------
}