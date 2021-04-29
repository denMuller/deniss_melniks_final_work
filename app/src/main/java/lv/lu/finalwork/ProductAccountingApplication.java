package lv.lu.finalwork;


import lv.lu.finalwork.model.repository.Product;
import lv.lu.finalwork.repository.ProductRepository;
import lv.lu.finalwork.service.ProductMapper;
import lv.lu.finalwork.service.ProductService;
import lv.lu.finalwork.ui.ConsulUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductAccountingApplication {

    public static void main(String[] args) {

        Map<Long, Product> repository = new HashMap<>();
        ProductRepository productRepository = new ProductRepository(repository);
        ProductMapper productMapper = new ProductMapper();
        ProductService service = new ProductService(productRepository, productMapper);
        Scanner scanner = new Scanner(System.in);
        ConsulUI consulUI = new ConsulUI(service, scanner);

        consulUI.startUi();
    }

}
