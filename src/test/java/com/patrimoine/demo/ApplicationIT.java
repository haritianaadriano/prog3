package com.patrimoine.demo;

import com.patrimoine.demo.model.Patrimoine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApplicationIT {
	@LocalServerPort int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void get_server_health() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello, World");
	}

	@Test
	void put_patrimoine_ok() throws Exception {
		String url = "/patrimoines/1";
		String requestBody = "{ \"possesseur\" : \"something\", \"derniereModification\" : \"2024-09-23T10:54:17+00:00\" }";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<Patrimoine> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Patrimoine.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().equals("{\n" +
				"  \"id\": \"1\",\n" +
				"  \"possesseur\": \"something\",\n" +
				"  \"derniereModification\": \"2024-09-23T10:54:17Z\"\n" +
				"}"));
	}

	@Test
	void get_patrimoine_ok() throws Exception {
		String url = "/patrimoines/1";
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + url,
				Patrimoine.class)).isEqualTo(new Patrimoine("1", "something", Instant.parse("2024-09-23T10:54:17Z")));
	}
}
