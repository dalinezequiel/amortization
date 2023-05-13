
window.addEventListener('load', function() {
	quant_balao[0].style.backgroundColor = "#fafafa";
	quant_balao[0].readOnly = true;

	input_cap_02[1].style.backgroundColor = "#fafafa";
	input_cap_02[1].readOnly = true;

	/*balao_type[0].disabled = true;
	balao_type[2].disabled = true;*/
})

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
			input_atraso[i].value = "";
		}
	}
})

//BLOQUEAR INPUTS BALÃO
const cbo_tipo_balao = document.getElementsByName("tipo_balao");
const input_balao = document.querySelectorAll(".periodo input");

cbo_tipo_balao[0].addEventListener('change', function() {
	if (cbo_tipo_balao[0].value != "Sem balões") {
		for (var i = 0; i < input_balao.length; i++) {
			if (i != 1) {
				input_balao[i].style.backgroundColor = "#ffffff";
				input_balao[i].readOnly = false;
			}
		}

	} else {
		for (var i = 0; i < input_balao.length; i++) {
			if (i != 1) {
				input_balao[i].style.backgroundColor = "#fafafa";
				input_balao[i].readOnly = true;
			}
		}
		periodo_balao[0].value = "";
		quant_balao[0].value = "";
		valor_balao[0].value = "";
	}
})

//PREENCHER DATA DA PRIMEIRA PRESTAÇÃO COM DATA DA CONTRATAÇÃO
const data_contrat = document.getElementsByName("data_contratacao");
const data_prest = document.getElementsByName("ultima_parcela");
data_contrat[0].addEventListener('change', function() {
	let data_array = data_contrat[0].value.split('-');
	let month = parseInt(data_array[1]) + 1;
	let data_primeira_prestacao;

	if (String(month).length == 1) {
		data_primeira_prestacao = data_array[0] + "-0" + month + "-" + data_array[2];
	} else {
		data_primeira_prestacao = data_array[0] + "-" + month + "-" + data_array[2];
	}

	if (data_array[1] == 12) {
		data_array[0] = parseInt(data_array[0]) + 1;
		data_array[1] = "01";
		month = data_array[1];
		data_primeira_prestacao = data_array[0] + "-" + month + "-" + data_array[2];
	}
	data_prest[0].value = String(data_primeira_prestacao);
})

// ACTUALIZAÇÃO DOS CAMPOS DEPOIS DO CLICK
const btn_calcular = document.getElementsByName("calcular");
const input_cap_01 = document.querySelectorAll(".cap-01 input");
const input_cap_02 = document.querySelectorAll(".cap-02 input");
const input_cap_03 = document.querySelectorAll(".cap-03 input");
const conf_sair = document.querySelectorAll(".conf_sair a");
const select_amort = document.getElementsByName("sys_amort");
const balao_type = document.querySelectorAll(".cap-02 option");

// SALVAR VALORES NO SESSIONSTORAGE
btn_calcular[0].addEventListener('click', function() {
	sessionStorage.setItem("input_vef", input_cap_01[0].value);
	sessionStorage.setItem("tax", input_cap_01[1].value);
	sessionStorage.setItem("prazo", input_cap_03[0].value);
	sessionStorage.setItem("ultima_parcela", input_cap_02[1].value);
	sessionStorage.setItem("data_contratacao", data_contrat[0].value);
	sessionStorage.setItem("sys_amort", select_amort[0].value);
	sessionStorage.setItem("cbo_atraso", cbo_atraso[0].value);
	sessionStorage.setItem("multa", input_atraso[0].value);
	sessionStorage.setItem("juro_atraso", input_atraso[1].value);

	sessionStorage.setItem("cbo_tipo_balao", cbo_tipo_balao[0].value);
	sessionStorage.setItem("periodo_balao", periodo_balao[0].value);
	sessionStorage.setItem("quant_balao", quant_balao[0].value);
	sessionStorage.setItem("valor_balao", valor_balao[0].value);
})

// PREENCHER INPUTS COM DADOS DO SESSIONSTORAGE
window.addEventListener('load', function() {
	input_cap_01[0].value = sessionStorage.getItem("input_vef");
	input_cap_01[1].value = sessionStorage.getItem("tax");
	input_cap_02[1].value = sessionStorage.getItem("ultima_parcela");
	input_cap_03[0].value = sessionStorage.getItem("prazo");
	data_contrat[0].value = sessionStorage.getItem("data_contratacao");
	select_amort[0].value = sessionStorage.getItem("sys_amort");
	cbo_atraso[0].value = sessionStorage.getItem("cbo_atraso");
	input_atraso[0].value = sessionStorage.getItem("multa");
	input_atraso[1].value = sessionStorage.getItem("juro_atraso");

	cbo_tipo_balao[0].value = sessionStorage.getItem("cbo_tipo_balao");
	periodo_balao[0].value = sessionStorage.getItem("periodo_balao");
	quant_balao[0].value = sessionStorage.getItem("quant_balao");
	valor_balao[0].value = sessionStorage.getItem("valor_balao");

	if (cbo_tipo_balao[0].value == "") {
		cbo_tipo_balao[0].value = "Sem balões";
	}

	if (cbo_atraso[0].value == "") {
		cbo_atraso[0].value = "Não";
	}

	if (cbo_tipo_balao[0].value == "Sem balões") {
		periodo_balao[0].value = "";
		quant_balao[0].value = "";
		valor_balao[0].value = "";
	}
})

select_amort[0].addEventListener('change', function() {
	if (select_amort[0].value == "PRICE") {
		balao_type[0].disabled = false;
		balao_type[2].disabled = false;
	} else if (select_amort[0].value == "SAC") {
		balao_type[0].disabled = false;
		balao_type[2].disabled = true;
	} else {
		balao_type[0].disabled = true;
		balao_type[2].disabled = true;
	}
});


// RESETAR O SESSION STORAGE
conf_sair[1].addEventListener('click', function() {
	sessionStorage.clear();
})

// 
const tipo_balao = document.getElementsByName("tipo_balao");
const periodo_balao = document.getElementsByName("periodo_balao");
const quant_balao = document.getElementsByName("quant_balao");
const valor_balao = document.getElementsByName("valor_balao");
const periodo_div=document.getElementsByClassName("periodo");

periodo_balao[0].addEventListener('mouseleave', function() {
	if (tipo_balao[0].value == "Balões e parcelas") {
		if (input_cap_03[0].value != "" && periodo_balao[0].value != "") {
			quant_balao[0].value = parseInt(parseInt(input_cap_03[0].value) / parseInt(periodo_balao[0].value)) + ",00";
		} else {
			quant_balao[0].value = "0,00";
		}
	}
})

quant_balao[0].addEventListener('mouseleave', function() {
	if (tipo_balao[0].value == "Balões e parcelas") {
		if (input_cap_03[0].value != "" && periodo_balao[0].value != "") {
			quant_balao[0].value = parseInt(parseInt(input_cap_03[0].value) / parseInt(periodo_balao[0].value)) + ",00";
		} else {
			quant_balao[0].value = "0,00";
		}
	}
})

periodo_div[0].addEventListener('mouseleave', function() {
	if (tipo_balao[0].value == "Balões e parcelas") {
		if (input_cap_03[0].value != "" && periodo_balao[0].value != "") {
			quant_balao[0].value = parseInt(parseInt(input_cap_03[0].value) / parseInt(periodo_balao[0].value)) + ",00";
		} else {
			quant_balao[0].value = "0,00";
		}
	}
})

periodo_div[1].addEventListener('mouseleave', function() {
	if (tipo_balao[0].value == "Balões e parcelas") {
		if (input_cap_03[0].value != "" && periodo_balao[0].value != "") {
			quant_balao[0].value = parseInt(parseInt(input_cap_03[0].value) / parseInt(periodo_balao[0].value)) + ",00";
		} else {
			quant_balao[0].value = "0,00";
		}
	}
})