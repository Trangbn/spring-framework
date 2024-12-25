package com.tgroup.trangbn;

import com.tgroup.trangbn.config.ProjectConfiguration;
import com.tgroup.trangbn.model.Song;
import com.tgroup.trangbn.services.VehicleService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TrangbnApplication {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
		var vehicleService = context.getBean(VehicleService.class);
		System.out.println(vehicleService.getClass());

		Song song = new Song();
		song.setTitle("song1");
		song.setSingerName("singer1");

		boolean vehicleStarted = false;
//		String moveVehicleStatus = vehicleService.moveVehicle(vehicleStarted);
		vehicleService.playMusic(vehicleStarted, song);

	}

}
