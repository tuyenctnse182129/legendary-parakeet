package application.aicomic.controllers;

import application.aicomic.models.Users;
import application.aicomic.repositories.UsersRepository;
import application.aicomic.services.UsersService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;

    public UsersController(UsersService usersService, UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable String userId) {
        return usersService.getUserById(userId);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        usersService.deleteUser(userId);
    }

    @PostMapping("/add-list-user")
    public List<Users> addListUser(@RequestBody List<Users> users){
        return usersService.addListOfUsers(users);
    }


    @GetMapping("/login")
    public Users getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        String email = principal.getAttribute("email");
        System.out.println("Email from OAuth2User: " + email);
        return usersRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found in database"));

    }

    @GetMapping("/logout")
    public String logout() {
        return "You have been logged out successfully!";
    }

}

