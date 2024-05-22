package com.example.userstories.service.impl;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;
import com.example.userstories.entity.Orders;
import com.example.userstories.entity.Stocks;
import com.example.userstories.entity.Users;
import com.example.userstories.enumeration.OrderType;
import com.example.userstories.exception.NotDataFound;
import com.example.userstories.exception.OrderNotFoundException;
import com.example.userstories.mapper.OrdersMapper;
import com.example.userstories.repository.OrdersRepository;
import com.example.userstories.repository.StocksRepository;
import com.example.userstories.repository.UsersRepository;
import com.example.userstories.service.CashBalanceService;
import com.example.userstories.service.OrdersService;
import jakarta.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final OrdersMapper ordersMapper;
    private final CashBalanceService cashBalanceService;
    private final StocksRepository stocksRepository;
    private final EmailService emailService; // Inject EmailService


    @Override
    public OrdersResponseDto placeOrder(OrdersRequest ordersRequest, String symbol) {
        int userId = ordersRequest.userId();
        int stockId = ordersRequest.stockId();
        // Fetch the Users and Stocks entities
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotDataFound("User not found with id: " + userId));
        Stocks stock = stocksRepository.findById(stockId)
                .orElseThrow(() -> new NotDataFound("Stock not found with id: " + stockId));

        if (ordersRequest.orderType().equals(OrderType.BUY)){
            placeOrderBuy(ordersRequest, symbol);
        }
        else if (ordersRequest.orderType().equals(OrderType.SELL)){
            placeOrderSell(ordersRequest, symbol);
        }
        Orders ordersEntity = ordersMapper.fromDto(ordersRequest);
        ordersEntity.setUser(user); // Set the user
        ordersEntity.setStocks(stock); // Set the stock
        ordersEntity.setFilledTimestamp(Timestamp.valueOf(LocalDateTime.now()));  // Ensure timestamp is set correctly
        ordersEntity = ordersRepository.save(ordersEntity);

        // Send email notification
        try {
            String subject = "Order Filled: " + ordersEntity.getOrderType();
            String body = String.format("Your order to %s %s at %s has been filled.",
                    ordersEntity.getOrderType().toLowerCase(),
                    stock.getSymbol(),
                    ordersEntity.getFilledTimestamp());
            emailService.sendOrderFilledEmail(user.getEmailAddress(), subject, body);
        } catch (MessagingException e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
        }

        return ordersMapper.toDto(ordersEntity);
    }
    private OrdersResponseDto placeOrderBuy(OrdersRequest ordersRequest, String symbol){
        String orderType = ordersRequest.orderType();
        double targetPrice = ordersRequest.targetPrice();
        double currentStockPrice = stocksRepository.findPriceBySymbol(symbol);
        if(currentStockPrice <= targetPrice){
            double transactionAmount = calculateTransactionAmount(orderType, currentStockPrice);
            cashBalanceService.withdraw(ordersRequest.userId(), transactionAmount);
            sendOrderFilledEmail(ordersRequest.userId(), OrderType.BUY.toString(), symbol);
        }
        Orders ordersEntity = ordersMapper.fromDto(ordersRequest);
        ordersEntity = ordersRepository.save(ordersEntity);
        return ordersMapper.toDto(ordersEntity);
    }
    private OrdersResponseDto placeOrderSell(OrdersRequest ordersRequest, String symbol){
        String orderType = ordersRequest.orderType();
        double targetPrice = ordersRequest.targetPrice();
        double currentStockPrice = stocksRepository.findPriceBySymbol(symbol);
        if(currentStockPrice >= targetPrice){
            double transactionAmount = calculateTransactionAmount(orderType, currentStockPrice);
            cashBalanceService.deposit(ordersRequest.userId(), transactionAmount);
            sendOrderFilledEmail(ordersRequest.userId(), OrderType.SELL.toString(), symbol);

        }
        Orders ordersEntity = ordersMapper.fromDto(ordersRequest);
        ordersEntity = ordersRepository.save(ordersEntity);
        return ordersMapper.toDto(ordersEntity);
    }

    private void sendOrderFilledEmail(int userId, String orderType, String symbol) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotDataFound("User not found with id: " + userId));
        String subject = "Order Filled: " + orderType;
        String body = String.format("Your order to %s %s has been filled.", orderType.toLowerCase(), symbol);
        try {
            emailService.sendOrderFilledEmail(user.getEmailAddress(), subject, body);
        } catch (MessagingException e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
        }
    }

    private double calculateTransactionAmount(String orderType, double currentStockPrice) {
        // Implement logic to calculate transaction amount based on order type and current stock price
        // For example, you can use fixed quantity or percentage of stock price
        // For demonstration, let's assume a fixed percentage (10%) of the current stock price
        double percentage = 0.10; // 10%
        if (orderType.equals(OrderType.BUY)) {
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

        ordersMapper.mapUpdateRequestToEntity(newOrder, ordersRequest);
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
