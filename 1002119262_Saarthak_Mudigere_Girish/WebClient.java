//Name: Saarthak Mudigere Girish
//UTA ID: 1002119262

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
	private static Socket socket;
	final static String CRLF = "\r\n";
	private static String fileName;

	public static void main(String args[]) throws Exception {
		long timeStart, timeEnd, timeUsed;
		timeStart = System.nanoTime();

		String serverUrl = "http://" + args[0] + ":" + args[1] + "/" + args[2];
		URL url = new URL(serverUrl);
		int count=0;
		socket = new Socket("localhost", Integer.parseInt(args[1]));
		System.out.println("[CONNECTION ESTABLISHED] Connection Successfully Established");
		System.out.println("Number of threads " + ++count);
		fileName = args[2];
		

		PrintStream printstream = new PrintStream(socket.getOutputStream());
		printstream.println("GET" + " /" + fileName + " " + "HTTP/1.1" + CRLF);
                

		System.out.println("Received Connection From : " + socket.getInetAddress().getHostName());
		System.out.println("Port : " + socket.getPort());
		System.out.println("Protocol :" + url.getProtocol());
		System.out.println("TCP No Delay : " + socket.getTcpNoDelay());
		System.out.println("Timeout : " + socket.getSoTimeout());
		timeEnd = System.nanoTime();
		timeUsed = (timeEnd - timeStart) / 1000000;
		System.out.println("RTT=" + (double) Math.round(timeUsed * 100) / 100 + " ms");
		System.out.println("response from server");

		try {
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder stringbuilder = new StringBuilder();
			String get_String;
			while ((get_String = bufferedreader.readLine()) != null) {
				stringbuilder.append(get_String + "\n");
			}
			bufferedreader.close();
			System.out.println(stringbuilder.toString());
			System.out.println("Enter 'Exit' to exit the connection");
			Scanner out = new Scanner(System.in);
			String name = out.nextLine();
			if(name=="EXIT")
				System.out.println("Number of threads " + --count);
				socket.close();
			
			
		} catch (IOException ex) {
			System.out.println("error");
		}
	}
}
