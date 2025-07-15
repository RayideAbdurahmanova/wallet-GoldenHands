package az.matrix.wolletms.service;

import az.matrix.wolletms.dto.BalanceDto;
import az.matrix.wolletms.dto.CreateBalanceRequest;
import az.matrix.wolletms.entity.Balance;
import az.matrix.wolletms.enumerated.Currency;
import az.matrix.wolletms.enumerated.PaymentMethod;
import az.matrix.wolletms.filter.JwtService;
import az.matrix.wolletms.mapper.BalanceMapper;
import az.matrix.wolletms.repository.BalanceRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final JwtService jwtService;
    private final BalanceMapper balanceMapper;

    public void topUp(HttpServletRequest request, CreateBalanceRequest createBalanceRequest) {
        var token = request.getHeader("Authorization").substring(7).trim();
        var userId = jwtService.extractUserId(token);
        var balance = balanceRepository.findByUserId(userId);

        BigDecimal currentBalance = BigDecimal.ZERO;
        if (!balance.isEmpty()) {
            var lastBalance = balance.get(balance.size() - 1);
            if (lastBalance.getTotalBalance() != null) {
                currentBalance = lastBalance.getTotalBalance();
            }
        }
        currentBalance = currentBalance.add(createBalanceRequest.getAmount());
        Balance newBalance = balanceMapper.mapToBalance(createBalanceRequest,userId,currentBalance);
        balanceRepository.save(newBalance);
    }

    public BalanceDto getMyBalance(HttpServletRequest request) {
        var token = request.getHeader("Authorization").substring(7).trim();
        var userId = jwtService.extractUserId(token);
        var balance = balanceRepository.findByUserId(userId);
        if (balance.isEmpty()) {
            Collections.emptyList();
        }
        var balanceEntity = balance.get(balance.size() - 1);
        return balanceMapper.mapToBalanceDto(balanceEntity);
    }
}
