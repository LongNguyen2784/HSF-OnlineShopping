package shopping.onlineshopping.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.userdto.*;
import shopping.onlineshopping.jwt.JwtService;
import shopping.onlineshopping.mapper.usermapper.AccountMapper;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.repository.userrepository.AccountRepository;
import shopping.onlineshopping.service.userservice.AccountService;
import shopping.onlineshopping.util.email.EmailService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger log=Logger.getLogger(AuthController.class.getName());

    @Value("${spring.application.security.jwt.expiration}")
    private long jwtExpiration;

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> register(@RequestBody RegisterRequest request) {
        Account account = accountService.registerAccount(request); // Entity Account

        // Gửi email thông báo tạo tài khoản
        emailService.sendAccountCreatedEmail(account.getEmail(), account.getUsername(), request.getPassword());

        // Gửi email kích hoạt tài khoản
        emailService.sendAccountActivationEmail(
                account.getEmail(),
                account.getUsername(),
                account.getActivationToken()
        );

        return ResponseEntity.ok(accountMapper.toDTO(account));
    }





    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Account user = (Account) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        ResponseCookie refreshCookie = jwtService.generateRefreshTokenCookie(refreshToken);
        ResponseCookie accessCookie = ResponseCookie.from("access_token", accessToken)
                .path("/")
                .httpOnly(true)
                .secure(false) // đặt thành true nếu dùng HTTPS
                .sameSite("Lax") // dùng None nếu bạn dùng HTTPS và muốn cookie gửi cross-site
                .maxAge(Duration.ofMillis(jwtExpiration))
                .build();

        response.addHeader("Set-Cookie", refreshCookie.toString());
        response.addHeader("Set-Cookie", accessCookie.toString());

        return ResponseEntity.ok(new AuthResponse(accessToken, user.getUsername(), user.getRole().name()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request) {
        // Xóa cookies bằng cách ghi đè cookies với tuổi thọ 0
        ResponseCookie accessCookie = ResponseCookie.from("access_token", "")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(0)
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", "")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", refreshCookie.toString());
        response.addHeader("Set-Cookie", accessCookie.toString());

        return ResponseEntity.ok("Logged out successfully");
    }
    @GetMapping("/activate/{username}")
    public ResponseEntity<String> activate(
            @PathVariable String username,
            @RequestParam String token) {

        boolean activated = accountService.activateAccount(username, token);
        if (activated) {
            return ResponseEntity.ok("Account activated successfully");
        } else {
            return ResponseEntity
                    .status(HttpServletResponse.SC_BAD_REQUEST)
                    .body("Invalid or expired token");
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        Optional<Account> optionalUser = accountRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email không tồn tại."));
        }

        Account user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusMinutes(15));
        accountRepository.save(user);

        emailService.sendAccountResetPasswordEmail(user.getEmail(), token);

        return ResponseEntity.ok(Map.of("message", "Email đặt lại mật khẩu đã được gửi."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordRequest request ) {
        accountService.resetPassword(request);
        return ResponseEntity.ok(Map.of("message","Mật khẩu đã được thay đổi thành công."));
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            accountService.changePassword(request);
            return ResponseEntity.ok(Map.of("message", "Đặt lại mật khẩu thành công."));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }
}
