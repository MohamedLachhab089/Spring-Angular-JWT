package ma.med.springangular;

import ma.med.springangular.entities.Payment;
import ma.med.springangular.entities.PaymentStatus;
import ma.med.springangular.entities.PaymentType;
import ma.med.springangular.entities.Student;
import ma.med.springangular.repository.PaymentRepo;
import ma.med.springangular.repository.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo, PaymentRepo paymentRepo) {
        return args -> {
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Mohamed")
                    .code("112233")
                    .programId("SDIA")
                    .build());
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Souad")
                    .code("112244")
                    .programId("SDIA")
                    .build());
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Yassine")
                    .code("112255")
                    .programId("GLSID")
                    .build());
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Ayoub")
                    .code("112266")
                    .programId("NJCC")
                    .build());

            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();
            studentRepo.findAll().forEach(st->{
                for (int i = 0; i < 10; i++) {
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(int)(Math.random()*200))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepo.save(payment);
                }
            });
        };
    }

}
