package org.pvh.model.dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.pvh.model.entity.Glasses} entity
 */
public class GlassesDTO implements Serializable {
    private Long id;
    private String glassesType;
    private String glassesSize;
    private String appearance;
    @Pattern(regexp = "sm|sa")
    private String location;
    private EyeDTO os;
    private EyeDTO od;

    public GlassesDTO(){}

    public GlassesDTO(Long id, String glassesType, String glassesSize, String appearance, String location, EyeDTO os, EyeDTO od) {
        this.id = id;
        this.glassesType = glassesType;
        this.glassesSize = glassesSize;
        this.appearance = appearance;
        this.location = location;
        this.os = os;
        this.od = od;
    }

    public GlassesDTO(String glassesType, String glassesSize, String appearance, String location, EyeDTO os, EyeDTO od) {
        this.glassesType = glassesType;
        this.glassesSize = glassesSize;
        this.appearance = appearance;
        this.location = location;
        this.os = os;
        this.od = od;
    }

    public String getGlassesType() {
        return glassesType;
    }

    public String getGlassesSize() {
        return glassesSize;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getLocation() {
        return location;
    }


    public EyeDTO getOs() {
        return os;
    }

    public EyeDTO getOd() {
        return od;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlassesDTO entity = (GlassesDTO) o;
        return
            Objects.equals(this.glassesType, entity.glassesType) &&
            Objects.equals(this.glassesSize, entity.glassesSize) &&
            Objects.equals(this.appearance, entity.appearance) &&
            Objects.equals(this.location, entity.location) &&
            Objects.equals(this.os, entity.os) &&
            Objects.equals(this.od, entity.od);
    }

    @Override
    public int hashCode() {
        return Objects.hash( glassesType, glassesSize, appearance, location,os, od);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "glassesType = " + glassesType + ", " +
            "glassesSize = " + glassesSize + ", " +
            "appearance = " + appearance + ", " +
            "location = " + location + ", " +
            "os = " + os + ", " +
            "od = " + od + ")";
    }
}
