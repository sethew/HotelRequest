<%@ page import="hotelrequest.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'addr1', 'error')} ">
	<label for="addr1">
		<g:message code="user.addr1.label" default="Addr1" />
		
	</label>
	<g:textField name="addr1" value="${userInstance?.addr1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'addr2', 'error')} ">
	<label for="addr2">
		<g:message code="user.addr2.label" default="Addr2" />
		
	</label>
	<g:textField name="addr2" value="${userInstance?.addr2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="user.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${userInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'country', 'error')} ">
	<label for="country">
		<g:message code="user.country.label" default="Country" />
		
	</label>
	<g:textField name="country" value="${userInstance?.country}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'current', 'error')} ">
	<label for="current">
		<g:message code="user.current.label" default="Current" />
		
	</label>
	<g:checkBox name="current" value="${userInstance?.current}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'customerClass', 'error')} required">
	<label for="customerClass">
		<g:message code="user.customerClass.label" default="Customer Class" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="customerClass" type="number" value="${userInstance.customerClass}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwdHash', 'error')} ">
	<label for="passwdHash">
		<g:message code="user.passwdHash.label" default="Passwd Hash" />
		
	</label>
	<g:textField name="passwdHash" value="${userInstance?.passwdHash}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'postalCode', 'error')} ">
	<label for="postalCode">
		<g:message code="user.postalCode.label" default="Postal Code" />
		
	</label>
	<g:textField name="postalCode" value="${userInstance?.postalCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="user.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${userInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userId', 'error')} required">
	<label for="userId">
		<g:message code="user.userId.label" default="User Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="userId" type="number" value="${userInstance.userId}" required=""/>
</div>

