package lv.lu.finalwork.ui;

import lv.lu.finalwork.model.repository.ProductCategory;
import lv.lu.finalwork.model.ui.ProductInputData;
import lv.lu.finalwork.service.ProductService;

import java.util.Arrays;
import java.util.Scanner;

public class ConsulUI {

    private ProductService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsulUI() {
        this.service = new ProductService();
    }

    public void startUi() {
        int userChoice;
        while (true) {

            printMenu();
            userChoice = scanner.nextInt();

            callServiceByChoice(userChoice);

            if (userChoice == 0) {
                break;
            }
        }

    }

    private void callServiceByChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                initProductSave();
                break;
            case 2:
                retrieveProductList();
                break;
        }
    }

    private void retrieveProductList() {
//        for (Product product : service.findAll()){
//            System.out.println(product);
//        }
        service.findAll().stream().
                forEach(System.out::println);
    }

    private void initProductSave() {
        ProductInputData product = new ProductInputData();

        System.out.print("Enter product name..");
        product.setName(scanner.next());


        System.out.println("Enter product price");
        product.setPrice(scanner.nextDouble());

        System.out.printf("Enter product category from (%s)",
                Arrays.asList(ProductCategory.values()));
        product.setCategory(scanner.next());

        service.save(product);
    }

    private void printMenu() {
        System.out.println(" === Product Accounting application === ");
        System.out.println(" Chose one option: \n");
        System.out.println(" Save product - 1");
        System.out.println(" List all products - 2");
        System.out.println(" Exit application - 0");
        System.out.println(" Please enter the choice");
    }

}
