package az.matrix.wolletms.mapper;


import az.matrix.wolletms.dto.BalanceDto;
import az.matrix.wolletms.entity.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceDto mapToBalanceDto(Balance balanceEntity);
}
