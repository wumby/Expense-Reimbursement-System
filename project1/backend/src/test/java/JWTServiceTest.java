import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sun.security.jca.GetInstance;

public class JWTServiceTest {

    private JWTService jwtService = new JWTService();

    @Test
    public void testSingleton(){
        JWTService instance = jwtService.getInstance();

        JWTService expected = instance;

        JWTService actual =instance;
        Assertions.assertEquals(expected,actual);

    }

    @Test
    public void testCreateValidJwt() {
        String jwt = jwtService.createJWT(new User(1, "username", "password", "first", "last","email", "manager"));

        Assertions.assertEquals(156, jwt.length());
    }



    @Test
    public void testParseValidJWT() {
        User user = new User(1, "username", "password", "first", "last","email", "manager");
        String jwt = jwtService.createJWT(user);

        Jws<Claims> token = jwtService.parseJwt(jwt);

        String username = token.getBody().getSubject();
        Object id = token.getBody().get("user_id");
        Object role = token.getBody().get("user_role");

        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertEquals("manager", role);
    }

}
