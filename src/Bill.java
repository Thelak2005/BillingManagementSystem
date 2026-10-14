import java.util.ArrayList;

public class Bill {

    private String customerName;
    private ArrayList<Product> billProducts;
    private ArrayList<Integer> billQuantities;

    public Bill(String customerName) {
        this.customerName = customerName;
        this.billProducts = new ArrayList<>();
        this.billQuantities = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        billProducts.add(product);
        billQuantities.add(quantity);
    }

    public double calculateSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < billProducts.size(); i++) {
            Product product = billProducts.get(i);
            int quantity = billQuantities.get(i);

            subtotal += product.getPrice() * quantity;
        }

        return subtotal;
    }

    public double calculateDiscount() {
        double subtotal = calculateSubtotal();

        if (subtotal >= 1000) {
            return subtotal * 0.10;
        }

        return 0;
    }

    public double calculateFinalTotal() {
        return calculateSubtotal() - calculateDiscount();
    }

    public void displayBill() {

        System.out.println("\n==============================================");
        System.out.println("              CUSTOMER BILL");
        System.out.println("==============================================");
        System.out.println("Customer Name: " + customerName);
        System.out.println("----------------------------------------------");

        System.out.printf("%-15s %-10s %-10s %-10s%n",
                "Product", "Price", "Qty", "Total");

        System.out.println("----------------------------------------------");

        for (int i = 0; i < billProducts.size(); i++) {

            Product product = billProducts.get(i);
            int quantity = billQuantities.get(i);

            double total = product.getPrice() * quantity;

            System.out.printf("%-15s %-10.2f %-10d %-10.2f%n",
                    product.getProductName(),
                    product.getPrice(),
                    quantity,
                    total);
        }

        System.out.println("----------------------------------------------");

        System.out.printf("Subtotal     : Rs. %.2f%n", calculateSubtotal());
        System.out.printf("Discount     : Rs. %.2f%n", calculateDiscount());
        System.out.printf("Final Total  : Rs. %.2f%n", calculateFinalTotal());

        System.out.println("==============================================");
        System.out.println("          Thank You! Come Again!");
        System.out.println("==============================================");
    }
}