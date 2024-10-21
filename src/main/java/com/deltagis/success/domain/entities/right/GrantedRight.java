package com.deltagis.success.domain.entities.right;

import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.entities.user.UserRole;
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

    private long userId;

    // @ManyToOne
    @Transient
    private User user;

    private long roleId;

    // @ManyToOne
    @Transient
    private UserRole role;

//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project;
//
//    public Project getProject() {
//        return project;
//    }

}
