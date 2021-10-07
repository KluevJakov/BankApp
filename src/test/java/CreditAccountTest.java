import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.CreditAccount;
import com.github.KluevJakov.account.CurrentAccount;
import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.client.TrustClient;
import com.github.KluevJakov.requester.CreditRequest;
import com.github.KluevJakov.requester.DepositRequest;
import com.github.KluevJakov.requester.RequestExecutor;
import com.github.KluevJakov.requester.Requester;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditAccountTest {
    Client client = TrustClient.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();
    double delta = 0.0001;

    @Test
    public void replenishNegativeCase() {
        Account currentAcc = new CreditAccount(client, 0, 5, 5000);

        currentAcc.replenish(-100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);

        currentAcc.replenish(0);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);

        currentAcc.replenish(100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);

        currentAcc.withdraw(100);

        assertEquals(900, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawToMinusCase() {
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);

        currentAcc.withdraw(3000);

        assertEquals(-2000, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);

        currentAcc.withdraw(-100);

        assertEquals(1000, currentAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);

        currentAcc.withdraw(10000);

        assertEquals(1000, currentAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = TrustClient.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        Account currentAcc = new CreditAccount(client, 1000, 5, 5000);
        Account otherAcc = new CreditAccount(otherClient, 0, 6, 6000);

        currentAcc.transfer(otherAcc, 50);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        Account currentAcc = new CreditAccount(client, 100, 4, 5000);
        Account otherAcc = new CreditAccount(client, 0, 3, 10000);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        Account currentAcc = new CreditAccount(client, 100, 4, 5000);
        Account otherAcc = new CreditAccount(client, 0, 4, 5000);

        currentAcc.transfer(otherAcc, -30);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        Account currentAcc = new CreditAccount(client, 100, 4, 5000);
        Account otherAcc = new CurrentAccount(client, 0, 5);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void requestDepositCase() {
        Account currentAcc = new CreditAccount(client, 100, 3.5, 5000);
        Requester requester = new RequestExecutor(currentAcc);
        requester.linkWith(new DepositRequest());
        currentAcc.setRequester(requester);

        currentAcc.accrueDeposit();

        assertEquals(105, currentAcc.getBalance(), delta);
    }

    @Test
    public void requestCommissionCase() {
        Account currentAcc = new CreditAccount(client, 100, 3.5, 5000);
        Requester requester = new RequestExecutor(currentAcc);
        requester.linkWith(new CreditRequest());
        currentAcc.setRequester(requester);

        currentAcc.accrueCommission();

        assertEquals(100, currentAcc.getBalance(), delta);
    }
}
