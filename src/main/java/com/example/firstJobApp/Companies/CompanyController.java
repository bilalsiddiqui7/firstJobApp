package com.example.firstJobApp.Companies;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstJobApp.jobs.Job;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> CompaniesList = companyService.getAllCompanies();
		return new ResponseEntity<List<Company>>(CompaniesList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Optional<Company> company = companyService.getCompanyById(id);
		if (company.isPresent())
			return new ResponseEntity<Company>(company.get(), HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Company> addCompany(@RequestBody Company company) {
		Company savedCompany = companyService.createCompany(company);
		return new ResponseEntity<Company>(savedCompany, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable int id, @RequestBody Company company) {
		boolean updated = companyService.updateCompany(id, company);
		if (updated == true)
			return new ResponseEntity<String>("Company updated", HttpStatus.OK);
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable int id) {
		boolean deleted = companyService.deleteCompany(id);
		if (deleted == true)
			return new ResponseEntity<String>("Company deleted", HttpStatus.OK);
		return new ResponseEntity<String>("Company id not found", HttpStatus.NOT_FOUND);
	}

}
