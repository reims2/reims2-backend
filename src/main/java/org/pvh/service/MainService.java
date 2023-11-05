package org.pvh.service;

import org.pvh.error.NoSkusLeftException;
import org.pvh.model.entity.Glasses;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MainService {

    Optional<Glasses> findGlassesById(long glassesId);

    Glasses saveGlasses(Glasses glasses) throws DataAccessException, org.pvh.error.NoSkusLeftException, NoSkusLeftException;

    void deleteGlasses(Glasses glasses) throws DataAccessException;

    Glasses saveGlassesAfterDispense(Glasses glasses) throws DataAccessException;

    Glasses saveGlassesAfterEdit(Glasses glasses) throws DataAccessException;

    Optional<Glasses> findAllByIdAndLocation(long id, String location);

    Optional<Glasses> findAllBySkuAndLocation(int sku, String location);

    Page<Glasses> findAllGlasses(Pageable pageable) throws DataAccessException;


    Page<Glasses> findByDispensedAndLocation(boolean dispensed, String location, Pageable pageable, Specification<Glasses> spec)
            throws DataAccessException;

    Page<Glasses> findByDispensedAndLocation(boolean dispensed, String location, Pageable pageable) throws DataAccessException;

    List<Glasses> findByDispensedAndLocation(boolean dispensed, String location) throws DataAccessException;

    List<Glasses> findDispensedBetween(Date startDate, Date endDate, String location);

    List<Glasses> findAllByLocationAndNotDispensed(String location);

    List<Glasses> findAllAndNotDispensed();
}
