package it.alessio.tastierino.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.Repository;

import java.util.List;

import org.junit.Test;

public class RepositoryTest {
	
	@Test
	public void findAllServiceTest(){
		Repository r = new Repository();
		List<Service> services = r.findAllServices();
		for(Service s : services){
			System.out.println(s.toString());
		}
	}
	
	@Test
	public void findAllMachineTest(){
		Repository r = new Repository();
		List<Machine> machines = r.findAllMachines();
		for(Machine m : machines){
			System.out.println(m);
		}
	}
}
