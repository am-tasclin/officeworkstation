var initOWS001 = function($http) {
	console.log(123)
	ctrl.ows001 = {}
	ctrl.ows001.createWord = function() {
	    var textData = [];

	    for(let i =0; i<ctrl.eMap[371357].cnt_child; i++){
	        if(ctrl.eMap[371357].children[i].cnt_child == 0 || ctrl.eMap[371357].children[i].cnt_child == null){
	            textData[textData.length+1] = ctrl.eMap[371357].children[i].value_1_22
	            console.log(ctrl.eMap[371357].children[i].value_1_22)
	        }
	        else{
                for(let j =0; j<ctrl.eMap[371357].children[i].cnt_child;j++){
                    if(ctrl.eMap[371357].children[i].children[j].cnt_child == 0 ||ctrl.eMap[371357].children[i].children[j].cnt_child == null){
                        textData[textData.length+1] = ctrl.eMap[371357].children[i].children[j].value_1_22
                        console.log(ctrl.eMap[371357].children[i].children[j].value_1_22)
                    }
                    else{
                        for(let k =0; k<ctrl.eMap[371357].children[i].children[j].cnt_child; k++){
                            textData[textData.length+1] = ctrl.eMap[371357].children[i].children[j].children[k].value_1_22
                            console.log(ctrl.eMap[371357].children[i].children[j].children[k].value_1_22)
                        }
                    }
                }
	        }

	    }
       console.log("Result->" + textData)
       var json = JSON.stringify(textData)
       console.log(typeof json)
	}
	read_write = new Read_write($http)

}



var read_write = {}
function Read_write($http){
	this.http = $http
}

