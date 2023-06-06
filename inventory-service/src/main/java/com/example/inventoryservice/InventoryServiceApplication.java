package com.example.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository repository) {
//		return args ->{
//			InventoryEntity entity1 = new InventoryEntity(null, "iphone_13", 100);
//			InventoryEntity entity2 = new InventoryEntity(null, "iphone_13_red", 0);
//
//			repository.save(entity1);
//			repository.save(entity2);
//		};
//	}
}
