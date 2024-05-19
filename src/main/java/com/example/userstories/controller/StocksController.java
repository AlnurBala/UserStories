package com.example.userstories.controller;

import com.example.userstories.dto.request.StocksRequest;
import com.example.userstories.dto.response.StocksResponseDto;
import com.example.userstories.service.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StocksController {
    private final StocksService stocksService;

    @GetMapping
    public List<StocksResponseDto> getAllStocks() {
        return stocksService.getAllStocks();
    }

    @PostMapping
    public ResponseEntity<StocksResponseDto> createCategory(@RequestBody StocksRequest stocksRequest) {
        return new ResponseEntity<>(stocksService.createStock(stocksRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public StocksResponseDto updateCategory(@PathVariable Integer id, @RequestBody StocksRequest stocksRequest) {
        return stocksService.updateStock(id, stocksRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteStockById(@PathVariable Integer id) {
        stocksService.deleteStock(id);
    }

}
