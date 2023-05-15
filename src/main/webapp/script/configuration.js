/**
 * 
 */
const butao = document.querySelectorAll('.config .butoe button');
const in_1 = document.querySelector('.config .info .in-1');
const in_11 = document.querySelector('.config .info .in-1-1');
const in_2 = document.querySelector('.config .info .in-2');
const in_3 = document.querySelector('.config .info .in-3');

//BLOQUEAR A DIV DE USER E HELP AND SUPPORT
window.addEventListener('load', function(){
	in_1.style.display = "flex";
	in_1.style.flexDirection = "row";
	
	/*in_11.style.display = "flex";
	in_11.style.flexDirection = "row";*/
	
	in_2.style.display = "none";
	in_3.style.display = "none";
})

//BUTÃO DO BANCO DE DADOS
butao[0].addEventListener('click', function() {
	for (var i = 0; i < butao.length; i++) {
		if (i == 0) {
			butao[i].style.backgroundColor = "#ededed";
			butao[i].style.borderLeft = "3px solid #14854c";
			in_1.style.display = "flex";
			in_1.style.flexDirection = "row";
			
			in_11.style.display = "flex";
			in_11.style.flexDirection = "column";
			
		} else {
			butao[i].style.backgroundColor = "#ffffff";
			butao[i].style.borderLeft = "3px solid #ffffff";
			in_2.style.display = "none";
			in_3.style.display = "none";
		}
	}
})

//BUTÃO DE USUÁRIO
butao[1].addEventListener('click', function() {
	for (var i = 0; i < butao.length; i++) {
		if (i == 1) {
			butao[i].style.backgroundColor = "#ededed";
			butao[i].style.borderLeft = "3px solid #14854c";
			in_2.style.display = "flex";
			in_2.style.flexDirection = "column";
			
		} else {
			butao[i].style.backgroundColor = "#ffffff";
			butao[i].style.borderLeft = "3px solid #ffffff";
			in_1.style.display = "none";
			in_11.style.display = "none";
			in_3.style.display = "none";
		}
	}
})

//BUTÃO DE AJUDA E SUPORTE
butao[2].addEventListener('click', function() {
	for (var i = 0; i < butao.length; i++) {
		if (i == 2) {
			butao[i].style.backgroundColor = "#ededed";
			butao[i].style.borderLeft = "3px solid #14854c";
			in_3.style.display = "flex";
			in_3.style.flexDirection = "column";
			
		} else {
			butao[i].style.backgroundColor = "#ffffff";
			butao[i].style.borderLeft = "3px solid #ffffff";
			in_1.style.display = "none";
			in_2.style.display = "none";
		}
	}
})

