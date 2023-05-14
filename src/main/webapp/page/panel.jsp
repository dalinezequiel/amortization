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
<link rel="stylesheet" type="text/css" href="../css/panel.css">
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
						</div> <a href="calculator.jsp?">Calcular VP</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calendar-days"></i>
						</div> <a href="holiday.jsp">Feriados</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-money-bill-transfer"></i>
						</div> <a href="comparator.jsp">Comparador</a></li>
				</ul>
			</div>
			<div>
				<ul class="conf_sair">
					<li><div class="lg">
							<i class="fa-solid fa-gear"></i>
						</div> <a href="configuration.jsp">Configuração</a></li>
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
					<div class="usuari">
						<div>
							<img src="../img/user.png">
						</div>
						<div class="usr_name">
							<p><%out.print(Profile.sys_profile()); %></p>
						</div>
					</div>
					<div>
					<div class="opcao">
						<div>
							<label class="execute" for="calculate"><img src="../img/play.png"></label>
						</div>
						<div>
							<label class="executa" for="printer"><img src="../img/printer-24.png"></label>
						</div>
					</div>
					</div>
					</div>
				</div>
				<div>
					<form method="POST">
						<div class="camp">
							<div class="cap">
							<div class="cap-01">
								<div>
									<label>Valor do emprest. ou finânciamento</label> <input
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
								<div class="carencia">
									<div>
									    <label>Carência</label> <input type="number" min="0" placeholder="0,00" name="carencia">
									</div>
									<div>
									    <label>Tipo de Carência</label> 
									    <select name="tipo_carencia">
										   <option>Sem juros</option>
										   <option>Com juros</option>
									    </select>
									</div>
								</div>
								<div>
									<label>Sistema de amortização</label> <select name="sys_amort" required>
										<option>Gaus</option>
										<option>PRICE</option>
										<option>SAC</option>
										<option>SAC.JS</option>
										<option>SACRE</option>
										<option>MAJS/ Hamburgues</option>
										<option>Não Periódicas</option>
										<option>SAL</option>
									</select>
								</div>
							</div>
							<div class="cap-02">
								<div>
									<label>Data da contratação</label> <input type="date" name="data_contratacao" required>
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
									<label>Periocidicidade do balão</label> <input type="text" placeholder="0,00" name="periodo_balao" readOnly>
								</div>
								<div class="periodo">
									<label>Quantidade de balões</label> <input type="text" min="0" placeholder="0,00" name="quant_balao" readOnly>
								</div>
								<div class="periodo">
									<label>Valor do balão</label> <input type="text" placeholder="0,00" name="valor_balao" readOnly>
								</div>
								<div class="multa">
									<div>
									    <label>Multa (%)</label> <input type="text" placeholder="0,00" name="multa" readOnly>
									</div>
									<div>
									    <label>Juro atr. (%)</label> <input type="text" placeholder="0,00" name="juro_atraso" readOnly>
									</div>
								</div>
							</div>
							</div>
							<div class="cap-05">
							<div>	
								<div style="display:none">
									<button id="calculate" type="submit" name="calcular" onclick='this.form.action=""'></button>
								</div>
								<div style="display:none">
									<button id="printer" type="submit" name="print" onclick='this.form.action="../Report"'></button>
								</div>
							</div>
						</div>
						</div>
					</form>
					<div class="tabela">
						<div>
							<table>
								<thead>
									<tr>
										<td><div>
												N°
											</div></td>
										<td><div>
												Vencimento
											</div></td>
										<td>Prestação</td>
										<td>Balcão</td>
										<td>Juros</td>
										<td>Amortização</td>
										<td>Saldo Devedor</td>
										<td>Índice</td>
										<!-- <td><div>
												Actualização mon.<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Outro<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
										<!-- <td><div>
												Pagamento<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
										<td>Multa</td>
										<td>Jur. atraso</td>
										<td>Enc. Totais</td>
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
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao()).equals(",00")||SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao())); %></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getBalao()).equals(",00")?"0,00": SistemaController.mascaraMoeda(app.getList().get(i).getBalao())); %></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getJuro()).equals(",00")||SistemaController.mascaraMoeda(app.getList().get(i).getJuro()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.getList().get(i).getJuro())); %></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getAmortizacao()).equals(",00")||SistemaController.mascaraMoeda(app.getList().get(i).getAmortizacao()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.getList().get(i).getAmortizacao())); %></td>
											    <td><%if((app.getList().get(i).getValorEmprestFinac() < app.getList().get(i).getAuxilio().getIntAux()) 
											    		|| (app.getList().get(i).getValorEmprestFinac() > app.getList().get(i).getAuxilio().getDoubleAux())){
											    	out.print(SistemaController.mascaraMoeda(app.getList().get(i).getValorEmprestFinac()));}
											    else
											    	{out.print("0,00");} %></td>
											    <td>0,00</td>
											    <td><%out.println(SistemaController.mascaraMoeda(app.getLatePayment().getMulta()).equals(",00")||SistemaController.mascaraMoeda(app.getLatePayment().getMulta()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.getLatePayment().getMulta())); %></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getLatePayment().getJuroAtraso()).equals(",00")||SistemaController.mascaraMoeda(app.getLatePayment().getJuroAtraso()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.getLatePayment().getJuroAtraso())); %></td>
											    <td><%out.print(SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao()).equals(",00")||SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao()).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.getList().get(i).getPrestacao())); %></td>
											   </tr>
											   <%
										   }
										   app.getList().clear();
										   app.setList(SistemaController.getReset());
									   }
									%>
								</tbody>
							</table>
						</div>
						<div>
					</div>
			</div>
			</div>
		</div>
	</div>
	</div>
	<script src="../script/inicio.js"></script>
	<script src="../script/panel.js"></script>
</body>
</html>