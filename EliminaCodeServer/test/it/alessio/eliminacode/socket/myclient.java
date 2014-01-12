package it.alessio.eliminacode.socket;

import java.net.*;
import java.io.*;

public class myclient
{
	public static void main(String[] args)
	{
		// verifica correttezza dei parametri
		if (args.length != 1)
		{
			System.out.println("Usage: java myclient \"message to send\"");
			return;
		}
		
		try
		{
			// preparazione dell'indirizzo del server
			InetAddress address = InetAddress.getByName("192.168.0.5");
			
			// creazione socket
			Socket client = new Socket(address, 12345);
			
			System.out.println("Client ready.\n");
			
			// creazione buffer di scrittura
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
	
			System.out.println("Buffer ready, sending message \""+"ciao"+"\"...\n");

			// scrittura del messaggio (passato come parametro) nel buffer in uscita
			out.println(args[0]);
			
			System.out.println("Message sent.\n");
			
			// chiusura socket
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
