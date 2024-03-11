package com.hierarchy.project.repository;

import com.hierarchy.project.models.StaffModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<StaffModel, Long> {
    @Query(nativeQuery = true, value = "" +
            "SELECT * FROM staff_hierarchy sh where sh.id = :id")
    StaffModel getStaff(@Param("id") Long id);
}