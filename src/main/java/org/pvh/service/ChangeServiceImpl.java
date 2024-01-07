package org.pvh.service;

import java.nio.charset.StandardCharsets;

import org.pvh.model.entity.ChangeValue;
import org.pvh.repository.ChangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

@Service
public class ChangeServiceImpl implements ChangeService{
    Logger logger = LoggerFactory.getLogger(ChangeServiceImpl.class);

    @Autowired
    private ChangeValueRepository changeValueRepository;

    public ChangeServiceImpl(ChangeValueRepository changeValueRepository) {
		this.changeValueRepository = changeValueRepository;
	}

	public String getHashValue(String location) {
        if(!changeValueRepository.findByLocation(location).isPresent()){
            String hashValue = calcHash();
            logger.info("HashValue is null, new hash value is calculated: " + hashValue + " for location: " + location);
            changeValueRepository.save(new ChangeValue(hashValue,location));
            return hashValue;
        }
        return changeValueRepository.findAll().get(0).getHashValue();
    }

    public void setNewHashValue(String location) {
        ChangeValue changeValue = changeValueRepository.findByLocation(location).get();
        if (changeValue != null) {
            changeValueRepository.delete(changeValue);
            logger.info("ChangeValue at location " + location + " has been deleted");
        } else {
            logger.info("No ChangeValue found at location " + location);
        }
        changeValueRepository.save(new ChangeValue(calcHash(),location));
        logger.info("A new hash value has been calculated");
    }

    private String calcHash() {
        // List<Glasses> glasses = this.mainService.findAllAndNotDispensed();
        // if (glasses.isEmpty())
        //     throw new PVHException("Glasses not found!", HttpStatus.NOT_FOUND);
        // StringBuilder concatenatedValues = new StringBuilder();
        // for (Glasses glasses2 : glasses) {
        //     concatenatedValues
        //             .append(Hashing.sha256().hashString(glasses2.toString(), StandardCharsets.UTF_8).toString());
        // }
        // return Hashing.sha256().hashString(concatenatedValues, StandardCharsets.UTF_8).toString();
        return Hashing.sha256().hashString(""+Math.random(), StandardCharsets.UTF_8).toString();
    }

}
