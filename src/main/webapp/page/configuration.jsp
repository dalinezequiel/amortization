<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.simor.controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="../css/panel.css">
<link rel="stylesheet" type="text/css" href="../css/configuration.css">
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
						</div> <a href="panel.jsp">Dashboard</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calculator"></i>
						</div> <a href="calculator.jsp">Calcular VP</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-calendar-days"></i>
						</div> <a href="holiday.jsp">Feriados</a></li>
					<li><div class="lg">
							<i class="fa-solid fa-money-bill-transfer"></i>
						</div> <a href="comparator.jsp">Comparador</a></li>
				</ul>
			</div>
			<div>
				<ul class="u2">
					<li><div class="lg">
							<i class="fa-solid fa-gear"></i>
						</div> <a href="configuration.jsp">Configuração</a>
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
						<p>Configuração</p>
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
						<button>Ajuda e Suporte</button>
					</div>
					<div class="info">
						<div class="in-1-1">
							<div class="in-1">
							    <div>
							          <img src="../img/user-128.png">
							    </div>
							     <div class="user_conf">
							          <div class="us">
							               <label>Usuário</label>
							               <label><%out.print(Profile.sys_profile()); %></label>
							          </div>
							          <div class="us">
							               <label>Email</label>
							               <label><%out.print(Profile.sys_prof(Profile.sys_profile()).getEmail()); %></label>
							          </div>
							          <div class="us">
							               <label>Senha</label>
							               <label><%out.print(Profile.sys_prof(Profile.sys_profile()).getSenha()); %></label>
							          </div>
							      </div>
							  </div><form action="" method="post">
							  <div class="del_conta">
							      <div>
							          <h4>Deletar esta conta</h4>
							          <p>Uma vez conta deletada, não terá mais acesso ao sistema. Por favor, tenha certeza.</p>
							      </div>
							      <div>
							          <button value="<%out.print(Profile.sys_prof(Profile.sys_profile()).getIdConta()); %>" onclick='this.form.action="../Delecount"' name="delete_account">Deletar esta conta</button>
							      </div>
							  </div></form>
						</div>
						<div class="in-2">
						    <div class="admins">
						        <a href="https://universoadministracao.com/">Universo Administração<i class="fa-solid fa-arrow-right"></i></a>
						    </div>
						</div>
						<div class="in-3">
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../script/configuration.js"></script>
</body>
</html>