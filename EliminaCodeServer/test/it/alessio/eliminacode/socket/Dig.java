package it.alessio.eliminacode.socket;

import org.jasypt.digest.StandardStringDigester;


public class Dig {
public static void main(String[] args) {
	StandardStringDigester digester = new StandardStringDigester();
	digester.setAlgorithm("SHA-1");   // optionally set the algorithm
	digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
	String msg = "cia";
	String s = digester.digest(msg);
	System.out.println(s);
}
}
