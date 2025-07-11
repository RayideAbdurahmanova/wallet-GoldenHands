package az.matrix.wolletms.controller;

import az.matrix.wolletms.dto.BalanceDto;
import az.matrix.wolletms.dto.CreateBalanceRequest;
import az.matrix.wolletms.service.BalanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("balance")
@RestController
public class BalanceController {
    private final BalanceService balanceService;

    @PostMapping("top-up")
    @PreAuthorize("hasAuthority('USER')")
    public void topUp(HttpServletRequest request,
                      @RequestBody @Valid CreateBalanceRequest createBalanceRequest) {
        balanceService.topUp(request, createBalanceRequest);
    }

    @GetMapping("user")
    @PreAuthorize("hasAuthority('USER')")
    public BalanceDto getMyBalance(HttpServletRequest request) {
       return  balanceService.getMyBalance(request);
    }
}
