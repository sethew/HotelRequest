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
  	
  	<div class="hotelBlock" id="hotel.id">
  		<ul>
  			<li class="hotelName">${hotel.name}</li>
  			<li class="hotelDesc">${hotel.desc}</li>
  			<li class="hotelImg"><img src="${createLinkTo(dir: 'images', file: hotel.imageURL)}" /></li>
  			<g:each in="${hotel.roomType}" var="room">
  			<g:if test="${fullRoomsTypesMap[room.id] != null }">
  				<g:set var="roomDisabled" value="true" scope="page" />
  				<g:set var="buttonText" value="None Available" scope="page" />
  			</g:if>
  			<g:else>
  				<g:set var="roomDisabled" value="false" scope="page" />
  				<g:set var="buttonText" value="Request Room" scope="page" />
  			</g:else>
  			<ul class="rooms" id="${room.id}">
  				<li class="roomTitle">${room.title}</li>
  				<li class="roomDesc">${room.desc}</li>
  				<li class="price">${room.price}</li>
  				<li class="roomImg"><g:if test="${(room.imageURL != null && !room.imageURL.isEmpty())}"><img src="${createLinkTo(dir: 'images', file: room.imageURL)}" /></g:if>
  				<g:else><img src="${createLinkTo(dir: 'images', file: 'defaultroom.png')}" /></g:else></li>
  				<li class="requestButton"><g:form useToken="true" id="${room.id}">
  				<g:hiddenField name="roomId" value="${room.id}"/>
  				<g:actionSubmit name="requestRoom${room.id}" value="${buttonText}" disabled="${roomDisabled}" /></g:form></li>
  			</ul>
  			</g:each>
  		</ul>
  	</div>

  </g:each>
  </div>
  </div>
</body>
</html>