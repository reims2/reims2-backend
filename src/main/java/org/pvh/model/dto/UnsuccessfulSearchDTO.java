package org.pvh.model.dto;

import java.util.Date;

import org.pvh.model.enums.AppearanceEnum;
import org.pvh.model.enums.GlassesSizeEnum;
import org.pvh.model.enums.GlassesTypeEnum;
import org.pvh.model.validator.IEnumValidator;

import jakarta.validation.constraints.Pattern;

public class UnsuccessfulSearchDTO {
    @IEnumValidator(enumClass = GlassesTypeEnum.class)
    private String glassesType;
    @IEnumValidator(enumClass = GlassesSizeEnum.class)
    private String glassesSize;
    @IEnumValidator(enumClass = AppearanceEnum.class)
    private String appearance;
    @Pattern(regexp = "sm|sa")
    private String location;

    private Date searchDate;

    private EyeDTO os;
    private EyeDTO od;

    


    public UnsuccessfulSearchDTO(@IEnumValidator(enumClass = GlassesTypeEnum.class) String glassesType,
            @IEnumValidator(enumClass = GlassesSizeEnum.class) String glassesSize,
            @IEnumValidator(enumClass = AppearanceEnum.class) String appearance,
            @Pattern(regexp = "sm|sa") String location, Date searchDate, EyeDTO os, EyeDTO od) {
        this.glassesType = glassesType;
        this.glassesSize = glassesSize;
        this.appearance = appearance;
        this.location = location;
        this.searchDate = searchDate;
        this.os = os;
        this.od = od;
    }
    public String getGlassesType() {
        return glassesType;
    }
    public void setGlassesType(String glassesType) {
        this.glassesType = glassesType;
    }
    public String getGlassesSize() {
        return glassesSize;
    }
    public void setGlassesSize(String glassesSize) {
        this.glassesSize = glassesSize;
    }
    public String getAppearance() {
        return appearance;
    }
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getSearchDate() {
        return searchDate;
    }
    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
    public EyeDTO getOs() {
        return os;
    }
    public void setOs(EyeDTO os) {
        this.os = os;
    }
    public EyeDTO getOd() {
        return od;
    }
    public void setOd(EyeDTO od) {
        this.od = od;
    }

    
}
