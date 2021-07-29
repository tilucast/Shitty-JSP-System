<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    

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
							                                    
							                                    <div class="form-group form-primary">
							                                        <input type="text" id="nickname" name="nickname" class="form-control" value="${user.getNickname()}" required="">
							                                        <span class="form-bar"></span>
							                                        <label class="float-label">Your Nickname</label>
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
							                                    
							                                    <!-- <button type="button" onclick="update()" id="submit-button" type="submit" class="btn btn-success btn-md btn-block waves-effect text-center m-b-20" data-dashlane-label="true" data-dashlane-rid="4d7b2dd372de02ba" data-form-type="action,register">Save Info</button> -->
							                                   	<button type="submit" id="submit-button" type="submit" class="btn btn-success btn-md btn-block waves-effect text-center m-b-20" data-dashlane-label="true" data-dashlane-rid="4d7b2dd372de02ba" data-form-type="action,register">Save Info</button> 
															
																<p style="color: green; font-weight: 500;" id="success"> ${Success} </p>
															
															</div>
														
														</div>
													
													</form>
													
													<form id="delete-account" class="md-float-material form-material" action="ServletUpdateUser" method="get" onsubmit="handleDeleteAccount(event)">
														<input type="hidden" name="delete-account" value="delete-account">
														<button style="margin-left: 20px;" class="btn btn-danger btn-md waves-effect text-center m-b-20">Delete Account</button>
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
	
	// Quick notes:
		
	// There is no reason to do an user update like this. Let the servlet handle all the dirty work. This kind of fetch request are
	// good for minor request that doesn't require changing any info that is present on the page.
	
	async function update() {
		
		const error = document.querySelector("#form-message")
		const success = document.querySelector("#success")
		
		error.textContent = "";
		success.textContent = "";
		
		const url = new URLSearchParams();
		url.append("login", form.elements[0].value);
		url.append("email", form.elements[1].value);
		url.append("password", form.elements[2].value);
		url.append("password", form.elements[3].value);
		url.append("confirm-password", form.elements[3].value);
		
		try{
			const result = await fetch(form.action, {
				 method: 'POST',
				    headers: {
				      'Content-Type': 'application/x-www-form-urlencoded',
				    },
				    body: url
			})
			const text = await result.text()
			
			result.status != 400 ? success.textContent = text : error.textContent = text
			
			console.log(result)
		}catch(error){
			console.log(error)
			error.textContent = error
		}
		
	}
		
		
	</script>
	
	<script>
		const handleDeleteAccount = (event) => {
			event.preventDefault();
			const formDelete = document.querySelector("#delete-account");
			const action = confirm("Are you sure you want to delete your account? This action is irreversible");
			if(action){
				formDelete.submit();
			}
		}
	</script>
	
	<script>
	
	const form = document.querySelector("#profile-form")
	function validatePasswords(passwordInput) {
		const formMessage = document.querySelector("#form-message")
		
		if(form.elements[3].value !== form.elements[4].value){
			form.elements[5].disabled = true;
			formMessage.textContent = "Both password fields should be equal!"
			
		} else{
			form.elements[5].disabled = false;
			formMessage.textContent = ""
			
		}
	}
	
	form.elements[3].addEventListener("keydown",validatePasswords)
	form.elements[3].addEventListener("keyup", validatePasswords)
	form.elements[4].addEventListener("keydown",  validatePasswords)
	form.elements[4].addEventListener("keyup", validatePasswords)
	
</script>

</body>
</html>