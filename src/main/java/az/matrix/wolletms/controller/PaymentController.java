package az.matrix.wolletms.controller;


import az.matrix.wolletms.dto.PaymentRequest;
import az.matrix.wolletms.entity.Payment;
import az.matrix.wolletms.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("payment")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("pay")
    @PreAuthorize("hasAuthority('USER')")
    public void pay(HttpServletRequest req,
                    @RequestBody @Valid PaymentRequest paymentRequest) {
        paymentService.pay(req,paymentRequest);
    }

    @PostMapping("execute")
    public void execute(){
        paymentService.changePaymentStatus();
    }
}
