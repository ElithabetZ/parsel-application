package com.pingwit.parsel.api.controlles;

import com.pingwit.parsel.api.converters.AddressConverter;
import com.pingwit.parsel.api.dto.AddressDto;
import com.pingwit.parsel.entity.Address;
import com.pingwit.parsel.entity.enums.Shortcode;
import com.pingwit.parsel.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;

    public AddressController(AddressService addressService, AddressConverter addressConverter) {
        this.addressService = addressService;
        this.addressConverter = addressConverter;
    }

    @PostMapping
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto dto) {
        ResponseEntity<AddressDto> response;
        Address saved = addressService.save(addressConverter.toModel(dto));
        if (saved == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(addressConverter.toDto(saved), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<AddressDto> update(@RequestBody AddressDto dto) {
        Address updated = addressService.update(addressConverter.toModel(dto));
        return new ResponseEntity<>(addressConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable Long id) {
        return addressService.findById(id)
                .map(result -> new ResponseEntity<>(addressConverter.toDto(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    @GetMapping("/search/shortCode")
    public ResponseEntity<List<AddressDto>> getByShortCodes(@RequestParam List<String> codes) {
        return new ResponseEntity<>(
                addressService.findByShortCodes(codes.stream()
                        .map(Shortcode::valueOf).collect(Collectors.toList())).stream()
                        .map(result -> addressConverter.toDto(result))
                        .collect(Collectors.toList()), HttpStatus.OK
        );
    }
}
