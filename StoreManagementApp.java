import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product [ID: " + id + ", Name: " + name + ", Price: $" + price + "]";
    }
}

class Customer {
    private String id;
    private String name;
    private List<Product> orders;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getOrders() {
        return orders;
    }

    public void addOrder(Product product) {
        orders.add(product);
    }

    public void removeOrder(Product product) {
        orders.remove(product);
    }

    @Override
    public String toString() {
        return "Customer [ID: " + id + ", Name: " + name + ", Orders: " + orders.size() + "]";
    }
}

public class StoreManagementApp {
    private List<Product> products;
    private Map<String, Customer> customers;

    public StoreManagementApp() {
        products = new ArrayList<>();
        customers = new HashMap<>();
    }

    // Add a product
    public void addProduct(String id, String name, double price) {
        Product product = new Product(id, name, price);
        products.add(product);
        System.out.println("Product added: " + product);
    }

    // View all products
    public void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("All Products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Search for a product by ID
    public Product searchProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Delete a product by ID
    public void deleteProduct(String id) {
        Product product = searchProduct(id);
        if (product != null) {
            products.remove(product);
            System.out.println("Product deleted: " + product);
        } else {
            System.out.println("Product not found with ID: " + id);
        }
    }

    // Count total number of products
    public int countProducts() {
        return products.size();
    }

    // Add a customer
    public void addCustomer(String id, String name) {
        Customer customer = new Customer(id, name);
        customers.put(id, customer);
        System.out.println("Customer added: " + customer);
    }

    // Add an order for a customer
    public void addCustomerOrder(String customerId, String productId) {
        Customer customer = customers.get(customerId);
        Product product = searchProduct(productId);
        if (customer != null && product != null) {
            customer.addOrder(product);
            System.out.println("Order added for customer " + customer.getName() + ": " + product);
        } else {
            System.out.println("Customer or product not found.");
        }
    }

    // View individual customer orders
    public void viewCustomerOrders(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Orders for customer " + customer.getName() + ":");
            for (Product order : customer.getOrders()) {
                System.out.println(order);
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    // View all customer reports
    public void viewAllCustomerReports() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("All Customer Reports:");
            for (Customer customer : customers.values()) {
                System.out.println(customer);
            }
        }
    }

    // Delete a customer and their orders
    public void deleteCustomer(String customerId) {
        Customer customer = customers.remove(customerId);
        if (customer != null) {
            System.out.println("Customer deleted: " + customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Update customer orders (add or remove)
    public void updateCustomerOrder(String customerId, String productId, boolean addOrder) {
        Customer customer = customers.get(customerId);
        Product product = searchProduct(productId);
        if (customer != null && product != null) {
            if (addOrder) {
                customer.addOrder(product);
                System.out.println("Order added for customer " + customer.getName() + ": " + product);
            } else {
                customer.removeOrder(product);
                System.out.println("Order removed for customer " + customer.getName() + ": " + product);
            }
        } else {
            System.out.println("Customer or product not found.");
        }
    }

    public static void main(String[] args) {
        StoreManagementApp app = new StoreManagementApp();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nStore Management Application");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Count Total Products");
            System.out.println("6. Add Customer");
            System.out.println("7. Add Customer Order");
            System.out.println("8. View Customer Orders");
            System.out.println("9. View All Customer Reports");
            System.out.println("10. Delete Customer");
            System.out.println("11. Update Customer Order");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    app.addProduct(productId, productName, productPrice);
                    break;
                case 2:
                    app.viewAllProducts();
                    break;
                case 3:
                    System.out.print("Enter Product ID to search: ");
                    String searchId = scanner.nextLine();
                    Product foundProduct = app.searchProduct(searchId);
                    if (foundProduct != null) {
                        System.out.println("Product found: " + foundProduct);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Product ID to delete: ");
                    String deleteId = scanner.nextLine();
                    app.deleteProduct(deleteId);
                    break;
                case 5:
                    System.out.println("Total number of products: " + app.countProducts());
                    break;
                case 6:
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    app.addCustomer(customerId, customerName);
                    break;
                case 7:
                    System.out.print("Enter Customer ID: ");
                    String custId = scanner.nextLine();
                    System.out.print("Enter Product ID: ");
                    String prodId = scanner.nextLine();
                    app.addCustomerOrder(custId, prodId);
                    break;
                case 8:
                    System.out.print("Enter Customer ID: ");
                    String viewCustId = scanner.nextLine();
                    app.viewCustomerOrders(viewCustId);
                    break;
                case 9:
                    app.viewAllCustomerReports();
                    break;
                case 10:
                    System.out.print("Enter Customer ID to delete: ");
                    String deleteCustId = scanner.nextLine();
                    app.deleteCustomer(deleteCustId);
                    break;
                case 11:
                    System.out.print("Enter Customer ID: ");
                    String updateCustId = scanner.nextLine();
                    System.out.print("Enter Product ID: ");
                    String updateProdId = scanner.nextLine();
                    System.out.print("Add (true/false) or Remove (false) order: ");
                    boolean addOrder = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    app.updateCustomerOrder(updateCustId, updateProdId, addOrder);
                    break;
                case 12:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
    }
}
