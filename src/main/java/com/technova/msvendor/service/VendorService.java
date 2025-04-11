package com.technova.msvendor.service;

import com.technova.Result;
import com.technova.msvendor.entity.VendorEntity;
import com.technova.msvendor.mapper.VendorMapper;
import com.technova.msvendor.repository.VendorRepository;
import com.technova.user.dto.PhoneNumber;
import com.technova.vendor.constants.RabbitVendorConstants;
import com.technova.vendor.dto.VendorCreateDTO;
import com.technova.vendor.dto.VendorFindDTO;
import com.technova.vendor.dto.VendorLoginRequest;
import com.technova.vendor.dto.VendorResponseDTO;
import com.technova.vendor.exceptions.VendorAlreadyExistsException;
import com.technova.vendor.exceptions.VendorNotFoundException;
import org.bson.types.ObjectId;
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

    public VendorEntity getVendorByCompanyName(String companyName) {
        return vendorRepository.findByCompanyName(companyName).orElse(null);
    }

    public VendorEntity getVendorByCompanyRegistrationNumber(String companyRegistrationNumber) {
        return vendorRepository.findByCompanyRegistrationNumber(companyRegistrationNumber).orElse(null);
    }

    public VendorEntity getVendorByPhoneNumber(PhoneNumber phoneNumber) {
        return vendorRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }

    @RabbitListener(queues = RabbitVendorConstants.VENDOR_SAVE_REQUEST_QUEUE)
    public Result<VendorResponseDTO> saveVendor(VendorCreateDTO dto) {
        System.out.println(dto.getEmail());
        if (getVendorByEmail(dto.getEmail()) != null || getVendorByCompanyName(dto.getCompanyName())  != null || getVendorByCompanyRegistrationNumber(dto.getCompanyRegistrationNumber()) != null || getVendorByPhoneNumber(dto.getPhoneNumber()) != null) {
            return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
        }
        return Result.success(VendorMapper.toResponseDTO(vendorRepository.save(VendorMapper.toEntity(dto))));
    }

    @RabbitListener(queues = RabbitVendorConstants.VENDOR_LOGIN_REQUEST_QUEUE)
    public Result<VendorResponseDTO> loginVendor(String email) {
        VendorEntity vendorEntity = getVendorByEmail(email);
        if (vendorEntity == null) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        return Result.success(VendorMapper.toResponseDTO(vendorEntity));
    }

    @RabbitListener(queues = RabbitVendorConstants.VENDOR_FIND_BY_ID_REQUEST_QUEUE)
    public Result<VendorFindDTO> findVendorById(String id) {
        if (!ObjectId.isValid(id)) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        VendorEntity vendorEntity = vendorRepository.findById(new ObjectId(id)).orElse(null);
        if (vendorEntity == null) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        return Result.success(VendorMapper.toFindDTO(vendorEntity));
    }
}
