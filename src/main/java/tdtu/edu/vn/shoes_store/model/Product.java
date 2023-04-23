package tdtu.edu.vn.shoes_store.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "product")
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    @ElementCollection
    private List<String> size;
    private int quantity;
    private String brands;
    private String categories;
    @ElementCollection
    private List<Long> relatedProducts;
    private String image;

}
