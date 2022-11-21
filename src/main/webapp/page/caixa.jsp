<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/caixa.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
</head>
<body>
	<div class="parent">
		<div class="navegacao">
			<div>
				<h1 class="logo">
					<a href=""><span>SIMOR</span><span>TIZE</span></a>
				</h1>
				<ul class="u1">
					<li><div class="lg">
							<i class="fa-solid fa-chart-pie"></i>
						</div> <a href="../index.jsp">Dashboard</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-user"></i>
						</div> <a href="cliente.jsp">Meu Cliente</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-hand-holding-dollar"></i>
						</div> <a href="emprestimo.jsp">Empréstimo</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-money-bill-transfer"></i>
						</div> <a href="movimento.jsp">Movimentos</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-sack-dollar"></i>
						</div> <a href="caixa.jsp">Meu Caixa</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calculator"></i>
						</div> <a href="calculadora.jsp">Calcular VP</a></li>
					<li><div class="lg">
							<!-- <i class="fa-solid fa-check-double"></i> -->
							<i class="fa-solid fa-money-check-dollar"></i>
						</div> <a href="">Comparador</a></li>
				</ul>
			</div>
			<div>
				<ul>
					<li><div class="lg">
							<i class="fa-solid fa-gear"></i>
						</div> <a href="">Configuração</a>
					<li><div class="lg">
							<i class="fa-sharp fa-solid fa-right-to-bracket"></i>
						</div> <a href="">Sair Sistema</a></li>
				</ul>
			</div>
		</div>
		<div class="menu">
			<div>
				<div class="conta">
					<div class="select">
						<i class="fa-solid fa-bars"></i>
						<p>Meu Caixa</p>
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
				<div class="visuall">
					<div class="saldo">
						<div>
							<h4>Saldo disponível</h4>
						</div>
						<div class="sd">
							<h2>R$</h2>
							<h2>0,00</h2>
						</div>
					</div>
					<div class="entrada">
					<div class="sd">
							<p>Entradas (+)</p>
							<a href="">Incluir</a>
						</div>
					</div>
					<div class="saida">
					<div class="sd">
							<p>Saídas (-)</p>
							<a href="">Incluir</a>
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
	<script type="text/javascript" src="../script/index.js"></script>
</body>
</html>