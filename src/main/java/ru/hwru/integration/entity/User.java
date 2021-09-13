package ru.hwru.integration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.hwru.integration.entity.Authority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", initialValue = 10, allocationSize = 1)
public class User implements UserDetails {

    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MIN_PASSWORD_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @Column(name = "id")
    private Long id;

    @Length(min = MIN_USERNAME_LENGTH, message = "Username must be at least " + MIN_USERNAME_LENGTH + " characters long")
    @NotEmpty(message = "Please enter username")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore // just in case Jackson tries wants to betray us
    @Length(min = MIN_PASSWORD_LENGTH, message = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long")
    @NotEmpty(message = "Please enter the password")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Collection<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "BlogUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", posts=" + posts +
                ", authorities=" + authorities +
                '}';
    }
}
