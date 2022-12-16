/**
 * 
 */
const moeda = document.querySelectorAll(".camp input[type='text']");

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
tLeave(moeda, 6);

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
tOver(moeda, 5);
tOver(moeda, 6);
