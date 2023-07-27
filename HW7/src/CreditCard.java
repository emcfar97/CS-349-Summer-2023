/**
 * This class defines a credit card by owner and limit.
 */
public class CreditCard {

    private Money balance;
    private Money creditLimit;
    private Person owner;

    public CreditCard(Person newCardHolder, Money limit) {
        owner = newCardHolder;
        creditLimit = new Money(limit);
        balance = new Money(0);
    }
    public Money getBalance() {
        return balance;
    }
    public Money getCreditLimit() {
        return creditLimit;
    }
    public String getPersonals() {
        return owner.toString();
    }
    public void charge(Money amount) {
        if (creditLimit.compareTo(balance.add(amount)) < 1) {
            System.out.println("Exceeds credit limit");
        }
        else {
            balance = balance.add(amount);
            System.out.println("Charge: " + amount.toString());
        }
    }
    public void payment(Money amount) {
        balance = balance.subtract(amount);
        System.out.println("Pay: " + amount.toString());
    }
}