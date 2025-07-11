package az.matrix.wolletms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateBalanceRequest {
    @NotNull
    @Positive(message = "Amount must be positive")
    BigDecimal amount;
}
