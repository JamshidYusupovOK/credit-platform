package org.integer.userservice.service;

import lombok.RequiredArgsConstructor;
import org.integer.userservice.config.RabbitMQConfig;
import org.integer.userservice.dto.AuthResponse;
import org.integer.userservice.dto.LoginRequest;
import org.integer.userservice.dto.RegisterRequest;
import org.integer.userservice.entity.Role;
import org.integer.userservice.entity.User;
import org.integer.userservice.event.UserRegisteredEvent;
import org.integer.userservice.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;
    private final RabbitTemplate rabbitTemplate;


    public AuthResponse registerUser(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, userRegisteredEvent);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getEmail(),user.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getEmail(),user.getRole().name());
    }

    public void logout(String token) {
        String jti = jwtService.extractJti(token);
        long expiration = jwtService.getExpiration(token);


        redisTemplate.opsForValue().set(
                "blacklist:" + jti,
                "revoked",
                expiration,
                TimeUnit.MILLISECONDS
        );
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
