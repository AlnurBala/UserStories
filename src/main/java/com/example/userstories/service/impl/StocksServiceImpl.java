package com.example.userstories.service.impl;

import com.example.userstories.dto.request.StocksRequest;
import com.example.userstories.dto.response.StocksResponseDto;
import com.example.userstories.entity.Stocks;
import com.example.userstories.mapper.StocksMapper;
import com.example.userstories.repository.StocksRepository;
import com.example.userstories.service.StocksService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final StocksRepository stocksRepository;
    private final StocksMapper stocksMapper;

    @Override
    public List<StocksResponseDto> getAllStocks() {
        var stockEntity = stocksRepository.findAll();
        return stocksMapper.toDTOs(stockEntity);
    }

    @Override
    public StocksResponseDto createStock(StocksRequest stocksRequest) {
        var stockEntity = stocksMapper.fromDTO(stocksRequest);
        stockEntity = stocksRepository.save(stockEntity);
        return stocksMapper.toDTO(stockEntity);
    }

    @Override
    public StocksResponseDto updateStock(Integer id, StocksRequest stocksRequest) {
        var newStock = stocksRepository.findById(id).orElse(new Stocks());
        stocksMapper.mapUpdateRequestToEntity(newStock, stocksRequest);
        newStock = stocksRepository.save(newStock);
        return stocksMapper.toDTO(newStock);
    }

    @Override
    public void deleteStock(Integer id) {
        stocksRepository.deleteById(id);
    }

}

