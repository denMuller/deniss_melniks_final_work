package lv.lu.finalwork.service;


import lv.lu.finalwork.model.ItemNotFoundException;
import lv.lu.finalwork.model.repository.Product;
import lv.lu.finalwork.model.ui.ProductData;
import lv.lu.finalwork.model.ui.ProductInputData;
import lv.lu.finalwork.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(ProductInputData productInputData) {
        final Product product = mapper.mapFrom(productInputData);
        repository.save(product);
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
