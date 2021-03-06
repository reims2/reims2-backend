package org.PVH.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.PVH.model.Dispense;
import org.PVH.model.Glasses;
import org.PVH.repository.DispenseRepository;
import org.PVH.repository.EyeRepository;
import org.PVH.repository.GlassesRepository;
import org.PVH.util.GlassesSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainServiceImpl implements MainService {

    private GlassesRepository glassesRepository;
    private EyeRepository eyeRepository;
    private DispenseRepository dispenseRepository;

    @Autowired
    public MainServiceImpl(GlassesRepository glassesRepository, EyeRepository eyeRepository, DispenseRepository dispenseRepository) {
        this.glassesRepository = glassesRepository;
        this.eyeRepository = eyeRepository;
        this.dispenseRepository = dispenseRepository;
    }

    @Override
    @Transactional
    public Optional<Glasses> findGlassesById(long glassesId) {
        Optional<Glasses> glasses = null;
        try {
            glasses = glassesRepository.findById(glassesId);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
        return glasses;
    }

    @Override
    @Transactional
    public Glasses saveGlasses(Glasses glasses) throws DataAccessException {
        eyeRepository.save(glasses.getOs());
        eyeRepository.save(glasses.getOd());
        Dispense dispense = new Dispense(null);
        // If dispense is already set...
        if (glasses.getDispense() != null)
            dispenseRepository.save(glasses.getDispense());
        else {
            // Else set dispense null in Dispense Table
            dispenseRepository.save(dispense);
            glasses.setDispense(dispense);
        }

        // todo someday make this configurable per location
        int min = 1;
        int max = 10001; // for some reason an SKU 10.001 exists...
        if (glasses.getLocation().equals("sm"))
            min = 5000;
        if (glasses.getLocation().equals("sa"))
            max = 5000;
        return glassesRepository.saveGlassesWithNextPossibleSKU(glasses, min, max);
    }

    @Override
    @Transactional
    public void deleteGlasses(Glasses glasses) throws DataAccessException {
        glassesRepository.delete(glasses);
    }

    @Override
    public Glasses saveGlassesAfterDispense(Glasses glasses) throws DataAccessException {
        dispenseRepository.save(glasses.getDispense());
        return glassesRepository.save(glasses);
    }

    @Override
    public Glasses saveGlassesAfterEdit(Glasses glasses) throws DataAccessException {
        eyeRepository.save(glasses.getOs());
        eyeRepository.save(glasses.getOd());
        return glassesRepository.save(glasses);
    }

    @Override
    public Optional<Glasses> findAllByIdAndLocation(long id, String location) {
        return glassesRepository.findAllByIdAndLocation(id, location);
    }

    @Override
    public Optional<Glasses> findAllBySkuAndLocation(int sku, String location) {
        return glassesRepository.findAllBySkuAndLocation(sku, location);
    }

    @Override
    @Transactional
    public Page<Glasses> findAllGlasses(Pageable pageable) throws DataAccessException {
        return glassesRepository.findAll(pageable);
    }

    @Override
    public Page<Glasses> findByDispensedAndLocation(boolean dispensed, String location, Pageable pageable, Specification<Glasses> spec)
            throws DataAccessException {
        return glassesRepository.findAll(
                Specification.where(spec).and(GlassesSpecs.hasLocation(location)).and(GlassesSpecs.isDispensed(dispensed)), pageable);

    }

    @Override
    public Page<Glasses> findByDispensedAndLocation(boolean dispensed, String location, Pageable pageable) throws DataAccessException {
        return findByDispensedAndLocation(dispensed, location, pageable, null);
    }

    @Override
    public List<Glasses> findDispensedBetween(Date startDate, Date endDate, String location) {
        return glassesRepository.findAll(Specification.where(GlassesSpecs.hasLocation(location)).and(GlassesSpecs.isDispensed(true))
                .and(GlassesSpecs.dispensedInRange(startDate, endDate)));
    }

}
