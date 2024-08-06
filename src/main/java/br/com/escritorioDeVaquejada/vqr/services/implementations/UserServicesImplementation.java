package br.com.escritorioDeVaquejada.vqr.services.implementations;

import br.com.escritorioDeVaquejada.vqr.repositories.UserRepository;
import br.com.escritorioDeVaquejada.vqr.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImplementation implements UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServicesImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username "+username+" not found!"));
    }
}
