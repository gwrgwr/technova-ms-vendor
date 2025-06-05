package com.technova.msvendor.service;

import com.technova.Result;
import com.technova.msvendor.entity.VendorEntity;
import com.technova.user.dto.PhoneNumber;
import com.technova.vendor.dto.VendorCreateDTO;
import com.technova.vendor.dto.VendorFindDTO;
import com.technova.vendor.dto.VendorResponseDTO;
import com.technova.vendor.dto.VendorUpdateDTO;

public interface VendorService {
    VendorEntity getVendorByEmail(String email);

    VendorEntity getVendorByCompanyName(String companyName);

    VendorEntity getVendorByCompanyRegistrationNumber(String companyRegistrationNumber);

    VendorEntity getVendorByPhoneNumber(PhoneNumber phoneNumber);

    Result<VendorResponseDTO> saveVendor(VendorCreateDTO dto);

    Result<VendorResponseDTO> loginVendor(String email);

    Result<VendorFindDTO> findVendorById(String id);

    Result<VendorResponseDTO> updateVendor(VendorUpdateDTO dto);

    void deleteVendor(String id);

    void softDeleteVendor(String id);

    Result<VendorResponseDTO> activateVendor(String id);

    void addProductToVendor(String vendorId, String productId);
}
