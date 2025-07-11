package az.matrix.wolletms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceDto {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime date;
    BigDecimal totalBalance;
}
