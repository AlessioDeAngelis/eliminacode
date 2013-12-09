package it.alessio.tastierino.persistance;

import static org.junit.Assert.*;
import it.alessio.eliminacode.common.model.Machine;
import it.alessio.eliminacode.common.model.Service;
import it.alessio.eliminacode.common.persistance.JDBCRepository;

import java.util.List;

import org.junit.Test;

public class JDBCRepositoryTest {
	
	@Test
	public void findAllServiceTest(){
		JDBCRepository r = new JDBCRepository();
		List<Service> services = r.findAllServices();
		for(Service s : services){
			System.out.println(s.toString());
		}
	}
	
	public void findAllMachineTest(){
		JDBCRepository r = new JDBCRepository();
		List<Machine> machines = r.findAllMachines();
		for(Machine m : machines){
			System.out.println(m);
		}
	}
	
	@Test
	public void findServiceTest(){
		JDBCRepository r = new JDBCRepository();
		Service service = r.findServiceById(0);
		assertEquals(service.getId(),0);
		assertEquals(service.getChiave(),1);
		service = r.findServiceById(1);
		assertEquals(service.getId(),1);
		assertEquals(service.getChiave(),2);
	}
}
