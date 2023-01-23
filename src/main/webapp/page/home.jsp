<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.simor.controller.*"
        import="java.util.*"
        import="com.simor.dao.*"
        import="com.simor.model.*"
%>
<%!
  HomeModel homeModelGlobal = new HomeModel();
  HomeCalculoController homeCalculoGlobal = null;
  CacheModel cacheModelGlobal = new CacheModel();
  Auxilio aux = new Auxilio();
  
  CalPriceController calPriceController = new CalPriceController();
  CalPriceModel calPriceModel = new CalPriceModel();
  CalculoModel calculoModel = null;
%>
<%
double val = 0;
if(request.getParameter("calcular") != null){
	
	calPriceController.getCalculoModelData(request, response);
	
	homeModelGlobal.setValorEmprestFinancia(SistemaController.isNullOrEmpty(request.getParameter("emprest_financia").trim()));
	homeModelGlobal.setTaxa(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa").trim())));
	homeModelGlobal.setTaxaMensal(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_mensal").trim())));
	homeModelGlobal.setTaxaAnual(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_anual").trim())));
	homeModelGlobal.setPrazo(Integer.parseInt(SistemaController.isNullOrEmpty(request.getParameter("prazo").trim())));
	
	val = Double.parseDouble(homeModelGlobal.getValorEmprestFinancia());
	homeCalculoGlobal = new HomeCalculoController(homeModelGlobal);

	cacheModelGlobal.setIdCache(SistemaController.getId());
	cacheModelGlobal.setPrestacao(homeCalculoGlobal.getCalculoDePrestacao());
	cacheModelGlobal.setJuro(homeCalculoGlobal.getCalculoDeJuro());
	cacheModelGlobal.setAmortizacao(homeCalculoGlobal.getCalculoDeAmortizacao());
	cacheModelGlobal.setSaldoDevedor(homeCalculoGlobal.getCalculoDeSaldoDevedor());
	cacheModelGlobal.setData(SistemaController.getFormatedDate(request.getParameter("ultima_parcela")).trim());
	
	CacheDAO.deleteCache();
	if(CacheDAO.insertIntoCache(cacheModelGlobal)){
	int a=0;
	}else{
		%>
		<h3>Ocorreu um erro ao cadastrar prestação!</h3>
		<%
		
	}
}else{
	cacheModelGlobal.setData("00-00-0000");
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
						<!-- <i class="fa-solid fa-bars"></i> -->
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
										type="text" placeholder="0.00" name="emprest_financia" required>
								</div>
								<div>
									<label>Taxa (%)</label> <input type="text" placeholder="0.00"
										name="taxa" required>
								</div>
								<div>
									<label>Essa será sua taxa mensal (%)</label> <input type="text"
										placeholder="0.00" name="taxa_mensal">
								</div>
								<div>
									<label>Essa será sua taxa anual (%)</label> <input type="text"
										placeholder="0.00" name="taxa_anual">
								</div>

							</div>
							<div class="cap-03">
								<div>
									<label>Prazo</label> <input type="number" min="0" placeholder="0"
										name="prazo" required>
								</div>
								<div>
									<label>Índice de actualização</label> <select>
										<option>*Sem índice</option>
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
									<label>Carência</label> <input type="number" min="0" placeholder="0">
								</div>
								<div>
									<label>Sistema de amortização</label> <select>
										<option>Gaus</option>
										<option>PRICE</option>
										<option>SAC.JS</option>
										<option>SACRE</option>
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
									<label>Tipo do balão</label> <select>
										<option>Balões e parcelas</option>
										<option>Sem balões</option>
									</select>
								</div>
								<div>
									<label>Calcular atraso?</label> <select>
										<option>Sim</option>
										<option>Não</option>
									</select>
								</div>
							</div>
							<div class="cap-04">
								<div>
									<label>Periocidicidade do balão</label> <input type="number" min="0" placeholder="0">
								</div>
								<div>
									<label>Quantidade de balões</label> <input type="number" min="0" placeholder="0">
								</div>
								<div>
									<label>Valor do balão</label> <input type="text" placeholder="0.00">
								</div>
								<div>
									<label>Juros de atraso (%)</label> <input type="text" placeholder="0.00">
								</div>
							</div>
							</div>
							<div class="cap-05">
								<div>
									<!-- <a>Calcular</a> -->
									<button name="calcular">Calcular</button>
									<!-- onclick='this.form.action="../index.jsp"' -->
								</div>
								<div>
									<!-- <a>Salvar</a> -->
									<button name="salvar">Salvar</button>
								</div>
								<div>
									<!-- <a>Consultar</a> -->
									<button>Consultar</button>
								</div>
								<div>
									<!-- <a>Limpar</a> -->
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
									   ArrayList<CacheModel> list = CacheDAO.listaCache();
									   if(!list.isEmpty() && list.get(0).getJuro() != 0.0){ 
										   //double val = homeModelGlobal.getValorEmprestFinancia();
										   double vd = val;
										   double prt = cacheModelGlobal.getPrestacao();
										   double jri = HomeCalculoController.getPercentual(homeModelGlobal.getTaxa());
										   double jr = 1;
										   double am = jr;
										   //double vd = am;
										   //calculoModel = calPriceController.getCalculoModelData(request, response);
										   calculoModel = new CalPriceController(request, response).calculoModelTeste();
										   
										   calPriceModel.setValorEmprestFinac(calculoModel.getValorEmprestFinancia());
										   calPriceModel.setJuro(CalPriceController.getPercentualPriceCalculado());
										   
										   System.out.println("Teste devo: " + calculoModel.getValorEmprestFinancia());
										   System.out.println("Teste prestação devo: " + new CalPriceController().getCalculoDePrestacao(calculoModel));
										   for(int i=0; i<12; i++){
											   jr = jri * vd;
											   am = prt - jr;
											   vd -= am;
											   //homeModelGlobal.setValorEmprestFinancia(Double.parseDouble(homeModelGlobal.getValorEmprestFinancia()) - i+"");
											   //calPriceModel.setAmortizacao(amortizacao)
											   %>
											   <tr>
											    <td><%out.print(calPriceModel.getJuro()); %></td>
											    <!-- <td><%//out.print(i+1); %></td> -->
											    <td><%out.print(cacheModelGlobal.getData()); %></td>
											    <td><%out.print(HomeCalculoController.mascaraMoeda(prt)); %></td>
											    <td></td>
											    <td><%out.print(HomeCalculoController.mascaraMoeda(jr)); %></td>
											    <td><%out.print(HomeCalculoController.mascaraMoeda(am)); %></td>
											    <td><%out.print(HomeCalculoController.mascaraMoeda(vd)); %></td>
											    <td></td>
											    <td></td>
											    <td></td>
											    <td><%out.print(HomeCalculoController.mascaraMoeda(list.get(0).getPrestacao())); %></td>
											   </tr>
											   <%
										   }
									   }
									   CacheDAO.deleteCache();
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
	<!-- <script src="../script/inicio.js"></script> -->
</body>
</html>