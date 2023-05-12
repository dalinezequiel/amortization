<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.simor.controller.*"
        import="java.util.*"
        import="com.simor.dao.*"
        import="com.simor.model.*"
%>
<%!
   private Appe app=null;
%>
<%
   app=new Appe(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/compare.css">
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
						</div> <a href="home.jsp">Dashboard</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calculator"></i>
						</div> <a href="calculadora.jsp">Calcular VP</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calendar-days"></i>
						</div> <a href="holiday.jsp">Feriados</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-money-bill-transfer"></i>
						</div> <a href="compare.jsp">Comparador</a></li>
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
						<i class="fa-solid fa-money-bill-transfer"></i>
						<p>Comparador</p>
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
				<div>
					<div class="tabela">
					<form action="" method="post">
						<div class="compar">
							<div>
							    <h3>Comparador entre sistemas | Cálculo normal</h3>
							</div>
							<div>
							    <button type="submit" name="calcular"><i class="fa-solid fa-circle-check"></i>Comparar</button>
						    </div>
						</div>
						<div class="tab">
							<div>
								<label>Valor do emprest. ou finânciamento</label> <input type="text" placeholder="0,00" name="emprest_financia" required>
							</div>
							<div>
							    <label>Taxa (%)</label> <input type="text" placeholder="0,00" name="taxa" required>
							</div>
							<div>
								<label>Prazo</label> <input type="number" min="0" placeholder="0,00" name="prazo" required>
							</div>
						</div>
						<div>
							<table>
								<thead>
									<tr>
										<td><div>
												Sistema
										</div></td>
										<td>1ª Parcela</td>
										<td>Última parcela</td>
										<td>Total de juros</td>
										<td>Total Pago</td>
									</tr>
								</thead>
								<tbody>
								    <%
								       if(app.sys_appe() != null){
								    	   for(int i=0; i<app.sys_appe().size(); i++){
								    	   %>
								    	      <tr>
								                   <td><div><%out.print(app.sys_appe().get(i).getSistema()); %></div></td>
								                   <td><%out.print(SistemaController.mascaraMoeda(app.sys_appe().get(i).getPrimeiraParcela()).equals(",00") || SistemaController.mascaraMoeda(app.sys_appe().get(i).getPrimeiraParcela()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.sys_appe().get(i).getPrimeiraParcela())); %></td>
								                   <td><%out.print(SistemaController.mascaraMoeda(app.sys_appe().get(i).getUltimaParcela()).equals(",00") || SistemaController.mascaraMoeda(app.sys_appe().get(i).getUltimaParcela()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.sys_appe().get(i).getUltimaParcela())); %></td>
								                   <td><%out.print(SistemaController.mascaraMoeda(app.sys_appe().get(i).getTotalJuro()).equals(",00")?"0,00":SistemaController.mascaraMoeda(app.sys_appe().get(i).getTotalJuro())); %></td>
								                   <td><%out.print(SistemaController.mascaraMoeda(app.sys_appe().get(i).getTotalPago()).equals(",00")?"0,00":SistemaController.mascaraMoeda(app.sys_appe().get(i).getTotalPago())); %></td>
								              </tr>
								    	   <%
								    	   }
								       }
								    %>
								</tbody>
							</table>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>