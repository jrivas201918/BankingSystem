import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: PHP " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: PHP " + amount);
        } else {
            System.out.println("Invalid or insufficient funds.");
        }
    }

    public void displayInfo() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: PHP " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Transferred PHP " + amount + " to " + recipient.accountHolder);
        } else {
            System.out.println("Transfer failed. Check amount or balance.");
        }
    }
}

public class BankingSystem {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Account");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> viewAccount();
                case 6 -> {
                    running = false;
                    System.out.println("Thank you for using the Banking System.");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createAccount() {

        String number;
        String name;
        double initial;

        while (true) {
            System.out.print("Enter Account Number: ");
            number = scanner.nextLine();
            if(number.matches("\\d+")){
                break;
            } else {
                System.out.println("Invalid. Please enter a numeric value.");

            }
            
        }

        while (true){
            System.out.print("Enter Account Holder Name: ");
            name = scanner.nextLine();
            if(!name.isEmpty()){
                break;
            } else {
                System.out.println("Invalid. Please enter a valid name.");
            }

        }
        while (true) {
            System.out.print("Enter Initial Deposit Amount: PHP ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a numeric value.");
                continue;
            }
            
            try {
                initial = Double.parseDouble(input);
                if (initial >= 0) {
                    break;
                } else {
                    System.out.println("Invalid amount. Please enter a non-negative value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private static void deposit() {
        System.out.println("\n--- Deposit ---");
        System.out.println("---------------------------");
        System.out.print("Enter Account Number: ");
        String number = scanner.nextLine();
        BankAccount account = accounts.get(number);

        if (account != null) {
            System.out.print("Enter Deposit Amount: PHP ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.println("\n--- Withdraw ---");
        System.out.println("---------------------------");
        System.out.print("Enter Account Number: ");
        String number = scanner.nextLine();
        BankAccount account = accounts.get(number);

        if (account != null) {
            System.out.print("Enter Withdraw Amount: PHP ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transfer() {
        System.out.println("\n--- Transfer ---");
        System.out.println("---------------------------");
        System.out.print("Sender Account Number: ");
        String senderNum = scanner.nextLine();
        BankAccount sender = accounts.get(senderNum);

        System.out.print("Recipient Account Number: ");
        String recipientNum = scanner.nextLine();
        BankAccount recipient = accounts.get(recipientNum);

        if (sender != null && recipient != null) {
            System.out.print("Enter Transfer Amount: PHP ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            sender.transfer(recipient, amount);
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private static void viewAccount() {
        System.out.println("\n--- View Account ---");
        System.out.println("---------------------------");
        System.out.print("Enter Account Number: ");
        String number = scanner.nextLine();
        BankAccount account = accounts.get(number);

        if (account != null) {
            account.displayInfo();
        } else {
            System.out.println("Account not found.");
        }
    }
}
