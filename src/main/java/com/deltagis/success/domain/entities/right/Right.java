package com.deltagis.success.domain.entities.right;

import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.entities.user.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private User user;

    @ManyToOne
    private UserRole role;

    @JsonProperty
    public User getUser() {
        return user;
    }

    @JsonProperty
    public UserRole getRole() {
        return role;
    }


}
