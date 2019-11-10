package hr.fer.opp.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    // TRASH CAN
    //GET /trash -> list of trash cans
    //POST /trash -> @RequestBody = ContainerDTO
    //PUT /trash -> @RequestBody = ContainerDTO
    //DELETE /trash/{id}

    // EMPLOYEE
    //GET /employee -> list of employees
    //GET /employee/{id} -> one employee
    //POST /employee -> @RequestBody = EmployeeDTO
    //PUT /employee -> @RequestBody = EmployeeDTO
    //DELETE /employee/{id}

    // NEIGHBORHOOD
    //GET /neighborhood -> list of neighborhoods
    //GET /neighborhood/{id} -> get one neighborhood
    //GET /neighborhood/{id}/trash -> get list of containers in neighborhood
    //GET /neighborhood/{id}/employee -> get list of employees in neighborhood
    //POST /neighborhood -> @RequestBody = NeighborhoodDTO
    //PUT /neighborhood -> @RequestBody = NeighborhoodDTO
    //DELETE /neighborhood/{id} -> DELETES ALL ASSOCIATED TRASH CANS AND CONVERTS EMPLOYEES TO CITIZENS

}
