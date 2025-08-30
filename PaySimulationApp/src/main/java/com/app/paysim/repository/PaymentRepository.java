package com.app.paysim.repository;

import com.app.paysim.entities.PaymentEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>, JpaSpecificationExecutor<PaymentEntity> {
    // Method to find all PaymentEntity records matching the given specification with pagination
    @Override
    Page<PaymentEntity> findAll(Specification<PaymentEntity> spec, Pageable pageable);
}