package project_authentification.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project_authentification.model.User;
import project_authentification.repository.RoleRepository;
import project_authentification.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;

    @Before

    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);

        User user = new User();
        user.setId(1)
                .setName("Alexandru")
                .setLastName("Cotonet")
                .setEmail("test@test.com");

        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
                .thenReturn(user);
    }

    @Test
    public void findUserByEmail() {
        // setup
        final String email = "test@test.com";

        // run the test
        final User result = userServiceUnderTest.findUserByEmail(email);

        // verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // setup
        final String email = "test@test.com";

        // run the test
        User result = userServiceUnderTest.saveUser(new User());

        // verify the result
        assertEquals(email, result.getEmail());
    }
}
