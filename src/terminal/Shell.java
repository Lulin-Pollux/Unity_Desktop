package terminal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Shell {
	
	/*멤버 변수 목록*/
	//---------------------------------------------------------------
	private static Scanner scan = new Scanner(System.in);	// 스캐너 전역변수
	
	protected static String directory = null;		// 터미널의 디렉토리 출력에 사용하는 변수
	protected static String command = null;		// Shell 명령어 저장
	protected static String command_split = null;	// 옵션 앞 명령어 저장
	protected static String option = null;		// Shell 명령어의 옵션 저장
	//---------------------------------------------------------------
	
	/*명령어 메소드 목록*/
	//---------------------------------------------------------------
	public static void date() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a (z Z)");
		System.out.println(df.format(date).toString());
		System.out.println();
	}
	
	private static void frontpage() {
		System.out.println("Unity Shell 개발자: Lulin Pollux");
		System.out.println("2017 Team Project. Copyright(c)");
		System.out.println();
		System.out.println("'help'를 입력하면 명령어를 확인할 수 있습니다.");
		System.out.println();
	}
	
	private static void help() {
		System.out.println("GNU bash CUSTOM, version 1.0.0(1) - relase (x86_64-pc-linux-gnu)");
		System.out.println("These shell commands are defined internally. Type 'help' to see this list.");
		System.out.println("A star (*) next to a name means that the command is disabled. \n");

		System.out.println("↑/↓ \t\t 이전에(↑) / 다음에(↓) 입력했던 명령어");
		System.out.println("clear \t\t 셸 내용을 모두 지웁니다.");
		System.out.println("date \t\t 현재 날짜와 시각을 표시합니다.");
		System.out.println("extern \t\t 외부 프로그램을 실행합니다.");
		System.out.println("externcmd \t OS의 셸 명령어를 실행하고 결과값을 가져옵니다.");
		System.out.println("exit \t\t Shell을 종료합니다.");
		System.out.println("frontpage \t 처음 Shell을 실행시켰을 때와 같은 화면을 출력합니다.");
		System.out.println("ifconfig \t 컴퓨터의 IP정보를 표시합니다.");
		System.out.println("ifconfig -a \t 비활성화된 인터페이스 정보까지 표시합니다.");
		System.out.println("ls \t\t 현재 위치한 directory의 파일과 하위 directory 목록 출력");
		System.out.println("pwd \t\t 현재 directory를 출력합니다.");
		System.out.println("ver \t\t 소프트웨어 정보를 표시합니다. \n");
	}
	//---------------------------------------------------------------
	
	public static void shell() {
		/* 쉘 초기설정 하는 중 */
		/*---------------------------------------------------------------------------------------------*/
		frontpage();			//시작 메시지 표시
		Cmd cmd = new Cmd();	//cmd 객체 생성
		String osName = System.getProperty("os.name").toLowerCase(); //OS 종류 확인
		/*---------------------------------------------------------------------------------------------*/
		
		while (true) {
			command = "";		//초기화
			command_split = "";	//초기화
			option = "";		//초기화
			
			/* 디렉토리 표시 코드 */
			//-----------------------------------------------------------------------
			directory = "~";		// ~ 표시를 함
			
			System.out.print(TextColor.GREEN_BRIGHT);
			System.out.print("ubuntu@lulin:");		//컴퓨터 이름@사용자 이름:
			System.out.print(TextColor.CYAN_BRIGHT);
			System.out.print(directory);		//현재경로
			System.out.print(TextColor.RESET);
			System.out.print("$ ");					//권한
			//-----------------------------------------------------------------------
			
			command = scan.nextLine();	//명령어 입력
			command_split = command;	//command를 command_split에 복사
			
			if (command.contains("-")) {	//명령어에 옵션이 있을 경우
				int index = command.indexOf("-");	//옵션 태그가 어느 위치에 있는지 저장
				option = command.substring(index);	//옵션 태그 포함해서 옵션 저장
				command_split = command.substring(0, index-1);	//옵션을 제거 후 명령 저장
			}

			/* 명령어 분기 시작. 명령어는 abc순으로 작성한다. */
			/*-------------------------------------------------------------------------------------*/
			switch (command_split) {
			
			case "":
				//다시 입력을 받는다.
				break;
				
			case "clear":
				CommonFn.clear();
				break;
				
			case "date":
				date();
				break;
				
			case "exit":
				System.out.print(TextColor.CYAN_BRIGHT);
				System.out.println("Exit!");
				System.out.println(TextColor.RESET);
				return;
				
			case "extern":
				if (command.equals("extern") || option.equals("-")) {
					System.out.println("옵션을 입력하세요. \n");
				}
				else {
					String exeFile = option.replace("-", "");
					System.out.print(TextColor.YELLOW_BRIGHT);
					System.out.print("exeFile: ");
					System.out.print(TextColor.CYAN_BRIGHT);
					System.out.println(exeFile);
					System.out.println(TextColor.RESET);
					
					try {
						Process process = Runtime.getRuntime().exec(exeFile);
						process.getErrorStream().close();
						process.getInputStream().close();
						process.getOutputStream().close();
					} catch (IOException e) {
						System.out.print(TextColor.RED_BRIGHT);
					    System.out.print("Cannot run program \"" + exeFile + 
					    		"\": ");
					    System.out.print(TextColor.RESET);
					    System.out.println("CreateProcess error=2, 지정된 파일을 찾을 수 없습니다 \n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			case "externcmd":
				if (command.equals("externcmd") || option.equals("-")) {
					System.out.println("옵션을 입력하세요. \n");
				}
				else {
					String command = cmd.inputCommand(option.replace("-", ""));
					String result = cmd.execCommand(command);
					
					System.out.println(result);
				}
				break;
				
			case "help":
				help();
				break;
				
			case "ifconfig":
				if (command.equals("ifconfig")) {
					if (osName.startsWith("windows")) {
						String command = cmd.inputCommand("ipconfig");
						String result = cmd.execCommand(command);
						
						System.out.println(result);
					}
					else {
						String command = cmd.inputCommand("ipconfig");
						String result = cmd.execCommand(command);
						
						System.out.println(result);
					}
				}
				else if (option.equals("-a")) {
					if (osName.startsWith("windows")) {
						String command = cmd.inputCommand("ipconfig /all");
						String result = cmd.execCommand(command);
					
						System.out.println(result);
					}
					else {
						String command = cmd.inputCommand("ifconfig -a");
						String result = cmd.execCommand(command);
					
						System.out.println(result);
					}
				}
				break;
				
			case "ls":
				if (command.equals("ls")) {
					if (osName.startsWith("windows")) {
						String command = cmd.inputCommand("dir");
						String result = cmd.execCommand(command);
						
						System.out.println(result);
					}
					else {
						String command = cmd.inputCommand("ls");
						String result = cmd.execCommand(command);
						
						System.out.println(result);
					}
				}
				else
					System.out.println("일치하는 매개 변수를 찾을 수 없습니다. \n");
				break;
				
			case "pwd":
				System.out.println(System.getProperty("user.dir"));
				System.out.println();
				break;
				
			case "frontpage":
				CommonFn.clear();
				frontpage();	//시작화면 불러오기
				break;
				
			case "ver":
				System.out.println();
				System.out.println("Lulin Laboratory's Unity Shell [Version 1.0.0]");
				System.out.println();
				System.out.println("운영체제 종류: " + System.getProperty("os.name") );
			    System.out.println("자바 가상머신 버전: " + System.getProperty("java.vm.version") );
			    System.out.println("클래스 버전: " + System.getProperty("java.class.version") );
			    System.out.println("사용자 로그인ID: " + System.getProperty("user.name") );
			    System.out.println();
				break;
				
			case "vi":
				System.out.println("Sorry, vi editer has been disabled... \n"); //vi 편집기 비활성화
				break;
				
			default:
				System.out.printf("%s: command not found \n", Shell.command);
				break;
			}
			/*-------------------------------------------------------------------------------------*/
		}
	}
}

/*  개발자: Lulin Pollux */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
