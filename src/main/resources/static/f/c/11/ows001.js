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
                            // console.log(ctrl.eMap[371357].children[i].children[j].children[k].value_1_22)
                        }
                    }
                }
	        }

	    }
    //    console.log("Result->" + textData)
       var json = JSON.stringify(textData)
	   var jO = {'k':textData}
	   console.log(jO)

	read_write.http.post('/r/createWord', jO)
	.then(function(response){
		let link,blob,url;
		
		blob = new Blob(['\ufeff',response.config.data.k], {type:"application/msword"});
		url = URL.createObjectURL(blob)
		console.log(url)

		link = document.createElement("A");
		link.href = url;
		link.title = "Download title"
		link.download = "document"
		
		if(navigator.msSaveOrOpenBlob)
			msSaveOrOpenBlob(blob,"Herrlich.doc")
		else link.click()
		
	})
	}
	read_write = new Read_write($http)

}



var read_write = {}
function Read_write($http){
	this.http = $http
}


