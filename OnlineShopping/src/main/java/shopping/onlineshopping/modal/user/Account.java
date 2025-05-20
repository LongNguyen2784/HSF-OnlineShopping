package shopping.onlineshopping.modal.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="user_name",nullable = false)
    @JsonProperty("userName")
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="password" ,nullable = false)
    private String password;

    @Column(name="last_name", nullable = false)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name="first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Roles role; // CUSTOMER, EMPLOYEE, ADMIN

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;


    // Nếu cần liên kết thông tin chi tiết
    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    private Employee employee;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != Status.SUSPENDED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == Status.ACTIVE;
    }
}

