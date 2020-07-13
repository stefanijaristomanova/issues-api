package com.blockverse.issues.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
//import javax.persistence.jar.javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ISSUE")
@SQLDelete(sql = "UPDATE ISSUE " + "SET DELETED = 1 " + "WHERE id = ?")
@Where(clause = "DELETED = 0")
public class IssueEntity implements Serializable {

    private static final long serialVersionUID = 3343003280979161263L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "DATE_ENTERED")
    private LocalDateTime dateCreated;

    @Column(name = "DATE_MODIFIED")
    private LocalDateTime dateModified;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "TEMPLATE_ID")
    private Long templateID;

    @Column(name = "VERSION")
    private Integer version;

}
