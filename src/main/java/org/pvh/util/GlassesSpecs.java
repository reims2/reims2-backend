package org.pvh.util;

import org.pvh.model.entity.Dispense;
import org.pvh.model.entity.Glasses;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.Date;

public class GlassesSpecs {

    public static Specification<Glasses> hasLocation(String location) {
        return (root, query, criteriaBuilder) -> {
            Predicate equalPredicate = criteriaBuilder.equal(root.get("location"), location);
            return equalPredicate;
        };
    }

    public static Specification<Glasses> isDispensed(boolean dispensed) {
        return (root, query, criteriaBuilder) -> {
            Predicate equalPredicate = criteriaBuilder.equal(root.get("dispensed"), dispensed);
            return equalPredicate;
        };
    }

    public static Specification<Glasses> dispensedInRange(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Glasses, Dispense> groupJoin = root.join("dispense");
            return criteriaBuilder.between(groupJoin.<Date>get("modifyDate"), startDate, endDate);
        };
    }

}