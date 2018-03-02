package cn.tarena.ht.pojo;
//部门对象  
public class Dept extends BaseEntity{
	
	//数据类型全部用包装类型
	private String deptId;  //部门id号
	private Dept parentDept;//上级部门   一对一封装
	private String deptName;	//部门名称
	private Integer state;	//部门状态  0表示停用   1表示启用
	
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Dept getParentDept() {
		return parentDept;
	}
	public void setParentDept(Dept parentDept) {
		this.parentDept = parentDept;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	  
	  
	  
}
