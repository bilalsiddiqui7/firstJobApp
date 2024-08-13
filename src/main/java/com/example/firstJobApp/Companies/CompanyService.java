package com.example.firstJobApp.Companies;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

	List<Company> getAllCompanies();

	Optional<Company> getCompanyById(Long id);

	Company createCompany(Company Company);

	boolean updateCompany(int id, Company company);

	boolean deleteCompany(int id);

}
