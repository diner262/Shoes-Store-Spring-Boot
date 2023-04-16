package tdtu.edu.vn.shoes_store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private List<String> size;
    private int quantity;
    private String brands;
    private String categories;
    private List<Long> relatedProducts;
    private String image;

    @Builder
    public ProductDto(Long id, String name, double price, String description, List<String> size, int quantity, String brands, String categories, List<Long> relatedProducts, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.size = size;
        this.quantity = quantity;
        this.brands = brands;
        this.categories = categories;
        this.relatedProducts = relatedProducts;
        this.image = image;
    }
}
