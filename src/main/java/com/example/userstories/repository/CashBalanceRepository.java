package com.example.userstories.repository;

import com.example.userstories.entity.CashBalance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashBalanceRepository extends JpaRepository<CashBalance, Integer> {

    CashBalance findByUserId(Integer userId);

    Optional<CashBalance> findFirstByUserId(Integer userId);

}
