package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<EmpregadoDeptProjection> list = repository.searchSQL_NOTIN();
		List<EmpregadoDeptDTO> resultSQL = list.stream().map(x -> new EmpregadoDeptDTO(x)).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ NOT IN:");
		for (EmpregadoDeptDTO obj : resultSQL) {
			System.out.println(obj);
		}
		System.out.println("\n\n");

		 list = repository.searchSQL_LEFTJOIN();
		 resultSQL = list.stream().map(x -> new EmpregadoDeptDTO(x)).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ LEFT JOIN:");
		for (EmpregadoDeptDTO obj : resultSQL) {
			System.out.println(obj);
		}
		System.out.println("\n\n");

		
		List<EmpregadoDeptDTO> resultJPQL = repository.searchJPQL();

		System.out.println("\n*** RESULTADO JPQL:");
		for (EmpregadoDeptDTO obj : resultJPQL) {
			System.out.println(obj);
		}
		System.out.println("\n\n");		
	}
}
