package io.hrnugr.ecommerce.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author harun ugur
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @NotNull
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Setter(AccessLevel.NONE)
    @Version
    @Column(name = "version")
    private Integer version;

}