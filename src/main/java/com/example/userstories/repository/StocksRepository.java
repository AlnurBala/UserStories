package com.example.userstories.repository;

import com.example.userstories.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StocksRepository extends JpaRepository<Stocks,Integer> {
    @Query("SELECT s.price FROM Stocks s WHERE s.symbol = :symbol")
    Double findPriceBySymbol(@Param("symbol") String symbol);

}
