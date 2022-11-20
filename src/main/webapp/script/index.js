/**
 * 
 */
 //const input = document.querySelectorAll('input[type="text"]');
 const input = document.getElementsByTagName('input');
 for(var i=0; i<input.length; i++){
	input[i].addEventListener('mouseover', function(){
		input[i].style.outline = "1px solid red";
	});
}

const check = document.querySelector('#check');
check.addEventListener('click', function(){
	if(check.checked){
		window.alert("Selected");
	}else{
		window.alert("Diselected");
	}
})