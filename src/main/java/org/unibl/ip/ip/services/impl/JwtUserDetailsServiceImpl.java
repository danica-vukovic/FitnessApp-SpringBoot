package org.unibl.ip.ip.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.repositories.UserEntityRepository;
import org.unibl.ip.ip.services.JwtUserDetailsService;
import org.unibl.ip.ip.exceptions.UnauthorizedException;


@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    private final UserEntityRepository userRepository;
    private final ModelMapper modelMapper;

    public JwtUserDetailsServiceImpl(UserEntityRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return modelMapper.map(userRepository.findByUsername(username).orElseThrow(UnauthorizedException::new), JwtUser.class);
    }
}