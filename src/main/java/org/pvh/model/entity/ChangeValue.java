package org.pvh.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "changevalue")
public class ChangeValue{
    @Id
    @Column(name = "hash_value")
    private String hashValue;

    public ChangeValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public ChangeValue() {

    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    
}
