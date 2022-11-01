package com.vladis1350.shop.repositories;

import com.vladis1350.dao.DatabaseConnection;
import com.vladis1350.shop.bean.Category;
import com.vladis1350.shop.mappers.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.MyRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getById(Long id);
}
