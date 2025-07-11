package az.matrix.wolletms.repository;

import az.matrix.wolletms.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findByUserId(Integer userId);
}
