<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page
   import="com.simor.dao.*, com.simor.model.*"
   import="java.util.*"
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
						<i class="fa-solid fa-calendar-days"></i>
						<p>Feriados</p>
					</div>
					<div class="usuario">
						<div>
							<img src="../img/user.png">
						</div>
						<div>
							<p>Usuario001</p>
						</div>
					</div>
				</div>
				<div>
					<div class="camp-head">
						<h3>Lista de feriados para cálculo de encargos por atraso
							1960 a 2050</h3>
					</div>
					<form action="../FeriadoServ" method="post">
					<div class="camp">
						<div class="cap-01">
							<div>
								<label>Data</label> <input type="date" name="data_feriado" required>
							</div>
							<div>
								<label>Descrição</label> <input type="text" name="descricao" required>
							</div>
							<div>
								<label>Ano</label> <input type="number" min="1960" name="ano" required>
							</div>

						</div>
						<div class="cap-05">
							<div>
							    <!-- <a>Salvar</a> -->
								<button name="salva">Salvar</button>
							</div>
							<div>
								<a>Limpar</a>
							</div>
						</div>
					</div>
					</form>
					<div class="tabela">
						<div>
						    <%
						        FeriadoDAO feria=new FeriadoDAO();
						        ArrayList<FeriadoModel> listFeriado = null;
						    %>
							<table>
								<thead>
									<tr>
										<td><div>
												Data<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Decrição<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Ano<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
									</tr>
								</thead>
								<tbody>
								 <%
								 listFeriado = FeriadoDAO.listaFeriado();
								  if(feria != null){
									  for(int i=0; i<listFeriado.size(); i++){
									  %>
									   <tr>
									       <td><%out.print(listFeriado.get(i).getDataFeriado()); %></td>
									       <td><%out.print(listFeriado.get(i).getDescricao()); %></td>
									       <td><%out.print(listFeriado.get(i).getAno()); %></td>
									   </tr>
									  <%
									  }
								  }
								 %>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="rodape">
				<p>&copy;2022 - Todos direitos reservados</p>
				<p>00-00-0000</p>
			</div>
		</div>
	</div>
	<script src="../script/feriado.js"></script>
</body>
</html>