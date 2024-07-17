package org.example.httpclients.httpinterface;

import org.springframework.web.service.annotation.GetExchange;

public interface RepositoryService {

	@GetExchange("/api/ok")
	String getOk();

}