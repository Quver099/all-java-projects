import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {

    public static class   Account {
        int id;
        String name;
        double balance;
        ArrayList<String> transactions = new ArrayList<>();
    
        public Account(int id, String name, double balance){
            this.id =id;
            this.name = name;
            this.balance = 0;
            transactions.add("Account opened.Money is eqaul to 0");
    }

    
        public void deposit(double amount){
            if(amount > 0){
                balance += amount;
                System.out.println(amount + " of money deposited to the account");
                transactions.add("Deposited: " + amount);
        }
            else{
                System.out.println("You can't deposit minus amount of money");
        }

    }
        boolean withdraw(double amount){
            if(balance >= amount ){
                balance -= amount;
                System.out.println(amount + " decreased from your account you only have " + balance);
                transactions.add("Withdrawn: " + amount);
                return true;
        }
            else{
                System.out.println("Your account do not have that much money");
                return false;
        }
    }
        public void viewBalance(){
            System.out.println("Balance: " + balance);
        
    }
        public void transfer(Account target, double amount){
            if(this.withdraw(amount)){
                target.deposit(amount);
                System.out.println("Transfer successful");
            }

    }
        public void viewTransactions(){
            for(String t : transactions){
                System.out.println(t);
        }
    }
}
    public static class Bank{
        private ArrayList<Account> accounts = new  ArrayList<>();
        private int NextId = 1;
    
        public void addAccount(String name){
            Account newAccount = new Account(NextId , name, 0);
            accounts.add(newAccount);
            System.out.println("New account opened id is: " + NextId);
            NextId++;
    }
        public void viewAccounts(){
            if(accounts.isEmpty()){
                System.out.println("No Account  found.");
            }
            else{
                for(Account a : accounts){
                    System.out.println("Name of account " + a.name + " Id of account is " + a.id);
                }

        }
}
        boolean  deleteAccount(int Id){
            for(int i = 0; i < accounts.size(); i++){
                if(accounts.get(i).id == Id){
                    accounts.remove(i);
                    System.out.println("Account " + Id + " deleted");
                    return true;
                }
            }
            System.out.println("Account Id not found");
            return false;
        }
        Account findAccountById(int Id){
            for(Account a : accounts){
                if(a.id == Id){
                    return a;
                }
            }
            System.out.println("Account Id not found");
            return null;
        }

    }

        public static void main(String[] args){
            int Choice;
            Bank bank = new Bank();
            Scanner scanner =  new Scanner(System.in);
            while(true){
                System.out.println("\n---  Banking System ---");
                System.out.println("1. Create Account\n2. View All Accounts\n3. Deposit Money");
                System.out.println("4. Withdraw Money\n5. Transfer Money\n6. View Balance\n7. Delete An account");
                System.out.println("8. View The Transaction History of account\n9. Exit");
                System.out.print("Your choice is: ");
                Choice = scanner.nextInt();
                if(Choice == 9){
                    break;
                }
                switch (Choice){
                    case 1:
                        System.out.println("Enter a name of account");
                        String name = scanner.next();
                        bank.addAccount(name);
                        break;
                    case 2:
                        bank.viewAccounts();
                        break;
                    case 3:
                        System.out.print("Enter account ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();

                        Account acc = bank.findAccountById(id);
                            if (acc != null) {
                                acc.deposit(amount);
                            }
                        break;
                    case 4:
                        System.out.print("Enter account ID: ");
                        int id1 = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double amount1 = scanner.nextDouble();

                        Account acc1 = bank.findAccountById(id1);
                        if(acc1 != null){
                            acc1.withdraw(amount1);
                        }
                        break;
                    case 5:
                        System.out.print("From ID: ");
                        int fromId = scanner.nextInt();

                        System.out.print("To ID: ");
                        int toId = scanner.nextInt();

                        System.out.print("Amount: ");
                        double amount2 = scanner.nextDouble();

                        Account from = bank.findAccountById(fromId);
                        Account to = bank.findAccountById(toId);

                        if (from != null && to != null) {
                            from.transfer(to, amount2);
                    }
                        break;
                    case 6:
                        System.out.println("Enter account ID: ");
                        int id2 = scanner.nextInt();
                        Account acc2 =  bank.findAccountById(id2);
                        if(acc2 != null){
                            acc2.viewBalance();
                        }
                        break;
                    case 7:
                        System.out.println("Enter account ID: ");
                        int id3 = scanner.nextInt();
                        bank.deleteAccount(id3);
                        break;
                    case 8:
                        System.out.println("Enter Account ID: ");
                        int id4 = scanner.nextInt();
                        Account acc3 = bank.findAccountById(id4);
                        if(acc3 != null)
                            acc3.viewTransactions();
                        break;            
                        
            }
    }
    scanner.close();
}
}


