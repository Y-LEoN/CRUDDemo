<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="menu" class="list-group">
	<li class="list-group-item">
		<a href="#" data-toggle="collapse" data-target="#strategy_detail">
			<span>系统管理</span>
		</a>
		<ul class="in" id="strategy_detail">
            <li class="employee"><a href="${pageContext.request.contextPath}/employee">员工管理</a></li>
			<li class="department"><a href="${pageContext.request.contextPath}/department">部门管理</a></li>
			<li class=""><a href="#">研发中......</a></li>
			<li class=""><a href="#">研发中......</a></li>
			<li class=""><a href="#">研发中......</a></li>
			<li class=""><a href="#">研发中......</a></li>
			<li class=""><a href="#">研发中......</a></li>
			<li class=""><a href="#">研发中......</a></li>
		</ul>
	</li>
</ul>


<script type="text/javascript">
	var url = location.pathname;
	url = url.substring(1);

    $(".in li."+url).addClass("active");
</script>
