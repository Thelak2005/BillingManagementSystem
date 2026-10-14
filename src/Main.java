import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("     BILLING MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Create Bill");
            System.out.println("4. Search Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    viewProducts();
                    break;

                case 3:
                    createBill();
                    break;
                case 4:
                    searchProduct();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    System.out.println("Thank you for using the Billing System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    public static void addProduct() {

        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        Product product = new Product(id, name, price, quantity);

        products.add(product);

        System.out.println("Product added successfully!");
    }

    public static void viewProducts() {

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n========== PRODUCT LIST ==========");
        System.out.printf("%-10s %-20s %-10s %-10s%n",
                "ID", "Name", "Price", "Quantity");

        for (Product product : products) {
            product.displayProduct();
        }
    }

    public static void searchProduct() {

        System.out.print("Enter Product ID to search: ");
        int id = scanner.nextInt();

        for (Product product : products) {

            if (product.getProductId() == id) {

                System.out.println("\nProduct Found!");
                System.out.println("Product ID   : " + product.getProductId());
                System.out.println("Product Name : " + product.getProductName());
                System.out.println("Price        : Rs. " + product.getPrice());
                System.out.println("Quantity     : " + product.getQuantity());

                return;
            }
        }

        System.out.println("Product not found.");
    }

    public static void createBill() {

        if (products.isEmpty()) {
            System.out.println("No products available. Please add products first.");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        Bill bill = new Bill(customerName);

        while (true) {

            System.out.print("\nEnter Product ID (0 to finish): ");
            int id = scanner.nextInt();

            if (id == 0) {
                break;
            }

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            boolean found = false;

            for (Product product : products) {

                if (product.getProductId() == id) {

                    if (quantity <= 0) {
                        System.out.println("Quantity must be greater than 0.");
                        return;
                    }

                    bill.addProduct(product, quantity);
                    System.out.println("Product added to bill!");

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Product not found.");
            }
        }

        bill.displayBill();
    }
    public static void deleteProduct() {

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();

        for (Product product : products) {

            if (product.getProductId() == id) {

                products.remove(product);

                System.out.println("Product deleted successfully!");

                return;
            }
        }

        System.out.println("Product not found.");
    }
}
