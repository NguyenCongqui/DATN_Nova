package com.example.duantotnghiepgiaythethaonova.restController.DiaChi;


import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/address")
public class AddressRest {
    @Autowired
    DiaChiService diaChiService;

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getAddressByCustomer(@PathVariable("id") Integer id){
//        return ResponseEntity.ok(diaChiService.getAddressByCustomer(id));
//    }
//    @GetMapping("/getBill/{code}")
//    public ResponseEntity<?> getBill(@PathVariable("code") String code){
//        return ResponseEntity.ok(service.getAddressByBill(code));
//    }
//    @GetMapping("/get/{id}")
//    public ResponseEntity<?> getAddressById(@PathVariable("id") Integer id){
//        return ResponseEntity.ok(service.getAddressById(id));
//    }
//    @PostMapping()
//    public ResponseEntity<?> add(@RequestBody AddressKhachLe addressKhachLe){
//        return ResponseEntity.ok(service.add(addressKhachLe));
//    }
//    @PostMapping("/add")
//    public ResponseEntity<?> addAddresss(@RequestBody DiaChiDTO diaChiDTO){
//        return ResponseEntity.ok(diaChiService.addAddress(diaChiDTO));
//    }
}
