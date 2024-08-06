package br.com.escritorioDeVaquejada.vqr.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
