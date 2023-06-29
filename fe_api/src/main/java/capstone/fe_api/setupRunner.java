package capstone.fe_api;

import capstone.fe_api.utenti.Role;
import capstone.fe_api.utenti.User;
import capstone.fe_api.utenti.repositories.RoleRepository;
import capstone.fe_api.utenti.repositories.UserRepository;


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
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;

    private String[] ruoliDefault = new String[]{"USER","ADMIN"};


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
    @Value("${admin.nome}")
    public void setAdminNome(String s) {
        this.nome = s;
    }
    @Value("${admin.cognome}")
    public void setAdminCognome(String s) {
        this.cognome = s;
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
        User admin = new User(cognome,email,nome,bcrypt.encode(password),username);
        log.info(username);
        Optional<Role> ruolo = roleRepository.findByNome("ADMIN");
        if (ruolo.isPresent() && !found.isPresent()){
            log.info(ruolo.get().toString());

            admin.getRuoli().add(ruolo.get());
            log.info(admin.toString());
            userRepository.save(admin);
        }
    }

    public void loadDefault(){
        if(roleRepository.count() == 0) {
            for (String ruolo : ruoliDefault) {
                roleRepository.save(new Role(ruolo));
            }
        }
    }
}
