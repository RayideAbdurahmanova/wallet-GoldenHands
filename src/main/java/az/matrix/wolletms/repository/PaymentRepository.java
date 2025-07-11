package az.matrix.wolletms.repository;

import az.matrix.wolletms.entity.Payment;
import az.matrix.wolletms.enumerated.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.userId IN :userIds")
    List<Payment> findByUserIds(@Param("userIds") List<Long> userId);

    List<Payment> findByStatus(Status status);
}
