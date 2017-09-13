/**
 * used to show/hide the unmatched report
 * 
 * @author abhinab
 */
window.onload = function() {
	var report = document.getElementById('report');
	var btnReport = document.getElementById('btnReport');
	report.style.display = 'none';
	btnReport.onclick = function() {
		if (report.style.display === 'none') {
			report.style.display = 'block';
		} else {
			report.style.display = 'none';
		}
	};
};