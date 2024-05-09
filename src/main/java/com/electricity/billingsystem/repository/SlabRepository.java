package com.electricity.billingsystem.repository;

import com.electricity.billingsystem.entity.SlabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlabRepository extends JpaRepository<SlabEntity, Long> {
    @Query("SELECT s FROM SlabEntity s WHERE s.unitFrom >= :unitFrom AND s.unitTo <= :unitTo")
    List<SlabEntity> findByUnitFromGreaterThanOrEqualAndUnitToLessThanOrEqual(Integer unitFrom, Integer unitTo);

    List<SlabEntity> findByUnitFromAndUnitTo(Integer unitFrom, Integer unitTo);
}