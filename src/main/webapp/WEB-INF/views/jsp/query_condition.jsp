<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row condition-wapper">
	<div class="col-md-3">
		<select class="form-control" data-key="key">
			<option value="email" selected="selected">Email</option>
			<option value="firstName">First Name</option>
			<option value="lastName">Last Name</option>
			<option value="tonariwaid">Tonariwa ID</option>
		</select>
	</div>
	<div class="col-md-3">
		<select class="form-control" data-operand="operand">
			<option value="=" selected="selected">EQUAL (=)</option>
			<option value=">">GEATER (>)</option>
			<option value=">=">GEATER THAN (>=)</option>
			<option value="&lt;">LESS THAN (&lt;)</option>
			<option value="&lt;&gt;">DIFF (&lt;&gt;)</option>
		</select>
	</div>
	<div class="col-md-4">
		<input class="form-control" type="text" placeholder="value"
			data-value="value" />
	</div>
	<div class="col-md-2">
		<a class="btn btn-warning" href="#"
			onclick="removeCondition($(this));"><i class="fa fa-remove"></i>
			Remove</a>
	</div>
</div>
