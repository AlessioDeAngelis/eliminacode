package it.alessio.tastierino.persistance;

import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.JPARepository;

import java.util.List;

import org.junit.Test;

public class RepositoryTest {
	
	@Test
	public void findAllServiceTest(){
		JPARepository r = new JPARepository();
		List<Service> services = r.findAllServices();
		for(Service s : services){
			System.out.println(s.toString());
		}
	}
	
	@Test
	public void findAllMachineTest(){
		JPARepository r = new JPARepository();
		List<Machine> machines = r.findAllMachines();
		for(Machine m : machines){
			System.out.println(m);
		}
	}
}
