import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.client.TrustClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrentAccountTest {
    private Client client = TrustClient.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();

    private final Double delta = 0.0001;

    private AccountFactory accountFactory = new AccountFactory(3.5, 1.2);

    @Test
    public void replenishNegativeCase() {
        Account currentAcc = accountFactory.getCurrent(client, 0);

        currentAcc.replenish(-100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.replenish(0);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        Account currentAcc = accountFactory.getCurrent(client, 0);

        currentAcc.replenish(100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.withdraw(100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.withdraw(-100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.withdraw(1000);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = TrustClient.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        Account currentAcc = accountFactory.getCurrent(client, 100);
        Account otherAcc = accountFactory.getCurrent(otherClient, 0);

        currentAcc.transfer(otherAcc, 50);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);
        Account otherAcc = accountFactory.getCurrent(client, 0);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);
        Account otherAcc = accountFactory.getCurrent(client, 0);

        currentAcc.transfer(otherAcc, -30);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);
        Account otherAcc = accountFactory.getCredit(client, 0,1000);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void requestDepositCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);

        assertEquals(103.5, currentAcc.getBalance(), delta);
    }

    @Test
    public void requestCommissionCase() {
        Account currentAcc = accountFactory.getCurrent(client, 100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

}
