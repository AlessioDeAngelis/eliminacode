package it.alessio.eliminacode.common.model.comparator;

import it.alessio.eliminacode.common.model.Machine;

import java.util.Comparator;

public class MachineComparator implements Comparator<Machine>{

	@Override
	public int compare(Machine o1, Machine o2) {
		int result = 0;
		result = o1.getNumberYouAreServing() - o2.getNumberYouAreServing();
		return result;
	}

}
