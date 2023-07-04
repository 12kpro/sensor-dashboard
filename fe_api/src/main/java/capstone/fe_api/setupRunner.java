package capstone.fe_api;

import capstone.fe_api.users.Role;
import capstone.fe_api.users.User;
import capstone.fe_api.users.repositories.RoleRepository;
import capstone.fe_api.users.repositories.UserRepository;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Slf4j
@Component
public class setupRunner implements CommandLineRunner {
    private Boolean firstRun;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

    private String[] defaultRole = new String[]{"USER","ADMIN"};


    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @Value("${firstrun}")
    public void setFirstrun(Boolean firstrun) {
        this.firstRun = firstrun;
    }
    @Value("${admin.name}")
    public void setAdminName(String s) {
        this.name = s;
    }
    @Value("${admin.surname}")
    public void setAdminSurname(String s) {
        this.surname = s;
    }
    @Value("${admin.username}")
    public void setAdminUserName(String s) {
        this.username = s;
    }
    @Value("${admin.email}")
    public void setAdminEmail(String s) {
        this.email = s;
    }
    @Value("${admin.password}")
    public void setAdminPassword(String s) {
        this.password = s;
    }
    @Override
    public void run(String... args) throws Exception {
        if (firstRun) {
            loadDefault();
            firstUserCreate();
        }
    }


    public void firstUserCreate(){
        //TODO nick prende un valore inesistente nei file properties e env
        Optional<User> found = userRepository.findByEmail(email);
        User admin = new User(surname,email,name,bcrypt.encode(password),username);
        log.info(username);
        Optional<Role> role = roleRepository.findByName("ADMIN");
        if (role.isPresent() && !found.isPresent()){
            log.info(role.get().toString());

            admin.getRoles().add(role.get());
            log.info(admin.toString());
            userRepository.save(admin);
        }
    }

    public void loadDefault(){
        if(roleRepository.count() == 0) {
            for (String role : defaultRole) {
                roleRepository.save(new Role(role));
            }
        }
    }
}
