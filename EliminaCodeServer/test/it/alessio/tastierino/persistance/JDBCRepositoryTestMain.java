package it.alessio.tastierino.persistance;

import static org.junit.Assert.*;
import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.JDBCRepository;

import java.util.List;

import org.junit.Test;

public class JDBCRepositoryTestMain {
	
	public static void main(String[] args) {
		JDBCRepository r = new JDBCRepository();
		r.printAllTables();
		List<Service> services = r.findAllServices();
		for(Service s : services){
			System.out.println(s.toString());
		}
		List<Machine> machines = r.findAllMachines();
		for(Machine m : machines){
			System.out.println(m);
		}
	}
	
}
