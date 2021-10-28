package demo.one.externalrules.repo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import demo.one.externalrules.model.DTO_Customer;

public class Repo_Customer {

	/**
	 * 
	 * @return list of customers 
	 */
	public static List<DTO_Customer> getAllCustomers(){
		return Arrays.asList(
				
				new DTO_Customer("138", "Rohit", 1340, 35, "M", "Business"),
				new DTO_Customer("139", "Sachin", 1540, 47, "M", "Teacher"),
				new DTO_Customer("140", "Sourav", 1294, 48, "M", "Doctor"),
				new DTO_Customer("141", "Jhulan", 340, 40, "F", "Nurse"),
				new DTO_Customer("142", "Mithali", 1040, 38, "F", "Teacher"),
				new DTO_Customer("143", "Virat", 1220, 34, "M", "Teacher"),
				new DTO_Customer("144", "Ajinka", 820, 36, "M", "Doctor")
				
				);
		
		
		
		
		
	}
	/**
	 * Checks for discount based on discountPredicate
	 * @param discountPredicate
	 */
	public static void checkoutPriceCalculationWithDiscount(Predicate<DTO_Customer> discountPredicate) {
		getAllCustomers()
						.stream()
						.filter(discountPredicate)
						.collect(Collectors.toList())
						.forEach(System.out::println);
	}
}
