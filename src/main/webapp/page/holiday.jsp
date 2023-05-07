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
					<div class="usuari">
						<div>
							<img src="../img/user.png">
						</div>
						<div class="usr_name">
							<p>Manuel Carlos Macuacua</p>
						</div>
					</div>
					
					</div>
				</div>
				<div class="holiday_id">
				<form action="../FeriadoServ" method="post">
					<div class="camp-head">
						<div>
						    <h3>Lista de feriados para cálculo de encargos por atraso
							1960 a 2050</h3>
						</div>
						<div>
							<button name="salva">Salvar</button>
						</div>
					</div>
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
												Data
											</div></td>
										<td><div>
												Decrição
											</div></td>
										<td>Ano</td>
										<td>Ação</td>
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
									       <td>
									          <div>
									             <button name="edit" value="<%out.print(listFeriado.get(i).getCodigo()); %>" onclick=''><!-- onclick='this.form.action="../DiagnosticoRelatorioServlet"' -->
									                <img src="../img/edit-18.png">
									             </button>
									             <button name="delete" value="<%out.print(listFeriado.get(i).getCodigo()); %>" onclick=''>
									                <img src="../img/delete-18.png">
									             </button></div>
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
		</div>
	</div>
	<script src="../script/feriado.js"></script>
</body>
</html>