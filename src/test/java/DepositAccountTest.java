import com.github.KluevJakov.account.AbstractAccount;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DepositAccountTest {
    private Client client = Client.newBuilder("Name", "Surname")
            .setAddress("Address")
            .setPassport(0)
            .build();

    private final Double delta = 0.0001;

    private AccountFactory accountFactory = new AccountFactory(5, 6);

    @Test
    public void replenishNegativeCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 0, new Date());

        currentAcc.replenish(-100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date());

        currentAcc.replenish(0);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 0, new Date());

        currentAcc.replenish(100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date(2020, Calendar.FEBRUARY, 1));

        currentAcc.withdraw(100);

        assertEquals(0, currentAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date());

        currentAcc.withdraw(-100);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date());

        currentAcc.withdraw(1000);

        assertEquals(100, currentAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = Client.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date());
        AbstractAccount otherAcc = accountFactory.getDeposit(otherClient, 0, new Date());

        currentAcc.transfer(otherAcc, 50);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date(2020, Calendar.FEBRUARY, 1));
        AbstractAccount otherAcc = accountFactory.getDeposit(client, 0, new Date(2021, Calendar.FEBRUARY, 1));

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date());
        AbstractAccount otherAcc = accountFactory.getDeposit(client, 0, new Date());

        currentAcc.transfer(otherAcc, -30);

        assertEquals(100, currentAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        AbstractAccount currentAcc = accountFactory.getDeposit(client, 100, new Date(2020, Calendar.FEBRUARY, 1));
        AbstractAccount otherAcc = accountFactory.getCredit(client, 0, 1000);

        currentAcc.transfer(otherAcc, 30);

        assertEquals(70, currentAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    /*
    @Test
    public void requestDepositCase() {
        Account currentAcc = accountFactory.getDeposit(client, 100, new Date());

        requester.linkWith(new DepositRequest());
        currentAcc.setRequester(requester);

        currentAcc.accrueDeposit();

        assertEquals(105, currentAcc.getBalance(), delta);
    }
     */

    /*
    @Test
    public void requestCommissionCase() {
        Account currentAcc = accountFactory.getDeposit(client, 100, new Date());
        Requester requester = new RequestExecutor(currentAcc);
        requester.linkWith(new CreditRequest());
        currentAcc.setRequester(requester);

        currentAcc.accrueCommission();

        assertEquals(100, currentAcc.getBalance(), delta);
    }
     */
}
