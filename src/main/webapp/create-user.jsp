<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<jsp:include page="principal/HeadInclude.jsp"></jsp:include>

<body>

	<jsp:include page="principal/ThemeLoader.jsp"></jsp:include>
	
  <!-- Pre-loader end -->
  <section class="login-block">
        <!-- Container-fluid starts -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <form class="md-float-material form-material" action="ServletCreateUser" method="post">
          				
                        <div class="auth-box card">
                            <div class="card-block">
                                <div class="row m-b-20">
                                    <div class="col-md-12">
                                        <h3 class="text-center txt-primary">Sign up</h3>
                                    </div>
                                </div>
                                <div class="form-group form-primary">
                                    <input id="username" type="text" name="username" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">Choose Username</label>
                                </div>
                                
                                <!-- 
                                	<div class="form-group form-primary">
                                    <input type="text" name="email" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">Your Email Address</label>
                               	 	</div>
                                 -->
                                
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group form-primary">
                                            <input id="password" type="password" name="password" class="form-control" required="">
                                            <span class="form-bar"></span>
                                            <label class="float-label">Password</label>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group form-primary">
                                            <input id="confirm-password" type="password" name="confirm-password" class="form-control" required="">
                                            
                                            <span class="form-bar"></span>
                                            <label class="float-label col-form-label">Confirm Password</label>
                                        </div>
                                    </div>
                                </div>
                                
                                <p id="form-message"></p>
                                
                                <!-- <div class="row m-t-25 text-left">
                                    <div class="col-md-12">
                                        <div class="checkbox-fade fade-in-primary">
                                            <label>
                                                <input type="checkbox" value="">
                                                <span class="cr"><i class="cr-icon icofont icofont-ui-check txt-primary"></i></span>
                                                <span class="text-inverse">I read and accept <a href="#">Terms &amp; Conditions.</a></span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="checkbox-fade fade-in-primary">
                                            <label>
                                                <input type="checkbox" value="">
                                                <span class="cr"><i class="cr-icon icofont icofont-ui-check txt-primary"></i></span>
                                                <span class="text-inverse">Send me the <a href="#!">Newsletter</a> weekly.</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>  -->
                                
                                <div class="row m-t-30">
                                    <div class="col-md-12">
                                        <button id="submit-button" type="submit" class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20">Sign up now</button>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-md-10">
                                        <p class="text-inverse text-left m-b-0">Thank you.</p>
                                        <p class="text-inverse text-left"><a href="index.jsp"><b>Back to website</b></a></p>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- end of col-sm-12 -->
            </div>
            <!-- end of row -->
        </div>
        <!-- end of container-fluid -->
    </section>

<jsp:include page="principal/JsScripts.jsp"></jsp:include>
<script>

	const form = document.querySelector("form");
	const password1 = document.querySelector("#password")
	const password2 = document.querySelector("#confirm-password")
	const button = document.querySelector("#submit-button")
	const formMessage = document.querySelector("#form-message")
	
	password2.addEventListener("keydown", function(event){
		
		if(password1.value !== this.value){
			console.log("Both password fields should be equal.")
			button.disabled = true;
			formMessage.textContent = "Both password fields should be equal."
			
		} else{
			button.disabled = false;
			formMessage.textContent = ""
			
		}
		
	})
	
</script>
</body>


</html>