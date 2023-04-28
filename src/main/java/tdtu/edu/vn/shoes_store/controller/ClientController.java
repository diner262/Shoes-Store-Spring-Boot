package tdtu.edu.vn.shoes_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.shoes_store.dto.BrandsDto;
import tdtu.edu.vn.shoes_store.dto.CategoriesDto;
import tdtu.edu.vn.shoes_store.dto.ProductDto;
import tdtu.edu.vn.shoes_store.dto.UserDto;
import tdtu.edu.vn.shoes_store.model.*;
import tdtu.edu.vn.shoes_store.repository.OrderDetailRepository;
import tdtu.edu.vn.shoes_store.repository.OrderRepository;
import tdtu.edu.vn.shoes_store.service.BrandsService;
import tdtu.edu.vn.shoes_store.service.CategoriesService;
import tdtu.edu.vn.shoes_store.service.ProductService;
import tdtu.edu.vn.shoes_store.service.UserService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/client")
public class ClientController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandsService brandsService;;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserService userService;




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

    @GetMapping("/brands")
    public List<BrandsDto> getAllBrands() {
        return getListBrands(brandsService.getAllBrands());
    }

    @GetMapping("/categories")
    public List<CategoriesDto> getAllCategories() {
        return getListCategories(categoriesService.getAllCategories());
    }

//    @PostMapping("/checkout")
//    public ResponseEntity<?> checkOut(@RequestBody  Map<String, Object> checkoutData){
//        Map<String, Object> result = new HashMap<>();
//
//        // Lấy thông tin user
//        Long userId = Long.parseLong(checkoutData.get("user_id").toString());
//        UserDto userDto = userService.findUserByID(userId);
//
//        // Tách các thông tin về sản phẩm, kích cỡ, số lượng và giá thành từ chuỗi JSON truyền vào
//        String productIdsString = checkoutData.get("product_id").toString();
//        List<Long> productIds = Arrays.stream(productIdsString.split(","))
//                .map(Long::parseLong)
//                .collect(Collections.toList());
//
//        String quantityString = checkoutData.get("quantity").toString();
//        List<Integer> quantities = Arrays.stream(quantityString.split(","))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//
//        String sizeString = checkoutData.get("size").toString();
//        List<List<Integer>> sizes = new ArrayList<>();
//        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
//        Matcher matcher = pattern.matcher(sizeString);
//        while (matcher.find()) {
//            String[] sizeArray = matcher.group(1).split(",");
//            List<Integer> sizeList = Arrays.stream(sizeArray)
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//            sizes.add(sizeList);
//        }
//
//        String priceString = checkoutData.get("price").toString();
//        List<Double> prices = Arrays.stream(priceString.split(","))
//                .map(Double::parseDouble)
//                .collect(Collectors.toList());
//
//        // Tạo đối tượng Order và các đối tượng OrderDetail tương ứng
//        Order order = new Order();
//        order.setUser(userDto.toEntity());
//        order.setDate(new Date());
//        order.setStatus("Pending");
//        order.setPayment(checkoutData.get("payment").toString());
//        order.setTotalPrice(Double.parseDouble(checkoutData.get("totalPrice").toString()));
//
//        List<OrderDetail> orderDetails = new ArrayList<>();
//        for (int i = 0; i < productIds.size(); i++) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setProduct(productService.getProductById(productIds.get(i)));
//            orderDetail.setSize(sizes.get(i));
//            orderDetail.setQuantity(quantities.get(i));
//            orderDetail.setPrice(prices.get(i));
//            orderDetails.add(orderDetail);
//        }
//
//        order.setOrderDetail(orderDetails);
//
//        // Lưu đối tượng Order và OrderDetail vào database
//        orderRepository.save(order);
//
//        result.put("message", "Checkout successfully!");
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

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

    private List<CategoriesDto> getListCategories(List<Categories> categories) {
        List<CategoriesDto> listCategories = new ArrayList<>();
        for (Categories category : categories) {
            CategoriesDto categoriesDto = new CategoriesDto();
            categoriesDto.setId(category.getId());
            categoriesDto.setName(category.getName());
            listCategories.add(categoriesDto);
        }
        return listCategories;
    }

    private List<BrandsDto> getListBrands(List<Brands> brands) {
        List<BrandsDto> listBrands = new ArrayList<>();
        for (Brands brand : brands) {
            BrandsDto brandDto = new BrandsDto();
            brandDto.setId(brand.getId());
            brandDto.setName(brand.getName());
            listBrands.add(brandDto);
        }
        return listBrands;
    }
}
