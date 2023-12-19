package com.pingwit.parsel.api.controlles;

import com.pingwit.parsel.api.converters.TransactionConverter;
import com.pingwit.parsel.api.dto.TransactionDto;
import com.pingwit.parsel.entity.Transaction;
import com.pingwit.parsel.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    public TransactionController(TransactionService transactionService, TransactionConverter transactionConverter) {
        this.transactionService = transactionService;
        this.transactionConverter = transactionConverter;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> save(@RequestBody TransactionDto dto) {
        ResponseEntity<TransactionDto> response;
        Transaction saved = transactionService.save(transactionConverter.toModel(dto));
        if (saved == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(transactionConverter.toDto(saved), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<TransactionDto> update(@RequestBody TransactionDto dto) {
        Transaction updated = transactionService.update(transactionConverter.toModel(dto));
        return new ResponseEntity<>(transactionConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable Long id){
        return transactionService.findById(id)
                .map(result -> new ResponseEntity<>(transactionConverter.toDto(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }
}
