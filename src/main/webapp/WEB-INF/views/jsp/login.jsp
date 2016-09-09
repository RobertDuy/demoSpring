<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" tabindex="-1" role="dialog" id="loginModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Login to Adobe Campaign</h4>
      </div>
      <div class="modal-body">
        <div class="login-group">
			<div class="form-group">
				<label for="lg_username" class="sr-only">Username</label>
				<input type="text" class="form-control" id="lg_username" name="lg_username" placeholder="username">
			</div>
			<div class="form-group">
				<label for="lg_password" class="sr-only">Password</label>
				<input type="password" class="form-control" id="lg_password" name="lg_password" placeholder="password">
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="submitLoginAjax();">Login</button>
      </div>
    </div>
  </div>
</div>