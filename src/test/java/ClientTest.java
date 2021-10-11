import com.github.KluevJakov.client.Client;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    private final double delta = 0.0001;

    @Test
    public void limitTrustedClientCase() {
        Client client = Client.newBuilder("Name", "Surname")
                .setAddress("Address")
                .setPassport(0)
                .build();

        //assertEquals(0, client.paymentLimit(), delta);
    }

    @Test
    public void limitDistrustedClientCase() {
        Client client = new Client("Name", "Surname");

        //assertEquals(1000, client.paymentLimit(), delta);
    }
}
