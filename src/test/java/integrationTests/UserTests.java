package integrationTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.UsersClient;
import users.UsersService;
import users.create.CreateUserRequestBody;
import users.create.response.CreateUserResponse;

import java.util.UUID;


public class UserTests {
    private UsersService usersService;

    @BeforeClass
    public void beforeClass() {
        usersService = new UsersService();
    }

    @Test
    public void shouldCreateAndGetUser() {
        // 1. Arrange
        CreateUserRequestBody requestBody = new CreateUserRequestBody.Builder().build();

        // 2. Act
        int id = usersService.createUser(requestBody).getData().getId();

        // 3. Assert
        usersService.getUser(id).assertUser(requestBody);
    }

    @Test
    public void shouldDeleteUser() {
        // 1. Arrange
        CreateUserRequestBody requestBody = new CreateUserRequestBody.Builder().build();

        // 2. Act
        int id = usersService.createUser(requestBody).getData().getId();
        int statusCode = usersService.deleteUser(id);

        // 3. Assert
        Assert.assertEquals(statusCode, 204);

        usersService.getUserExpectingError(id)
                .assertError(404, "Resource not found");
    }
}
