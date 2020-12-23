var initOWS001 = function ($http) {
	console.log(1234)
	ctrl.ows001 = {}
	ctrl.ows001.createWord2 = function () {
		var textData = { textData: createTextData() }
		console.log(textData)
		read_write.http.post('/r/set_json_poi_data', textData)
			.then(function (response) {
				console.log('---123', response)
			})
	}

	// 
	var turndownService = new TurndownService();
	var converter = new showdown.Converter();

	ctrl.MdToHTML = function (MD) {
		return converter.makeHtml(MD);
	}
	ctrl.stringToHTML = function (str) {
		var dom = document.createElement('div');
		dom.innerHTML = str;
		return dom;
	};

	ctrl.getXHTML = function (Node) {
		var nsx = "http://www.w3.org/1999/xhtml";
		function outerXHTML(node) {
			var tempNode = node.cloneNode(true);
			var xdoc = document.implementation.createDocument(nsx, 'html');
			xdoc.documentElement.appendChild(tempNode);
			return tempNode.outerHTML;
		}
		var tempNode = outerXHTML(Node);
		return tempNode;
	}


	ctrl.getMarkdownFromHTML = function () {
		let markdown = turndownService.turndown(document.getElementById('wikiHTML'));
		return markdown;
	}

	ctrl.sendDataToServer = () => {
		let url = "/r/setData";
		let data = ctrl.getMarkdownFromHTML();
		data = ctrl.MdToHTML(data);
		let domHTML = ctrl.stringToHTML(data);
		let sendData = ctrl.getXHTML(domHTML);
		$http.post(url, { data: sendData }).then
			(function (response) {
				console.log('setMD', response);
				window.open("/r/getDOCX", "_blank");
				// $http.get("/r/getDOCX").then(function (response) {
				// 	console.log('getDOCX', response);
				// 	$http.get("/r/clearData").then(function (response) {
				// 		console.log('clearData', response);
				// 	}, function (reject) {
				// 		console.log('Error clearData', reject);
				// 	})
				// }, function (reject) {
				// 	console.log('Error getDOCX', reject);
				// }, function (reject) {
				// 	console.log('Error setMD', reject);
				// })
			})
	}

	ctrl.downloadPDF=function(){
		let url = "/r/setData";
		let data = ctrl.getMarkdownFromHTML();
		data = ctrl.MdToHTML(data);
		let domHTML = ctrl.stringToHTML(data);
		let sendData = ctrl.getXHTML(domHTML);
		$http.post(url, { data: sendData }).then
			(function (response) {
				console.log('setData', response);
				window.open("/r/getPDF", "_blank");
				// $http.get("/r/getPDF").then(function (response) {
				// 	console.log('getDOCX', response);
				// 	$http.get("/r/clearData").then(function (response) {
				// 		console.log('clearData', response);
				// 	}, function (reject) {
				// 		console.log('Error clearData', reject);
				// 	})
				// }, function (reject) {
				// 	console.log('Error getDOCX', reject);
				// }, function (reject) {
				// 	console.log('Error setMD', reject);
				// })
			})
	}
	ctrl.downloadMD = function () {
		let data = ctrl.getMarkdownFromHTML();
		const reg = new RegExp(/[-=#]/);
		let filename = data.split(reg)[0]+".MD";
		let type="text/markdown; charset=UTF-8";
		download(data,filename,type);
	}

	function download(data, filename, type) {
		var file = new Blob([data], { type: type });
		if (window.navigator.msSaveOrOpenBlob) // IE10+
			window.navigator.msSaveOrOpenBlob(file, filename);
		else { // Others
			var a = document.createElement("a"),
				url = URL.createObjectURL(file);
			a.href = url;
			a.download = filename;
			document.body.appendChild(a);
			a.click();
			setTimeout(function () {
				document.body.removeChild(a);
				window.URL.revokeObjectURL(url);
			}, 0);
		}
	}
	///

	///

	var createTextData = () => {
		var textData = [];

		for (let i = 0; i < ctrl.eMap[371357].cnt_child; i++) {
			if (ctrl.eMap[371357].children[i].cnt_child == 0 || ctrl.eMap[371357].children[i].cnt_child == null) {
				textData[textData.length + 1] = ctrl.eMap[371357].children[i].value_1_22
				// console.log(ctrl.eMap[371357].children[i].value_1_22)
			}
			else {
				for (let j = 0; j < ctrl.eMap[371357].children[i].cnt_child; j++) {
					if (ctrl.eMap[371357].children[i].children[j].cnt_child == 0 || ctrl.eMap[371357].children[i].children[j].cnt_child == null) {
						textData[textData.length + 1] = ctrl.eMap[371357].children[i].children[j].value_1_22
						// console.log(ctrl.eMap[371357].children[i].children[j].value_1_22)
					}
					else {
						for (let k = 0; k < ctrl.eMap[371357].children[i].children[j].cnt_child; k++) {
							textData[textData.length + 1] = ctrl.eMap[371357].children[i].children[j].children[k].value_1_22
							// console.log(ctrl.eMap[371357].children[i].children[j].children[k].value_1_22)
						}
					}
				}
			}

		}
		return textData
	}

	ctrl.ows001.createWord = function () {
		console.log(112233)
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {
				blob = new Blob([this.response], { type: "application/octet-stream" })
				console.log(blob)
				link = document.createElement("a")
				link.href = window.URL.createObjectURL(blob)
				link.download = 'fn.docx'
				link.click()
			}
		}
		xhr.open('GET', '/r/helloWord3', true)
		xhr.send()

		var textData = createTextData()

		return
		//    console.log("Result->" + textData)
		var json = JSON.stringify(textData)
		var jO = { 'k': textData }
		console.log(jO)

		//    "/r/helloWord2"
		//    read_write.http.post('/r/createWord', jO)

		read_write.http.post('/r/helloWord2', jO)
			.then(function (response) {
				console.log('---123', response)
				let link, blob, url
				// console.log(response.data.substr(30))
				blob = new Blob([response.data], { type: "application/msword" })
				// blob = new Blob([response.data.substr(30)], { type: "application/msword" })
				console.log(blob)
				link = document.createElement("a")
				link.href = window.URL.createObjectURL(blob)
				link.download = 'fn.docx'
				let reader = new FileReader()
				reader.onload = function () {
					let blobAsDataUrl = reader.result
					window.location = blobAsDataUrl
					// link.href = reader.result; // data url
					// console.log(link)
					//   link.click();
				}
				reader.readAsDataURL(blob); // converts the blob to base64 and calls onload
				return
				link.click()

				console.log(url)

				// console.log(reader)
				// reader.readAsDataURL(blob)
				// blob = new Blob(['\ufeff',response.config.data.k], {type:"application/msword"})
				// url = URL.createObjectURL(blob)
				// console.log(url)
				// link.href = reader.result
				link.href = url;
				console.log(link)
				window.URL.revokeObjectURL(url);
				link.click()

				// link.title = "Download title"
				// link.download = "document"

				// if(navigator.msSaveOrOpenBlob)
				// 	msSaveOrOpenBlob(blob,"Herrlich.doc")
				// else 
				// link.click()

			})
	}
	read_write = new Read_write($http)

}



var read_write = {}
function Read_write($http) {
	this.http = $http
}





