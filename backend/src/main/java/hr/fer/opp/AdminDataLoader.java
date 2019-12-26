package hr.fer.opp;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataLoader implements ApplicationRunner {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private static final String DEFAULT_PASSWORD = "password";

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (adminRepository.count() == 0) {
            System.out.println("Admin repository empty, adding admins ...");
            String pwdHash = encoder.encode(DEFAULT_PASSWORD);

            adminRepository.insertAdmin(new Admin("Miroslav", "Bicanic", "miroslav.bicanic@fer.hr", pwdHash));
            adminRepository.insertAdmin(new Admin("Sven", "Skender", "sven.skender@fer.hr", pwdHash));
            adminRepository.insertAdmin(new Admin("Matea", "Vasilj", "matea.vasilj@fer.hr", pwdHash));
            adminRepository.insertAdmin(new Admin("Mario", "Tropcic", "mario.tropcic@fer.hr", pwdHash));
            adminRepository.insertAdmin(new Admin("Hrvoje", "Hrvoj", "hrvoje.hrvoj@fer.hr", pwdHash));
            adminRepository.insertAdmin(new Admin("Marko", "Turina", "marko.turina@fer.hr", pwdHash));
        }

    }

}