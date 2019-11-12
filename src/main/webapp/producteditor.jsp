<%-- 
    Document   : producteditor
    Created on : 11 Nov 2019, 11:23:01
    Author     : zarpy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Editor</title>
    </head>
    
    <body>
        <div>
            <form name="update" action="FrontController" method = "POST">
                <input type="hidden" name="cmd" value="update_product">

                <h1>Edit product</h1>
                <br><br>

                <label for="product_id"><b>ID</b></label>
                <input type="text" name="product_id" id="id" onkeyup="validateId();" required>
                <div id="divValidateId"></div>
                <br>
                <label for="product_name"><b>Product Name</b></label>
                <input type="text" name="product_name" required>
                <br><br>
                <label for="product_desc"><b>Description</b></label>
                <input type="text" name="product_desc" required>
                <br><br>
                <label for="product_category"><b>Category</b></label>
                <input type="text" name="product_category" id="category" onkeyup="validateCategory();" required>
                <div id="divValidateCategory"></div>
                <br>

                <!-- Exception handling -->
                <c:if test="${error != null}">
                    <div class="form-alert">${error}</div>
                </c:if>
                    
                <input class="updatebtn" type="submit" value="Save Changes" onclick="confirmation()"/>
                <input class="deletebtn" type="submit" value="Delete Product" onclick="dconfirmation()"/>

            </form>
        </div>
        
                <!-- JavaScript functions -->
        <script>
            function validateID() {
                var id = $("#product_id").val();
                var idformat = /[0-9]/;

                if (!id.match(idformat)) {
                    $("#updatebtn").attr('disabled', 'diasabled');
                    $("#divValidateId").html("Invalid Id").addClass('form-alert');
                } else {
                    $("#updatebtn").removeAttr('disabled');
                    $("#divValidateId").html("").removeClass('form-alert');
                }
            }
            
            function validateCategory() {
                var category = $("#product_category").val();
                var categoryformat = /[a-z]/;
                
                if (!category.match(categoryformat)) {
                    $("#updatebtn").attr('disabled', 'diasbled');
                    $("#divValidateCategory").html("Invalid Category").addClass('form-alert');
                } else {
                    $("#updatebtn").removeAttr('disabled');
                    $("#divValidateCategory").html("").removeClass('form-alert');
                }
            }
            
            function confirmation() {
                alert("Product updated succesfully!");
            }
            
            function dconfirmation() {
                if (confirm("You are about to delete a product!")) {
                txt = "Product has been deleted!";
                } else {
                txt = "Return to Product page!";
                }
            }

        </script>
        
    </body>
</html>
