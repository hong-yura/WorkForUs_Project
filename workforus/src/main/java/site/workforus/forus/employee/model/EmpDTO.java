package site.workforus.forus.employee.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("empDto")
public class EmpDTO {
	private String empId;
	private String empNm;
	private String empPw;
	private String empCheckPw;
	private String empEmail;
	private String empAssistEmail;
	private String empGender;
	private String empPhone;
	private String empTel;
	private String empBirth;
	private String empAddr;
	private String empHireDt;
	private String empResignDt;
	private String empTenure;
	private String empResignFl;
	private String empPhotoUrl;
	private String empPhotoNm;
	private String empStatus;
	private String empLoginFail;
	private String empLoginLock;
	private String empFirstLogin;
	private String deptNo;
	private String jobId;
	private String empSalary;
	private String empStamp;
	
	public EmpDTO() {}

	public EmpDTO(String empId, String empNm, String empPw, String empCheckPw, String empEmail, String empAssistEmail,
			String empPhone, String empTel, String deptNo, String jobId, String empSalary) {
		super();
		this.empId = empId;
		this.empNm = empNm;
		this.empPw = empPw;
		this.empCheckPw = empCheckPw;
		this.empEmail = empEmail;
		this.empAssistEmail = empAssistEmail;
		this.empPhone = empPhone;
		this.empTel = empTel;
		this.deptNo = deptNo;
		this.jobId = jobId;
		this.empSalary = empSalary;
	}
}
