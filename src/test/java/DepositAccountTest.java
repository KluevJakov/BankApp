import com.github.KluevJakov.account.Account;
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

    private AccountFactory accountFactory = new AccountFactory(5, 6, 200);

    @Test
    public void replenishNegativeCase() {
        Account depositAcc = accountFactory.getDeposit(client, 0, new Date());

        depositAcc.replenish(-100);

        assertEquals(0, depositAcc.getBalance(), delta);
    }

    @Test
    public void replenishNullCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date());

        depositAcc.replenish(0);

        assertEquals(100, depositAcc.getBalance(), delta);
    }

    @Test
    public void replenishPositiveCase() {
        Account depositAcc = accountFactory.getDeposit(client, 0, new Date());

        depositAcc.replenish(100);

        assertEquals(100, depositAcc.getBalance(), delta);
    }

    @Test
    public void withdrawPositiveCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date(2020, Calendar.FEBRUARY, 1));

        depositAcc.withdraw(100);

        assertEquals(0, depositAcc.getBalance(), delta);
    }

    @Test
    public void withdrawNegativeCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date());

        depositAcc.withdraw(-100);

        assertEquals(100, depositAcc.getBalance(), delta);
    }

    @Test
    public void notEnoughtBalanceCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date());

        depositAcc.withdraw(1000);

        assertEquals(100, depositAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherOwnerCase() {
        Client otherClient = Client.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date());
        Account otherAcc = accountFactory.getDeposit(otherClient, 0, new Date());

        depositAcc.transfer(otherAcc, 50);

        assertEquals(100, depositAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToMyAccountCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date(2020, Calendar.FEBRUARY, 1));
        Account otherAcc = accountFactory.getDeposit(client, 0, new Date(2021, Calendar.FEBRUARY, 1));

        depositAcc.transfer(otherAcc, 30);

        assertEquals(70, depositAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferNegativeCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date());
        Account otherAcc = accountFactory.getDeposit(client, 0, new Date());

        depositAcc.transfer(otherAcc, -30);

        assertEquals(100, depositAcc.getBalance(), delta);
        assertEquals(0, otherAcc.getBalance(), delta);
    }

    @Test
    public void transferToOtherAccountTypeCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date(2020, Calendar.FEBRUARY, 1));
        Account otherAcc = accountFactory.getCredit(client, 0, 1000);

        depositAcc.transfer(otherAcc, 30);

        assertEquals(70, depositAcc.getBalance(), delta);
        assertEquals(30, otherAcc.getBalance(), delta);
    }

    @Test
    public void processPercentageCase() {
        Account depositAcc = accountFactory.getDeposit(client, 100, new Date());

        depositAcc.processPercentage();

        assertEquals(105, depositAcc.getBalance(), delta);
    }
}
