//Name: Saarthak Mudigere Girish
//UTA ID: 1002119262

import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class WebServer
{
	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		int port = 8080;
		ServerSocket newServerSocket = new ServerSocket(port);
		System.out.println("[STARTING] The server is starting...");
		System.out.println("[LISTENING] Server is listening on port: " + port);

         while(true)
         {
            Socket connectionSocket = newServerSocket.accept();
			System.out.println();
			System.out.println("[NEW CONNECTION] Connection Successfully Established");

			// Construct an object to process the HTTP request message.
			HttpRequest request = new HttpRequest(connectionSocket);

			// Create a new thread to process the request.
			Thread thread = new Thread(request);  

			// Start the thread.
			thread.start();
         }
	}
}

final class HttpRequest implements Runnable
{
	final static String CRLF = "\r\n";
	Socket socket;
	
	// Constructor
	public HttpRequest(Socket socket) throws Exception 
	{
		this.socket = socket;
	}

	// Implement the run() method of the Runnable interface.
	public void run()
	{
		try {
			processRequest();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void processRequest() throws Exception
	{
		// Get a reference to the socket's input and output streams.
		InputStream is = this.socket.getInputStream();
		DataOutputStream os = new DataOutputStream(this.socket.getOutputStream());

		// Set up input stream filters.
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// Get the request line of the HTTP request message.
		String requestLine = br.readLine();

		// Display the request line.
		System.out.println();
		System.out.println(requestLine);

		// Get and display the header lines.
		String headerLine = null;
		while ((headerLine = br.readLine()).length() != 0) {
			System.out.println(headerLine);
		}
		
		// Extract the filename from the request line.
		StringTokenizer tokens = new StringTokenizer(requestLine);
		tokens.nextToken();  // skip over the method, which should be "GET"
		String fileName = tokens.nextToken();

		// Prepend a "." so that file request is within the current directory.
		fileName = "." + fileName;

		// Open the requested file.
		FileInputStream fis = null;
		boolean fileExists = true;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			fileExists = false;
		}
		// Construct the response message.
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		if (fileExists) {
			statusLine = "HTTP/1.1 200 OK" + CRLF;
			contentTypeLine = "Content-type: " + 
				contentType( fileName ) + CRLF;
		} else {
			statusLine = "HTTP/1.1 404 NOT FOUND";
			contentTypeLine = "Content-type: text/html" + CRLF;
			entityBody = "<HTML>" + 
				"<HEAD><TITLE>Not Found</TITLE></HEAD>" +
				"<BODY>404 Page Not Found</BODY></HTML>";
		}

		
		os.writeBytes(statusLine); // Send the status line.
		os.writeBytes(contentTypeLine); // Send the content type line.
		os.writeBytes(CRLF); // Send a blank line to indicate the end of the header lines.
		
		// Send the entity body.
		if (fileExists)	{
			sendBytes(fis, os);
			fis.close();
		} else {
			os.writeBytes(entityBody);
		}
		String inputLine;
		while ((inputLine = br.readLine()) != null) {
    	// If the client sends a specific message to disconnect, break out of the loop
    		if (inputLine.equals("DISCONNECT")) {
        		break;
    	}
    	// Handle the message from the client
    	// ...
}
		
		//Closing the connection
		os.close();
		br.close();
		socket.close();
		System.out.println("[END CONNECTION] Client Disconnected");
	}

	private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception
	{
		// Construct a 1K buffer to hold bytes on their way to the socket.
		byte[] buffer = new byte[1024];
		int bytes = 0;
		// Copy requested file into the socket's output stream.
		while((bytes = fis.read(buffer)) != -1 ) {
		os.write(buffer, 0, bytes);
		}
		
	}	
	private static String contentType(String fileName)
	{
		//This is to establish connection to HTML pages
		if(fileName.endsWith(".htm") || fileName.endsWith(".html")) {
			return "text/html";
		}
		//For Gif images
		if(fileName.endsWith(".gif")) {
			return "image/gif";
		}
		//For images of format jpeg
		if(fileName.endsWith(".jpeg")) {
			return "image/jpeg";
		}
		//For audio formats
		if(fileName.endsWith(".ram") || fileName.endsWith(".ra")) {
			return "audio/x-pn-realaudio";
		}
		return "application/octet-stream";
	}

}