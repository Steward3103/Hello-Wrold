<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${ctx}/staticfile/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/staticfile/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<SCRIPT type="text/javascript">

		
		
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		//无尽战刃    暴风大剑    暴击手套  十字镐    小短剑
		var zNodes = [{id:1,name:"小短剑",pId:0},{id:2,name:"十字镐",pId:1},
		              {id:3,name:"暴风大剑",pId:2},{id:4,name:"无尽战刃",pId:3}]
		
		/*让页面全部加载完成后再执行js代码*/
		$(document).ready(function(){
			/*初始化一颗树  */
			$.fn.zTree.init($("#htZtree"), setting, zNodes);
			
			
			var zTreeObj = $.fn.zTree.getZTreeObj("htZtree");
			zTreeObj.expandAll(true);		//展开所有树节点
		});
		
		
		
	</SCRIPT>

</head>

<body>
<h1>Ztree入门教例</h1>
<div style="padding:30px;">
		<ul id="htZtree" class="ztree"></ul>
</div>
 
</div>
 
 
</form>
</body>
</html>

