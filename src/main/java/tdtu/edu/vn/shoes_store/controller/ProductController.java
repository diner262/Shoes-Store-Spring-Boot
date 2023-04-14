package tdtu.edu.vn.shoes_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.edu.vn.shoes_store.model.Product;
import tdtu.edu.vn.shoes_store.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/add")
    public Product addProduct() {
        Product product = new Product();
        product.setName("test");
        product.setPrice(1.0);
        product.setDescription("Nice");
        product.setSize(List.of("M", "L", "XL"));
        product.setQuantity(100);
        product.setBrands("Adidas");
        product.setCategories("Men");
        product.setRelatedProducts(List.of(1L, 2L, 3L));
        productService.saveProduct(product);
        return product;
    }
}
