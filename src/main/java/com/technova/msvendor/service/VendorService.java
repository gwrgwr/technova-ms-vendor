package com.technova.msvendor.service;

import com.technova.msvendor.entity.VendorEntity;
import com.technova.msvendor.mapper.VendorMapper;
import com.technova.msvendor.repository.VendorRepository;
import com.technova.user.dto.Result;
import com.technova.vendor.constants.RabbitVendorConstants;
import com.technova.vendor.dto.VendorCreateDTO;
import com.technova.vendor.dto.VendorLoginRequest;
import com.technova.vendor.dto.VendorResponseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public VendorEntity getVendorByEmail(String email) {
        return vendorRepository.findByEmail(email).orElse(null);
    }

    @RabbitListener(queues = RabbitVendorConstants.VENDOR_SAVE_REQUEST_QUEUE)
    public Result<VendorResponseDTO> saveVendor(VendorCreateDTO dto) {
        VendorEntity vendorEntity = getVendorByEmail(dto.getEmail());
        if (vendorEntity != null) {
            return Result.error(new RuntimeException("Vendor already exists"));
        }
        return Result.success(VendorMapper.toResponseDTO(vendorRepository.save(VendorMapper.toEntity(dto))));
    }

    @RabbitListener(queues = RabbitVendorConstants.VENDOR_LOGIN_REQUEST_QUEUE)
    public Result<VendorResponseDTO> loginVendor(String email) {
        VendorEntity vendorEntity = getVendorByEmail(email);
        if (vendorEntity == null) {
            return Result.error(new RuntimeException("Vendor not found"));
        }
        return Result.success(VendorMapper.toResponseDTO(vendorEntity));
    }
}
