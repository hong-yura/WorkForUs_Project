package site.workforus.forus.admin.controller;


import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.service.DeptService;
import site.workforus.forus.employee.model.EmpDTO;

@Controller
@RequestMapping(value = "/admin")
public class DeptController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private DeptService deptService;
	
	// 부서 전체 조회
	@GetMapping(value = "/dept_manage")
	public String getDeptAll(Model model, DeptDTO deptDto) {
		
		List<DeptDTO> deptDatas = deptService.getDeptAll(); 
		model.addAttribute("deptDatas", deptDatas);
		logger.info("data: {}", deptDatas);
		
		return "admin/dept_manage";
	}
	
	// 부서 상세 조회
	@SuppressWarnings("unchecked")
	@ResponseBody
	@GetMapping(value = "/dept_detail", produces="application/json; charset=utf-8")
	public String getDeptDetail(Model model
			, @RequestParam int no) {
		
		DeptDTO data = deptService.getDeptDetail(no);
		
		JSONObject json = new JSONObject();
		
		json.put("deptNo", data.getDeptNo());
		json.put("deptName", data.getDeptName());
		json.put("deptMngId", data.getDeptMngId());
		json.put("deptAddDt", data.getDeptAddDt() + "");
		json.put("deptModDt", data.getDeptModDt() + "");
		
		
		return json.toJSONString();
	}
	
	
	
	// 부서 추가 폼 요청
	@GetMapping(value = "/dept_add")
	public String addDept(Model model, EmpDTO emDto) {
		DeptDTO deptDatas = deptService.addDept();
		model.addAttribute("deptDatas", deptDatas);
		logger.info("addDept: {}", deptDatas);
		
		return "admin/dept_add";
	}
	
	// 부서 추가 저장 요청
	@PostMapping(value = "/dept_add", produces="application/json; charset=utf-8")
	public String addDept(Model model, EmpDTO emDto, DeptDTO deptDto) {
		
		return null;
	}
	
	// 부서 수정 폼 요청
	@GetMapping(value = "dept_modify")
	public void foo() {
		
	}
	
}