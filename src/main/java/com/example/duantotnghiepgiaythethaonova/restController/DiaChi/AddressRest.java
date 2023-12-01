package com.spring.beebeta.rest;

import com.spring.beebeta.request.AddressKhachLe;
import com.spring.beebeta.request.AddressRequest;
import com.spring.beebeta.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/address")
public class AddressRest {
    @Autowired
    AddressService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressByCustomer(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getAddressByCustomer(id));
    }
    @GetMapping("/getBill/{code}")
    public ResponseEntity<?> getBill(@PathVariable("code") String code){
        return ResponseEntity.ok(service.getAddressByBill(code));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getAddressById(id));
    }
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AddressKhachLe addressKhachLe){
        return ResponseEntity.ok(service.add(addressKhachLe));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addAddresss(@RequestBody AddressRequest request){
        return ResponseEntity.ok(service.addAddress(request));
    }
}
