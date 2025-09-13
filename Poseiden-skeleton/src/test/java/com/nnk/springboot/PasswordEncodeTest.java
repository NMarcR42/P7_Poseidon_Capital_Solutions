package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit test for password encoding with BCryptPasswordEncoder.
 * This class demonstrates how a plain text password is hashed without regex pattern 
 * using BCrypt algorithm to ensure security in database storage.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PasswordEncodeTest {
	/**
     * Test method for encoding a plain text password.
     * Prints the encoded hash in console.
     */
    @Test
    public void testPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode("12345678");
        System.out.println("[ "+ pw + " ]");
    }
}
