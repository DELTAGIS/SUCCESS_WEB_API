package com.deltagis.success.domain.entities.user;

import com.deltagis.success.domain.entities.right.GrantedRight;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID uuid;

    @Column(unique = true)
    private String username;

    /**
     * Indicates whether this user can only be authenticated externally, such as
     * through OpenID or LDAP.
     */
    private boolean externalAuth;

    /**
     * Unique OpenID.
     */
    private String openId;

    /**
     * Unique LDAP distinguished name.
     */
    private String ldapId;

    /**
     * Required. Will be stored as a hash.
     */
    private String password;

    /**
     * Required. Does this user have two factor authentication
     */
    private boolean twoFA;

    /**
     * Required. Automatically set in constructor
     */
    private String secret;

    /**
     * Date when password was changed.
     */
    private Date passwordLastUpdated;


    /**
     * Date of the user's last login.
     */
    private Date lastLogin;

    /**
     * The token used for a user account restore. Will be stored as a hash.
     */
    private String restoreToken;

    /**
     * The token used for a user lookup when sending restore and invite emails.
     */
    private String idToken;

    /**
     * The timestamp representing when the restore window expires.
     */
    private Date restoreExpiry;

    /**
     * Indicates whether this user was originally self registered.
     */
    private boolean selfRegistered;

    /**
     * Indicates whether this user is currently an invitation.
     */
    private boolean invitation;

    /**
     * Indicates whether this is user is disabled, which means the user cannot
     * be authenticated.
     */
    private boolean disabled;

    private boolean isCredentialsNonExpired;

    private boolean isAccountNonLocked;

    /**
     * The timestamp representing when the user account expires. If not set the
     * account does never expire.
     */
    private Date accountExpiry;

    private String surname;

    private String firstName;

    private String email;

    private String phoneNumber;

    private String jobTitle;

    private String introduction;

    private String gender;

    private Date birthday;

    private String nationality;

    private String employer;

    private String education;

    private String interests;

    private String languages;

    private String welcomeMessage;

    private Date lastCheckedInterpretations;

    @ManyToMany
    private Set<UserGroup> groups = new HashSet<>();

    private String avatar;

    /**
     * Max organisation unit level for data output and data analysis operations,
     * may be null.
     */
    private Integer dataViewMaxOrganisationUnitLevel;

    /**
     * Ordered favorite apps.
     */
    @ElementCollection
    private List<String> apps = new ArrayList<>();

    /**
     * OBS! This field will only be set when de-serialising a user with settings
     * so the settings can be updated/stored.
     * <p>
     * It is not initialised when loading a user from the database.
     */
    private transient UserSettings settings;

    private List<GrantedAuthority> roles;

    /**
     * Set of user roles.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<GrantedRight> grantedRights = new HashSet<>();
}
