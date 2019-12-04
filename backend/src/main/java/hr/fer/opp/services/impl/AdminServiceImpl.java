package hr.fer.opp.services.impl;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.dto.AddContainerDTO;
import hr.fer.opp.dto.AddNeighborhoodDTO;
import hr.fer.opp.dto.RegisterEmployeeDTO;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Neighborhood;
import hr.fer.opp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Container getContainerById(Long containerId) {
        return null;
    }

    @Override
    public List<Container> getContainerByNeighborhoodId(Long neighborhoodId) {
        return null;
    }

    @Override
    public List<Container> getAllContainers() {
        return null;
    }

    @Override
    public Container registerNewContainer(AddContainerDTO containerDTO) {
        return null;
    }

    @Override
    public Container updateContainer(Container container) {
        return null;
    }

    @Override
    public boolean deleteContainerById(Long containerId) {
        return false;
    }

    @Override
    public Neighborhood getNeighborhoodById(Long neighborhoodId) {
        return null;
    }

    @Override
    public Neighborhood getNeighborhoodByName(String neighborhoodName) {
        return null;
    }

    @Override
    public List<Neighborhood> getAllNeighborhoods() {
        return null;
    }

    @Override
    public Neighborhood registerNewNeighborhood(AddNeighborhoodDTO neighborhoodDTO) {
        return null;
    }

    @Override
    public Neighborhood updateNeighborhood(Neighborhood neighborhood) {
        return null;
    }

    @Override
    public boolean deleteNeighborhoodById(Long neighborhoodId) {
        return false;
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return null;
    }

    @Override
    public Employee getEmployeeByOIB(String OIB) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByNeighborhoodId(Long neighborhoodId) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee registerNewEmployee(RegisterEmployeeDTO employeeDTO) {
        return null;
    }

    @Override
    public Employee updateEmployeeProfile(Employee employee) {
        return null;
    }

    @Override
    public boolean removeEmployeeById(Long employeeId) {
        return false;
    }

}
