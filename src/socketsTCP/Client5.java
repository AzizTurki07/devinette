package socketsTCP;

import java.net.*;
import java.io.*;  import java.util.Scanner;
public class Client5 {
	public static void main(String[] args){ 
try {   
    Socket s = new Socket("localhost", 123); 
    InputStream is=s.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr); 
    OutputStream os=s.getOutputStream();
    PrintWriter pw = new PrintWriter(os,true); 
    Scanner clavier = new Scanner(System.in);

    while ((!Serveur5C.finJeu)) {
        String res = br.readLine();
        System.out.println("Server :" + res); 

       
        String str=clavier.next();
        pw.println(str); // le client envoie son essai
        String res2 = br.readLine();
		System.out.println("Server :" + res2);
    }
} catch (Exception e) {
    e.printStackTrace();
}
	}}