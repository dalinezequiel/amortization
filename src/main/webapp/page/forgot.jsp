<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page
   import="com.simor.dao.*, com.simor.model.*"
 %>
 
 <%
	String user = request.getParameter("usuario");
	//user="voa";
 %>
 
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
			<form method="post"> <!--  action="../forgotServ" -->
				<div class="p-2-1">
					<h2>Cred�ncial</h2>
					<p>Recupera��o de cred�nciais</p>
				</div>
				<div class="p-2-2">
					<div class="cf">
						<label>Email</label><input type="email" name="email" required>
					</div>
					<div class="cf">
						<label>Usu�rio</label><input type="text" name="usuario">
					</div>
					<div class="cf">
						<label>Nova Senha</label><input type="password" name="senha" required>
					</div>
					<div class="cf">
						<label>Comfirmar Senha</label><input type="password" name="comfirma_senha" required>
					</div>
					<div class="p-butoe">
						<div>
							<button name="salvar">Salvar</button>
						</div>
						<div>
							<a href="../index.jsp">Voltar</a>
						</div>
					</div>
				</div>
			</form>
			<div class="">
			<p>
			<%
			if(request.getMethod() == "POST"){
				%>
				<div class="erro_dialogo">
			         <p>Metodo POST</p>
			    </div>
				<%
			}
			if(request.getParameter("salvar") != null){
				%>
				<!-- Resultado do requestDispacher servelet: /*user* -->
				<div class="erro_dialogo">
			         <p>Ocorreu um erro ao tentar recuperar as cred�nciais.</p>
			    </div>
				<%
			}
			%>
			</p>
			    <!-- <div class="erro_dialogo">
			         <p>Ocorreu um erro ao tentar recuperar as cred�nciais.</p>
			    </div> -->
			</div>
		</div>
	</div>
</body>
</html>