package ma.med.springangular.repository;

import ma.med.springangular.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,String> {
    Student findByCode(String code);
    List<Student> findByProgramId(String programId);
}
