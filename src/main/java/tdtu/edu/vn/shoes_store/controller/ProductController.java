package tdtu.edu.vn.shoes_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.shoes_store.dto.ProductDto;
import tdtu.edu.vn.shoes_store.model.Product;
import tdtu.edu.vn.shoes_store.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final Map<String, Object> result = new HashMap<>();

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        getProductBody(productDto, product);

        productService.saveProduct(product);
        result.put("status", HttpStatus.OK);
        result.put("message", "Add product successfully!");
        result.put("content", product);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);
        if(product == null) {
            result.put("status", HttpStatus.NOT_FOUND);
            result.put("message", "Product not found!");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        productService.deleteProduct(product.getId());
        result.put("status", HttpStatus.OK);
        result.put("message", "Delete product successfully!");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") Long id,
                                                @RequestBody ProductDto productDto) {
        Product product = productService.getProductById(id);
        if(product == null) {
            result.put("status", HttpStatus.NOT_FOUND);
            result.put("message", "Product not found!");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        getProductBody(productDto, product);

        productService.updateProduct(product);
        result.put("status", HttpStatus.OK);
        result.put("message", "Update product successfully!");
        result.put("content", product);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


     private void getProductBody(ProductDto productDto, Product product) {

        if(productDto.getName() != null) product.setName(productDto.getName());
        if(productDto.getDescription() != null) product.setDescription(productDto.getDescription());
        if(productDto.getImage() != null) product.setImage(productDto.getImage());
        if(productDto.getCategories() != null) product.setCategories(productDto.getCategories());
        if(productDto.getBrands() != null) product.setBrands(productDto.getBrands());
        if(productDto.getSize() != null) product.setSize(productDto.getSize());
        if(productDto.getRelatedProducts() != null) product.setRelatedProducts(productDto.getRelatedProducts());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
    }
}
