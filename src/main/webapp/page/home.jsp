<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.simor.controller.*"
        import="java.util.*"
        import="com.simor.dao.*"
        import="com.simor.model.*"
%>
<%!
  private AppModel app = null;
%>
<%
  app = new App(request, response).sys_all();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
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
						<i class="fa-solid fa-chart-pie"></i>
						<p>Dashboard</p>
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
					<form action="" method="POST">
						<div class="camp">
							<div class="cap">
							<div class="cap-01">
								<div>
									<label>Valor do emprest. ou finânc.</label> <input
										type="text" placeholder="0,00" name="emprest_financia" required>
								</div>
								<div class="mensal_anual">
									<div>
									    <label>Taxa (%)</label> <input type="text" placeholder="0,00"
										    name="taxa" required>
									</div>
									<div>
									    <label>Periôdo</label> <select name="mensal_anual">
										   <option>Mensal</option>
										   <option>Anual</option>
									    </select>
									</div>
								</div>
								<div>
									<label>Essa será sua taxa mensal (%)</label> <input type="text"
										placeholder="0,00" name="taxa_mensal" value="<%if(app.getTax() != null){out.print(SistemaController.maskNum(app.getTax().getTaxaMensal())); }%>" readOnly>
								</div>
								<div>
									<label>Essa será sua taxa anual (%)</label> <input type="text"
										placeholder="0,00" name="taxa_anual" value="<%if(app.getTax() != null){out.print(SistemaController.maskNum(app.getTax().getTaxaAnual())); }%>" readOnly>
								</div>

							</div>
							<div class="cap-03">
								<div>
									<label>Prazo</label> <input type="number" min="0" placeholder="0,00"
										name="prazo" required>
								</div>
								<div class="indice_actual">
									<div>
									    <label>Índice actuali.</label> <select>
										   <option disabled selected>Sem índice</option>
										   <option>CDI</option>
										   <option>CUB-SC</option>
										   <option>IGP-M</option>
										   <option>INCC-DI</option>
										   <option>INPC</option>
										   <option>IPCA</option>
										   <option>SELIC</option>
									    </select>
									</div>
									<div>
									    <label>Incidên. índice</label> <select disabled>
										   <option disabled selected>Sem índice</option>
										   <option>Balão</option>
										   <option>Balão e parcela</option>
										   <option>Parcela</option>
										   <option>Parcela 12m</option>
										   <option>Saldo devedor</option>
										   <option>Saldo 12 meses</option>
									    </select>
									</div>
								</div>
								<div>
									<label>Carência</label> <input type="number" min="0" placeholder="0,00">
								</div>
								<div>
									<label>Sistema de amortização</label> <select name="sys_amort" required>
										<option>Gaus</option>
										<option>PRICE</option>
										<option>SAC</option>
										<option>SAC.JS</option>
										<option>SACRE</option>
										<option>MAJS/ Hamburgues</option>
									</select>
								</div>
							</div>
							<div class="cap-02">
								<div>
									<label>Data da contratação</label> <input type="date">
								</div>
								<div>
									<label>Primeira parcela</label> <input type="date" name="ultima_parcela" required>
								</div>
								<div>
									<label>Tipo do balão</label> <select name="tipo_balao">
										<option>Balões e parcelas</option>
										<option selected>Sem balões</option>
										<option>Somente balões</option>
									</select>
								</div>
								<div>
									<label>Calcular atraso?</label> <select name="calc_atraso">
										<option>Sim</option>
										<option selected>Não</option>
									</select>
								</div>
							</div>
							<div class="cap-04">
								<div class="periodo">
									<label>Periocidicidade do balão</label> <input type="number" min="0" placeholder="0,00" readOnly>
								</div>
								<div class="periodo">
									<label>Quantidade de balões</label> <input type="number" min="0" placeholder="0,00" readOnly>
								</div>
								<div class="periodo">
									<label>Valor do balão</label> <input type="text" placeholder="0,00" readOnly>
								</div>
								<div class="multa">
									<div>
									    <label>Multa (%)</label> <input type="text" placeholder="0,00" readOnly>
									</div>
									<div>
									    <label>Juro atr. (%)</label> <input type="text" placeholder="0,00" readOnly>
									</div>
								</div>
							</div>
							</div>
							<div class="cap-05">
								<div>
									<button name="calcular">Calcular</button>
								</div>
								<div>
									<button name="salvar">Salvar</button>
								</div>
								<div>
									<button>Consultar</button>
								</div>
								<div>
									<button>Limpar</button>
								</div>
							</div>
						</div>
					</form>
					<div class="tabela">
					    <h4>Lista de resultados</h4>
						<div>
							<table>
								<thead>
									<tr>
										<td><div>
												N°<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Vencimento<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Prestação<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Balcão<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Juros<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Amortização<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Saldo Devedor<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Índice<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<!-- <td><div>
												Actualização mon.<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Outro<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
										<!-- <td><div>
												Pagamento<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
										<td><div>
												Multa<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Juros atraso<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Enc. Totais<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
									</tr>
								</thead>
								<tbody>
									<%
									   if(app.getList() != null){ 
										   for(int i=0; i<app.getList().size(); i++){
											   %>
											   <tr>
											    <td><%out.print(SistemaController.getCounter(i)); %></td>
											    <td><%out.print(SistemaController.getFormatedDate(app.getList().get(i).getDataVencimento().toString()));%></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao())); %></td>
											    <td></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getJuro())); %></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getAmortizacao())); %></td>
											    <td><%if((app.getList().get(i).getValorEmprestFinac() < app.getList().get(i).getAuxilio().getIntAux()) 
											    		|| (app.getList().get(i).getValorEmprestFinac() > app.getList().get(i).getAuxilio().getDoubleAux())){
											    	out.print(SistemaController.mascaraMoeda(app.getList().get(i).getValorEmprestFinac()));}
											    else
											    	{out.print("0,00");} %></td>
											    <td></td>
											    <td></td>
											    <td></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao())); %></td>
											   </tr>
											   <%
										   }
										   app.setList(SistemaController.getReset());
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
				<p><%out.print(SistemaController.getDataActual()); %></p>
			</div>
		</div>
	</div>
	<script src="../script/inicio.js"></script>
</body>
</html>