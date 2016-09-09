<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Adobe Campaign | Recipient Query</title>
    
    <spring:url value="/resources/core/css/font-awesome.min.css" var="fontAwesome" />
    <link href="${fontAwesome}" rel="stylesheet" type="text/css">
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<spring:url value="/resources/core/css/custom.css" var="customCss" />
	<link href="${customCss}" rel="stylesheet" />
</head>
<script type="text/javascript">
	var currentIndexOperand = 0;
</script>

<body>
	<spring:url value="/" var="homeUrl" />
	
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                <spring:url value="/resources/image/logoAC.png" var="logoAC"/>               
                <img src="${logoAC}" width="50" height="50"  style="float:left; margin-left:10px"/>
                
                <a class="navbar-brand" href="/">
                	Adobe Campaign Connector
               	</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Robert Duy</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Robert Duy</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Insert Recipient example</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu alert-dropdown">
                        <li>
                            <a href="#">Notification <span class="label label-success">Insert successful</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">See more</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Robert Duy <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="${homeUrl}"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#recipient"><i class="fa fa-user"></i> Recipient <i class="fa fa-chevron-right"></i></a>
                        <ul id="recipient" class="collapse in">
                            <li class="active">
                            	<spring:url value="/recipient/query" var="recipientQueryUrl" />
								<a href="${recipientQueryUrl}" target="_self">Query Builder</a>
                            </li>
                            <li>
                            	<spring:url value="/recipient/writer" var="recipientWriterUrl" />
								<a href="${recipientWriterUrl}" target="_self">Import Builder</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="charts.html"><i class="fa fa-users"></i> List Group</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">
            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Recipient Query Builder<small></small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active" id="liTokenSection">
                            	<c:if test="${not empty acToken}">
                            		Current AC token:  <c:out value="${acToken}" default=""/> 
                            	</c:if>
                            	<c:if test="${empty acToken}">
                            		<spring:url value="/login" var="loginUrl" />
									<a class="btn btn-primary" href="#" onclick="openLoginModal();">Login</a>
									<p style="font-style: italic; font-size: 10px">*To use this feature, please login by account Adobe Campagin*</p>
                            	</c:if>
                            </li>
                        </ol>
                        <c:if test="${not empty acToken}">
                        	<div class="row" style="margin-top: 10px; margin-left: 1px;" id="folderDiv">
	                        	<div class="col-md-3"><h3>Recipient Folder: </h3></div>
	                        	<div class="col-md-5" style="margin-top: 12px;">
	                        		<select class="form-control" data-folder="folder">
	                        			<c:forEach items="${listFolder}" var="folder" varStatus="countFolder">
	                        				<c:if test="${countFolder.index eq 0}">
	                        					<option value="${folder.folderId}" selected="selected">${folder.folderName} (ROOT)</option>	
	                        				</c:if>
	                        				<c:if test="${countFolder.index ne 0}">
										   		<option value="${folder.folderId}">${folder.folderName}</option>
										   	</c:if>
										</c:forEach>
									</select>
	                        	</div>
	                        	<div class="col-md-1" style="margin-top: 18px; cursor: pointer;">
	                        		<i title="Choose folder you want to query in" data-placement="top" data-toggle="tooltip" class="fa fa-2x fa-question-circle"></i>
	                        	</div>
	                        </div>
                        </c:if>
                        <c:if test="${empty acToken}">
                        	<div class="row" style="margin-top: 10px; margin-left: 1px; display: none;" id="folderDiv">
	                        	<div class="col-md-3"><h3>Recipient Folder: </h3></div>
	                        	<div class="col-md-5" style="margin-top: 12px;">
	                        	</div>
	                        	<div class="col-md-1" style="margin-top: 18px; cursor: pointer;">
	                        		<i title="Choose folder you want to query in" data-placement="top" data-toggle="tooltip" class="fa fa-2x fa-question-circle"></i>
	                       		 </div>
	                        </div>
                        </c:if>
                        <div class="row" style="margin:10px">
                        	<ul class="nav nav-tabs">
						    	<li class="active"><a data-toggle="tab" href="#home">Select One</a></li>
						    	<li><a data-toggle="tab" href="#menu1">Select Mutiples</a></li>
						    	<li><a data-toggle="tab" href="#menu2">Count(*)</a></li>
						  	</ul>
						  	<div class="tab-content">
						  	
						  		<!-- TAB SELECT ONE -->
						    	<div id="home" class="tab-pane fade in active">
						     	 <h3>Select entity</h3>
						      	 <%@ include file="/WEB-INF/views/jsp/query_condition.jsp" %>
						      	 <div class="row div-condition" style="margin-top:5px; margin-left: 1px;">
						      	 	<a class="btn btn-primary" href="#" onclick="runQuery('home','get');"><i class="fa fa-rocket"></i> Run Query</a>
						      	 	<a class="btn btn-primary" href="#" onclick="addCondition('home');"><i class="fa fa-plus"></i> Add Condition</a>
						      	 	
						      	 	<spring:url value="/recipient/export?mode=get" var="exportLink"/>
						      	 	<a class="exportLink" href="${exportLink}" style="text-decoration: none; display:none; float: right; margin-top: 10px;"> >> Export cvs</a>
						      	 </div>
						      	 
						      	 <div class="row" style="margin-top:10px; margin-left: 1px;">
						      	 	<table class="table table-striped table-bordered">
									    <thead>
									      <tr>
									        <th>First name</th>
									        <th>Last name</th>
									        <th>Email</th>
									        <th>Tonariwa ID</th>
									      </tr>
									    </thead>
									    <tbody class="resultQuery">
									    </tbody>
									  </table>
						      	 </div>
						    	</div>
						    	
						    	<!-- TAB SELECT MUTIPLE -->
							    <div id="menu1" class="tab-pane fade">
							      <h3>Select Mutiples</h3>
							      	 <div class="row div-condition" style="margin-top:5px; margin-left: 1px;">
							      	 	<a class="btn btn-primary" href="#" onclick="runQuery('menu1','select');"><i class="fa fa-rocket"></i> Run Query</a>
							      	 	<a class="btn btn-primary" href="#" onclick="addCondition('menu1');"><i class="fa fa-plus"></i> Add Condition</a>
							      	 	
							      	 	<spring:url value="/recipient/export?mode=select" var="exportLink"/>
							      	 	<a class="exportLink" href="${exportLink}" style="text-decoration: none; display:none; float: right; margin-top: 10px;"> >> Export cvs</a>
							      	 </div>
							      	 
							      	 <div class="row" style="margin-top:10px; margin-left: 1px;">
							      	 	<table class="table tareturnble-striped table-bordered">
										    <thead>
										      <tr>
										        <th>First name</th>
										        <th>Last name</th>
										        <th>Email</th>
										        <th>Tonariwa ID</th>
										      </tr>
										    </thead>
										    <tbody class="resultQuery">
										    </tbody>
										  </table>
							      	 </div>
							    </div>
							    
							    <!-- TAB COUNT -->
							    <div id="menu2" class="tab-pane fade">
							      <h3>Count()</h3>
							      <div class="row div-condition" style="margin-top:5px; margin-left: 1px;">
							      	 <a class="btn btn-primary" href="#" onclick="runQuery('menu2','count');"><i class="fa fa-rocket"></i> Run Query</a>
							      	 <a class="btn btn-primary" href="#" onclick="addCondition('menu2');"><i class="fa fa-plus"></i> Add Condition</a>
							      </div>
							      
							      <div class="row" style="margin-top:10px; margin-left: 1px;">
							      	 	<table class="table table-striped table-bordered">
										    <thead>
										      <tr>
										        <th>Count(*)</th>
										      </tr>
										    </thead>
										    <tbody class="resultQuery">
										    </tbody>
										  </table>
							      	 </div>
							    </div>
						  </div>                        
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <spring:url value="/resources/core/js/jquery.js" var="jqueryJS" />
    <script src="${jqueryJS}"></script>
    
    <!-- jQuery -->
    <spring:url value="/resources/core/js/bootstrap.min.js" var="boostrapJS" />
    <script src="${boostrapJS}"></script>
    
    <%@ include file="/WEB-INF/views/jsp/login.jsp" %>
    <script type="text/javascript">
    	var homeUrl = '${homeUrl}';
    	homeUrl = (homeUrl.substring(homeUrl.length - 1,homeUrl.length) == '/')? homeUrl.substring(0, homeUrl.length - 1) : homeUrl;
    	function openLoginModal(){
    		$('#loginModal').modal('show');
    	}
    	function submitLoginAjax(){
    		var username = $('#lg_username').val().trim();
    		var password = $('#lg_password').val().trim();
    		
    		var xmlhttp = new XMLHttpRequest();
    		var url = homeUrl + "/login/sumbit/ajax?username=admin&password=$1$abcd#adm";

    		xmlhttp.onreadystatechange = function() {
    		    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    		        $('#liTokenSection').html('<p>Current token : '+ xmlhttp.responseText +'</p>');
    				$('#loginModal').modal('hide');
    				initFolderSelection();
    		    }
    		};
    		xmlhttp.open("POST", url, true);
    		xmlhttp.send();
    	}
    	
    	function initFolderSelection(){
    		$.ajax({
    			url : homeUrl + "/folder/get_recipient_folder",
    			type : "GET",
    			dataType : "json",
    			success : function(json){
    				$('#folderDiv').show();
    				var html = '<select class="form-control" data-folder="folder">';
    				var selectedTxt = 'selected="selected"';
    				html += '<option value="'+ json[0].folderId +'" selected="selected">'+ json[0].folderName +' (ROOT)</option>';
    				for(var i=1; i < json.length; i++){
    					html += '<option value="'+ json[i].folderId +'">'+ json[i].folderName +'</option>';
    				}
    				$($('#folderDiv .col-md-5')[0]).append(html);
    			},
    			error : function(er){
    				console.log(err);
    			}
    		});
    	}
    	
    	function addCondition(parentDiv){
    		var html = '<div class="row condition-wapper" style="margin-top:5px">'+
      	 				'<div class="col-md-3">'+
      	 				'<select class="form-control" data-key="key">'+
      	 				'<option value="email" selected="selected">Email</option>'+
      	 				'<option value="firstName">First Name</option>'+
      	 				'<option value="lastName">Last Name</option>'+
      	 				'<option value="tonariwaid">Tonariwa ID</option>'+
      	 				'</select>'+
      	 				'</div><div class="col-md-3">'+
      	 					'<select class="form-control" data-operand="operand">'+
			       			'<option value="=" selected="selected">EQUAL (=)</option>'+
			        		'<option value=">">GEATER (>)</option>'+
			        		'<option value=">=">GEATER THAN (>=)</option>'+
			        		'<option value="&lt;">LESS THAN (&lt;)</option>'+
			        		'<option value="&lt;&gt;">DIFF (&lt;&gt;)</option>'+
			    			'</select>'+
      	 				'</div>'+
      	 				'<div class="col-md-4">'+
      	 		 		'<input class="form-control" type="text" placeholder="value" data-value="value"/>'+
      	 				'</div><div class="col-md-2">'+
		      	 		'<a class="btn btn-warning" href="#" onclick="removeCondition($(this));"><i class="fa fa-remove"></i> Remove</a></div></div>';
			$('#'+parentDiv+' div.div-condition:last').before($(html));
    	}
    	function removeCondition(aElement){
    		$(aElement).closest("div.condition-wapper").remove();
    	}
    	function runQuery(parentDiv, mode){
    		var listKey = [], listOperand = [], listValue = [];
    		$('#'+ parentDiv + " select[data-key]").each(function(){
    			listKey.push($(this).val().trim());
    		});
    		$('#'+ parentDiv + " select[data-operand]").each(function(){
    			listOperand.push($(this).val().trim());
    		});
    		$('#'+ parentDiv + " input[data-value]").each(function(){
    			listValue.push($(this).val().trim());
    		});
    		var data = {
    			"mode": mode,
				"listKey" : JSON.stringify(listKey),
				"listOperand" : JSON.stringify(listOperand),
				"listValue" : JSON.stringify(listValue),
				"folder" : $('select[data-folder]').val()
    		};
    		$.ajax({
    			headers: { 
    		        'Accept': 'application/json',
    		        'Content-Type': 'application/json' 
    		    },
    			url : homeUrl + "/recipient/submit",
    			type : "POST",
    			dataType : "json",
    			data: JSON.stringify({"data": data}),
    			success : function(json){
    				if(json instanceof Array || mode == 'count'){
    					bindingTableRecipientHtml(parentDiv, json, mode);
    				}else if(json && json.email != null && json.email != undefined && json.email != ''){
    					bindingTableRecipientHtml(parentDiv, json, mode);
    				}else{
    					$('#'+ parentDiv + " tbody.resultQuery").html('');
    					$('#'+ parentDiv + " .exportLink").hide();
    				}
    			},
    			error : function(er){
    				alert("Please login first !!");
    			}
    		});
    	}
    	
    	function bindingTableRecipientHtml(parentDiv, recipient, mode){
    		var recipients = []
    		if(!(recipient instanceof Array)){
    			recipients.push(recipient);
    		}else{
    			recipients = recipient;
    		}
    		var html = '';
    		for(var i=0; i < recipients.length; i++){
    			var reci = recipients[i];
    			if(mode == 'count'){
    				html += '<tr><td>'+ reci + '</td>'+
	          		'</tr>';
    			}else{
    				html += '<tr><td>'+ reci.firstName + '</td>'+
	            	'<td>'+ reci.lastName +'</td>'+
	            	'<td>'+ reci.email +'</td>'+
	            	'<td>'+ reci.tonariwaid +'</td>'+
	          		'</tr>';    				
    			}
    		}
    		$('#'+ parentDiv + " tbody.resultQuery").html(html);
    		$('#'+ parentDiv + " .exportLink").show();
    	}
    	
    </script>
</body>

</html>