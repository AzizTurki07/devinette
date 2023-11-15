package socketsTCP;

import java.net.*;
import java.io.*;
public class Serveur5C extends Thread{
	// cas de plusieurs clients (joueurs) servis de fa�on concurrente
	static int nbSecret= (int)(Math.random()*100);
	static boolean finJeu = false;
	public static void main (String args[]){
		System.out.println("Le nombre secret est " +nbSecret);
	try{
		//Cr�ation de socket d'attente de connexion, cot� serveur uniquement.
		//La socket est li�e au port dont le num�ro est pass� en param�tre.
		//L'exception est lev�e notamment si ce port est d�j� li� � une socket.
		ServerSocket secoute = new ServerSocket(123);
		System.out.println("Je suis en attente d'une connexion");
		
		while(!finJeu){
		//Attente de connexion d'un client distant.
		//Quand la connexion est faite, retourne une socket permettant de
		//communiquer avec le client : socket de service.
		Socket sServ = secoute.accept();
		System.out.println("Connexion accept�e");
		ServiceJeu svcJ=new ServiceJeu(sServ);
		Thread t=new Thread(svcJ);
		t.start();			
		}
		secoute.close();

	}catch (Exception e){ 	e.printStackTrace(); }

}

}



