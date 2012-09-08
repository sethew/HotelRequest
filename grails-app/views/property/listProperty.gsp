<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Hotel Request - List Hotels and Rooms</title>
</head>

<body>
  <div class="body">
  <div class="eventDesc">Place Holder for some event description text</div>
  <div class="hotels">
  <g:each in="${properties}" var="hotel">
  	<%--<g:if test="${hotel.isAvalible }"> --%>
  	<div class="hotelBlock">
  		<ul>
  			<li class="hotelName">${hotel.name}</li>
  			<li class="hotelDesc">${hotel.desc}</li>
  			<li class="hotelImg"><img src="${createLinkTo(dir: 'images', file: hotel.imageURL)}" /></li>
  			<g:each in="${hotel.roomType}" var="room">
  			<ul class="rooms">
  				<li class="roomTitle">${room.title}</li>
  				<li class="roomDesc">${room.desc}</li>
  				<li class="price">${room.price}</li>
  				<li class="roomImg"><g:if test="${(room.imageURL != null && !room.imageURL.isEmpty())}"><img src="${createLinkTo(dir: 'images', file: room.imageURL)}" /></g:if>
  				<g:else><img src="${createLinkTo(dir: 'images', file: 'defaultroom.png')}" /></g:else></li>
  				<li class="requestButton"><g:form useToken="true"><g:actionSubmit value="Request Room" /></g:form></li>
  			</ul>
  			</g:each>
  		</ul>
  	</div>

  </g:each>
  </div>
  </div>
</body>
</html>