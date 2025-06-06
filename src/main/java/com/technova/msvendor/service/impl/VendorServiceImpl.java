package com.technova.msvendor.service.impl;

import com.technova.Result;
import com.technova.exceptions.BaseException;
import com.technova.msvendor.entity.VendorEntity;
import com.technova.msvendor.mapper.VendorMapper;
import com.technova.msvendor.repository.VendorRepository;
import com.technova.msvendor.service.VendorService;
import com.technova.user.dto.PhoneNumber;
import com.technova.vendor.constants.RabbitVendorConstants;
import com.technova.vendor.dto.VendorCreateDTO;
import com.technova.vendor.dto.VendorFindDTO;
import com.technova.vendor.dto.VendorResponseDTO;
import com.technova.vendor.dto.VendorUpdateDTO;
import com.technova.vendor.enums.VendorStatus;
import com.technova.vendor.exceptions.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public VendorEntity getVendorByEmail(String email) {
        return vendorRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public VendorEntity getVendorByCompanyName(String companyName) {
        return vendorRepository.findByCompanyName(companyName).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public VendorEntity getVendorByCompanyRegistrationNumber(String companyRegistrationNumber) {
        return vendorRepository.findByCompanyRegistrationNumber(companyRegistrationNumber).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public VendorEntity getVendorByPhoneNumber(PhoneNumber phoneNumber) {
        return vendorRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }

    @Override
    @Transactional
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_SAVE_REQUEST_QUEUE)
    public Result<VendorResponseDTO> saveVendor(VendorCreateDTO dto) {
        System.out.println(dto.getEmail());
        if (getVendorByEmail(dto.getEmail()) != null || getVendorByCompanyName(dto.getCompanyName()) != null || getVendorByCompanyRegistrationNumber(dto.getCompanyRegistrationNumber()) != null || getVendorByPhoneNumber(dto.getPhoneNumber()) != null) {
            return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
        }
        return Result.success(VendorMapper.toResponseDTO(vendorRepository.save(VendorMapper.toEntity(dto))));
    }

    @Override
    @Transactional
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_LOGIN_REQUEST_QUEUE)
    public Result<VendorResponseDTO> loginVendor(String email) {
        VendorEntity vendorEntity = getVendorByEmail(email);
        if (vendorEntity == null) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        return Result.success(VendorMapper.toResponseDTO(vendorEntity));
    }

    @Override
    @Transactional(readOnly = true)
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_FIND_BY_ID_REQUEST_QUEUE)
    public Result<VendorFindDTO> findVendorById(String id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElse(null);
        if (vendorEntity == null) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        return Result.success(VendorMapper.toFindDTO(vendorEntity));
    }

    @Override
    @Transactional
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_UPDATE_REQUEST_QUEUE)
    public Result<VendorResponseDTO> updateVendor(VendorUpdateDTO dto) {
        VendorEntity vendorEntity = vendorRepository.findById(dto.getId()).orElse(null);
        if (vendorEntity == null) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        if (dto.getAddress() != null) {
            if (dto.getAddress().equals(vendorEntity.getAddress())) {
                return Result.error(new VendorAddressAlreadyRegisteredException("Address already registered"));
            }
            if (getVendorByPhoneNumber(dto.getPhoneNumber()) != null) {
                return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
            }
            vendorEntity.setAddress(dto.getAddress());
            this.vendorRepository.save(vendorEntity);
            return Result.success(VendorMapper.toResponseDTO(vendorEntity));
        }
        if (dto.getCompanyName() != null) {
            if (dto.getCompanyName().equals(vendorEntity.getCompanyName())) {
                return Result.error(new VendorCompanyNameAlreadyRegisteredException("Company name already registered"));
            }
            if (getVendorByCompanyName(dto.getCompanyName()) != null) {
                return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
            }
            vendorEntity.setCompanyName(dto.getCompanyName());
            this.vendorRepository.save(vendorEntity);
            return Result.success(VendorMapper.toResponseDTO(vendorEntity));
        }
        if (dto.getCompanyRegistrationNumber() != null) {
            if (dto.getCompanyRegistrationNumber().equals(vendorEntity.getCompanyRegistrationNumber())) {
                return Result.error(new VendorCompanyRegistrationNumberAlreadyRegisteredException("Company registration number already registered"));
            }
            if (getVendorByCompanyRegistrationNumber(dto.getCompanyRegistrationNumber()) != null) {
                return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
            }
            vendorEntity.setCompanyRegistrationNumber(dto.getCompanyRegistrationNumber());
            this.vendorRepository.save(vendorEntity);
            return Result.success(VendorMapper.toResponseDTO(vendorEntity));
        }
        if (dto.getEmail() != null) {
            if (dto.getEmail().equals(vendorEntity.getEmail())) {
                return Result.error(new VendorEmailAlreadyRegisteredException("Email already registered"));
            }
            if (getVendorByEmail(dto.getEmail()) != null) {
                return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
            }
            vendorEntity.setEmail(dto.getEmail());
            this.vendorRepository.save(vendorEntity);
            return Result.success(VendorMapper.toResponseDTO(vendorEntity));
        }
        if (dto.getPassword() != null) {
            if (dto.getPassword().equals(vendorEntity.getPassword())) {
                return Result.error(new VendorPasswordAlreadyRegisteredException("Password already registered"));
            }
            vendorEntity.setPassword(dto.getPassword());
            this.vendorRepository.save(vendorEntity);
            return Result.success(VendorMapper.toResponseDTO(vendorEntity));
        }
        if (dto.getPhoneNumber() != null) {
            if (dto.getPhoneNumber().equals(vendorEntity.getPhoneNumber())) {
                return Result.error(new VendorPhoneNumberAlreadyRegisteredException("Phone number already registered"));
            }
            if (getVendorByPhoneNumber(dto.getPhoneNumber()) != null) {
                return Result.error(new VendorAlreadyExistsException("Vendor already exists"));
            }
            vendorEntity.setPhoneNumber(dto.getPhoneNumber());
            this.vendorRepository.save(vendorEntity);
            return Result.success(VendorMapper.toResponseDTO(vendorEntity));
        }
        return Result.error(new BaseException("Invalid update request"));
    }

    @Override
    @Transactional
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_DELETE_REQUEST_QUEUE)
    public void deleteVendor(String id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElse(null);
        if (vendorEntity == null) {
            return;
        }
        vendorEntity.setStatus(VendorStatus.INACTIVE);
        vendorRepository.save(vendorEntity);
    }

    @Override
    @Transactional
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_SOFT_DELETE_REQUEST_QUEUE)
    public void softDeleteVendor(String id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElse(null);
        if (vendorEntity == null) {
            return;
        }
        vendorEntity.setStatus(VendorStatus.SUSPENDED);
        vendorRepository.save(vendorEntity);
    }

    @Override
    @Transactional
    @RabbitListener(queues = RabbitVendorConstants.VENDOR_ACTIVE_REQUEST_QUEUE)
    public Result<VendorResponseDTO> activateVendor(String id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).orElse(null);
        if (vendorEntity == null) {
            return Result.error(new VendorNotFoundException("Vendor not found"));
        }
        vendorEntity.setStatus(VendorStatus.ACTIVE);
        vendorRepository.save(vendorEntity);
        return Result.success(VendorMapper.toResponseDTO(vendorEntity));
    }

    @Override
//    @RabbitListener(queues = "RabbitVendorConstants.VENDOR_ADD_PRODUCT_TO_VENDOR_REQUEST_QUEUE")
    public void addProductToVendor(String vendorId, String productId) {
// TODO
    }

}
