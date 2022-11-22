<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/cliente.css">
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
						</div> <a href="configuracao.jsp">Configuração</a>
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
						<p>Cliente</p>
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
					<div class="visual">
						<form action="">
							<div class="identificacao">
								<div>
									<label>Código</label> <input type="number" autocomplete="off"
										required>
								</div>
								<div>
									<label>CPF</label> <input type="number" autocomplete="off"
										required>
								</div>
								<div>
									<label>CEP</label> <input type="number" autocomplete="off"
										required>
								</div>
								<div>
									<label>Nome Cliente</label> <input type="text" required>
								</div>
							</div>
							<div class="endereco">
								<div class="ender">
									<div>
										<label>Estado</label> <select required>
											<option selected disabled>Selecione seu estado</option>
											<option>São Paulo</option>
											<option>Rio de Janeiro</option>
											<option>Paraíba</option>
											<option>Rio Grande do Norte</option>
										</select>
									</div>
									<div>
										<label>Cidade</label> <input type="text" required>
									</div>
									<div>
										<label>Bairro</label> <input type="text">
									</div>
								</div>
								<div class="ender">
									<div>
										<label>Avenida/ Rua</label> <input type="text" required>
									</div>
									<div>
										<label>Número</label> <input type="text">
									</div>
								</div>
							</div>
							<div class="contacto">
								<div class="contac">
									<div>
										<label>Email</label> <input type="email">
									</div>
									<div>
										<label>Telefone Celular</label> <input type="text" required>
									</div>
									<div>
										<label>Telefone Fixo</label> <input type="text">
									</div>
								</div>
								<div class="contac">
									<div>
										<label>WhatsApp</label> <input type="text">
									</div>
								</div>
							</div>
							<div class="check-cliente">
								<div>
									<input id="check" type="checkbox"> <label for="check">Preencher
										com o número do campo de telefone celular?</label>
								</div>
							</div>
							<div class="estado">
								<div>
									<label>Cliente Activo</label> <input type="text">
								</div>
								<div>
									<label>Data Registo</label> <input type="date">
								</div>
							</div>
							<div class="botao">
								<div class="salvar">
									<button>Salvar</button>
								</div>
								<div class="resetar">
									<button>Consulta</button>
								</div>
							</div>
						</form>
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