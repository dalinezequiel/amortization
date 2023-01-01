<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.simor.controller.HomeCalculoController"
        import="java.util.*"
        import="com.simor.dao.*"
        import="com.simor.model.*"
%>

<!-- import="jakarta.servlet.ServletException"
        import="jakarta.servlet.http.HttpServlet"
        import="jakarta.servlet.http.HttpServletRequest"
        import="jakarta.servlet.http.HttpServletResponse"
        import="java.io.IOException" -->

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortiza��o</title>
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
						</div> <a href="configuracao.jsp">Configura��o</a>
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
							<div class="cap-01">
								<div>
									<label>Valor do emprest. ou fin�nciamento</label> <input
										type="text" placeholder="0,00" name="emprest_financia">
								</div>
								<div>
									<label>Taxa (%)</label> <input type="text" placeholder="0,00"
										name="taxa">
								</div>
								<div>
									<label>Essa ser� sua taxa mensal (%)</label> <input type="text"
										placeholder="0,00" name="taxa_mensal">
								</div>
								<div>
									<label>Essa ser� sua taxa anual (%)</label> <input type="text"
										placeholder="0,00" name="taxa_anual">
								</div>

							</div>
							<div class="cap-03">
								<div>
									<label>Prazo</label> <input type="text" placeholder="0,00"
										name="prazo">
								</div>
								<div>
									<label>�ndice de actualiza��o</label> <select>
										<option>*Sem �ndice</option>
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
									<label>Car�ncia</label> <input type="text" placeholder="0,00">
								</div>
								<div>
									<label>Sistema de amortiza��o</label> <select>
										<option>Gaus</option>
										<option>PRICE</option>
										<option>SAC.JS</option>
										<option>SACRE</option>
									</select>
								</div>
							</div>
							<div class="cap-02">
								<div>
									<label>Data da contrata��o</label> <input type="date">
								</div>
								<div>
									<label>Data da primeira parcela]</label> <input type="date">
								</div>
								<div>
									<label>Tipo do bal�o</label> <select>
										<option>Bal�es e parcelas</option>
										<option>Sem bal�es</option>
									</select>
								</div>
								<div>
									<label>Calcular atraso?</label> <select>
										<option>Sim</option>
										<option>N�o</option>
									</select>
								</div>
							</div>
							<div class="cap-05">
								<div>
									<!-- <a>Calcular</a> -->
									<button onclick='this.form.action="../Home"'>Calcular</button>
									<!-- onclick='this.form.action="../index.jsp"' -->
								</div>
								<div>
									<!-- <a>Salvar</a> -->
									<button>Salvar</button>
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
						<div>
							<table>
								<thead>
									<tr>
										<td><div>
												N�<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Vencimento<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Presta��o<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Balc�o<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Juros<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Amortiza��o<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Saldo Devedor<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												�ndice<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<!-- <td><div>
												Actualiza��o mon.<i class="fa-solid fa-arrow-down-a-z"></i>
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
										<!-- <td><div>
												Enc. Totais<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
									</tr>
								</thead>
								<tbody>
									<%
									   //HomeCalculoController homeCalculo = new HomeCalculoController();
									     ArrayList<CacheModel> list = CacheDAO.listaCache();
									     //CacheDAO che = new CacheDAO();
									   //out.print("2");
									   //String c = request.getParameter("emprest_financia").trim();
									  // Home h = new Home();
									   //HomeModel homeModel = homeCalculo.requestHomeModel(request, response);

									   if(list != null){
										   //homeModel = sr.requestHomeModel(request, response);
										   //hc = new HomeCalculoController(homeModel);
										   
										   //for(int i=0; i<=homeModel.getPrazo(); i++){
											   %>
											   <tr>
											    <td><%out.print(HomeCalculoController.mascaraMoeda(3467)); %></td>
											   </tr>
											   <%
										   //}
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
	<!-- <script src="../script/inicio.js"></script> -->
</body>
</html>