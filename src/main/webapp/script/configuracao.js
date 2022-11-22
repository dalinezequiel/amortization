/**
 * 
 */
const butao = document.querySelectorAll('.config .butoe button');
//BUTÃO DO BANCO DE DADOS
butao[0].addEventListener('click', function() {
	for (var i = 0; i < butao.length; i++) {
		if (i == 0) {
			butao[i].style.backgroundColor = "#ededed";
			butao[i].style.borderLeft = "3px solid #14854c";
		} else {
			butao[i].style.backgroundColor = "#ffffff";
			butao[i].style.borderLeft = "3px solid #ffffff";
		}
	}
})

//BUTÃO DE USUÁRIO
butao[1].addEventListener('click', function() {
	for (var i = 0; i < butao.length; i++) {
		if (i == 1) {
			butao[i].style.backgroundColor = "#ededed";
			butao[i].style.borderLeft = "3px solid #14854c";
		} else {
			butao[i].style.backgroundColor = "#ffffff";
			butao[i].style.borderLeft = "3px solid #ffffff";
		}
	}
})

//BUTÃO DE AJUDA E SUPORTE
butao[2].addEventListener('click', function() {
	for (var i = 0; i < butao.length; i++) {
		if (i == 2) {
			butao[i].style.backgroundColor = "#ededed";
			butao[i].style.borderLeft = "3px solid #14854c";
		} else {
			butao[i].style.backgroundColor = "#ffffff";
			butao[i].style.borderLeft = "3px solid #ffffff";
		}
	}
})