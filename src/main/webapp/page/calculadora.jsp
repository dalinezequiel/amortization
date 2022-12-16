<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortiza��o</title>
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
						<i class="fa-solid fa-calculator"></i>
						<p>Calcular valor presente</p>
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
				<div class="calculadora">
					<div>
						<h3>Calcular valor real do empr�stimo | PRICE</h3>
					</div>
					<div class="c-1">
						<div>
							<label>Parcela (PMT)</label> <input type="text"
								placeholder="0,00">
						</div>
						<div>
							<label>Taxa (a.m.)</label> <input type="text" placeholder="0,00%">
						</div>
						<div>
							<label>Prazo (a.m.)</label> <input type="number" placeholder="0">
						</div>
					</div>
					<div class="c-1-1">
						<div>
							<label>Valor pago adicional</label> <input type="text"
								placeholder="0,00">
						</div>
					</div>
					<div class="c-2">
						<button>Calcular</button>
						<button>Limpar</button>
					</div>
					<div class="c-3">
						<div>
							<label>Valor do empr�stimo (VP)</label> <input type="text"
								placeholder="0,00">
						</div>
						<div>
							<label>Valor que voc� ir� pagar</label> <input type="text"
								placeholder="0,00">
						</div>
						<div>
							<label>Total pago</label> <input type="text" placeholder="0,00">
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
</body>
</html>