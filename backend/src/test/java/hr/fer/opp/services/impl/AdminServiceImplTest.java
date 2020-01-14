package hr.fer.opp.services.impl;


import hr.fer.opp.SetupTest;
import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.dao.NeighborhoodRepository;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Neighborhood;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest extends SetupTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private ContainerRepository containerRepository;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    public void getContainerById_Success() {
        Container c = new Container();
        c.setId(1L);

        Mockito.doReturn(Optional.of(c)).when(containerRepository).findById(1l);

        Container actual = adminService.getContainerById(1l);

        assertThat(actual).isEqualTo(c);
    }

    @Test(expected = RequestDeniedException.class)
    public void getContainerById_Exception() {
        Container c = new Container();
        c.setId(1L);

        Container actual = adminService.getContainerById(2l);
    }

    @Test
    public void getContainerByNeighborhoodId_Success() {
        Neighborhood n = new Neighborhood();
        n.setId(1l);

        Container c1 = new Container();

        Container c2 = new Container();

        List<Container> expectedContainers = new ArrayList<>();
        expectedContainers.add(c1);
        expectedContainers.add(c2);

        n.setContainers(expectedContainers);

        Mockito.doReturn(Optional.of(n)).when(neighborhoodRepository).findById(1l);

        List<Container> actualContainers = adminService.getContainersByNeighborhoodId(1l);

        assertThat(actualContainers).isEqualTo(expectedContainers);
    }

    @Test (expected = RequestDeniedException.class)
    public void getContainerByNeighborhoodId_Exception() {
        Neighborhood n = new Neighborhood();
        n.setId(1l);

        Container c1 = new Container();

        Container c2 = new Container();

        List<Container> expectedContainers = new ArrayList<>();
        expectedContainers.add(c1);
        expectedContainers.add(c2);

        n.setContainers(expectedContainers);

        List<Container> actualContainers = adminService.getContainersByNeighborhoodId(2l);
    }

    @Test
    public void whenGetAllContainers_Success() {
        Container c1 = new Container();
        c1.setId(1L);

        Container c2 = new Container();
        c2.setId(2L);

        List<Container> expectedContainers = new ArrayList<>();
        expectedContainers.add(c1);
        expectedContainers.add(c2);

        Mockito.doReturn(expectedContainers).when(containerRepository).findAll();

        List<Container> actualContainers = adminService.getAllContainers();

        assertThat(actualContainers).isEqualTo(expectedContainers);
    }

    @Test
    public void getNeighborhoodById_Success() {
        Neighborhood n = new Neighborhood();
        n.setId(1l);

        Mockito.doReturn(Optional.of(n)).when(neighborhoodRepository).findById(1l);

        Neighborhood actual = adminService.getNeighborhoodById(1l);

        assertThat(actual).isEqualTo(n);
    }

    @Test (expected = RequestDeniedException.class)
    public void getNeighborhoodById_Exception() {
        Neighborhood n = new Neighborhood();
        n.setId(1l);

        Neighborhood actual = adminService.getNeighborhoodById(2l);
    }

    @Test
    public void getEmployeeByOIB_Success(){
        Employee e = new Employee();
        e.setOIB("12345678900");

        Mockito.doReturn(Optional.of(e)).when(employeeRepository).findByOIB("12345678900");

        Employee actual = adminService.getEmployeeByOIB("12345678900");

        assertThat(actual).isEqualTo(e);
    }

    @Test (expected = RequestDeniedException.class)
    public void getEmployeeByOIB_Exception(){
        Employee e = new Employee();
        e.setOIB("12345678900");

        Employee actual = adminService.getEmployeeByOIB("9999999999");
    }
}
