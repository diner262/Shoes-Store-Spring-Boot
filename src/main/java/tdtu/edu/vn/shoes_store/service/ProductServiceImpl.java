package tdtu.edu.vn.shoes_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.shoes_store.model.Product;
import tdtu.edu.vn.shoes_store.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transient
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByBrands(String brands) {
        List<Product> productsByBrands = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.getBrands().getName().equals(brands)) {
                productsByBrands.add(product);
            }
        }

        return productsByBrands;
    }

    @Override
    public List<Product> getAllProductByCategories(String category) {
        List<Product> productsByCategories = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.getCategories().getName().equals(category)) {
                productsByCategories.add(product);
            }
        }
        return productsByCategories;
    }
}
