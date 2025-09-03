package shopping.onlineshopping.service.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userdto.*;
import shopping.onlineshopping.jwt.JwtService;
import shopping.onlineshopping.mapper.usermapper.AccountMapper;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Roles;
import shopping.onlineshopping.modal.user.Status;
import shopping.onlineshopping.repository.userrepository.AccountRepository;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;
    private final JwtService jwtService;

    public Account registerAccount(RegisterRequest request) {
        if (request.getUsername().equalsIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Username không được trùng với Email.");
        }

        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại.");
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }
        Account account = accountMapper.fromRegisterRequest(request);
        account.setEmail(request.getEmail());
        account.setUsername(request.getUsername());
        account.setRole(Roles.CUSTOMER);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setStatus(Status.INACTIVE);

        String token = UUID.randomUUID().toString();
        account.setActivationToken(token);

        return accountRepository.save(account); // phải return entity đã được save
    }
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream().filter(user -> user.getRole() != Roles.ADMIN)
                .map(accountMapper::toDTO)
                .toList();
    }

    public Optional<AccountDTO> getByUsername(String username) {
        return accountRepository.findByUsername(username)
                .map(accountMapper::toDTO);
    }
    public UserProfileDTO updateUserProfile(UpdateAccountRequest request, Account user) {
        accountMapper.updateAccountFromRequest(request, user);
        if (request.getImageUrl() != null && !request.getImageUrl().isBlank()) {
            String cleanedUrl = request.getImageUrl().replace("\\", "/");
            user.setImageUrl(cleanedUrl);
        }

        accountRepository.save(user);
        return accountMapper.toProfileDTO(user);
    }





    public boolean activateAccount(String username, String token) {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndActivationToken(username, token);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setStatus(Status.ACTIVE);
            account.setActivationToken(null); // xoá token sau khi dùng
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public void resetPassword(ChangePasswordRequest request) {
        Account user = accountRepository.findByResetToken(request.getToken())
                .orElseThrow(() -> new IllegalArgumentException("Token không hợp lệ."));

        if (user.getTokenExpiry() == null || user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token đã hết hạn.");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu mới không được trùng với mật khẩu hiện tại.");
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new IllegalArgumentException("Mật khẩu xác nhận không khớp.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetToken(null);
        user.setTokenExpiry(null);
        accountRepository.save(user);
    }
    public void changePassword(ChangePasswordRequest request) {
        String token = request.getToken();
        String username = jwtService.extractUsername(token); // ✅ Tự viết hàm này

        Account user = accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tài khoản."));

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu mới không được trùng với mật khẩu hiện tại.");
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new IllegalArgumentException("Mật khẩu xác nhận không khớp.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(user);
    }
    public Account addUserByAdmin(RegisterRequest request) {
        if (request.getUsername().equalsIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Username không được trùng với Email.");
        }

        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại.");
        }

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }

        Account account = accountMapper.fromRegisterRequest(request);

        account.setEmail(request.getEmail());
        account.setUsername(request.getUsername());

        account.setRole(request.getRole());

        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setStatus(Status.INACTIVE);

        String token = UUID.randomUUID().toString();
        account.setActivationToken(token);

        return accountRepository.save(account);
    }
}
