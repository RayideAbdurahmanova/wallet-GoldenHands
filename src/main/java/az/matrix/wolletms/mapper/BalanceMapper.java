package az.matrix.wolletms.mapper;


import az.matrix.wolletms.dto.BalanceDto;
import az.matrix.wolletms.dto.CreateBalanceRequest;
import az.matrix.wolletms.dto.PaymentRequest;
import az.matrix.wolletms.entity.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(source = "request.amount", target = "amount")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "totalBalance", target = "totalBalance")
    @Mapping(constant = "AZN", target = "currency")
    @Mapping(constant = "CARD", target = "paymentMethod")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "date")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "updated")
    Balance mapToBalance(CreateBalanceRequest request, Integer userId, BigDecimal totalBalance);

    BalanceDto mapToBalanceDto(Balance balanceEntity);

}
