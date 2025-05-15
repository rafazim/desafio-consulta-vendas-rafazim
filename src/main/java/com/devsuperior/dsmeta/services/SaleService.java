package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.ReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<ReportMinDTO> findByReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate maxDate = (maxDateStr == null || maxDateStr.isBlank()) ? today : LocalDate.parse(maxDateStr);
		LocalDate minDate = (minDateStr == null || minDateStr.isBlank()) ? maxDate.minusYears(1L) : LocalDate.parse(minDateStr);
		String safeName = (name == null || name.isBlank()) ? null : name;

		Page<Sale> page = repository.searchByReport(minDate, maxDate, safeName, pageable);
		return page.map(ReportMinDTO::new);
	}

	public List<SummaryMinDTO> findSummary(String minDateStr, String maxDateStr) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate maxDate = (maxDateStr == null || maxDateStr.isBlank()) ? today : LocalDate.parse(maxDateStr);
		LocalDate minDate = (minDateStr == null || minDateStr.isBlank()) ? maxDate.minusYears(1L) : LocalDate.parse(minDateStr);

		return repository.searchSummary(minDate, maxDate);
	}
}
