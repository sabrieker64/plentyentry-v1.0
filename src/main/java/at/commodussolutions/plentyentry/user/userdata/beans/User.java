package at.commodussolutions.plentyentry.user.userdata.beans;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.user.payment.beans.PaymentMethod;
import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.listener.UserListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: @Eker
 */

@Entity
@Table(name = "USER")
@Data
@EntityListeners(UserListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String lastName;

    @Column(name = "EMAIL", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String email;

    @Column(name = "PASSWORD", columnDefinition = ("VARCHAR2(250 CHAR)"))
    private String password;

    @Column(name = "STREET", columnDefinition = ("VARCHAR2(100 CHAR)"))
    private String street;

    @Column(name = "POST_CODE", columnDefinition = ("VARCHAR2(10 CHAR)"))
    private String postCode;

    @Column(name = "CITY", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String city;

    @Column(name = "AGE", columnDefinition = ("NUMBER(11, 0)"))
    private Integer age;

    @Column(name = "SV_NUMBER")
    private Integer svNumber;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "USER_GENDER")
    @Enumerated(EnumType.STRING)
    private UserGender userGender;

    @Column(name = "LOGGED_IN")
    private Boolean isLoggedIn = false;

    @Column(name = "VERIFIED_AS_ENTERTAINER")
    private Boolean isVerifiedAsEntertainer = false;

    @Column(name = "LOCKED")
    private Boolean locked = false;

    @Column(name = "ENABLED")
    private Boolean enabled = false;

    @ManyToMany
    @JoinTable(name = "ENTERTAINED_EVENTS", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    private Set<Event> entertainedEvents;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<PaymentMethod> paymentMethod = new HashSet<>();

    @Column(name = "JWT_TOKEN")
    private String jwtToken;

    //MINA
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHOPPINGCART_ID", referencedColumnName = "ID",nullable = true)
    private ShoppingCart shoppingCart;
    //MINA END

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userType.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
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
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
