window.addEventListener('DOMContentLoaded', function() {
	
	background = document.createElement('div');
	background.classList.add('layout-sidenav__container');
	var container = document.querySelector('.layout-container');
	container.appendChild(background);
	sideNav = document.querySelector('.layout-container .layout-sidenav');
	
	var collapseButtons = document.querySelectorAll('.layout-title .btn');
	Array.prototype.forEach.call(collapseButtons, function(b) {
		b.addEventListener('click', onMenuClick);
	});
});
function onMenuClick(e) {
	background.classList.add('active');
	sideNav.classList.add('active');
	background.addEventListener('click', onBgClick);
}
function onBgClick(e) {
	background.classList.remove('active');
	sideNav.classList.remove('active');
}