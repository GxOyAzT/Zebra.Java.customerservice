package com.customerservice.repository;

import com.customerservice.domain.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepo extends JpaRepository<CustomerEntity, UUID> {
}
