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
						<i class="fa-solid fa-gear"></i>
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
					<div class="butoe">
						<button>Database</button>
						<button>User</button>
						<button>Help And Support</button>
					</div>
					<div class="info">
						<div class="in-1">
							<div class="cf">
								<label>Database Management System</label> <select>
									<option disabled>Microsoft Access</option>
									<option disabled>Microsoft SQL Server</option>
									<option disabled>MySQL</option>
									<option>PostgreSQL</option>
								</select>
							</div>
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
							<div class="botao">
								<div>
									<button>Salvar</button>
								</div>
								<div>
									<button>Testar Conexão</button>
								</div>
							</div>
						</div>
						<div class="in-2">
							<div class="cf">
								<label>Perfil</label> <select>
									<option disabled>Administrador</option>
									<option>Normal</option>
								</select>
							</div>
							<div>
								<label>Email</label><input type="email">
							</div>
							<div>
								<label>Usuário</label><input type="text">
							</div>
							<div>
								<label>Senha</label><input type="password">
							</div>
							<div>
								<label>Comfirmar Senha</label><input type="password">
							</div>
							<div class="bota">
								<div>
									<button>Salvar</button>
								</div>
								<div>
									<button>Excluir</button>
								</div>
							</div>
						</div>
						<div class="in-3">
							<div>
								<label>Sobre Aplicação</label>
								<p>
									Categoria "Sistema Finânceiro"<br> Sub Categoria "Gestão
									de Amortização"<br> Versão 1.0.0 <br> &copy;2022
									Dalin Academy&#8482;<br> &copy;Todos direitos reservados
								</p>
							</div>
							<div>
								<label>Website Oficial</label> <a
									href="https://www.simor.com">https://www.simor.com</a>
							</div>
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
			<div class="rodape">
				<p>&copy;2022 - Todos direitos reservados</p>
				<p>00-00-0000</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../script/configuracao.js"></script>
</body>
</html>