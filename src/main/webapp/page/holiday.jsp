<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.simor.dao.*" 
         import="com.simor.controller.*"
         import="com.simor.model.*"
         import="java.util.*"
         import="java.sql.*"%>
<%
    HolidayCom.sys_del(String.valueOf(HolidayCom.sys_hol(request.getParameter("edit"), request, response).getDataFeriado()), request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>

<link rel="stylesheet" type="text/css" href="../css/holiday.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
</head>
<body>
	<div class="parent">
		<div class="navegacao">
			<div>
				<h1 class="logo">
					<a href="">SIMOR</a>
				</h1>
				<ul class="u1">
					<li><div class="lg">
							<i class="fa-solid fa-chart-pie"></i>
						</div> <a href="panel.jsp">Dashboard</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calculator"></i>
						</div> <a href="calculator.jsp">Calcular VP</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calendar-days"></i>
						</div> <a href="holiday.jsp">Feriados</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-money-bill-transfer"></i>
						</div> <a href="comparator.jsp">Comparador</a></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><div class="lg">
							<i class="fa-solid fa-gear"></i>
						</div> <a href="configuracao.jsp">Configuração</a>
					<li><div class="lg">
							<i class="fa-sharp fa-solid fa-right-to-bracket"></i>
						</div> <a href="../index.jsp">Sair Sistema</a></li>
				</ul>
			</div>
		</div>
		<div class="menu">
			<div>
				<div class="conta">
					<div class="select">
						<i class="fa-solid fa-calendar-days"></i>
						<p>Feriados</p>
					</div>
					<div class="usuario">
						<div class="usuari">
							<div>
								<img src="../img/user.png">
							</div>
							<div class="usr_name">
								<p><%out.print(Profile.sys_profile()); %></p>
							</div>
						</div>

					</div>
				</div>
				<div class="holiday_id">
					<form action="" method="post">
						<div class="camp-head">
							<div>
								<h3>Lista de feriados para cálculo de encargos por atraso
									1960 a 2050</h3>
							</div>
							<div>
								<button name="register" value="register"
									onclick='this.form.action="../Holiday"'>
									<i class="fa-solid fa-circle-check"></i>Salvar
								</button>
							</div>
						</div>
						<div class="cap-01">
							<div>
								<label>Data</label> <input type="date" name="data_feriado" value="<%out.print(HolidayCom.sys_hol(request.getParameter("edit"), request, response).getDataFeriado().equals(java.sql.Date.valueOf("2000-01-01"))?"":HolidayCom.sys_hol(request.getParameter("edit"), request, response).getDataFeriado());%>">
							</div>
							<div>
								<label>Descrição</label> <input type="text" name="descricao" value="<%out.print(HolidayCom.sys_hol(request.getParameter("edit"), request, response).getDescricao()==null?"":HolidayCom.sys_hol(request.getParameter("edit"), request, response).getDescricao());%>"> 
							</div>
							<div>
								<label>Ano</label> <input type="number" min="1960" name="ano" value="<%out.print(HolidayCom.sys_hol(request.getParameter("edit"), request, response).getAno()==0?"":HolidayCom.sys_hol(request.getParameter("edit"), request, response).getAno());%>">
							</div>

						</div>
						<div class="tabela">
							<div>
								<%
								    HolidayDAO feria = new HolidayDAO();
								    ArrayList<FeriadoModel> listFeriado = null;
								%>
								<table>
									<thead>
										<tr>
											<td><div>Data</div></td>
											<td><div>Descrição</div></td>
											<td>Ano</td>
											<td>Ação</td>
										</tr>
									</thead>
									<tbody>
										<%
											listFeriado = HolidayDAO.list();
											if (feria != null) {
												for (int i = 0; i < listFeriado.size(); i++) {
										%>
										<tr>
											<td>
												<%
												out.print(listFeriado.get(i).getDataFeriado());
												%>
											</td>
											<td>
												<%
												out.print(listFeriado.get(i).getDescricao());
												%>
											</td>
											<td>
												<%
												out.print(listFeriado.get(i).getAno());
												%>
											</td>
											<td>
												<div>
												    <button name="edit"
														value="<%out.print(listFeriado.get(i).getDataFeriado());%>"
														onclick=''>
														<img src="../img/edit-18.png">
													</button>
													<button name="delete"
														value="<%out.print(listFeriado.get(i).getCodigo());%>"
														onclick='this.form.action="../FeriadoRemove"'>
														<img src="../img/delete-18.png">
													</button>
												</div>
											</td>
										</tr>
										<%
										}
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- <div class="rodape">
				<p>&copy;2022 - Todos direitos reservados</p>
				<p>00-00-0000</p>
			</div> -->
		</div>
	</div>
	<script src="../script/holiday.js"></script>
</body>
</html>