package ma.med.springangular.repository;

import ma.med.springangular.entities.Payment;
import ma.med.springangular.entities.PaymentStatus;
import ma.med.springangular.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
