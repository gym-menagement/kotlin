package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    
    override fun loadUserByUsername(username: String): UserDetails {
        val users = userRepository.findByLoginid(username)
        val user = users.firstOrNull()
            ?: throw UsernameNotFoundException("User not found with username: $username")

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.loginid)
            .password(user.passwd)
            .authorities(listOf(SimpleGrantedAuthority("ROLE_${user.role}")))
            .build()
    }
}