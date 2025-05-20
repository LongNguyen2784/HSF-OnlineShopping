package shopping.onlineshopping.service.userService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userDTO.AccountDTO;
import shopping.onlineshopping.dto.userDTO.RegisterRequest;
import shopping.onlineshopping.mapper.userMapper.AccountMapper;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Roles;
import shopping.onlineshopping.repository.userRepository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;

    // Đăng ký tài khoản
    public AccountDTO registerAccount(RegisterRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Tài khoản đã tồn tại");
        }

        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(request.getRole() != null ? request.getRole() : Roles.CUSTOMER);

        return accountMapper.toDTO(accountRepository.save(account));
    }

    // Đổi mật khẩu
    @Transactional
    public void changePassword(String email, String newPassword) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        account.setPassword(passwordEncoder.encode(newPassword));
    }

    // Xoá tài khoản
    public void deleteAccount(String email) {
        accountRepository.deleteByEmail(email);
    }

    // Lấy tất cả tài khoản (ẩn mật khẩu)
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream().filter(user -> user.getRole() != Roles.ADMIN)
                .map(accountMapper::toDTO)
                .toList();
    }

    // Tìm theo username
    public Optional<AccountDTO> getByUsername(String username) {
        return accountRepository.findByUsername(username)
                .map(accountMapper::toDTO);
    }
}
