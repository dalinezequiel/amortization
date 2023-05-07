<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/compare.css">
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
						<!-- <i class="fa-solid fa-bars"></i> -->
						<i class="fa-solid fa-money-bill-transfer"></i>
						<p>Comparador</p>
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
				<div>
					<div class="tabela">
						<div class="compar">
							<div>
							    <h3>Comparador entre sistemas | Cálculo normal</h3>
							</div>
							<div>
							    <button name="salva">Comparar</button>
						    </div>
						</div>
						<div class="tab">
							<div>
								<label>Valor do emprest. ou finânciamento</label> <input type="text" placeholder="0,00">
							</div>
							<div>
							    <label>Taxa (%)</label> <input type="text" placeholder="0,00" name="taxa" required>
							</div>
							<div>
								<label>Prazo</label> <input type="number" min="0" placeholder="0,00" name="prazo" required>
							</div>
							<!-- <div class="cap-05">
								<div>
									<a>Comparar</a>
								</div>
								<div>
									<a>Limpar</a>
								</div>
							</div> -->
						</div>
						<div>
							<table>
								<thead>
									<tr>
										<td><div>
												Sistema
											</div></td>
										<td>1ª Parcela</td>
										<td>Última parcela</td>
										<td>Total de juros</td>
										<td>Total Pago</td>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>