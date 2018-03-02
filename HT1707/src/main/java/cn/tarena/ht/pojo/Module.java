package cn.tarena.ht.pojo;

public class Module extends BaseEntity{
	private String moduleId;		//模块名称
	private Module parentModule;	//父级模块对象
	private String name;			//模块名称
	private Integer ctype;			//1主菜单/2左侧菜单/3按钮  模块类型
	private Integer state;			//模块状态  0 停用  1启用
	private Integer orderNo;		//排序号
	private String  remark;			//备注信息
	
	private Boolean checked;
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Module getParentModule() {
		return parentModule;
	}
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	//为了满足zTree树的结构 添加getXX()方法
	public String getId(){
		return moduleId;
	}
	
	//实现子父级关系
	public String getpId(){
		
		//判断是否还有上级
		if(parentModule !=null){
			return parentModule.getModuleId();
		}
		return "";
		
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
}
