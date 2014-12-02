<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
<title>代理商首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/css/agentiframe/base[2].css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/agentiframe/base_table[1]_2.css" type="text/css" rel="stylesheet">
<style type="text/css">
table.gridtable{
	width:98%;
	font-family: verdana,arial,sans-serif;
	font-size: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	margin:2px;
}
table.gridtable th {
	border-width: 1px;
	padding: 3px;
	border-style: solid;
	border-color: #007d65;
	background-color: #84bf96;
	line-height:18px;
}
table.gridtable td {
	border-width: 1px;
	padding: 3px;
	border-style: solid;
	border-color: #007d65;
	background-color: #ffffff;
	line-height:27px;
}
</style>
</head>
<body >
	<ul id="desktopBox">
		<div class="centerDesktopBox">
			<ul class="workBox">
				<li class="box" style="width: 300px;">
					<div class="title">
						<div class="desktopTitleName">通知公告</div>
					</div>
					<div id="ggList">
						<div class="list">
							<div class="listName">
								<a href="#" title="本月4号系统升级，请周知">本月4号系统升级，请周知</a>
								</div>
							<div class="desktopTime">
								2014-07-01
							</div>
						</div>

						<div class="list">
							<div class="listName">
								<a href="#" title="本月全国新增用户数10万户">本月全国新增用户数10万户</a>
								</div>
							<div class="desktopTime">
								2014-06-30
							</div>
						</div>
					</div>
				</li>
				<li id="dbwork" class="box" style="width: 645px;">
					<div class="title">
						<div class="desktopTitleName">待办工作</div>
					</div>
					<div id="dbList">
						<div class="list">
							<div class="listName">
								<a title="本月水订单120笔，请各代理商落实" href="#">
									<span class="error warn" id="id_1101_401">
									 本月水订单120笔，请各代理商落
									</span>
								</a>
							</div>
							<div class="desktopTime">
								2014-07-03
							</div>
						</div>
						<div class="list">
							<div class="listName">
								<a title="本月水订单120笔，请各代理商落实" href="#">
									<span class="error warn" id="id_1101_363">
									 本月电订单120笔，请各代理商落
									</span>
								</a>
							</div>
							<div class="desktopTime">
								2014-07-03
							</div>
						</div>
					</div>
				</li>
			</ul>
			<div style="clear: both;"></div>
			<ul class="workBox">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">当月订单完成情况</div>
									</div>
									<div class="" id="bt1">
										<img src="<%=request.getContextPath() %>/images/bt1.jpg" align="right" height="200px">
									</div>
								</li>
							</td>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">当月新增用户</div>
									</div>
									<div class="" id="bt1">
										<img src="<%=request.getContextPath() %>/images/bt2.jpg" align="right" height="200px">
									</div>
								</li>
							</td>
							<td rowspan="2" valign="top">
								<li class="box" style="width: 330px; height: 492px;">
									<div class="title">
										<div class="desktopTitleName" id="xxlist">2014年综合统计报表</div>
										<div class="desktopMore"></div>
									</div>
									<div id="xqList">
										<div id="bt12" style="display: ">
											<div style="clear: both;"></div>
											<table class="gridtable">
												<tbody>
													<tr>
														<th>项目</th>
														<th>用户数</th>
														<th>占比（%）</th>
														<th>订单金额</th>
														<th>占比（%）</th>
													</tr>
													<tr>
														<td align="left">合计</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">一月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">二月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">三月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">四月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">五月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">六月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">七月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">八月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">九月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">十月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">十一月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left">十二月份</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td align="left"></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div> <!-- <div class="rightTableInfo"></div> -->
								</li>
							</td>
						</tr>
						<tr>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">用户投诉</div>
									</div>
									<div class="" id="bt1">
										<img src="<%=request.getContextPath() %>/images/bt3.jpg" align="right" height="200px">
									</div>
								</li>
							</td>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">当月决算</div>
									</div>
									<div class="" id="bt1">
										<img src="<%=request.getContextPath() %>/images/bt4.jpg" align="right" height="200px">
									</div>
								</li>
							</td>
						</tr>
					</tbody>
				</table>
			</ul>
			<div style="clear: both; width: 100px; height: 15px;"></div>
		</div>
	</ul>
</body>