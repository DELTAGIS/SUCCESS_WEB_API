package com.deltagis.success.domain.entities.right;

import com.deltagis.success.domain.entities.project.Project;
import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.entities.user.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @JacksonXmlRootElement(localName = "grantedRight", namespace = DxfNamespaces.DXF_2_0)
public class GrantedRight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private UUID uid;

    @ManyToOne
    private User user;

    @ManyToOne
    private UserRole role;

    @JsonProperty
    public Project getProject() {
        return role.getProject();
    }

}
