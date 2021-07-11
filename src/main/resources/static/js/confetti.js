var counter = 0;

function spaff() {

	if(counter < 1000) {

		confetti({
			particleCount: 100,
			startVelocity: 30,
			spread: 360,
			origin: {
				x: Math.random(),
				y: Math.random() - 0.2
			}
		});
		
		counter++;
	
	}

}


setInterval(spaff, 1000);