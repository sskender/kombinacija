package hr.fer.opp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {

    // TRASH CAN
    //GET /trash -> list of trash cans
    //POST /trash -> @RequestBody = AddContainerDTO
    //PUT /trash -> @RequestBody = AddContainerDTO
    //DELETE /trash/{id}

    // EMPLOYEE
    //GET /employee -> list of employees
    //GET /employee/{id} -> one employee
    //POST /employee -> @RequestBody = RegisterEmployeeDTO
    //PUT /employee -> @RequestBody = RegisterEmployeeDTO
    //DELETE /employee/{id}

    // NEIGHBORHOOD
    //GET /neighborhood -> list of neighborhoods
    //GET /neighborhood/{id} -> get one neighborhood
    //GET /neighborhood/{id}/trash -> get list of containers in neighborhood
    //GET /neighborhood/{id}/employee -> get list of employees in neighborhood
    //POST /neighborhood -> @RequestBody = AddNeighborhoodDTO
    //PUT /neighborhood -> @RequestBody = AddNeighborhoodDTO
    //DELETE /neighborhood/{id} -> DELETES ALL ASSOCIATED TRASH CANS AND CONVERTS EMPLOYEES TO CITIZENS

}
