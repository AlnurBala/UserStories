package com.example.userstories.service.impl;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;
import com.example.userstories.entity.Orders;
import com.example.userstories.entity.Stocks;
import com.example.userstories.entity.Users;
import com.example.userstories.exception.NotDataFound;
import com.example.userstories.exception.OrderNotFoundException;
import com.example.userstories.mapper.OrdersMapper;
import com.example.userstories.repository.OrdersRepository;
import com.example.userstories.repository.StocksRepository;
import com.example.userstories.repository.UsersRepository;
import com.example.userstories.service.CashBalanceService;
import com.example.userstories.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final OrdersMapper ordersMapper;
    private final CashBalanceService cashBalanceService;
    private final StocksRepository stocksRepository;

    @Override
    public OrdersResponseDto placeOrder(OrdersRequest ordersRequest, String symbol) {
        // Get the order details from the request
        String orderType = ordersRequest.getOrderType();
        double targetPrice = ordersRequest.getTargetPrice();
        double currentStockPrice = stocksRepository.findPriceBySymbol(symbol);
        int userId = ordersRequest.getUserId();
        int stockId=ordersRequest.getStockId();

        // Check if the order should be fulfilled based on its type and the current stock price
        boolean shouldFillOrder = (orderType.equals("BUY") && currentStockPrice <= targetPrice) ||
                (orderType.equals("SELL") && currentStockPrice >= targetPrice);

        // If the order should be fulfilled, update the user's cash balance
        if (shouldFillOrder) {
            // Calculate the transaction amount based on the order type and current stock price
            double transactionAmount = calculateTransactionAmount(orderType, currentStockPrice);

            // Update the user's cash balance
            if (orderType.equals("BUY")) {
                cashBalanceService.withdraw(userId, transactionAmount);
            } else {
                cashBalanceService.deposit(userId, transactionAmount);
            }
        }
        // Fetch the Users and Stocks entities
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotDataFound("User not found with id: " + userId));
        Stocks stock = stocksRepository.findById(stockId)
                .orElseThrow(() -> new NotDataFound("Stock not found with id: " + stockId));


        // Save the order to the database
        Orders ordersEntity = ordersMapper.fromDto(ordersRequest);
        ordersEntity.setUser(user); // Set the user
        ordersEntity.setStocks(stock); // Set the stock
        ordersEntity.setFilledTimestamp(Timestamp.valueOf(LocalDateTime.now()));  // Ensure timestamp is set correctly
        ordersEntity = ordersRepository.save(ordersEntity);
        return ordersMapper.toDto(ordersEntity);
    }

    private double calculateTransactionAmount(String orderType, double currentStockPrice) {
        // Implement logic to calculate transaction amount based on order type and current stock price
        // For example, you can use fixed quantity or percentage of stock price
        // For demonstration, let's assume a fixed percentage (10%) of the current stock price
        double percentage = 0.10; // 10%
        if (orderType.equals("BUY")) {
            return currentStockPrice * percentage;
        } else {
            return -1 * currentStockPrice * percentage; // For SELL order, transaction amount is negative
        }
    }

    @Override
    public List<OrdersResponseDto> getAllOrders() {
        List<Orders> ordersEntities = ordersRepository.findAll();
        return ordersMapper.toDtos(ordersEntities);
    }

    @Override
    public OrdersResponseDto createOrder(OrdersRequest ordersRequest) {
        Orders ordersEntity = ordersMapper.fromDto(ordersRequest);
        ordersEntity = ordersRepository.save(ordersEntity);
        return ordersMapper.toDto(ordersEntity);
    }

    @Override
    public OrdersResponseDto updateOrder(Integer id, OrdersRequest ordersRequest) {
        Orders newOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        ordersMapper.mapUpdateRequestToEntity(newOrder,ordersRequest);
        newOrder = ordersRepository.save(newOrder);
        return ordersMapper.toDto(newOrder);
    }

    @Override
    public void deleteOrder(Integer id) {
        if (!ordersRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        ordersRepository.deleteById(id);
    }
}
