package application.aicomic.services;

import application.aicomic.dataAccess.UserServiceResponseDto;
import application.aicomic.models.Users;
import application.aicomic.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(String userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public List<Users> addListOfUsers(List<Users> users) {
        return usersRepository.saveAll(users);
    }

    public UserServiceResponseDto getById(String id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return new UserServiceResponseDto(true, "User found.", Collections.singletonList(user));
        }
        return new UserServiceResponseDto(false, "No user found for the given user ID.", Collections.emptyList());
    }

    public UserServiceResponseDto deleteUser(String id) {
        try {
            Optional<Users> userOptional = usersRepository.findById(id);
            if (userOptional.isEmpty()) {
                return new UserServiceResponseDto(false, "User not found.", Collections.emptyList());
            }

            Users user = userOptional.get();
            user.setStatus((byte) 0);
            usersRepository.save(user);

            return new UserServiceResponseDto(true, "User status updated to inactive (deleted).", Collections.singletonList(user));
        } catch (Exception ex) {
            logger.error("Error updating user status: ", ex);
            return new UserServiceResponseDto(false, "An error occurred: " + ex.getMessage(), Collections.emptyList());
        }
    }

}
