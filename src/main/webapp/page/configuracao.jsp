<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/configuracao.css">
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
						</div> <a href="">Movimentos</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-sack-dollar"></i>
						</div> <a href="">Meu Caixa</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calculator"></i>
						</div> <a href="">Calcular VP</a></li>
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
						<p>Configuração</p>
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
				<div class="config">
					<!-- <div class="btn-1">
						<div class="salvar">
							<button>Salvar</button>
						</div>
						<div class="resetar">
							<button>Testar Conexão</button>
						</div>
						<div class="salvar">
							<button>Localize</button>
						</div>
					</div> -->
					<div class="c-db">
						<h2>
							Configuração do banco de dados
						</h2>
					</div>
					<div>
						<div class="cf">
							<label>Database Management System</label> <select>
								<option>Microsoft Access</option>
								<option>Microsoft SQL Server</option>
								<option>MySQL</option>
								<option>PostgreSQL</option>
							</select>
						</div>
						<div class="cf">
							<label>Server</label> <input type="text">
						</div>
						<div class="cf">
							<label>Database</label> <input type="text">
						</div>
						<div class="cf">
							<label>Port</label> <input type="number">
						</div>
						<div class="cf">
							<label>Username</label> <input type="text">
						</div>
						<div class="cf">
							<label>Password</label> <input type="password">
						</div>
						<div class="botao">
							<div class="salvar">
								<button>Salvar</button>
							</div>
							<!-- <div class="resetar">
								<button>Testar Conexão</button>
							</div> -->
							<div class="salvar">
								<button>Localize</button>
							</div>
						</div>
					</div>
					<div></div>
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