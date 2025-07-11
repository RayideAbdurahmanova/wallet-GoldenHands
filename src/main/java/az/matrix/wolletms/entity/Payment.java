package az.matrix.wolletms.entity;


import az.matrix.wolletms.enumerated.Currency;
import az.matrix.wolletms.enumerated.PaymentMethod;
import az.matrix.wolletms.enumerated.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    @Column(nullable = false)
    LocalDateTime date;
    @Column(nullable = false)
    LocalDateTime updated;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Currency currency;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PaymentMethod paymentMethod;
}
