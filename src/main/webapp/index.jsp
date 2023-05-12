<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SIMOR - Sistema de Amortização</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
</head>
<body>
	<div class="parent">
		<div class="pa-1">
			<div>
				<div class="pa-1-1">
					<h1>SIMOR</h1>
					<p>Sistema de Amortização</p>
				</div>
				<div class="pa-1-2">
					<div class="p-1-2-0">
						<h2>
							Optimização, eficácia.
						</h2>
					</div>
					<div class="p-1-2-1">
						<div>
							<i class="fa-solid fa-user-shield"></i>
						</div>
						<div>
							<p>Use suas credênciais para acessar o sistema gerenciador de empréstimos.</p>
						</div>
					</div>
					<div class="p-1-2-2">
						<div>
							<i class="fa-solid fa-rotate-right"></i>
						</div>
						<div>
							<p>Não partilhe as suas credênciais com terceiros, garanta a segurança do seu negócio.</p>
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
			<div class="p-2-1">
				<div>
					<h2>Olá, bem vindo!</h2>
				</div>
				<div>
					<label>Novo no Simor?<a href="page/credencial.jsp">Criar credênciais</a></label>
				</div>
			</div>
			<form action="Login" method="post">
				<div class="p-2-2">
					<div>
						<label>Usuário ou email</label> <input type="text" name="usr_email">
					</div>
					<div>
						<label>Senha</label> <input type="password" name="pass">
					</div>
					<div class="error">
						<p>Usuário ou Senha incorrectos!</p>
					</div>
					<div>
						<button name="login" value="teste">Log in</button><!-- onclick='this.form.action="page/home.jsp"' -->
					</div>
					<div class="p-2-2-1">
						<div>
							<input id="check" type="checkbox"><label for="check">Lembre-me</label>
						</div>
						<div>
							<a href="page/forgot.jsp">Esqueceu a senha?</a>
						</div>
					</div>
				</div>
				<div class="social">
					<div>
						<p></p>
					</div>
					<div>
						<a href="https://www.youtube.com/@jorgeabrao" target="_blank"><i class="fa-brands fa-youtube"></i></a>
						<a href="https://www.instagram.com/jorge.abrao/" target="_blank"><i class="fa-brands fa-instagram"></i></a>
						<a href="https://universoadministracao.com/" target="_blank"><i class="fa-solid fa-globe"></i></a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>