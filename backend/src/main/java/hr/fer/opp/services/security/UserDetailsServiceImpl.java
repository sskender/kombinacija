package hr.fer.opp.services.security;

import hr.fer.opp.model.Person;
import hr.fer.opp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonService personService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try{
            Person p = personService.fetchByEmail(email);
            List<GrantedAuthority> auth = authorities(p);
            for(GrantedAuthority a : auth){
                System.out.println(a.getAuthority());
            }
            return new User(p.getEmail(), p.getPwdHash(), auth);
        } catch (RuntimeException e){
            throw new UsernameNotFoundException("User with given email does not exist");
        }
    }

    private List<GrantedAuthority> authorities(Person p){
        if(personService.isAdmin(p.getId())){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,CITIZEN");
        } else if(personService.isEmployee(p.getId())){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("EMPLOYEE,CITIZEN");
        } else if (personService.isCitizen(p.getId())){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("CITIZEN");
        }
        return new ArrayList<>();
    }
}
