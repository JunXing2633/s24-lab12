package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class AndrewWebServicesTest {
    // Database database;
    private InMemoryDatabase fakeDB;
    private RecSys recommender;
    private PromoService promoService;
    private AndrewWebServices services;

    @Before
    public void setUp() {
        // database = new Database(); // don't want to access our real database...
        fakeDB = new InMemoryDatabase();
        // recommender = new RecSys();
        recommender = Mockito.mock(RecSys.class); // Create a mock of RecSys
        // promoService = new PromoService();
        promoService = Mockito.mock(PromoService.class); // Create a mock of PromoService

        // andrewWebService = new AndrewWebServices(database, recommender,
        // promoService);
        services = new AndrewWebServices(fakeDB, recommender, promoService);

        // Stubbing getRecommendation to return a fixed movie name based on user input
        Mockito.when(recommender.getRecommendation("Scotty")).thenReturn("Star Trek");
        Mockito.when(recommender.getRecommendation("Kirk")).thenReturn("Star Wars");
    }

    @Test
    public void testLogIn() {
        assertTrue("Login should succeed for valid credentials", services.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Star Trek", services.getRecommendation("Scotty"));
        assertEquals("Star Wars", services.getRecommendation("Kirk"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?

        String testEmail = "test@example.com";
        services.sendPromoEmail(testEmail);

        // Verify that mailTo is called exactly once with the specified email
        verify(promoService, times(1)).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        // Simulate login - assuming login would not trigger an email send
        services.logIn("Scotty", 17214);

        // Verify that no emails are sent during or after logging in
        verify(promoService, never()).mailTo(anyString());
    }
}
