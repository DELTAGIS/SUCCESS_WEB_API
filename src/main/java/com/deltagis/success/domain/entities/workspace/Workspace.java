package com.deltagis.success.domain.entities.workspace;
/*
 * Copyright (c) 2012-2021, DELTAGIS
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the BKSB project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.Date;
import java.util.Set;

import com.deltagis.success.domain.entities.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.apache.catalina.webresources.FileResource;

import com.deltagis.success.domain.entities.project.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Serge BROU <bkacou@omcprojets.com, brouserge1er@gmail.com>
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JacksonXmlRootElement( localName = "workspace", namespace = DxfNamespaces.DXF_2_0 )
// extends BaseIdentifiableObject
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int limitUser;

    // @OneToOne(fetch = FetchType.LAZY)
    // TODO : private FileResource logo;

    private int limitProject;

    private Date limitTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private WorkSpaceStatus status;

    private Long userId;

    @JsonProperty
    @Transient
    private User owner;

    // @OneToMany(mappedBy = "workspace")
    @JsonProperty
    @Transient
    private Set<Project> projects;
}
