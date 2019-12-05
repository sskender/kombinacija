package hr.fer.opp.services;

import hr.fer.opp.dto.request.AddContainerDTO;
import hr.fer.opp.dto.request.AddNeighborhoodDTO;
import hr.fer.opp.dto.request.RegisterEmployeeDTO;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Neighborhood;

import java.util.List;

public interface AdminService {

    /**
     * @param containerId
     * @return
     */
    Container getContainerById(Long containerId);

    /**
     * @param neighborhoodId
     * @return
     */
    List<Container> getContainersByNeighborhoodId(Long neighborhoodId);

    /**
     * @return
     */
    List<Container> getAllContainers();

    /**
     * @param containerDTO
     * @return
     */
    Container registerNewContainer(AddContainerDTO containerDTO);

    /**
     * @param container
     * @return
     */
    Container updateContainer(Container container);

    /**
     * @param containerId
     * @return
     */
    boolean deleteContainerById(Long containerId);

    /**
     * @param container
     * @return
     */
    default boolean deleteContainer(Container container) {
        return deleteContainerById(container.getId());
    }


    /**
     * @param neighborhoodId
     * @return
     */
    Neighborhood getNeighborhoodById(Long neighborhoodId);

    /**
     * @param neighborhoodName
     * @return
     */
    Neighborhood getNeighborhoodByName(String neighborhoodName);

    /**
     * @return
     */
    List<Neighborhood> getAllNeighborhoods();

    /**
     * @param neighborhoodDTO
     * @return
     */
    Neighborhood registerNewNeighborhood(AddNeighborhoodDTO neighborhoodDTO);

    /**
     * @param neighborhood
     * @return
     */
    Neighborhood updateNeighborhood(Neighborhood neighborhood);

    /**
     * @param neighborhoodId
     * @return
     */
    boolean deleteNeighborhoodById(Long neighborhoodId);

    /**
     * @param neighborhoodName
     * @return
     */
    default boolean deleteNeighborhoodByName(String neighborhoodName) {
        return deleteNeighborhoodById(getNeighborhoodByName(neighborhoodName).getId());
    }

    /**
     * @param neighborhood
     * @return
     */
    default boolean deleteNeighborhood(Neighborhood neighborhood) {
        return deleteNeighborhoodById(neighborhood.getId());
    }


    /**
     * @param employeeId
     * @return
     */
    Employee getEmployeeById(Long employeeId);

    /**
     * @param OIB
     * @return
     */
    Employee getEmployeeByOIB(String OIB);

    /**
     * @param neighborhoodId
     * @return
     */
    List<Employee> getEmployeesByNeighborhoodId(Long neighborhoodId);

    /**
     * @param neighborhood
     * @return
     */
    default List<Employee> getEmployeesByNeighborhood(Neighborhood neighborhood) {
        return getEmployeesByNeighborhoodId(neighborhood.getId());
    }

    /**
     * @return
     */
    List<Employee> getAllEmployees();

    /**
     * @param employeeDTO
     * @return
     */
    Employee registerNewEmployee(RegisterEmployeeDTO employeeDTO);

    /**
     * @param employee
     * @return
     */
    Employee updateEmployeeProfile(Employee employee);

    /**
     * @param employeeId
     * @return
     */
    boolean removeEmployeeById(Long employeeId);

   /**
    * @param employee
    * @return
    */
   default boolean removeEmployee(Employee employee) {
      return removeEmployeeById(employee.getId());
   }

    /**
     * @param employeeOIB
     * @return
     */
    default boolean removeEmployeeByOIB(String employeeOIB) {
        return removeEmployeeById(getEmployeeByOIB(employeeOIB).getId());
    }

}
