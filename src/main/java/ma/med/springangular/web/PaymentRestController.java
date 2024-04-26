package ma.med.springangular.web;

import ma.med.springangular.entities.Payment;
import ma.med.springangular.entities.PaymentStatus;
import ma.med.springangular.entities.PaymentType;
import ma.med.springangular.entities.Student;
import ma.med.springangular.repository.PaymentRepo;
import ma.med.springangular.repository.StudentRepo;
import ma.med.springangular.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*") // côté backend j'autorise n'importe quelle page de n'importe quel domaine à utiliser ses services.
public class PaymentRestController {
    private StudentRepo studentRepo;
    private PaymentRepo paymentRepo;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepo studentRepo, PaymentRepo paymentRepo, PaymentService paymentService) {
        this.studentRepo = studentRepo;
        this.paymentRepo = paymentRepo;
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<Payment> allPayments() {
        return paymentRepo.findAll();
    }

    @GetMapping("/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code) {
        return paymentRepo.findByStudentCode(code);
    }

    @GetMapping("payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepo.findByStatus(status);
    }

    @GetMapping("payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type) {
        return paymentRepo.findByType(type);
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepo.findById(id).orElse(null);
    }

    @GetMapping("/students")
    public List<Student> allStudents() {
        return studentRepo.findAll();
    }

    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code) {
        return studentRepo.findByCode(code);
    }

    @GetMapping("/studentsByProgramId")
    public List<Student> getStudentsByProgramId(@RequestParam String programId) {
        return studentRepo.findByProgramId(programId);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long id) {
        return paymentService.updatePaymentStatus(status, id);
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        return this.paymentService.savePayment(file, date, amount, type, studentCode);
    }

    @GetMapping(path = "/payments/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
    }
}
