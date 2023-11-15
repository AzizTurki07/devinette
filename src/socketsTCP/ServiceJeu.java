package socketsTCP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

public class ServiceJeu implements Runnable{

	Socket s;
	public ServiceJeu(Socket s){
		this.s=s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		InputStream isServ=s.getInputStream();
		
		//La classe InputStreamReader permet de lire des octets et les traduire en caract�res
		InputStreamReader isrServ=new InputStreamReader(isServ);
		
		/*La classe BufferedReader permet de lire du texte � partir d'un flux d'entr�e de caract�res, 
		 * mettant en m�moire tampon les caract�res afin de fournir plus d'efficacit� 
		 * pour lire des caract�res.
		 */
		BufferedReader brServ=new BufferedReader(isrServ);
		
		//Cr�ation du flux de sortie
		OutputStream osServ=s.getOutputStream();
		
		/*La classe PrintWriter permet d'ajouter au flux la possibilt� de faire des �criture sous 
		 * forme de texte, des chaines de carat�res,...
		 */
		PrintWriter pwServ=new PrintWriter(osServ,true);
		pwServ.println("Donnez un nombre entre 0 et 100 : ");
		
		int nbTentatives=5;
System.out.println(Serveur5C.finJeu);
		while((!Serveur5C.finJeu) && (nbTentatives!=0)) {
			pwServ.println("Vous avez encore "+nbTentatives+" essai(s) !");
			System.out.println(Serveur5C.finJeu);
            System.out.println(Serveur5C.nbSecret);
			nbTentatives--;
		
			String essaiCl= brServ.readLine();
			int nbEssaiCl  = Integer.parseInt(essaiCl);
			
			if(nbEssaiCl > Serveur5C.nbSecret){
				if(nbTentatives==0)
					{pwServ.println("Vous avez perdu ! ");}
				else
				{pwServ.println("SVP donnez un nombre plus petit ");}
			}
			else if(nbEssaiCl < Serveur5C.nbSecret){
				if(nbTentatives==0)
					{pwServ.println("Vous avez perdu ! ");}
				else
				{pwServ.println("SVP donnez un nombre plus grand ");}
			}
			else {
				Serveur5C.finJeu = true;
				pwServ.println("Vous avez gagne");
				pwServ.println("Fin de Jeu !");
				//Garder l'adresse IP du joueur gagnant
				System.out.println("Le gagnant est "+ s.getRemoteSocketAddress());
			}
		}
		//Fermeture des flux d'entr�e/sortie et de la connexion
		pwServ.close();
		brServ.close();
		s.close();
	}
		catch (Exception e){ 	e.printStackTrace(); }
		
	}

}