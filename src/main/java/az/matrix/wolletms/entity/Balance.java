package az.matrix.wolletms.entity;

import az.matrix.wolletms.enumerated.Currency;
import az.matrix.wolletms.enumerated.PaymentMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "balance")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer userId;
    LocalDateTime date;
    LocalDateTime updated;
    BigDecimal totalBalance;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    Currency currency;
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
}
