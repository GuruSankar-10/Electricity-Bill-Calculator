import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ElectricityBillingSystem {

    static Scanner sc = new Scanner(System.in);

    // Generate New Bill
    public static void generateBill() {

        System.out.println("\n====== Generate New Bill ======");

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter electricity units used: ");
        double units = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.println("\nConnection Type");
        System.out.println("1. Domestic");
        System.out.println("2. Commercial");
        System.out.print("Enter choice (1/2): ");
        String choice = sc.nextLine();

        double energyBill = 0;
        double fixedCharge = 0;
        String typeConn = "";

        if (choice.equals("1")) {
            typeConn = "Domestic";
            fixedCharge = 75;

            if (units <= 100) {
                energyBill = units * 1.5;
            } else if (units <= 200) {
                energyBill = 100 * 1.5 + (units - 100) * 2.5;
            } else if (units <= 300) {
                energyBill = 100 * 1.5 + 100 * 2.5 + (units - 200) * 4;
            } else {
                energyBill = 100 * 1.5 + 100 * 2.5 + 100 * 4 + (units - 300) * 6;
            }

        } else if (choice.equals("2")) {
            typeConn = "Commercial";
            fixedCharge = 150;

            if (units <= 100) {
                energyBill = units * 2.5;
            } else if (units <= 200) {
                energyBill = 100 * 2.5 + (units - 100) * 4;
            } else if (units <= 300) {
                energyBill = 100 * 2.5 + 100 * 4 + (units - 200) * 6;
            } else {
                energyBill = 100 * 2.5 + 100 * 4 + 100 * 6 + (units - 300) * 8;
            }

        } else {
            System.out.println("Invalid choice");
            return;
        }

        double subtotal = energyBill + fixedCharge;
        double gst = subtotal * 0.18;
        double totalBill = subtotal + gst;

        System.out.println("\n========== BILL RECEIPT ==========");
        System.out.println("Customer Name : " + name);
        System.out.println("Connection    : " + typeConn);
        System.out.println("Units Consumed: " + units);
        System.out.println("Energy Charge : ₹ " + String.format("%.2f", energyBill));
        System.out.println("Fixed Charge  : ₹ " + fixedCharge);
        System.out.println("GST (18%)     : ₹ " + String.format("%.2f", gst));
        System.out.println("-----------------------------------");
        System.out.println("Total Payable : ₹ " + String.format("%.2f", totalBill));

        // Save to file
        try {
            FileWriter fw = new FileWriter("records.txt", true);
            fw.write("\n-----------------------------\n");
            fw.write("Customer Name : " + name + "\n");
            fw.write("Connection    : " + typeConn + "\n");
            fw.write("Units         : " + units + "\n");
            fw.write("Total Bill    : Rs." + String.format("%.2f", totalBill) + "\n");
            fw.close();
            System.out.println("\nBill saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // View Previous Records
    public static void viewRecords() {

        System.out.println("\n====== Previous Records ======");

        try {
            BufferedReader br = new BufferedReader(new FileReader("records.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No records found yet.");
        }
    }

    // Main Menu
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Electricity Billing Menu =====");
            System.out.println("1. Generate New Bill");
            System.out.println("2. View Previous Bills");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");

            String option = sc.nextLine();

            if (option.equals("1")) {
                generateBill();
            } else if (option.equals("2")) {
                viewRecords();
            } else if (option.equals("3")) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
