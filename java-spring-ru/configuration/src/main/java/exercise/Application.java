package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    @Autowired
    private UserProperties userAdmins;

    // BEGIN
    @GetMapping("/admins")
    public List<String> admins() {
        var listOfAdminsEmails = userAdmins.getAdmins();
        var usedNames = new ArrayList<>();
        return listOfAdminsEmails.stream()
                .map( i -> {
                    var userAdmin = users.stream().filter(e -> e.getEmail().equals(i)).findFirst();
                    var name = userAdmin.get().getName();
                    if(usedNames.contains(name)){
                        String finalName = name;
                        var newUser = users.stream().filter(z -> z.getEmail().equals(i) && !z.getName().equals(finalName)).findFirst();
                        name = newUser.get().getName();
                    }
                    usedNames.add(name);
                    return name;
                }).collect(Collectors.toList());
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
