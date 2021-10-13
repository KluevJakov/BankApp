import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class AccountTest {
    private final double delta = 0.0001;

    private Client trustedClient = Client.newBuilder("Name", "Surname")
            .setPassport(1111)
            .setAddress("Address")
            .build();

    private Client distrustedClient = Client.newBuilder("Name", "Surname").build();

    private AccountFactory accountFactory = new AccountFactory(2,3, 100);

    @Test
    public void distrustedClientCase() {
        assertFalse(distrustedClient.isTrusted());
    }

    @Test
    public void trustedClientCase() {
        assertTrue(trustedClient.isTrusted());
    }

    @Test
    public void distrustedCurrentAccountCase() {
        Account account = accountFactory.getCurrent(distrustedClient, 1000);

        assertFalse(account.withdraw(200));
    }

    @Test
    public void distrustedDepositAccountCase() {
        Account account = accountFactory.getDeposit(distrustedClient, 1000, new Date(2020, Calendar.FEBRUARY, 1));

        assertFalse(account.withdraw(200));
    }

    @Test
    public void distrustedCreditAccountCase() {
        Account account = accountFactory.getCredit(distrustedClient, 1000, 500);

        assertFalse(account.withdraw(200));
    }

    @Test
    public void trustedCurrentAccountCase() {
        Account account = accountFactory.getCurrent(trustedClient, 1000);

        assertTrue(account.withdraw(50));
    }

    @Test
    public void trustedDepositAccountCase() {
        Account account = accountFactory.getDeposit(trustedClient, 1000, new Date(2020, Calendar.FEBRUARY, 1));

        assertTrue(account.withdraw(50));
    }

    @Test
    public void trustedCreditAccountCase() {
        Account account = accountFactory.getCredit(trustedClient, 1000, 500);

        assertTrue(account.withdraw(50));
    }
}
