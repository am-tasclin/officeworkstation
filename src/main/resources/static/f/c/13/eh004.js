app.controller('AppCtrl', function($scope, $http, $timeout) {
	ctrl = this
	initApp($scope, $http, $timeout)
	random_newValue('edProtocol','value2')
	initCrud004($timeout)
	initOWS001($http)
	ctrl.page_title = 'mc:' + ctrl.request.parameters.doc2doc
	ctrl.views_template_list = [368797,369967,369984,369988,371294,371306, 359249 ]
//	ctrl.views_template_list = [368797,369967,369984,369988,371294,371306,371704,371562]

	open_children_doc2doc()
	
	exe_fn.add_eMap = function(v){
		if( [371705,371799].indexOf(v.reference)>=0 ){// view_sql_ua_001, edit_sql_001
			if(ctrl.views_template_list.indexOf(v.reference2)<0){
				ctrl.views_template_list.push(v.reference2)
			}
		}
		if(v.doc_id==ctrl.doc2doc_ids[1] && ctrl.request.parameters.views && ctrl.request.parameters.views.indexOf('doc')>=0){
			v.open_children = true
		}
	}

})
/*
SELECT *, v1*v2 sum FROM (
SELECT row.*, v1.value v1, v2.value v2 FROM doc row 
left join (SELECT dd.*, i.value, dt.doctype doctype_r FROM doc dt, doc dd
left join integer i on i.integer_id=dd.doc_id
  where dd.reference=dt.doc_id and dd.reference=369352
) v1 on v1.parent=row.doc_id
left join (SELECT dd.*, f.value, dt.doctype doctype_r FROM doc dt, doc dd
left join double f on f.double_id=dd.doc_id
  where dd.reference=dt.doc_id and dd.reference=369353
) v2 on v2.parent=row.doc_id
where row.parent=369363
) x
 */
