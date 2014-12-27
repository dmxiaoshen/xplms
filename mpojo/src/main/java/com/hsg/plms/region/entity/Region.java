package com.hsg.plms.region.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hsg.plms.base.entity.BaseEntity;

@Entity
@Table(name = "region")
public class Region extends BaseEntity {

    /** */
    private static final long serialVersionUID = 9037315639391269854L;

    private String code;
    private Integer level;
    private String name;
    private Region region;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="parent_id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
