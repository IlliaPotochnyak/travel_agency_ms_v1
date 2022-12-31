<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    </head>
    <body>
        <h2>Registration page</h2>
        <hr>
        ${errorRegister}
        <hr>
        <form action="RegisterServlet" method="post">
          <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label for="firstName"><b>First Name</b></label>
            <input type="text" placeholder="Enter First Name" name="firstName" id="firstName"
                    pattern="^[A-Za-z' А-Яа-яіІїЇ]{2,40}" required>
            <br>
            <br>
            <label for="lastName"><b>Last Name</b></label>
            <input type="text" placeholder="Enter Last Name" name="lastName" id="lastName"
                    pattern="^[A-Za-z' А-Яа-яіІїЇ]{2,40}" required>
            <br>
            <br>
            <label for="email"><b>Email</b></label>
            <input type="email" placeholder="Enter Email" name="email" id="email" required>
            <br>
            <br>
            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password"
                    pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$"
                    title="Must contain at least one number, one special character and one uppercase and lowercase letter, and at least 6 or more characters"
                    required>
            <br>
            <br>
            <label for="confirm_password"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="confirm_password" id="confirm_password"
                    pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$" required>
            <br>
            <br>
            <label for="phone"><b>Phone number</b></label>
            <input type="tel" placeholder="Phone number" name="phone" id="phone"
                    pattern="^[+][0-9]{2}-[0-9]{3}-[0-9]{3}-[0-9]{4}" required><br>
            <small>Format: +12-123-123-1234</small><br><br>
            <hr>

            <button type="submit" class="registerbtn" id="submit">Register</button>
          </div>

          <div class="container signin">
            <p>Already have an account? <a href="Login.jsp">Sign in</a>.</p>
          </div>
        </form>

        <hr>

    </body>
</html>
