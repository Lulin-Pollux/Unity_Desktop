package terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cmd {
	
	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;
	
	public String inputCommand(String cmd) {
		
		buffer = new StringBuffer();
		
		buffer.append("cmd.exe /c ");
		buffer.append(cmd);

		return buffer.toString();
	}
	
	public String execCommand(String cmd) {
		try {
			process = Runtime.getRuntime().exec(cmd);
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line = null;
			readBuffer = new StringBuffer();
			
			while((line = bufferedReader.readLine()) != null) {
				readBuffer.append(line);
				readBuffer.append("\n");
			}
			
			return readBuffer.toString();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return null;
	}
}

/*  ������: Lulin Pollux */
/*  MIT License
	�ش� �ҽ��ڵ� (.java)�� MIT License�� ���� �̿��� �� �ֽ��ϴ�.
	�˰��� ��ó: http://hojak99.tistory.com/338 */
