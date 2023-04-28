package tdtu.edu.vn.shoes_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.shoes_store.dto.ProductDto;
import tdtu.edu.vn.shoes_store.model.Product;
import tdtu.edu.vn.shoes_store.service.BrandsService;
import tdtu.edu.vn.shoes_store.service.CategoriesService;
import tdtu.edu.vn.shoes_store.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandsService brandsService;;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/products")
    public List<ProductDto> getAllProduct() {
        return getListProduct(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable(name = "id") Long id) {
        return getProductDtoBody(productService.getProductById(id));
    }

    @GetMapping("/products/getByBrands")
    public List<ProductDto> getAllProductByBrands(@RequestParam Long id) {
        return getListProduct(productService.getAllProductByBrands(id));
    }

    @GetMapping("/products/getByCategories")
    public List<ProductDto> getAllProductByCategories(@RequestParam Long id) {
        return getListProduct(productService.getAllProductByCategories(id));
    }

    private ProductDto getProductDtoBody(Product product) {
        ProductDto productDto = new ProductDto();

        if(product.getId() != null) productDto.setId(product.getId());
        if(product.getName() != null) productDto.setName(product.getName());
        if(product.getDescription() != null) productDto.setDescription(product.getDescription());
        if(product.getImage() != null) productDto.setImage(product.getImage());
        if(product.getBrands() != null) productDto.setBrands(product.getBrands().getName());
        if(product.getCategories() != null) productDto.setCategories(product.getCategories().getName());
        if(product.getSize() != null) productDto.setSize(product.getSize());
        if(product.getRelatedProducts() != null) productDto.setRelatedProducts(product.getRelatedProducts());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());

        return productDto;
    }

    private List<ProductDto> getListProduct(List<Product> products) {
        List<ProductDto> listProduct = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = getProductDtoBody(product);
            listProduct.add(productDto);
        }
        return listProduct;
    }
}
