package com.technova.msvendor.repository;

import com.technova.msvendor.entity.VendorEntity;
import com.technova.user.dto.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, String> {
    Optional<VendorEntity> findByEmail(String email);

    Optional<VendorEntity> findByCompanyName(String companyName);

    Optional<VendorEntity> findByCompanyRegistrationNumber(String companyRegistrationNumber);

    Optional<VendorEntity> findByPhoneNumber(PhoneNumber phoneNumber);
}
