/**
 * 
 */
const button = document.querySelectorAll(".calculadora .c-2 button");

const input_c1 = document.querySelectorAll(".calculadora .c-1 input");
const input_c3 = document.querySelectorAll(".calculadora .c-3 input[type='text']");

button[0].addEventListener('click', function() {
	let valorAPagar = input_c1[0].value * input_c1[2].value;
	input_c3[1].value = valorAPagar;
});
