package it.alessio.tabellone.listeners;

import it.alessio.eliminacode.common.model.Service;
import it.alessio.tabellone.controller.Singleton;
import it.alessio.tabellone.controller.TabelloneController;

import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * It is used for having a class that listens to the change in the service
 * stored in the db
 * */
public class ServiceEntityListener {
	// private TabelloneController tabelloneController;
	//
	// public ServiceEntityListener(TabelloneController tabelloneController) {
	// this.tabelloneController = tabelloneController;
	// }

//	@PreUpdate
//	@PostUpdate
//	public void onPostUpdate(Service service) {
//		System.out.println("POST UPDATE " + service);
//	}
//
//	@PrePersist
//	public void onPrePersist(Service service) {
//		System.out.println("PRE PRESIST " + service);
//	}
//
//	@PostLoad
//	public void postLoad(Service service) {
//		System.out.println("POSTLOAD QUI " + service);
//		// this.tabelloneController.onServiceEntityUpdatedAction(service);
//		TabelloneController  controller = Singleton.getTabelloneController();
//		System.out.println(controller==null);
//		if (controller != null) {
//			controller.onServiceEntityUpdatedAction();
//		}
//	}

}
