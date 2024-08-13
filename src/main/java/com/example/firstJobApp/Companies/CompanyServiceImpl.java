package com.example.firstJobApp.Companies;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.firstJobApp.jobs.Job;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Optional<Company> getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id);
	}

	@Override
	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public boolean updateCompany(int id, Company company) {
		Optional<Company> companyToUpdate = companyRepository.findById((long) id);
		if (companyToUpdate.isPresent()) {
			Company updatedCompany = companyToUpdate.get();
			updatedCompany.setDescription(company.getDescription());
			updatedCompany.setJobs(company.getJobs());
			updatedCompany.setName(company.getName());
			companyRepository.save(updatedCompany);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCompany(int id) {
		try {
			companyRepository.deleteById((long) id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
