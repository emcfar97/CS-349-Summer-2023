// Task #2
public class SavingsAccount extends BankAccount {
 
    private double rate = .0025;
    private int savingsNumber = 0;
    private String accountNumber;

    public SavingsAccount(String name, double amount) {
        super(name, amount);
        accountNumber = super.getAccountNumber() + "-" + savingsNumber;
    }
    public SavingsAccount(SavingsAccount oldAccount, double amount) {
        super(oldAccount.getOwner(), amount);
        String oldAccountNumber = oldAccount.getAccountNumber().substring(0, 7);
        accountNumber = oldAccountNumber + (savingsNumber + 1);
    }
    public void postInterest() {
        double balance = this.getBalance();
        this.setBalance(balance + (balance * rate));
    }
    public String getAccountNumber() {
        return accountNumber;
    }
}