package it.alessio.eliminacode.main;

import it.alessio.tastierino.controller.TastierinoController;

public class TastierinoClient {
	public static void main(String[] args) {
		String machineId = "0";
		if(args.length >0){
			machineId = args[0];
		}		
		TastierinoController controller = new TastierinoController(Integer.parseInt(machineId));
	}
}
