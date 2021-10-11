import com.github.KluevJakov.account.AbstractAccount;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditAbstractAccountTest {
    private Client client = Client.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();
    private AccountFactory accountFactory = new AccountFactory(3, 5);
    double delta = 0.0001;

    @Test
    public void replenishNegativeCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 0, 5000);

        creditAcc.replenish(-100);

        assertEquals(0, creditAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000, 5000);

        creditAcc.replenish(0);

        assertEquals(100, creditAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.replenish(100);

        assertEquals(100, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(100);

        assertEquals(900, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawToMinusCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(3000);

        assertEquals(-2000, creditAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(-100);

        assertEquals(1000, creditAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000,5000);

        creditAcc.withdraw(10000);

        assertEquals(1000, creditAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = Client.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        AbstractAccount creditAcc = accountFactory.getCredit(client, 1000, 5000);
        AbstractAccount otherAcc = accountFactory.getCredit(otherClient, 0,6000);

        creditAcc.transfer(otherAcc, 50);

        assertEquals(100, creditAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 100, 5000);
        AbstractAccount otherAcc = accountFactory.getCredit(client, 0,10000);

        creditAcc.transfer(otherAcc, 30);

        assertEquals(70, creditAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 100, 5000);
        AbstractAccount otherAcc = accountFactory.getCredit(client, 0,5000);

        creditAcc.transfer(otherAcc, -30);

        assertEquals(100, creditAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 100,5000);
        AbstractAccount otherAcc = accountFactory.getCurrent(client, 0);

        creditAcc.transfer(otherAcc, 30);

        assertEquals(70, creditAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void requestDepositCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 100,5000);

        assertEquals(105, creditAcc.getBalance(), delta);
    }

    @Test
    public void requestCommissionCase() {
        AbstractAccount creditAcc = accountFactory.getCredit(client, 100,5000);

        assertEquals(100, creditAcc.getBalance(), delta);
    }
}
