var ext$ = {};
ext$.loginPage="";
ext$.nologinPage="";
ext$.welcomePage="";
ext$.errorPage="";
ext$.netErrorPage="";
ext$.mainPage="";
//ext$.rootpath= ext$.getRootpath();
ext$.getRootpath = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};
ext$.Ajax = function(url,data,type,fun) {//'POST'
	$.ajax({url:url,data:data, type:type, dataType:'text', //contentType:'application/json', 
		success:function(result, status, xhr) {  
			fun(result);
		},
		error:function(xhr,error,exception) {  
			$.messager.alert('异常', exception.toString(), 'error');
		}  

	});
};
ext$.post = function(url,data,fun) {
	return ext$.Ajax (url,data,'POST',fun) ;
};
ext$.get = function(url,data,fun) {
	return ext$.Ajax (url,data,'GET',fun) ;
};
ext$.put = function(url,data,fun) {
	return ext$.Ajax (url,data,'PUT',fun) ;
};
ext$.deleteF = function(url,data,fun) {
	return ext$.Ajax (url,data,'DELETE',fun) ;
};
