import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditAccountTest {
    private Client client = Client.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();
    private AccountFactory accountFactory = new AccountFactory(3, 5, 200);
    private double delta = 0.0001;

    @Test
    public void replenishNegativeCase() {
        Account creditAcc = accountFactory.getCredit(client, 0, 5000);

        creditAcc.replenish(-100);

        assertEquals(0, creditAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 5000);

        creditAcc.replenish(0);

        assertEquals(100, creditAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.replenish(100);

        assertEquals(1100, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(100);

        assertEquals(900, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawToMinusCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(3000);

        assertEquals(-2000, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(100);

        assertEquals(900, creditAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        Account creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(10000);

        assertEquals(1000, creditAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = Client.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();

        Account creditAcc = accountFactory.getCredit(client, 1000, 5000);
        Account otherAcc = accountFactory.getCredit(otherClient, 0,6000);

        creditAcc.transfer(otherAcc, 50);

        assertEquals(1000, creditAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 5000);
        Account otherAcc = accountFactory.getCredit(client, 0,10000);

        creditAcc.transfer(otherAcc, 30);

        assertEquals(70, creditAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        Account creditAcc = accountFactory.getCredit(client, 100, 5000);
        Account otherAcc = accountFactory.getCredit(client, 0,5000);

        creditAcc.transfer(otherAcc, -30);

        assertEquals(100, creditAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        Account creditAcc = accountFactory.getCredit(client, 100,5000);
        Account otherAcc = accountFactory.getCurrent(client, 0);

        creditAcc.transfer(otherAcc, 30);

        assertEquals(70, creditAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void processPercentagePositiveBalanceCase() {
        Account creditAcc = accountFactory.getCredit(client, 100,5000);

        creditAcc.processPercentage();

        assertEquals(100, creditAcc.getBalance(), delta);
    }

    @Test
    public void processPercentageNegativeBalanceCase() {
        Account creditAcc = accountFactory.getCredit(client, -100,5000);

        creditAcc.processPercentage();

        assertEquals(-105, creditAcc.getBalance(), delta);
    }
}
