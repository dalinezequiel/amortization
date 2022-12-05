<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortiza��o</title>
<link rel="stylesheet" type="text/css" href="../css/credencial.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
</head>
<body>
	<div class="parent">
		<div class="pa-1">
			<div>
				<div class="pa-1-1">
					<h1>SIMOR</h1>
					<p>Sistema de Amortiza��o</p>
				</div>
				<div class="pa-1-2">
					<div class="p-1-2-0">
						<h2>
							Optimiza��o, efic�cia.
						</h2>
					</div>
					<div class="p-1-2-1">
						<div>
							<i class="fa-solid fa-user-shield"></i>
						</div>
						<div>
							<p>Use suas cred�nciais para acessar o sistema gerenciador de
								empr�stimos.</p>
						</div>
					</div>
					<div class="p-1-2-2">
						<div>
							<i class="fa-solid fa-rotate-right"></i>
						</div>
						<div>
							<p>N�o partilhe as suas cred�nciais com terceiros, garanta a
								seguran�a do seu neg�cio.</p>
						</div>
					</div>
					<div class="p-1-2-3">
						<h4>
							<a href="">Precisa de ajuda ou suporte? Clique aqui!</a>
						</h4>
					</div>
				</div>
			</div>
			<div class="copi">
				<p>&copy;2022 - Todos direitos reservados</p>
			</div>
		</div>
		<div class="pa-2">
			<!-- <div class="p-2-1">
			<h2>Cred�ncial</h2>
				<div>
					<h2>Cred�ncial</h2>
				</div>
			</div> -->
			<form action="">
				<div class="p-2-1">
					<h2>Cred�ncial</h2>
					<p>Cria��o de cred�nciais</p>
					<!-- <div>
					<h2>Cred�ncial</h2>
				</div> -->
				</div>
				<div class="p-2-2">
					<div class="cf">
						<label>Perfil</label> <select>
							<option disabled>Administrador</option>
							<option>Normal</option>
						</select>
					</div>
					<div class="cf">
						<label>Email</label><input type="email">
					</div>
					<div class="cf">
						<label>Usu�rio</label><input type="text">
					</div>
					<div class="cf">
						<label>Senha</label><input type="password">
					</div>
					<div class="cf">
						<label>Comfirmar Senha</label><input type="password">
					</div>
					<div class="p-butoe">
						<div>
							<button>Salvar</button>
						</div>
						<div>
							<button onclick='this.form.action="../index.jsp"'>Voltar</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>