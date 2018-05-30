package terminal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Shell {
	
	/*��� ���� ���*/
	//---------------------------------------------------------------
	private static Scanner scan = new Scanner(System.in);	// ��ĳ�� ��������
	
	protected static String directory = null;		// �͹̳��� ���丮 ��¿� ����ϴ� ����
	protected static String command = null;		// Shell ��ɾ� ����
	protected static String command_split = null;	// �ɼ� �� ��ɾ� ����
	protected static String option = null;		// Shell ��ɾ��� �ɼ� ����
	//---------------------------------------------------------------
	
	/*��ɾ� �޼ҵ� ���*/
	//---------------------------------------------------------------
	public static void date() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a (z Z)");
		System.out.println(df.format(date).toString());
		System.out.println();
	}
	
	private static void frontpage() {
		System.out.println("Unity Shell ������: Lulin Pollux");
		System.out.println("2017 Team Project. Copyright(c)");
		System.out.println();
		System.out.println("'help'�� �Է��ϸ� ��ɾ Ȯ���� �� �ֽ��ϴ�.");
		System.out.println();
	}
	
	private static void help() {
		System.out.println("GNU bash CUSTOM, version 1.0.0(1) - relase (x86_64-pc-linux-gnu)");
		System.out.println("These shell commands are defined internally. Type 'help' to see this list.");
		System.out.println("A star (*) next to a name means that the command is disabled. \n");

		System.out.println("��/�� \t\t ������(��) / ������(��) �Է��ߴ� ��ɾ�");
		System.out.println("clear \t\t �� ������ ��� ����ϴ�.");
		System.out.println("date \t\t ���� ��¥�� �ð��� ǥ���մϴ�.");
		System.out.println("extern \t\t �ܺ� ���α׷��� �����մϴ�.");
		System.out.println("externcmd \t OS�� �� ��ɾ �����ϰ� ������� �����ɴϴ�.");
		System.out.println("exit \t\t Shell�� �����մϴ�.");
		System.out.println("frontpage \t ó�� Shell�� ��������� ���� ���� ȭ���� ����մϴ�.");
		System.out.println("ifconfig \t ��ǻ���� IP������ ǥ���մϴ�.");
		System.out.println("ifconfig -a \t ��Ȱ��ȭ�� �������̽� �������� ǥ���մϴ�.");
		System.out.println("ls \t\t ���� ��ġ�� directory�� ���ϰ� ���� directory ��� ���");
		System.out.println("pwd \t\t ���� directory�� ����մϴ�.");
		System.out.println("ver \t\t ����Ʈ���� ������ ǥ���մϴ�. \n");
	}
	//---------------------------------------------------------------
	
	public static void shell() {
		/* �� �ʱ⼳�� �ϴ� �� */
		/*---------------------------------------------------------------------------------------------*/
		frontpage();			//���� �޽��� ǥ��
		Cmd cmd = new Cmd();	//cmd ��ü ����
		String osName = System.getProperty("os.name").toLowerCase(); //OS ���� Ȯ��
		/*---------------------------------------------------------------------------------------------*/
		
		while (true) {
			command = "";		//�ʱ�ȭ
			command_split = "";	//�ʱ�ȭ
			option = "";		//�ʱ�ȭ
			
			/* ���丮 ǥ�� �ڵ� */
			//-----------------------------------------------------------------------
			directory = "~";		// ~ ǥ�ø� ��
			
			System.out.print(TextColor.GREEN_BRIGHT);
			System.out.print("ubuntu@lulin:");		//��ǻ�� �̸�@����� �̸�:
			System.out.print(TextColor.CYAN_BRIGHT);
			System.out.print(directory);		//������
			System.out.print(TextColor.RESET);
			System.out.print("$ ");					//����
			//-----------------------------------------------------------------------
			
			command = scan.nextLine();	//��ɾ� �Է�
			command_split = command;	//command�� command_split�� ����
			
			if (command.contains("-")) {	//��ɾ �ɼ��� ���� ���
				int index = command.indexOf("-");	//�ɼ� �±װ� ��� ��ġ�� �ִ��� ����
				option = command.substring(index);	//�ɼ� �±� �����ؼ� �ɼ� ����
				command_split = command.substring(0, index-1);	//�ɼ��� ���� �� ��� ����
			}

			/* ��ɾ� �б� ����. ��ɾ�� abc������ �ۼ��Ѵ�. */
			/*-------------------------------------------------------------------------------------*/
			switch (command_split) {
			
			case "":
				//�ٽ� �Է��� �޴´�.
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
					System.out.println("�ɼ��� �Է��ϼ���. \n");
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
					    System.out.println("CreateProcess error=2, ������ ������ ã�� �� �����ϴ� \n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			case "externcmd":
				if (command.equals("externcmd") || option.equals("-")) {
					System.out.println("�ɼ��� �Է��ϼ���. \n");
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
					System.out.println("��ġ�ϴ� �Ű� ������ ã�� �� �����ϴ�. \n");
				break;
				
			case "pwd":
				System.out.println(System.getProperty("user.dir"));
				System.out.println();
				break;
				
			case "frontpage":
				CommonFn.clear();
				frontpage();	//����ȭ�� �ҷ�����
				break;
				
			case "ver":
				System.out.println();
				System.out.println("Lulin Laboratory's Unity Shell [Version 1.0.0]");
				System.out.println();
				System.out.println("�ü�� ����: " + System.getProperty("os.name") );
			    System.out.println("�ڹ� ����ӽ� ����: " + System.getProperty("java.vm.version") );
			    System.out.println("Ŭ���� ����: " + System.getProperty("java.class.version") );
			    System.out.println("����� �α���ID: " + System.getProperty("user.name") );
			    System.out.println();
				break;
				
			case "vi":
				System.out.println("Sorry, vi editer has been disabled... \n"); //vi ������ ��Ȱ��ȭ
				break;
				
			default:
				System.out.printf("%s: command not found \n", Shell.command);
				break;
			}
			/*-------------------------------------------------------------------------------------*/
		}
	}
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�. */
