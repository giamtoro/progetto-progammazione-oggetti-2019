package it.univpm.Historic_EU_payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * quasta  classe del main
 * @author gianmarco troni
 * @version 1.4
 *
 */

@SpringBootApplication(scanBasePackages = "it.univpm")
/** 
* spring scanneriza il packages pper trovare il controller
*/
public class HistoricEuPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoricEuPaymentsApplication.class, args);
	}

} 