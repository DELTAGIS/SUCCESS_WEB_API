package com.deltagis.success.domain.entities.right;

import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.entities.user.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

    // @ManyToOne
    @Transient
    private User user;

    private long roleId;

    // @ManyToOne
    @Transient
    private UserRole role;
}
