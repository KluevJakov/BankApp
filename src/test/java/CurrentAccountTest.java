import com.github.KluevJakov.account.AbstractAccount;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrentAccountTest {
    private Client client = Client.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();

    private final Double delta = 0.0001;

    private AccountFactory accountFactory = new AccountFactory(3.5, 1.2);

    @Test
    public void replenishNegativeCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 0);

        currentAcc.replenish(-100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.replenish(0);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 0);

        currentAcc.replenish(100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.withdraw(100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.withdraw(-100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);

        currentAcc.withdraw(1000);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = Client.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);
        AbstractAccount otherAcc = accountFactory.getCurrent(otherClient, 0);

        currentAcc.transfer(otherAcc, 50);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);
        AbstractAccount otherAcc = accountFactory.getCurrent(client, 0);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);
        AbstractAccount otherAcc = accountFactory.getCurrent(client, 0);

        currentAcc.transfer(otherAcc, -30);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);
        AbstractAccount otherAcc = accountFactory.getCredit(client, 0,1000);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void requestDepositCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);

        assertEquals(103.5, currentAcc.getBalance(), delta);
    }

    @Test
    public void requestCommissionCase() {
        AbstractAccount currentAcc = accountFactory.getCurrent(client, 100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

}
