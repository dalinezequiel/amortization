/**
 * 
 */
const data = document.getElementsByName("data_feriado");
const ano = document.getElementsByName("ano");
data[0].addEventListener('change', function() {
	ano[0].value = new Date(data[0].value).getFullYear();
})

window.addEventListener('load', function() {
	ano[0].readOnly = true;
})
