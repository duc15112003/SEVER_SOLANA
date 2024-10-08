package com.solana.com.controller;
import com.solana.com.service.SolanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/solana")
public class SolanaController {
    @Autowired
    private SolanaService solanaService;

//    @GetMapping("/balance/{address}")
//    public ResponseEntity<String> getBalance(@PathVariable String address) {
//        String balance = solanaService.getBalance(address);
//        return ResponseEntity.ok(balance);
//    }
    @GetMapping("/send")
    public String sendTransaction(@RequestParam String fromPrivateKey,
                                  @RequestParam String toPublicKey,
                                  @RequestParam Double amount) {
        System.out.println(fromPrivateKey);
        return solanaService.createAndSendTransaction(fromPrivateKey, toPublicKey, amount);
    }
}
