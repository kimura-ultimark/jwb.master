window.addEventListener('DOMContentLoaded', function() {
    var modalPanel = document.querySelectorAll('.panel-container .panel');
    Array.prototype.forEach.call(modalPanel, function(p) {
        var modalHeader = p.querySelectorAll('.panel-header');
        Array.prototype.forEach.call(modalHeader, function(h) {
            h.addEventListener('mousedown', onMouseDown)
        });
    });
});
function onMouseDown(e) {
    var parent = this.parentNode;
	eventY = e.clientY - parent.offsetTop;
    eventX = e.clientX - parent.offsetLeft;
    this.addEventListener('mousemove', onMouseMove);
    this.addEventListener('mouseleave', onMouseLeave);
    this.addEventListener('mouseup', onMouseUp);
}
function onMouseMove(e) {
    var parent = this.parentNode;
    var mouseY = (e.clientY - parent.offsetTop) - eventY;
    var mouseX = (e.clientX - parent.offsetLeft) - eventX;
    parent.style.top = parent.offsetTop + mouseY + 'px';
    parent.style.left = parent.offsetLeft + mouseX + 'px';
}
function onMouseLeave(e) {
    this.removeEventListener('mousemove', onMouseMove);
}
function onMouseUp(e) {
	this.removeEventListener('mousemove', onMouseMove);
}