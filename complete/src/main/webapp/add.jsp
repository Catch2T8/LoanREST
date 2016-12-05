<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
      <title>Add a new record</title>
   </head>
   <body>
      <h3>Add a new record</h3>
      <form action="add" method="get" class="form-horizontal">
         <div class="form-group">
            <label class="control-label col-sm-2">Age: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="age" name="age">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Sex: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="sex" name="sex">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Civil: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="civil" name="civil">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Children: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="children" name="children">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Car: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="car" name="car">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">House: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="house" name="house">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Subdivision: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="subdivision" name="subdivision">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Employment: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="employment" name="employment">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Annum: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="annum" name="annum">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Assets: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="assets" name="assets">
            </div>
         </div>
         <div class="form-group">
            <label class="control-label col-sm-2">Liabilities: </label>
            <div class="col-sm-10">
               <input type="text" class="form-control" id="liabilities" name="liabilities">
            </div>
         </div>
         <input type="submit" class="btn btn-default">Submit</input>
      </form>
   </body>
</html>
