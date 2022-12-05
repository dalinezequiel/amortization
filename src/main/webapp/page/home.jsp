<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
							<i class="fa-solid fa-calendar"></i>
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
						<i class="fa-solid fa-bars"></i>
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
					<div class="camp">
						<div class="cap-01">
							<div>
								<label>Valor do emprest. ou finânciamento</label> <input
									type="text" placeholder="0,00">
							</div>
							<div>
								<label>Taxa</label> <input type="text" placeholder="0,00">
							</div>
							<div>
								<label>Essa será sua taxa mensal</label> <input type="text"
									placeholder="0,00">
							</div>
							<div>
								<label>Essa será sua taxa anual</label> <input type="text"
									placeholder="0,00">
							</div>

						</div>
						<div class="cap-03">
							<div>
								<label>Prazo</label> <input type="text" placeholder="0,00">
							</div>
							<div>
								<label>Índice de actualização</label> <input type="text"
									placeholder="0,00">
							</div>
							<div>
								<label>Carência</label> <input type="text" placeholder="0,00">
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
								<label>Data da primeira parcela]</label> <input type="date">
							</div>
							<div>
								<label>Tipo do balcão</label> <select>
									<option>Balcões e parcelas</option>
									<option>Sem balcões</option>
								</select>
							</div>
							<div>
								<label>Calcular atraso?</label> <select>
									<option>Sim</option>
									<option>Não</option>
								</select>
							</div>
						</div>
						<div class="cap-05">
							<div>
								<a>Calcular</a>
							</div>
							<div>
								<a>Salvar</a>
							</div>
							<div>
								<a>Consultar</a>
							</div>
							<div>
								<a>Limpar</a>
							</div>
						</div>
					</div>
					<div class="tabela">
						<div>
							<table>
								<thead>
									<tr>
										<td><div>
												N°<!-- <i class="fa-solid fa-arrow-down-a-z"></i> -->
											</div></td>
										<td><div>
												Vencimento<!-- <i class="fa-solid fa-arrow-down-a-z"></i> -->
											</div></td>
										<td><div>
												Prestação<!-- <i class="fa-solid fa-arrow-down-a-z"></i> -->
											</div></td>
										<td><div>
												Balcão
											</div></td>
										<td><div>
												Juros
											</div></td>
										<td><div>
												Amortização
											</div></td>
										<td><div>
												Saldo Devedor
											</div></td>
										<td><div>
												Índice
											</div></td>
										<!-- <td><div>
												Actualização mon.<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td>
										<td><div>
												Outro<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
										<td><div>
												Pagamento
											</div></td>
										<td><div>
												Multa
											</div></td>
										<td><div>
												Juros atraso
											</div></td>
										<!-- <td><div>
												Enc. Totais<i class="fa-solid fa-arrow-down-a-z"></i>
											</div></td> -->
									</tr>
								</thead>
								<tbody></tbody>
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
	<script src="../script/inicio.js"></script>
</body>
</html>