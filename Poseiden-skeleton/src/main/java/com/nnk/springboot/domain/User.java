package com.nnk.springboot.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Pattern(
    	    regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
    	    message = "Password must contain at least 8 characters, one uppercase letter, one number and one symbol"
    	)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * Returns the authorities granted to the user and allow spring security to identify user role.
	 * The role is prefixed with "ROLE_" if not already.
	 *
	 * @return a collection of granted authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();	
		
	    if (role != null) {
	        if (role.startsWith("ROLE_")) {
	            authorities.add(new SimpleGrantedAuthority(role));
	        } else {
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
	        }
	    }
	    
	    return authorities;
	}
	
	/**
	 * Indicates whether the user's account has expired.
	 * Returning true means the account is always valid (non-expired).
	 *
	 * @return true since this implementation does not handle expiration
	 */
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}
	
	/**
	 * Indicates whether the user is locked or unlocked.
	 * Returning true means the account is never locked.
	 *
	 * @return true since this implementation does not handle locking
	 */
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	
	/**
	 * Indicates whether the user's credentials (password) have expired.
	 * Returning true means the credentials are always valid.
	 *
	 * @return true since this implementation does not handle credential expiration
	 */
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}
	
	/**
	 * Indicates whether the user is enabled or disabled.
	 * Returning true means the account is always enabled.
	 *
	 * @return true since this implementation does not handle enabling/disabling
	 */
	@Override
	public boolean isEnabled() { 

		return true;
	}

    
}
