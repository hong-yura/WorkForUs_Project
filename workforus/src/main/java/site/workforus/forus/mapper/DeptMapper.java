package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.dept.model.DeptDTO;

public interface DeptMapper {
	// DAO 역할을 한다.
	
	public List<DeptDTO> selectAll();
	
	
}