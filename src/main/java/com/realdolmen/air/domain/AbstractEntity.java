package com.realdolmen.air.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Defines common fields used by the ReDo Air Entities.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public AbstractEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @PreUpdate
    @PrePersist
    public void updateLastUpdateDate(){
        this.lastUpdateDate = new Date();
    }
}
