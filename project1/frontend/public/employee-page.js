let logoutBtn = document.querySelector('#newlogout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwt');

    window.location = '/index.html';
});




document.getElementById("addReimbursement-form").style.display = "none";
function addReimbursement(){
    document.getElementById("reimbursements-tbl").style.display = "none";
    let tbody = document.querySelector('#reimbursements-tbl > tbody');
    tbody.innerHTML = '';
    let thead = document.querySelector('#reimbursements-tbl > thead');
    thead.innerHTML = '';
    let x = document.getElementById("addReimbursement-form");
    if (x.style.display === "none") {
        x.style.display = "block";
      } else {
        x.style.display = "none";
      }

}



function show_list() {
    var courses = document.getElementById("courses_id");
    

    if (courses.style.display == "block") {
        courses.style.display = "none";
    } else {
        courses.style.display = "block";
    }
}


let reimbursementSubmit = document.querySelector('#submitBtn');
reimbursementSubmit.addEventListener('click', async () => {
    
    let amountInput = document.querySelector('#amount');
    let dateInput = document.querySelector('#submitted');
    let descriptionInput = document.querySelector('#description');
    let imageInput = document.querySelector('#receipt');
    let typeInput = document.querySelector('#type');

    let formData = new FormData();
    formData.append('amount', amountInput.value);
    formData.append('submitted', dateInput.value);
    formData.append('description', descriptionInput.value);
    formData.append('receipt', imageInput.files[0]);
    formData.append('type',typeInput.value);
    console.log(dateInput.value);
    

    try {
        await fetch(`http://34.134.113.192:8080/users/${localStorage.getItem('user_id')}/reimbursements`, {
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }
        });
        populateReimbursementsTable();

        
    } catch (e) {
        console.log(e);
    }
    
});


async function populateReimbursementsTable() {
    document.getElementById("reimbursements-tbl").style.display = "block";
    const URL = `http://34.134.113.192:8080/users/${localStorage.getItem('user_id')}/reimbursements`;

    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
        }
    })
    let tbody = document.querySelector('#reimbursements-tbl > tbody');
    tbody.innerHTML = '';
    let thead = document.querySelector('#reimbursements-tbl > thead');
    thead.innerHTML = '';
    let x = document.getElementById("addReimbursement-form");
    if (x.style.display === "block") {
        x.style.display = "none";
      } 
    if (res.status === 200) {
        let reimbursements = await res.json();

        for (let reimbursement of reimbursements) {
            let tr = document.createElement('tr');

            let td1 = document.createElement('td');
            td1.innerText = reimbursement.id;

            let td2 = document.createElement('td');
            td2.innerText = reimbursement.reimbAmount;

            let td3 = document.createElement('td');
            td3.innerText = reimbursement.reimbSubmitted;

            let td4 = document.createElement('td');
            td4.innerText = reimbursement.reimbDescription;

            let receiptImage = document.createElement('td');
                        let imgElement = document.createElement('img');
                        imgElement.setAttribute('src', `http://34.134.113.192:8080/reimbursements/${reimbursement.id}/image`);
                        //document.getElementById("img").alt = "NULL";
                        imgElement.style.height = '100px';
                        receiptImage.appendChild(imgElement);

            let td5 = document.createElement('td');
            td5.innerText = reimbursement.reimbAuthor;

            let td6 = document.createElement('td');
            if(reimbursement.reimbStatusId ==1){
                td6.innerText = "Approved"
                td6.style.color = 'green';
            }
            if(reimbursement.reimbStatusId ==2){
                td6.innerText = "Pending"
                td6.style.color = 'yellow';
            }
            if(reimbursement.reimbStatusId ==3){
                td6.innerText = "Rejected"
                td6.style.color = 'red';
            }
            
            let td7 = document.createElement('td');
            if (reimbursement.reimbTypeId == 1) {
                td7.innerText = "Lodging";
                
            }
            if (reimbursement.reimbTypeId == 2) {
                td7.innerText = "Travel";
                
            }
            if (reimbursement.reimbTypeId == 3) {
                td7.innerText = "Food";
                
            }
            if (reimbursement.reimbTypeId == 4) {
                td7.innerText = "Other";
                
            }

            let td8 = document.createElement('td');
            td8.innerText = reimbursement.employeeUsername;

            let td9 = document.createElement('td');
            td9.innerText = (reimbursement.managerUsername ? reimbursement.managerUsername : 'Not Resolved');
            td9.style.color = (reimbursement.managerUsername ? td5.style.color : 'RGB(255, 0, 0)');

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(receiptImage);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(td9);

            
            tbody.appendChild(tr);

            

        
        }
        let tr = document.createElement('tr');

        let td1 = document.createElement('th');
        td1.innerText = "Reimbursement Id";

        let td2 = document.createElement('th');
        td2.innerText = "Amount";

        let td3 = document.createElement('th');
        td3.innerText = "Date Submitted";

        let td4 = document.createElement('th');
        td4.innerText = "Description";

        let receiptheader = document.createElement('th');
        receiptheader.innerText = "Receipt";

        let td5 = document.createElement('th');
        td5.innerText = "Employee Id";

        let td6 = document.createElement('th');
        td6.innerText = "Status";
            
        let td7 = document.createElement('th');
        td7.innerText = "Type";

        let td8 = document.createElement('th');
        td8.innerText = "Employee Username"

        let td9 = document.createElement('th');
        td9.innerText = "Manager Username";
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(receiptheader);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        
        thead.appendChild(tr);

            

        
        }
    }

async function populatePendingReimbursementsTable() {
    document.getElementById("reimbursements-tbl").style.display = "block";
    const URL = `http://34.134.113.192:8080/users/${localStorage.getItem('user_id')}/reimbursements/pending`;

    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
        }
    })
    let tbody = document.querySelector('#reimbursements-tbl > tbody');
    tbody.innerHTML = '';
    let thead = document.querySelector('#reimbursements-tbl > thead');
    thead.innerHTML = '';
    let x = document.getElementById("addReimbursement-form");
    if (x.style.display === "block") {
        x.style.display = "none";
      } 
    if (res.status === 200) {
        let reimbursements = await res.json();

        for (let reimbursement of reimbursements) {
            let tr = document.createElement('tr');

            let td1 = document.createElement('td');
            td1.innerText = reimbursement.id;

            let td2 = document.createElement('td');
            td2.innerText = reimbursement.reimbAmount;

            let td3 = document.createElement('td');
            td3.innerText = reimbursement.reimbSubmitted;

            let td4 = document.createElement('td');
            td4.innerText = reimbursement.reimbDescription;

            let receiptImage = document.createElement('td');
                        let imgElement = document.createElement('img');
                        imgElement.setAttribute('src', `http://34.134.113.192:8080/reimbursements/${reimbursement.id}/image`);
                        //document.getElementById("img").alt = "NULL";
                        imgElement.style.height = '100px';
                        receiptImage.appendChild(imgElement);

            let td5 = document.createElement('td');
            td5.innerText = reimbursement.reimbAuthor;

            let td6 = document.createElement('td');
            if(reimbursement.reimbStatusId ==1){
                td6.innerText = "Approved"
                td6.style.color = 'green';
            }
            if(reimbursement.reimbStatusId ==2){
                td6.innerText = "Pending"
                td6.style.color = 'yellow';
            }
            if(reimbursement.reimbStatusId ==3){
                td6.innerText = "Rejected"
                td6.style.color = 'red';
            }
            
            let td7 = document.createElement('td');
            if (reimbursement.reimbTypeId == 1) {
                td7.innerText = "Lodging";
                
            }
            if (reimbursement.reimbTypeId == 2) {
                td7.innerText = "Travel";
                
            }
            if (reimbursement.reimbTypeId == 3) {
                td7.innerText = "Food";
                
            }
            if (reimbursement.reimbTypeId == 4) {
                td7.innerText = "Other";
                
            }

            let td8 = document.createElement('td');
            td8.innerText = reimbursement.employeeUsername;

            let td9 = document.createElement('td');
            td9.innerText = (reimbursement.managerUsername ? reimbursement.managerUsername : 'Not Resolved');
            td9.style.color = (reimbursement.managerUsername ? td5.style.color : 'RGB(255, 0, 0)');

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(receiptImage);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(td9);

            
            tbody.appendChild(tr);

            

        
        }
        let tr = document.createElement('tr');

        let td1 = document.createElement('th');
        td1.innerText = "Reimbursement Id";

        let td2 = document.createElement('th');
        td2.innerText = "Amount";

        let td3 = document.createElement('th');
        td3.innerText = "Date Submitted";

        let td4 = document.createElement('th');
        td4.innerText = "Description";

        let receiptheader = document.createElement('th');
        receiptheader.innerText = "Receipt";

        let td5 = document.createElement('th');
        td5.innerText = "Employee Id";

        let td6 = document.createElement('th');
        td6.innerText = "Status";
            
        let td7 = document.createElement('th');
        td7.innerText = "Type";

        let td8 = document.createElement('th');
        td8.innerText = "Employee Username"

        let td9 = document.createElement('th');
        td9.innerText = "Manager Username";
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(receiptheader);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        
        thead.appendChild(tr);

            

        
        }
    }

    async function populateResolvedReimbursementsTable() {
        document.getElementById("reimbursements-tbl").style.display = "block";
        const URL = `http://34.134.113.192:8080/users/${localStorage.getItem('user_id')}/reimbursements/resolved`;
    
        let res = await fetch(URL, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
            }
        })
        let tbody = document.querySelector('#reimbursements-tbl > tbody');
        tbody.innerHTML = '';
        let thead = document.querySelector('#reimbursements-tbl > thead');
        thead.innerHTML = '';
        let x = document.getElementById("addReimbursement-form");
        if (x.style.display === "block") {
            x.style.display = "none";
          } 
        if (res.status === 200) {
            let reimbursements = await res.json();
    
            for (let reimbursement of reimbursements) {
                let tr = document.createElement('tr');
    
                let td1 = document.createElement('td');
                td1.innerText = reimbursement.id;
    
                let td2 = document.createElement('td');
                td2.innerText = reimbursement.reimbAmount;
    
                let td3 = document.createElement('td');
                td3.innerText = reimbursement.reimbSubmitted;
    
                let td4 = document.createElement('td');
                td4.innerText = reimbursement.reimbDescription;

                let receiptImage = document.createElement('td');
                        let imgElement = document.createElement('img');
                        imgElement.setAttribute('src', `http://34.134.113.192:8080/reimbursements/${reimbursement.id}/image`);
                        //document.getElementById("img").alt = "NULL";
                        imgElement.style.height = '100px';
                        receiptImage.appendChild(imgElement);
    
                let td5 = document.createElement('td');
                td5.innerText = reimbursement.reimbAuthor;
    
                let td6 = document.createElement('td');
                if(reimbursement.reimbStatusId ==1){
                    td6.innerText = "Approved"
                    td6.style.color = 'green';
                }
                if(reimbursement.reimbStatusId ==2){
                    td6.innerText = "Pending"
                    td6.style.color = 'yellow';
                }
                if(reimbursement.reimbStatusId ==3){
                    td6.innerText = "Rejected"
                    td6.style.color = 'red';
                }
                
                let td7 = document.createElement('td');
                if (reimbursement.reimbTypeId == 1) {
                    td7.innerText = "Lodging";
                    
                }
                if (reimbursement.reimbTypeId == 2) {
                    td7.innerText = "Travel";
                    
                }
                if (reimbursement.reimbTypeId == 3) {
                    td7.innerText = "Food";
                    
                }
                if (reimbursement.reimbTypeId == 4) {
                    td7.innerText = "Other";
                    
                }
    
                let td8 = document.createElement('td');
                td8.innerText = reimbursement.employeeUsername;
    
                let td9 = document.createElement('td');
                td9.innerText = (reimbursement.managerUsername ? reimbursement.managerUsername : 'Not Resolved');
                td9.style.color = (reimbursement.managerUsername ? td5.style.color : 'RGB(255, 0, 0)');
    
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(receiptImage);
                tr.appendChild(td5);
                tr.appendChild(td6);
                tr.appendChild(td7);
                tr.appendChild(td8);
                tr.appendChild(td9);
    
                
                tbody.appendChild(tr);
    
                
    
            
            }
            let tr = document.createElement('tr');
    
            let td1 = document.createElement('th');
            td1.innerText = "Reimbursement Id";
    
            let td2 = document.createElement('th');
            td2.innerText = "Amount";
    
            let td3 = document.createElement('th');
            td3.innerText = "Date Submitted";
    
            let td4 = document.createElement('th');
            td4.innerText = "Description";

            let receiptheader = document.createElement('th');
            receiptheader.innerText = "Receipt";
    
            let td5 = document.createElement('th');
            td5.innerText = "Employee Id";
    
            let td6 = document.createElement('th');
            td6.innerText = "Status";
                
            let td7 = document.createElement('th');
            td7.innerText = "Type";
    
            let td8 = document.createElement('th');
            td8.innerText = "Employee Username"
    
            let td9 = document.createElement('th');
            td9.innerText = "Manager Username";
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(receiptheader);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(td9);
            
            thead.appendChild(tr);
    
                
    
            
            }
        }

    