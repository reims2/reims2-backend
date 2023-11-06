package org.pvh.model.entity;

import java.util.Date;

import org.pvh.model.enums.AppearanceEnum;
import org.pvh.model.enums.GlassesSizeEnum;
import org.pvh.model.enums.GlassesTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "unsuccessful_search")
public class UnsuccessfulSearch extends BaseEntity {

    @Column(name = "glasses_type")
    private String glassesType;

    @Column(name = "glasses_size")
    private String glassesSize;

    @Column(name = "appearance")
    private String appearance;

    @Column(name = "location")
    @Pattern(regexp = "sm|sa")
    private String location;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "search_date")
    private Date searchDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OS_ID")
    private Eye os;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OD_ID")
    private Eye od;

    public Eye getOs() {
        return os;
    }

    public void setOs(Eye os) {
        this.os = os;
    }

    public Eye getOd() {
        return od;
    }

    public void setOd(Eye od) {
        this.od = od;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GlassesSizeEnum getGlassesSize() {
        return Enum.valueOf(GlassesSizeEnum.class, glassesSize);
    }

    public void setGlassesSize(GlassesSizeEnum glassesSize) {
        this.glassesSize = glassesSize.name();
    }

    public AppearanceEnum getAppearance() {
        return Enum.valueOf(AppearanceEnum.class, appearance);
    }

    public void setAppearance(AppearanceEnum appearance) {
        this.appearance = appearance.name();
    }

    public GlassesTypeEnum getGlassesType() {
        return Enum.valueOf(GlassesTypeEnum.class, glassesType);
    }

    public void setGlassesType(GlassesTypeEnum glassesType) {
        this.glassesType = glassesType.name();
    }

    public void setGlassesType(String glassesType) {
        this.glassesType = glassesType;
    }

    public void setGlassesSize(String glassesSize) {
        this.glassesSize = glassesSize;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    @Override
    public String toString() {
        return "UnsuccessfulSearch [glassesType=" + glassesType + ", glassesSize=" + glassesSize + ", appearance="
                + appearance + ", location=" + location + ", searchDate=" + searchDate + ", os=" + os + ", od=" + od
                + "]";
    }

    
}
