/**
 * 
 */
/*const moeda = document.querySelectorAll(".camp input[type='text']");

function tLeave(comp, index) {
	comp[index].addEventListener('mouseleave', function() {
		let tex = comp[index].value;
		let n = new Intl.NumberFormat().format(tex);
		let ki = "";
		for (var i = 0; i < n.length; i++) {
			if (n.charAt(i) == ",") {
				ki += ".";
			} else {
				ki += n.charAt(i);
			}
		}
		let z = ",00";
		comp[index].value = ki + z;
	})
}

tLeave(moeda, 0);
tLeave(moeda, 1);
tLeave(moeda, 2);
tLeave(moeda, 3);
tLeave(moeda, 4);
tLeave(moeda, 5);

function tOver(comp, index) {
	moeda[index].addEventListener('mouseover', function() {
		let tex = comp[index].value;
		let ki = "";
		for (var i = 0; i < tex.length; i++) {

			if (tex[i] == ".") {
				ki += "";
			} else {
				ki += tex[i];
			}
		}

		let f = ki.split(",");
		let z = f[0];
		comp[index].value = z;
	})
}

tOver(moeda, 0);
tOver(moeda, 1);
tOver(moeda, 2);
tOver(moeda, 3);
tOver(moeda, 4);
tOver(moeda, 5);*/


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
