package ua.bohomolov.learningspringboot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.bohomolov.learningspringboot.model.User;
import ua.bohomolov.learningspringboot.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/v1/users"
)
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<User> fetchUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(
            path = "{userUid}",
            method = RequestMethod.GET
    )
    public User fetchUser(@PathVariable(name = "userUid") UUID userUid){
        return userService.getUser(userUid).get();
    }
}
