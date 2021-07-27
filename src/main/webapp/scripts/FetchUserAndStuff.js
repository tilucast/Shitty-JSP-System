async function popModal(event){
	event.preventDefault()
	const form = document.querySelector("form");
	const input = form.elements[0];
	const launchModal = document.querySelector("#launch-modal")
	
	if(input.value && input.value.trim()){
		launchModal.click()
		const usersInJson = await searchUser(input.value, form)
		populateTableWithUsers(usersInJson)
	}
}

async function searchUser(nickname, formElement){
	const url = new URLSearchParams()
	url.append("fetch-users", "fetch-users")
	url.append("nickname", nickname)
	
	try{
		
		const result = await fetch(formElement.action + '?' + url, {
			 headers: {
			      'Content-Type': 'application/x-www-form-urlencoded',
			 }
		})
		
		const x = await result.text()
		const parsedJson = JSON.parse(x)
		
		return parsedJson
		
	}catch(error){
		console.error(error)
	}
}
	
function populateTableWithUsers(usersInJson){
	const table = document.querySelector("table")
	
	if(!usersInJson.length){
		table.children[1].innerHTML = '<h4 style="margin-top: 20px;">No users found.</h4>'
		return;
	}
	
	table.children[1].innerHTML = usersInJson.map(userJson => `
			<tr>
				<th scope="row" >${userJson.id}</th>
				<td scope="row" >${userJson.nickname}</td>
				<td scope="row" >${userJson.email}</td>
				<td scope="row" ><button class="btn btn-info waves-effect waves-light">Info</button></td>
			</tr>
			`).join(" ")
}