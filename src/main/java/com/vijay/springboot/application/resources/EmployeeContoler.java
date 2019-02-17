package com.vijay.springboot.application.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.springboot.application.constants.ResourceConstant;
import com.vijay.springboot.application.domain.Employee;
import com.vijay.springboot.application.exception.ResourceNotFoundException;
import com.vijay.springboot.application.service.EmployeeService;

/**
 * EmployeeContoler used to get the Employee details
 * 
 * @author Vijay.Kumar
 * @since 15/02/2019
 *
 */
@RestController
@RequestMapping(value = ResourceConstant.API)
public class EmployeeContoler {

	private static final Logger LOGGER = Logger.getLogger(EmployeeContoler.class.getName());

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(ResourceConstant.GET_EMPLOYEES)
	private List<Employee> getAllEmplyees() {
		List<Employee> listEmp = null;
		try {
			listEmp = employeeService.getAllEmployees();

			if (listEmp == null || listEmp.size() == 0) {
				throw new ResourceNotFoundException("Employees Not found");
			}
		} catch (ResourceNotFoundException e) {
			LOGGER.info("getAllEmplyees() :: Employees Not found");
		}
		return listEmp;
	}

	@GetMapping(ResourceConstant.GET_EMPLOYEE_ID)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping(ResourceConstant.CREATE_EMPLOYEE)
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping(ResourceConstant.UPDATE_EMPLOYEE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping()
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeService.deleteEmployee(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
