package az.matrix.wolletms.service;

import az.matrix.wolletms.dto.PaymentRequest;
import az.matrix.wolletms.entity.Balance;
import az.matrix.wolletms.entity.Payment;
import az.matrix.wolletms.exceptions.DataNotFoundException;
import az.matrix.wolletms.filter.JwtService;
import az.matrix.wolletms.repository.BalanceRepository;
import az.matrix.wolletms.repository.PaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static az.matrix.wolletms.enumerated.Currency.AZN;
import static az.matrix.wolletms.enumerated.PaymentMethod.CARD;
import static az.matrix.wolletms.enumerated.Status.PENDING;
import static az.matrix.wolletms.enumerated.Status.SUCCESS;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final JwtService jwtService;
    private final BalanceRepository balanceRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public void pay(HttpServletRequest req, PaymentRequest paymentRequest) {
        var token = req.getHeader("Authorization").trim().substring(7);
        var userId = jwtService.extractUserId(token);

        var balance = balanceRepository.findByUserId(userId);
        if (balance.isEmpty()) {
            throw new DataNotFoundException("Have not enough balance");
        }
        var balanceEntity = balance.get(balance.size() - 1);
        if (balanceEntity.getTotalBalance().compareTo(paymentRequest.getAmount()) < 0) {
            throw new DataNotFoundException("Not enough balance");
        }
        var lastTotalBalance = balanceEntity.getTotalBalance();
        Balance updatedBalance = new Balance();
        updatedBalance.setUserId(userId);
        updatedBalance.setTotalBalance(lastTotalBalance);
        updatedBalance.setUpdated(LocalDateTime.now());
        updatedBalance.setAmount(paymentRequest.getAmount());
        updatedBalance.setDate(LocalDateTime.now());
        updatedBalance.setCurrency(AZN);
        updatedBalance.setPaymentMethod(CARD);
        balanceRepository.save(updatedBalance);

        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setUserId(Long.valueOf(userId));
        payment.setDate(LocalDateTime.now());
        payment.setUpdated(LocalDateTime.now());
        payment.setCurrency(AZN);
        payment.setStatus(PENDING);
        payment.setPaymentMethod(CARD);
        paymentRepository.save(payment);
    }

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void changePaymentStatus() {
        var paymentList = paymentRepository.findByStatus(PENDING);
        for (Payment payment : paymentList) {
            var amount = payment.getAmount();
            var userId = payment.getUserId();
            var balance = balanceRepository.findByUserId(Math.toIntExact(userId));

            if (balance.isEmpty()) {
                continue;
            }

            var balanceEntity = balance.get(balance.size() - 1);
            var updateLastBalance = balanceEntity.getTotalBalance().subtract(amount);
            balanceEntity.setTotalBalance(updateLastBalance);
            balanceEntity.setUpdated(LocalDateTime.now());

            payment.setStatus(SUCCESS);
            payment.setUpdated(LocalDateTime.now());

            balanceRepository.save(balanceEntity);
            paymentRepository.save(payment);
        }
    }
}
