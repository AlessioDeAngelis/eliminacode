package it.alessio.eliminacode.security;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptorManager {
	private BasicTextEncryptor textEncryptor;

	public EncryptorManager() {
		this.textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("alessio");
	}

	public String encryptText(String plainText) {
		String encryptedText = this.textEncryptor.encrypt(plainText);
		return encryptedText;
	}

	public String decryptText(String encryptedText) {
		return this.decryptText(encryptedText);
	}
	
	public String generateActivationCodeFromMac(String text){
		String activationCode = "";
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("MD5"); // optionally set the algorithm
		digester.setIterations(10); // increase security by performing 50000
									// hashing iterations
		digester.setSaltSizeBytes(0); // no salt so the code is always the same
		String digestedText = digester.digest(text);
		char[] code = new char[16];
		for(int i = 0; i < 16; i++){
			code[i] = digestedText.charAt(i);
		}
		
		for (int i = 0; i < code.length; i++) {
			char c = code[i];
			if (c == '=') {
				c = '3';
			}
			if (c == '/') {
				c = 'a';
			}
			if (i > 0 && i % 4 == 0) {
				activationCode += "-";
			}
			activationCode += c;
		}
		return activationCode;
	}

	/**
	 * The text is the mac address coded as activation key. It will return a 16 digits serial number
	 * */
	public String generateSerialKey(String text) {
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("MD5"); // optionally set the algorithm
		digester.setIterations(10); // increase security by performing 50000
									// hashing iterations
		digester.setSaltSizeBytes(0);
		String digestedText = digester.digest(text);

		digester = new StandardStringDigester();
		digester.setAlgorithm("MD2");
		digester.setSaltSizeBytes(0);
		digestedText += digester.digest(text);

		digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-1");
		digester.setIterations(10);
		digester.setSaltSizeBytes(0);
		digestedText += digester.digest(text);
		digester = new StandardStringDigester();
		digester.setAlgorithm("MD5");
		digester.setSaltSizeBytes(0);
		digestedText += digester.digest(text);

		char[] serial = new char[16];
		serial[0] = digestedText.charAt(3);
		serial[1] = digestedText.charAt(11);
		serial[2] = digestedText.charAt(89);
		serial[3] = digestedText.charAt(41);
		serial[4] = digestedText.charAt(70);
		serial[5] = digestedText.charAt(21);
		serial[6] = digestedText.charAt(49);
		serial[7] = digestedText.charAt(1);
		serial[8] = digestedText.charAt(23);
		serial[9] = digestedText.charAt(69);
		serial[10] = digestedText.charAt(77);
		serial[11] = digestedText.charAt(54);
		serial[12] = digestedText.charAt(45);
		serial[13] = digestedText.charAt(99);
		serial[14] = digestedText.charAt(35);
		serial[15] = digestedText.charAt(29);

		String serialText = "";
		for (int i = 0; i < serial.length; i++) {
			char c = serial[i];
			if (c == '=') {
				c = '3';
			}
			if (c == '/') {
				c = 'a';
			}
			if (i > 0 && i % 4 == 0) {
				serialText += "-";
			}
			serialText += c;
		}

		return serialText;
	}
}
