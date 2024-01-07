package org.pvh.model.mapper;

import org.pvh.model.dto.UnsuccessfulSearchDTO;
import org.pvh.model.entity.UnsuccessfulSearch;
import org.pvh.model.enums.BalLensEnum;
import org.pvh.model.enums.GlassesTypeEnum;
import org.springframework.stereotype.Component;

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
                unsuccessfulSearch.getBalLens().name(),
                unsuccessfulSearch.getIncreaseSearchTolerance(),
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
        if (unsuccessfulSearchDTO.getGlassesType() == null)
            return null;
        if (unsuccessfulSearchDTO.getBalLens() == null)
            return null;
        unsuccessfulSearch.setBalLens(Enum.valueOf(BalLensEnum.class, unsuccessfulSearchDTO.getBalLens()));
        unsuccessfulSearch.setGlassesType(Enum.valueOf(GlassesTypeEnum.class, unsuccessfulSearchDTO.getGlassesType()));


        return unsuccessfulSearch;
    }
}
