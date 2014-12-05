function eitherOr(field, rules, i, options){
	var relative=$("#"+rules[2]);
	var validate=true;
	if(rules.length>4){
		var condition=$("#"+rules[3]);
		if(!_required(condition,true)){
			validate=false;
		}
	}
	if(validate){
		if(!_required(relative,true)){
			if(!_required(field)){
				return "人民币和美元必须填一个";
			}
		}
	}
} 
function condValueRequiredX(field, rules, i, options){
	var relative=$("#"+rules[2]);
	var condValue=rules[3];
	if(_required(relative)&&relative.val()==condValue){
		if(!_required(field)){
			return options.allrules["required"].alertText;
		}
	}
} 

function _required(field,condRequired) {
	switch (field.prop("type")) {
		case "text":
		case "password":
		case "textarea":
		case "file":
		case "select-one":
		case "select-multiple":
		default:
			var field_val      = $.trim( field.val()                               );
			var dv_placeholder = $.trim( field.attr("data-validation-placeholder") );
			var placeholder    = $.trim( field.attr("placeholder")                 );
			if (
				   ( !field_val                                    )
				|| ( dv_placeholder && field_val == dv_placeholder )
				|| ( placeholder    && field_val == placeholder    )
			) {
				return false;
			}
			break;
		case "radio":
		case "checkbox":
			// new validation style to only check dependent field
			if (condRequired) {
				if (!field.attr('checked')) {
					return false;
				}
				break;
			}
			// old validation style
			var form = field.closest("form, .validationEngineContainer");
			var name = field.attr("name");
			if (form.find("input[name='" + name + "']:checked").size() == 0) {
				if (form.find("input[name='" + name + "']:visible").size() == 1)
					return false;
				else
					return false;
			}
			break;
	}
	return true;
}
