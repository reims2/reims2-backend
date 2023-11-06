package org.pvh.model.mapper;

import org.pvh.model.dto.EyeDTO;
import org.pvh.model.dto.GlassesRequestDTO;
import org.pvh.model.dto.GlassesResponseDTO;
import org.pvh.model.dto.GlassesResponseDTO.DispenseResponseDto;
import org.pvh.model.dto.UnsuccessfulSearchDTO;
import org.pvh.model.entity.Dispense;
import org.pvh.model.entity.Eye;
import org.pvh.model.entity.Glasses;
import org.pvh.model.entity.UnsuccessfulSearch;
import org.pvh.model.enums.AppearanceEnum;
import org.pvh.model.enums.DispenseReasonEnum;
import org.pvh.model.enums.GlassesSizeEnum;
import org.pvh.model.enums.GlassesTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Component
public class UnsuccessfulSearchMapperImpl implements UnsuccessfulSearchMapper {


    private static UnsuccessfulSearchMapperImpl mapper;

    private UnsuccessfulSearchMapperImpl() {}

    public static UnsuccessfulSearchMapperImpl getInstance() {
        if (mapper == null) {
            mapper = new UnsuccessfulSearchMapperImpl();
        }

        return mapper;
    }

    @Override
    public UnsuccessfulSearchDTO unsuccessfulSearchToUnsuccessfulSearchDTO(UnsuccessfulSearch unsuccessfulSearch) {
        if (unsuccessfulSearch == null) {
            return null;
        }

        UnsuccessfulSearchDTO unsuccessfulSearchDTO = new UnsuccessfulSearchDTO(
                unsuccessfulSearch.getGlassesType().name(),
                unsuccessfulSearch.getGlassesSize().name(),
                unsuccessfulSearch.getAppearance().name(),
                unsuccessfulSearch.getLocation(),
                unsuccessfulSearch.getSearchDate(),
                GlassesMapperImpl.getInstance().eyeToEyeDTO(unsuccessfulSearch.getOs()),
                GlassesMapperImpl.getInstance().eyeToEyeDTO(unsuccessfulSearch.getOd()));

        return unsuccessfulSearchDTO;}

    @Override
    public UnsuccessfulSearch unsuccessfulSearchDTOToUnsuccessfulSearch(UnsuccessfulSearchDTO unsuccessfulSearchDTO) {
        if (unsuccessfulSearchDTO == null) {
            return null;
        }
        UnsuccessfulSearch unsuccessfulSearch = new UnsuccessfulSearch();

        unsuccessfulSearch.setOs(GlassesMapperImpl.getInstance().eyeDTOToEye(unsuccessfulSearchDTO.getOs()));
        unsuccessfulSearch.setOd(GlassesMapperImpl.getInstance().eyeDTOToEye(unsuccessfulSearchDTO.getOd()));
        unsuccessfulSearch.setSearchDate(unsuccessfulSearchDTO.getSearchDate());
        unsuccessfulSearch.setLocation(unsuccessfulSearchDTO.getLocation());
        if (unsuccessfulSearchDTO.getGlassesSize() == null)
            return null;
        if (unsuccessfulSearchDTO.getAppearance() == null)
            return null;
        if (unsuccessfulSearchDTO.getGlassesType() == null)
            return null;
        unsuccessfulSearch.setGlassesSize(Enum.valueOf(GlassesSizeEnum.class, unsuccessfulSearchDTO.getGlassesSize()));
        unsuccessfulSearch.setAppearance(Enum.valueOf(AppearanceEnum.class, unsuccessfulSearchDTO.getAppearance()));
        unsuccessfulSearch.setGlassesType(Enum.valueOf(GlassesTypeEnum.class, unsuccessfulSearchDTO.getGlassesType()));


        return unsuccessfulSearch;
    }
}
