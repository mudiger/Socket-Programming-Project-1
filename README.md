# Socket-Programming-Project-1
Name: Saarthak Mudigere Girish
ID: 1002119262

Package: JDK (Java Development Kit) is the complete environment required to run java codes on any system.
We can download the JDK using the below link:
https://www.oracle.com/java/technologies/downloads/

Packages used:
import java.io.* ;
import java.net.* ;
import java.util.* ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

After Downloading follow the instruction shown in the below video to set it up on your system:
https://www.youtube.com/watch?v=IJ-PJbvJBGs

After JDK is successfully installed we use an IDE to run the code.

Here I am using Visual Studio Code as my IDE. Use the below link to install it...
https://code.visualstudio.com/download

Once Installed 

1)Unzip the folder 1002119262_Saarthak_Mudigere_Girish.zip
2)Open the above in Visual Studio Code
3)Open your terminal and Compile using the below code:
	javac WebServer.java
4)Run the code using 
	java WebServer
5)Once the server starts running open the desired files in the browser with the name of your machine and the port number: 8080
  as shown below:
	a)http://127.0.0.1:8080/index.html
	b)http://127.0.0.1:8080/index2.html
6)You can also access the above through your terminal. Open a new terminal, Compile and Run using the below code:
	Compile: javac WebClient.java
	Run: java WebClient 127.0.0.1 8080 index.html
If there are any errors, change the port number, re-compile, and rerun the code.
7)Now open the terminal to see the connection setup and other information.
8)You can see that the connection would have been successfully set up and the file would have been loaded in the browser.

I have added notes on Socket Programming and all the Screenshots of the Code Execution in the ProjectDocumentation

References:
1)https://www.youtube.com/watch?v=FqufxoA4m70
2)https://www.geeksforgeeks.org/multithreaded-servers-in-java/
3)https://stackoverflow.com/questions/35388966/retrieve-file-from-remote-ftp-to-a-webserver
4)https://www.ucg.ac.me/skladiste/blog_44233/objava_64433/fajlovi/Computer%20Networking%20_%20A%20Top%20Down%20Approach,%207th,%20converted.pdf
