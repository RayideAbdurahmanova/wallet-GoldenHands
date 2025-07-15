package az.matrix.wolletms.mapper;

import az.matrix.wolletms.dto.PaymentRequest;
import az.matrix.wolletms.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "request.amount", target = "amount")
    @Mapping(source = "userId", target = "userId")
    @Mapping(constant = "AZN", target = "currency")
    @Mapping(constant = "CARD", target = "paymentMethod")
    @Mapping(constant = "PENDING", target = "status")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "date")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "updated")
    Payment mapToPayment(PaymentRequest request, Long userId);
}
