package com.postgresaccess.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

import com.postgresaccess.exception.ResourceNotFoundException;
import com.postgresaccess.model.Txn;
import com.postgresaccess.repository.TxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class TxnController {
    @Autowired
    private TxnRepository txnRepository;

    @GetMapping("/txn")
    public List<Txn> getAllTxn() {
        return txnRepository.findAll();
    }

    @GetMapping("/txn/{id}")
    public ResponseEntity<Txn> getTxnById(@PathVariable(value = "id") Long txnId)
        throws ResourceNotFoundException {
        Txn txn = txnRepository.findById(txnId)
          .orElseThrow(() -> new ResourceNotFoundException("Txn not found for this id :: " + txnId));
        return ResponseEntity.ok().body(txn);
    }
    
    @PostMapping("/txn")
    public Txn createTxn(
            //@Valid
            @RequestBody Txn txn) {
        return txnRepository.save(txn);
    }

    @PutMapping("/txn/{id}")
    public ResponseEntity<Txn> updateTxn(@PathVariable(value = "id") Long txnId,
         //@Valid
         @RequestBody Txn txnDetails) throws ResourceNotFoundException {
        Txn txn = txnRepository.findById(txnId)
        .orElseThrow(() -> new ResourceNotFoundException("Txn not found for this id :: " + txnId));

        txn.setTxnDate(txnDetails.getTxnDate());
        txn.setTxnType(txnDetails.getTxnType());
        txn.setComments(txnDetails.getComments());
        txn.setTxnAmount(txnDetails.getTxnAmount());
        final Txn updatedTxn = txnRepository.save(txn);
        return ResponseEntity.ok(updatedTxn);
    }

    @DeleteMapping("/txn/{id}")
    public Map<String, Boolean> deleteTxn(@PathVariable(value = "id") Long txnId)
         throws ResourceNotFoundException {
        Txn txn = txnRepository.findById(txnId)
       .orElseThrow(() -> new ResourceNotFoundException("Txn not found for this id :: " + txnId));

        txnRepository.delete(txn);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}