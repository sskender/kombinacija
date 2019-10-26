package hr.fer.opp.dao;

import hr.fer.opp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Employee> findById(Long id);

    /**
     * @param email
     * @return
     */
    Optional<Employee> findByEmail(String email);

    /**
     * @param employee
     * @return
     */
    default boolean insertEmployee(Employee employee) {
        return employee.equals(save(employee));
    }

    /**
     * @param employees
     * @return
     */
    default int insertAllEmployees(Iterable<Employee> employees) {
        return saveAll(employees).size();
    }

}
