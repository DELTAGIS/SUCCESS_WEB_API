package com.deltagis.success.domain.entities.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JsonProperty
    private UUID uuid;

    /**
     * Set of related users
     */
    @ManyToMany(mappedBy = "groups")
    private Set<User> members = new HashSet<>();

    /**
     * User groups (if any) that members of this user group can manage the
     * members within.
     */
    @ManyToMany
    private Set<UserGroup> managedGroups = new HashSet<>();

    /**
     * User groups (if any) whose members can manage the members of this user
     * group.
     */
    @ManyToMany(mappedBy = "managedGroups")
    private Set<UserGroup> managedByGroups = new HashSet<>();
}
