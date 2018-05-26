package terminal;

/* 공통적으로 많이 사용하는 함수들을 여기에 기록한다. */
import java.io.IOException;
import java.util.Scanner;

public class CommonFn {
	
	private static Scanner scan = new Scanner(System.in);
	
	/* 함수 목록 */
	//----------------------------------------------------------------------------
	public static void clear() {		// 콘솔창 깨끗하게 해주는 메소드 (글자가 지워지지는 않음)
		for (int i=0; i<=80; i++) {
			System.out.println();
		}
	}
	
	public static void pause() {	// Enter 키를 누를 때까지 정지시키기
		System.out.print("계속하려면 Enter키를 누르세요 . . . ");
	    try {
	      System.in.read();
	      scan.nextLine();
	    } catch (IOException e) {
	    	e.printStackTrace();	//예외 설명 출력
	    }
	}
	//----------------------------------------------------------------------------
}