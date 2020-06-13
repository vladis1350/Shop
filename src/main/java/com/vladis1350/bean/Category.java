package com.vladis1350.bean;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Builder
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;

    public Category() {
    }

    public Category(String productCategory) {
        this.nameCategory = productCategory;
    }

    public Category(Long id, String productCategory) {
        this.id = id;
        this.nameCategory = productCategory;
    }

}
