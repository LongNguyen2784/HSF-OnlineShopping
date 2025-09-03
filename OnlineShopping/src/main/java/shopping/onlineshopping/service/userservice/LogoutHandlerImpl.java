package shopping.onlineshopping.service.userservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import shopping.onlineshopping.jwt.JwtService;
import shopping.onlineshopping.modal.user.RevokedToken;
import shopping.onlineshopping.repository.userrepository.AccountRepository;
import shopping.onlineshopping.repository.userrepository.RevokedTokenRepository;

@Component
public class LogoutHandlerImpl implements LogoutHandler {
    private final AccountRepository accountRepository;
    private final RevokedTokenRepository revokedTokenRepository;
    private final JwtService jwtService;

    @Autowired
    public LogoutHandlerImpl(
            AccountRepository accountRepository,
            RevokedTokenRepository revokedTokenRepository,
            JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.revokedTokenRepository = revokedTokenRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);
            String refreshToken = jwtService.getTokenFromCookie(request, "refresh_token");
            String email = jwtService.extractUsername(accessToken);
            if (email != null && refreshToken != null) {
                accountRepository.findByEmail(email).ifPresent(account  -> {
                    String ipAddress = request.getRemoteAddr();
                    String deviceInfo = request.getHeader(HttpHeaders.USER_AGENT);
                    revokedTokenRepository.save(RevokedToken.builder()
                            .account(account)
                            .token(accessToken)
                            .ipAddress(ipAddress)
                            .deviceInfo(deviceInfo)
                            .build());
                    revokedTokenRepository.save(RevokedToken.builder()
                            .account(account)
                            .token(refreshToken)
                            .ipAddress(ipAddress)
                            .deviceInfo(deviceInfo)
                            .build());
                });
            }
        }

        SecurityContextHolder.clearContext();
        ResponseCookie rtCookie = ResponseCookie.from("refresh_token", "")
                .path("/")
                .httpOnly(true)
                .secure(true)
                .maxAge(0)
                .sameSite("None")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, rtCookie.toString());
    }
}
