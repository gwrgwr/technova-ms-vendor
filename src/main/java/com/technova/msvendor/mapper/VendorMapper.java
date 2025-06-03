package com.technova.msvendor.mapper;

import com.technova.msvendor.entity.VendorEntity;
import com.technova.vendor.dto.VendorCreateDTO;
import com.technova.vendor.dto.VendorFindDTO;
import com.technova.vendor.dto.VendorResponseDTO;

public class VendorMapper {
    public static VendorEntity toEntity (VendorCreateDTO dto) {
        return new VendorEntity(dto.getCompanyName(), dto.getCompanyType(), dto.getCompanyRegistrationNumber(), dto.getName(), dto.getUsername(), dto.getEmail(), dto.getPassword(), "VENDOR", dto.getAddress(), dto.getPhoneNumber());
    }

    public static VendorResponseDTO toResponseDTO (VendorEntity entity) {
        VendorResponseDTO dto = new VendorResponseDTO();
        dto.setId(entity.getId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }

    public static VendorFindDTO toFindDTO (VendorEntity entity) {
        VendorFindDTO dto = new VendorFindDTO();
        dto.setId(entity.getId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setCompanyType(entity.getCompanyType());
        dto.setCompanyRegistrationNumber(entity.getCompanyRegistrationNumber());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
