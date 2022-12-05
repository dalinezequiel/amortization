<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortiza��o</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/emprestimo.css">
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
						</div> <a href="../index.jsp">Dashboard</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-user"></i>
						</div> <a href="cliente.jsp">Meu Cliente</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-hand-holding-dollar"></i>
						</div> <a href="emprestimo.jsp">Empr�stimo</a></li>
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
						<i class="fa-solid fa-bars"></i>
						<p>Empr�stimo</p>
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
							<div>
								<div class="identificacao">
									<div class="d-1">
										<label>C�digo</label> <input type="text">
									</div>
									<div class="d-1">
										<label>C�d. Cliente</label> <input type="text">
									</div>
									<div class="nome">
										<div>
											<label>Nome cliente</label>
										</div>
										<div>
											<input type="text">
											<div>
												<button>:::</button>
											</div>
										</div>
									</div>
								</div>
								<div class="capital">
									<div class="cpt">
										<label>Capital</label> <input type="text" placeholder="0,00">
									</div>
									<div>
										<label>Juros (%)</label> <input type="text">
									</div>
									<div>
										<label>Juros</label> <input type="text" placeholder="0,00">
									</div>
									<div>
										<label>Juros atr.(%)</label> <input type="text">
									</div>
									<div>
										<label>Juros atraso</label> <input type="text"
											placeholder="0,00">
									</div>
								</div>
								<div class="parcela">
									<div>
										<label>N�mero de parcelas</label> <input type="number">
									</div>
									<div>
										<!-- <label>Parcela �nica</label> <input type="text"> -->
										<label>Parcela �nica</label> <select>
											<option>Sim</option>
											<option>N�o</option>
										</select>
									</div>
									<div>
										<label>Primeira parcela</label> <input type="date">
									</div>
									<div>
										<label>�ltima parcela</label> <input type="date">
									</div>
								</div>
								<div class="tabel">
									<div>
										<h4>Distribui��o das parcelas</h4>
									</div>
									<div>
										<table>
											<thead>
												<tr>
													<td>C�digo</td>
													<td>Parcela</td>
													<td>Capital</td>
													<td>Juros</td>
													<td>Juros atraso</td>
													<td>Data Pagamento</td>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
								<div class="total">
									<div class="tot-1">
										<div>
											<label>Data Registo</label> <input type="date">
										</div>
									</div>
									<div class="tot-2">
										<div>
											<label>Juros total</label> <input type="text"
												placeholder="0,00">
										</div>
										<div>
											<label>Mont. em d�vida</label> <input type="text"
												placeholder="0,00">
										</div>
										<div>
											<label>Montante total</label> <input type="text"
												placeholder="0,00">
										</div>
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
</body>
</html>