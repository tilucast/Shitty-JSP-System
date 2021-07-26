<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<jsp:include page="HeadInclude.jsp"></jsp:include>

<body>

	<jsp:include page="ThemeLoader.jsp"></jsp:include>
	
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		
		<div class="pcoded-container navbar-wrapper">
		
			<jsp:include page="TopNavbar.jsp"></jsp:include>
			
			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
				
					<jsp:include page="SideNavbar.jsp" ></jsp:include>
					
					<div class="pcoded-content">
					
						<jsp:include page="PageHeader.jsp"></jsp:include>
						
						<div class="pcoded-inner-content">
						
							<div class="main-body">
								
								<div class="page-wrapper">
								
									<div class="page-body">
									
										<div class="row">
										
											<div class="col-sm-12">
											
												<div class="card">
													
													<div class="card-header">
													
														<h5>User Information</h5>
														<span>You can see and edit your personal info here.</span>
														
													</div>
													
													<form id="profile-form" class="md-float-material form-material" action="ServletUpdateUser" method="post">
													
														<div class="auth-box card">
														
															<div class="card-block">
															
																<div class="form-group form-primary">
							                                        <input type="text" id="login" name="login" class="form-control" value="${user.getLogin()}" required="">
							                                        <span class="form-bar"></span>
							                                        <label class="float-label">Your Login</label>
							                                    </div>
							                                    
							                                    <div class="form-group form-primary">
							                                        <input type="email" id="email" name="email" class="form-control" value="${user.getEmail()}" required="">
							                                        <span class="form-bar"></span>
							                                        <label class="float-label">Your Email Adress</label>
							                                    </div>
							                                    
							                                    <div class="row">
							                                    
							                                    	<div class="col-sm-6">
							                                    	
							                                    		<div class="form-group form-primary">
									                                        <input type="password" id="password" name="password" class="form-control" value="${ user.getPassword() }" required="">
									                                        <span class="form-bar"></span>
									                                        <label class="float-label">Your Password</label>
									                                    </div>
							                                    		
							                                    	</div>
							                                    	
							                                    	<div class="col-sm-6">
							                                    	
							                                    		<div class="form-group form-primary">
									                                        <input type="password" id="confirm-password" name="confirm-password" class="form-control" value="${ user.getPassword() }" required="">
									                                        <span class="form-bar"></span>
									                                        <label class="float-label">Confirm the Password</label>
									                                    </div>
							                                    		
							                                    	</div>
							                                    
							                                    </div>
							                                    
							                                    <p style="color: crimson; font-weight: 500;" id="form-message"> ${Message} </p>
							                                    
							                                    <button id="submit-button" type="submit" class="btn btn-success btn-md btn-block waves-effect text-center m-b-20" data-dashlane-label="true" data-dashlane-rid="4d7b2dd372de02ba" data-form-type="action,register">Save Info</button>
															
																<p style="color: green; font-weight: 500;" id="success"> ${Success} </p>
															
															</div>
														
														</div>
													
													</form>
												
												</div>
											
											</div>
										
										</div>
									
									</div>
								
								</div>
								
							</div>
						
						</div>
						
					</div>
					
				</div>
			</div>
		
		</div>
	</div>

	<jsp:include page="JsScripts.jsp"></jsp:include>
	
	<script>
	const form = document.querySelector("#profile-form")
	const formMessage = document.querySelector("#form-message")
	
	function validatePasswords(passwordInput) {
		
		if(form.elements[2].value !== form.elements[3].value){
			form.elements[4].disabled = true;
			formMessage.textContent = "Both password fields should be equal!"
			
		} else{
			form.elements[4].disabled = false;
			formMessage.textContent = ""
			
		}
	}
	
	form.elements[2].addEventListener("keydown",validatePasswords)
	form.elements[2].addEventListener("keyup", validatePasswords)
	form.elements[3].addEventListener("keydown",  validatePasswords)
	form.elements[3].addEventListener("keyup", validatePasswords)
	
</script>

</body>
</html>