var initOWS001 = function($http) {
	console.log(123)
	ctrl.ows001 = {}
	ctrl.ows001.createWord = function() {
		console.log(321)
	}
	read_write = new Read_write($http)

}


var read_write = {}
function Read_write($http){
	this.http = $http
}


