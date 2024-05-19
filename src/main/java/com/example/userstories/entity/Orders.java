package com.example.userstories.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders", schema = "story")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Column(name = "order_type")
    private String orderType;
    @Column(name = "target_price")
    private Double targetPrice;
    @Column(name = "filled_timestamp")
    private Timestamp filledTimestamp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stocks stocks;
}

