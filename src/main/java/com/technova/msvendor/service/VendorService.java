package com.technova.msvendor.service;

import com.technova.msvendor.entity.VendorEntity;
import com.technova.msvendor.repository.VendorRepository;
import com.technova.vendor.constants.RabbitVendorConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @RabbitListener(queues = RabbitVendorConstants.VENDOR_SAVE_REQUEST_QUEUE)
    public void saveVendor(VendorEntity vendor) {
        vendorRepository.save(vendor);
    }

//    teste
}
