package id.ac.ui.cs.pustakaone.bookshop.model;

public interface PaymentState {
    void processPayment(Cart cart);
    void completePayment(Cart cart);
    String getStatus();
}

class NotPaidState implements PaymentState {
    public void processPayment(Cart cart) {
        cart.setState(new ProcessedState());
    }

    public void completePayment(Cart cart) {
        System.out.println("Payment needs to be processed first!");
    }

    public String getStatus() {
        return "not paid";
    }
}

class ProcessedState implements PaymentState {
    public void processPayment(Cart cart) {
        System.out.println("Payment is already processed!");
    }

    public void completePayment(Cart cart) {
        cart.setState(new PaidState());
    }

    public String getStatus() {
        return "processed";
    }
}

class PaidState implements PaymentState {
    public void processPayment(Cart cart) {
        System.out.println("Payment is already complete!");
    }

    public void completePayment(Cart cart) {
        System.out.println("Payment is already complete!");
    }

    public String getStatus() {
        return "paid";
    }
}
