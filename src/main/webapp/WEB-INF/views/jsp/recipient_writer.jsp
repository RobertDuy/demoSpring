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
    <title>Adobe Campaign | Import Builder</title>
    
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
                            Recipient Import Builder<small></small>
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
	                        		<select class="form-control" data-folder="folder" onchange="changeFolder($(this))">
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
	                        		<i title="Choose folder you want to import in" data-placement="top" data-toggle="tooltip" class="fa fa-2x fa-question-circle"></i>
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
                        <div class="row" style="margin:10px; display: none;" id="folderDiv">
                        	<div class="col-md-2"><h3>Recipient Folder: </h3></div>
                        	<div class="col-md-5">
                        		<select class="form-control" data-folder="">
								</select>
                        	</div>
                        </div>
                        <div class="row" style="margin:10px">
                        	<div class="row">
                        			<label class="control-label col-md-4">Select File</label>
                        			<spring:url value="/resources/core/import.csv" var="importCSV" />
                        			<span>Example input file <a href="${importCSV}">import.csv</a></span>
                        	</div>
                        	<spring:url value="/recipient/writer/submit" var="actionWriterSubmit" />
                        	<form action="${actionWriterSubmit}" enctype="multipart/form-data" method="POST" style="margin-top:10px">
                        		<div class="row">
                        			<input id="fileInput" name="file" type="file" class="file col-md-4" />
                        			<input id="folderId" name="folderId" type="hidden" value=""/>
                        			<input class="btn btn-primary col-md-2" type="submit" value="Import" />
                        		</div>
                        	</form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

	<%@ include file="/WEB-INF/views/jsp/login.jsp" %>
    <!-- jQuery -->
    <spring:url value="/resources/core/js/jquery.js" var="jqueryJS" />
    <script src="${jqueryJS}"></script>
    
    <!-- jQuery -->
    <spring:url value="/resources/core/js/bootstrap.min.js" var="boostrapJS" />
    <script src="${boostrapJS}"></script>
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
    				var html = '<select class="form-control" data-folder="folder" onchange="changeFolder($(this))">';
    				var selectedTxt = 'selected="selected"';
    				html += '<option value="'+ json[0].folderId +'" selected="selected">'+ json[0].folderName +' (ROOT)</option>';
    				for(var i=1; i < json.length; i++){
    					html += '<option value="'+ json[i].folderId +'">'+ json[i].folderName +'</option>';
    				}
    				$($('#folderDiv .col-md-5')[0]).append(html);
    				$("form #folderId").val(json[0].folderId);
    			},
    			error : function(er){
    				console.log(err);
    			}
    		});
    	}
		function changeFolder(selectElement){
			$("form #folderId").val($(selectElement).val());
		}
    </script>
</body>

</html>