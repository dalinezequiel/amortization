
//BLOQUEAR INPUTS ATRASO
const cbo_atraso = document.getElementsByName("calc_atraso");
const input_atraso = document.querySelectorAll(".multa input");

cbo_atraso[0].addEventListener('change', function() {
	if (cbo_atraso[0].value == "Sim") {
		for (var i = 0; i < input_atraso.length; i++) {
			input_atraso[i].style.backgroundColor = "#ffffff";
			input_atraso[i].readOnly = false;
		}

	} else {
		for (var i = 0; i < input_atraso.length; i++) {
			input_atraso[i].style.backgroundColor = "#fafafa";
			input_atraso[i].readOnly = true;
		}
	}
})

//BLOQUEAR INPUTS BALÃO
const cbo_tipo_balao = document.getElementsByName("tipo_balao");
const input_balao = document.querySelectorAll(".periodo input");

cbo_tipo_balao[0].addEventListener('change', function() {
	if (cbo_tipo_balao[0].value != "Sem balões") {
		for (var i = 0; i < input_balao.length; i++) {
			input_balao[i].style.backgroundColor = "#ffffff";
			input_balao[i].readOnly = false;
		}

	} else {
		for (var i = 0; i < input_balao.length; i++) {
			input_balao[i].style.backgroundColor = "#fafafa";
			input_balao[i].readOnly = true;
		}
	}
})

// ACTUALIZAÇÃO DOS CAMPOS DEPOIS DO CLICK
const btn_calcular = document.getElementsByName("calcular");
const input_cap_01 = document.querySelectorAll(".cap-01 input");
const conf_sair = document.querySelectorAll(".conf_sair a");

// SALVAR VALORES NO SESSIONSTORAGE
btn_calcular[0].addEventListener('click', function() {
	sessionStorage.setItem("input_vef", input_cap_01[0].value);
	sessionStorage.setItem("tax", input_cap_01[1].value);
})

// PREENCHER INPUTS COM DADOS DO SESSIONSTORAGE
window.addEventListener('load', function() {
	input_cap_01[0].value = sessionStorage.getItem("input_vef");
	input_cap_01[1].value = sessionStorage.getItem("tax");
})

// RESETAR O SESSIONSTORAGE
conf_sair[1].addEventListener('click', function() {
	sessionStorage.clear();
})