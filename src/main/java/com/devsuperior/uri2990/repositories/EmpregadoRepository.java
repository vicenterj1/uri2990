package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
	
	@Query(nativeQuery = true, value = "select e.cpf, e.enome, d.dnome "
			+ "from empregados e "
			+ "inner join departamentos d on e.dnumero = d.dnumero "
			+ "WHERE e.cpf not in (select ee.cpf "
			+ "				   from empregados ee "
			+ "				   inner join trabalha as t on t.cpf_emp = ee.cpf  ) "
			+ "order by e.cpf	")
	List<EmpregadoDeptProjection> searchSQL_NOTIN();

	@Query(nativeQuery = true, value = "select e.cpf, e.enome, d.dnome"
			+ "	from empregados e"
			+ "	inner join departamentos d on e.dnumero = d.dnumero"
			+ "	left join trabalha as t on t.cpf_emp = e.cpf"
			+ "	where t.pnumero is null"
			+ "	order by e.cpf")
	List<EmpregadoDeptProjection> searchSQL_LEFTJOIN();	
	
	@Query( value = "select new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) "
			+ "from Empregado obj "
			+ "WHERE obj.cpf not in (select ee.cpf "
			+ "				   from Empregado  ee"
			+ "				   inner join ee.projetosOndeTrabalha  ) "
			+ "order by obj.cpf	")
	List<EmpregadoDeptDTO> searchJPQL();
	
	

}
