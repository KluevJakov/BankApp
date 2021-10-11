import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.client.TrustClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditAccountTest {
    Client client = TrustClient.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();
    AccountFactory accountFactory = new AccountFactory();
    double delta = 0.0001;

    @Test
    public void replenishNegativeCase() {
        Account creditAcc = accountFactory.getCredit(client, 0, 5, 5000);

        creditAcc.replenish(-100);

        assertEquals(0, creditAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);

        creditAcc.replenish(0);

        assertEquals(100, creditAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);

        creditAcc.replenish(100);

        assertEquals(100, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);

        creditAcc.withdraw(100);

        assertEquals(900, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawToMinusCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);

        creditAcc.withdraw(3000);

        assertEquals(-2000, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);

        creditAcc.withdraw(-100);

        assertEquals(1000, creditAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);

        creditAcc.withdraw(10000);

        assertEquals(1000, creditAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = TrustClient.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        Account creditAcc = accountFactory.getCredit(client, 1000, 5, 5000);
        Account otherAcc = accountFactory.getCredit(otherClient, 0, 6, 6000);

        creditAcc.transfer(otherAcc, 50);

        assertEquals(100, creditAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 4, 5000);
        Account otherAcc = accountFactory.getCredit(client, 0, 3, 10000);

        creditAcc.transfer(otherAcc, 30);

        assertEquals(70, creditAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 4, 5000);
        Account otherAcc = accountFactory.getCredit(client, 0, 4, 5000);

        creditAcc.transfer(otherAcc, -30);

        assertEquals(100, creditAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 4, 5000);
        Account otherAcc = accountFactory.getCurrent(client, 0, 5);

        creditAcc.transfer(otherAcc, 30);

        assertEquals(70, creditAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void requestDepositCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 3.5, 5000);

        assertEquals(105, creditAcc.getBalance(), delta);
    }

    @Test
    public void requestCommissionCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 3.5, 5000);

        assertEquals(100, creditAcc.getBalance(), delta);
    }
}
