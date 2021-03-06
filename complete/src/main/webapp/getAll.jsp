<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
      <title>Get all</title>
   </head>
   <body>
      <h1>Get all</h1><br>
      <form action="getAll" method="get">
         <input type="submit">
      </form>

      <c:if test="${records!=null }">
         <table class="table-striped">
            <thead>
               <tr>
                  <td>Age</td>
                  <td>Sex</td>
                  <td>Civil</td>
                  <td>Children</td>
                  <td>Car</td>
                  <td>House</td>
                  <td>Subdivision</td>
                  <td>Employment</td>
                  <td>Annum</td>
                  <td>Assets</td>
                  <td>Liabilities</td>
               </tr>
            </thead>
            <c:forEach items="${records}" var="curRecord" varStatus="loop">
               <tr>
                  <td><c:out value="${curRecord.getAge()}"/></td>
                  <td><c:out value="${curRecord.getSex()}"/></td>
                  <td><c:out value="${curRecord.getCivil()}"/></td>
                  <td><c:out value="${curRecord.getChildren()}"/></td>
                  <td><c:out value="${curRecord.getCar()}"/></td>
                  <td><c:out value="${curRecord.getHouse()}"/></td>
                  <td><c:out value="${curRecord.getSubdivision()}"/></td>
                  <td><c:out value="${curRecord.getEmployment()}"/></td>
                  <td><c:out value="${curRecord.getAnnum()}"/></td>
                  <td><c:out value="${curRecord.getAssets()}"/></td>
                  <td><c:out value="${curRecord.getLiabilities()}"/></td>
                  <td><c:out value="${curRecord.getPredictedAppraisal()}"/></td>
               </tr>
            </c:forEach>            
         </table>
      </c:if>
   </body>
</html>
