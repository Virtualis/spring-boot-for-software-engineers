package ua.bohomolov.learningspringboot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.bohomolov.learningspringboot.model.User;
import ua.bohomolov.learningspringboot.service.UserService;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<?> fetchUser(@PathVariable(name = "userUid") UUID userUid){
        Optional<User> userOptional = userService.getUser(userUid);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage("User " + userUid + " was not found"));
        }

        return ResponseEntity.ok(userOptional.get());
    }

    class ErrorMessage {

        private String errorMessage;

        public ErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
