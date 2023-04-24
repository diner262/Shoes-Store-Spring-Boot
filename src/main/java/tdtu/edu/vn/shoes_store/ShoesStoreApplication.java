package tdtu.edu.vn.shoes_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tdtu.edu.vn.shoes_store.model.Categories;
import tdtu.edu.vn.shoes_store.model.Product;
import tdtu.edu.vn.shoes_store.repository.CategoriesRepository;

import java.util.Collections;

@SpringBootApplication
public class ShoesStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShoesStoreApplication.class, args);
	}

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Override
	public void run(String... args) throws Exception {
//		Categories categories = new Categories();
//		categories.setName("Kid");
//		categoriesRepository.saveAndFlush(categories);

//		Product product = new Product();
//		product.setName("Adidas Prophere");
//		product.setPrice(1000);
//		product.setDescription("The adidas Primeknit upper wraps the foot with a supportive fit that enhances movement");
//		product.setCategories(categories);
//
//		categories.setProducts(Collections.singletonList(product));

//		categoriesRepository.saveAndFlush(categories);
	}
}
