package site.workforus.forus.admin.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.model.DeptVO;
import site.workforus.forus.admin.service.DeptService;

@Controller
@RequestMapping(value = "/admin")
public class DeptController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private DeptService deptService;
	
	// 부서 전체 조회
	@GetMapping(value = "/dept_manage")
	public String getDeptAll(Model model, HttpSession session
			, DeptDTO deptDto) {
		
		List<DeptDTO> deptDatas = deptService.getDeptAll(); 
		model.addAttribute("deptDatas", deptDatas);
		logger.info("data: {}", deptDatas);
		
		return "admin/dept_manage";
	}
	
	// 부서 상세 조회
	@SuppressWarnings("unchecked")
	@ResponseBody
	@GetMapping(value = "/dept_detail", produces="application/json; charset=utf-8")
	public String getDeptDetail(HttpSession session, @RequestParam int no) {
		logger.info("getDeptDetail(no): {}", no);
		// 로그인 세션 추가하기
		
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
	public String addDept() {
		// 로그인 세션 추가하기
		return "admin/dept_add";
	}
	
	// 부서 추가 저장 요청
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping(value = "/dept_add", produces="application/json; charset=utf-8")
	public String addDept(HttpSession session, @RequestBody DeptDTO deptDto) {
		// 로그인 세션 추가하기
		logger.info("addDept(DeptDTO={})", deptDto);
		
		DeptDTO data = new DeptDTO();
		data.setDeptName(deptDto.getDeptName());
		data.setDeptMngId(deptDto.getDeptMngId());
		boolean result = deptService.addDept(data);
		
		JSONObject json = new JSONObject();
		if(result) {
			json.put("title", "Success");
			json.put("message", "부서 추가 작업이 완료 되었습니다.");
			return json.toJSONString();
		} else {
			json.put("title", "Fail");
			json.put("message", "부서 추가 작업 중 알 수 없는 문제가 발생하였습니다.");
			return json.toJSONString();
		}
		
		
	}
	
	
	
	// 부서 수정 폼 요청
	@GetMapping(value = "dept_modify")
	public String modifyDept() {
		// 로그인 세션 추가하기
		return "admin/dept_modify";
	}
	
	// 부서 수정 저장 요청
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping(value = "/dept_modify", produces="application/json; charset=utf-8")	
	public String modifyDept(@RequestParam int no
						   , @ModelAttribute DeptVO deptVo) {
		// 로그인 세션 추가하기
		logger.info("modifyDept(DeptVO={})", deptVo);
		
		DeptDTO data = deptService.getDeptDetail(no);
		
		//data.setDeptName(deptVo.getDeptName());
		//data.setDeptMngId(deptVo.getDeptMngId());
		
		boolean result = deptService.modifyDept(data);
		
		JSONObject json = new JSONObject();
		if(result) {
			json.put("title", "Success");
			json.put("message", "부서 수정이 완료 되었습니다.");
			return json.toJSONString();
		} else {
			json.put("title", "Fail");
			json.put("message", "수정 작업 중 알 수 없는 문제가 발생하였습니다.");
			return json.toJSONString();
		}
		// 수정 권한 없음
		
	}
	
	
	// 부서 삭제
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping(value ="/dept_delete", produces="application/json; charset=utf-8")
	public String removeDept(HttpSession session, @RequestParam int no) {
		// 로그인 세션 추가하기
		logger.info("removeDept(no): {}", no);
		
		DeptDTO data = deptService.getDeptDetail(no);
		
		JSONObject json = new JSONObject();
		if(data == null) {
			// 이미 삭제된 상태
			json.put("title", "Already Deleted");
			json.put("message", "해당 부서는 이미 삭제 처리 되었습니다.");
			return json.toJSONString();
		} else {
			// 권한 조건
			// 삭제 가능
			try {
				json.put("title", "Delete Completed");
				json.put("message", "삭제 처리가 완료 되었습니다.");
				return json.toJSONString();
			} catch (Exception e) {
				json.put("title", "Delete Fail");
				json.put("message", "삭제 작업 중 알 수 없는 문제가 발생하였습니다.");
				return json.toJSONString();
			}
			
			// 삭제 권한 없음
		}
		
	}
	
}
