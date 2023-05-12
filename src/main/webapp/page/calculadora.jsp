<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	
	import="com.simor.controller.*"
%>
<%!
   private Appo app=null;
%>
<%
   app=new Appo(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/calculadora.css">
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
						<i class="fa-solid fa-calculator"></i>
						<p>Calcular valor presente</p>
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
				<div class="calculadora">
				<form action="" method="post">
					<div class="ladora">
						<div>
						    <h3>Calcular valor real do empréstimo | PRICE</h3>
						</div>
						<div>
							<button type="submit" name="calcular"><i class="fa-solid fa-circle-check"></i>Calcular</button>
						</div>
					</div>
					<div class="c-1">
						<div>
							<label>Parcela (PMT)</label> <input type="text" placeholder="0,00" name="parcela" required>
						</div>
						<div>
							<label>Taxa (a.m.)</label> <input type="text" placeholder="0,00%" name="taxa">
						</div>
						<div>
							<label>Prazo (a.m.)</label> <input type="number" placeholder="0" name="prazo" required>
						</div>
						<div>
							<label>Valor pago adicional</label> <input type="text"
								placeholder="0,00" name="adicional">
						</div>
					</div>
					<div class="c-3">
						<div>
							<label>Valor do empréstimo (VP)</label> <input type="text" 
							placeholder="0,00" value='<%out.print(SistemaController.mascaraMoeda(app.sys_cal().get(2)).equals(",00") || SistemaController.mascaraMoeda(app.sys_cal().get(2)).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.sys_cal().get(2))); %>'readOnly>
						</div>
						<div>
							<label>Valor que você irá pagar</label> <input type="text"
								placeholder="0,00" value='<%out.print(SistemaController.mascaraMoeda(app.sys_cal().get(0)).equals(",00") || SistemaController.mascaraMoeda(app.sys_cal().get(0)).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.sys_cal().get(0))); %>'readOnly>
						</div>
						<div>
							<label>Total pago</label> <input type="text" placeholder="0,00" value='<%out.print(SistemaController.mascaraMoeda(app.sys_cal().get(1)).equals(",00") || SistemaController.mascaraMoeda(app.sys_cal().get(1)).equals("NaN")?"0,00":SistemaController.mascaraMoeda(app.sys_cal().get(1))); %>' readOnly>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="../script/calculadora.js"></script>
</body>
</html>