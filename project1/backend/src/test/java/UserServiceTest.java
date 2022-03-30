import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // ExtendWith is used in Jupiter (JUnit 5) to add additional functionality
// for running and executing tests. It allows us in particular to make use of the @Mock and @InjectMocks annotations
public class UserServiceTest {

    // Mock the DAO
    @Mock
    private UserDao mockDao;

    // Automatically instantiate UserService with the Mocks injected
    @InjectMocks
    private UserService systemUnderTest;

    @Test
    public void testLogin_positive() throws FailedLoginException, SQLException {
        when(mockDao.getUserByUsernameAndPassword(eq("test"), eq("test"))).thenReturn(
                new User(1000, "test", "test","test","test","test","manager"));



        User actual = this.systemUnderTest.login("test", "test");

        User expected = new User(1000, "test", "test","test","test","test","manager");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLogin_negative() {
        Assertions.assertThrows(FailedLoginException.class, () -> {
            this.systemUnderTest.login("test", "test");
        });
    }

}
