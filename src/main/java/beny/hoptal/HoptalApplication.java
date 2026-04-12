package beny.hoptal;

import beny.hoptal.data.models.User;
import beny.hoptal.data.repositories.UserRepository;
import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.dtos.responses.CreerDocteurResponse;
import beny.hoptal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HoptalApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(HoptalApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
