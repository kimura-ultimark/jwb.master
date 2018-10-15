window.addEventListener('DOMContentLoaded', function() {
	
	var buttons = document.querySelectorAll('button.btn');
	Array.prototype.forEach.call(buttons, function(b) {
		
		b.addEventListener('click', createRipple);
	});
});
function createRipple(e) {
	
	var oldRipple = this.querySelectorAll('div.ripple');
	var btn = this;
	Array.prototype.forEach.call(oldRipple, function(r) {
	
		btn.removeChild(r);
	});
	
	var ripple = document.createElement('div');
	this.appendChild(ripple);
	ripple.classList.add('ripple');
	
	var d = Math.max(this.clientHeight, this.clientWidth);
	ripple.style.height = ripple.style.width = d + 'px';
	
	ripple.style.top = e.clientY - this.getBoundingClientRect().top - d / 2 + 'px';
	ripple.style.left = e.clientX - this.getBoundingClientRect().left - d / 2 + 'px';
}