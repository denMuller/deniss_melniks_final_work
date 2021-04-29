package lv.lu.finalwork.service;



import lv.lu.finalwork.model.ItemNotFoundException;
import lv.lu.finalwork.model.repository.Product;
import lv.lu.finalwork.model.repository.ProductCategory;
import lv.lu.finalwork.model.ui.ProductData;
import lv.lu.finalwork.model.ui.ProductInputData;
import lv.lu.finalwork.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private ProductRepository repository;
    private ProductMapper mapper = new ProductMapper();

    public ProductService() {
        this.repository = new ProductRepository();
    }

    public void save(ProductInputData productInputData) {
        repository.save(mapper.mapFrom(productInputData));
    }

    public List<ProductData> findAll() {
//        List<ProductData> result = new ArrayList<>();
//        for (Product product : repository.findAll()) {
//            ProductData productData = mapper.mapFrom(product);
//            result.add(productData);
//        }
//        return result;

        return repository.findAll().stream()
//                .map(product -> mapper.mapFrom(product))
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product Id can't be null");
        }

        Optional<Product> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ItemNotFoundException(
                    String.format("Product by id: %d is not found", id));
        }

        return result.get();
    }



    public void delete(Long id) {

    }
}
