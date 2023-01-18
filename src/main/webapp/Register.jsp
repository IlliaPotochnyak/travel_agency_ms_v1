<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="languages/messages"/>
<html lang="${sessionScope.lang}">

<html>
    <head>
        <script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

         <style>
            #form label{float:left; width:140px;}
            #error_msg{color:red; font-weight:bold;}
         </style>

         <script>
            $(document).ready(function(){
                var $submitBtn = $("#form input[type='submit']");
                var $passwordBox = $("#password");
                var $confirmBox = $("#confirm_password");
                var $errorMsg =  $('<span id="error_msg">Passwords do not match.</span>');

                // This is incase the user hits refresh - some browsers will maintain the disabled state of the button.
                $submitBtn.removeAttr("disabled");

                function checkMatchingPasswords(){
                    if($confirmBox.val() != "" && $passwordBox.val != ""){
                        if( $confirmBox.val() != $passwordBox.val() ){
                            $submitBtn.attr("disabled", "disabled");
                            $errorMsg.insertAfter($confirmBox);
                        }
                    }
                }

                function resetPasswordError(){
                    $submitBtn.removeAttr("disabled");
                    var $errorCont = $("#error_msg");
                    if($errorCont.length > 0){
                        $errorCont.remove();
                    }
                }


                $("#confirm_password, #password")
                     .on("keydown", function(e){
                        /* only check when the tab or enter keys are pressed
                         * to prevent the method from being called needlessly  */
                        if(e.keyCode == 13 || e.keyCode == 9) {
                            checkMatchingPasswords();
                        }
                     })
                     .on("blur", function(){
                        // also check when the element looses focus (clicks somewhere else)
                        checkMatchingPasswords();
                    })
                    .on("focus", function(){
                        // reset the error message when they go to make a change
                        resetPasswordError();
                    })

            });
          </script>
          <!-- Latest compiled and minified CSS -->
              <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

              <!-- Latest compiled JavaScript -->
              <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="WEB-INF/view/Header.jsp" flush="true"/>

        <div class="container">
            ${errorRegister}
        </div>
        <hr>
        <div class="container mt-3">


            <form action="RegisterServlet" method="post">

                <h1><fmt:message key="register.message" /></h1>
                <p><fmt:message key="register.messageText" /></p>
                <hr>
                <div class="mb-2">
                    <label for="firstName" class="form-label"><b><fmt:message key="register.FirstName" />*</b></label>
                    <input type="text" class="form-control" placeholder="Enter First Name" name="firstName" id="firstName"
                            pattern="^[A-Za-z' А-Яа-яіІїЇ]{2,40}" required>
                </div>

                <div class="mb-2">
                    <label for="lastName" class="form-label"><b><fmt:message key="register.LastName" />*</b></label>
                    <input type="text" class="form-control" placeholder="Enter Last Name" name="lastName" id="lastName"
                            pattern="^[A-Za-z' А-Яа-яіІїЇ]{2,40}" required>
                </div>

                <div class="mb-2">
                    <label for="email" class="form-label"><b>Email*</b></label>
                    <input type="email" class="form-control" placeholder="Enter Email" name="email" id="email" required>
                </div>

                <div class="mb-2">
                    <label for="password" class="form-label"><b><fmt:message key="register.password" />*</b></label>
                    <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password"
                            pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$"
                            title="Must contain at least one number, one special character and one uppercase and lowercase letter, and at least 6 or more characters"
                            required>
                </div>

                <div class="mb-2">
                    <label for="confirm_password" class="form-label"><b><fmt:message key="register.passwordRep" />*</b></label>
                    <input type="password" class="form-control" placeholder="Repeat Password" name="confirm_password" id="confirm_password"
                            pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$" required>
                </div>

                <div class="mb-2">
                    <label for="phone" class="form-label"><b><fmt:message key="register.phone" />*</b></label>
                    <input type="tel" class="form-control" placeholder="Phone number" name="phone" id="phone"
                            pattern="^[+][0-9]{2}-[0-9]{3}-[0-9]{3}-[0-9]{4}" required><br>
                    <small><fmt:message key="register.format" />: +12-123-123-1234</small><br><br>
                </div>

                <button type="submit"  class="btn btn-primary" id="submit"><fmt:message key="register.registerButton" /></button>
                </div>

                <div class="container signin">
                <p><fmt:message key="register.haveAnAccount" />? <a href="Login.jsp"><fmt:message key="register.signIn" /></a>.</p>
                </div>
            </form>
        </div>

        <hr>

    </body>
</html>
