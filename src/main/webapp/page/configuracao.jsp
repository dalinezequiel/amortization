<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.simor.controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortiza��o</title>
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
				<ul class="u2">
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
						<i class="fa-solid fa-gear"></i>
						<p>Configura��o</p>
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

					</div>
				</div>
				<div class="config">
					<div class="butoe">
						<button>Minha Conta</button>
						<!-- <button>User</button> -->
						<button>Ajuda e Suporte</button>
					</div>
					<div class="info">
						<div class="in-1">
							<div class="cf">
								<label>Server</label> <input type="text"
									placeholder="Default: localhost">
							</div>
							<div class="cf">
								<label>Database</label> <input type="text">
							</div>
							<div class="cf">
								<label>Port</label> <input type="number"
									placeholder="Default: 5432">
							</div>
							<div class="cf">
								<label>Username</label> <input type="text"
									placeholder="Default: postgres">
							</div>
							<div class="cf">
								<label>Password</label> <input type="password">
							</div>
						</div>
						<div class="in-2">
							<div>
								<label>Email</label><input type="email">
							</div>
							<div>
								<label>Usu�rio</label><input type="text">
							</div>
							<div>
								<label>Senha</label><input type="password">
							</div>
							<div>
								<label>Comfirmar Senha</label><input type="password">
							</div>
						</div>
						<div class="in-3">
							
						</div>
					</div>
					<div class="serv">
						<div class="ser-1">
							<div>
								<label>Server: N/E</label>
							</div>
							<div>
								<label>Database: N/E</label>
							</div>
							<div>
								<label>Port: N/E</label>
							</div>
						</div>
						<div class="ser-2">
							<i class="fa-solid fa-database"></i><label>Disconnected</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../script/configuracao.js"></script>
</body>
</html>