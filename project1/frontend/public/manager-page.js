let logoutBtn = document.querySelector('#newlogout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwt');

    window.location = '/index.html';
});
let judgeReimbBtn = document.querySelector('#logout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwt');

    window.location = '/index.html';
});


function show_list() {
    var courses = document.getElementById("courses_id");
    

    if (courses.style.display == "block") {
        courses.style.display = "none";
    } else {
        courses.style.display = "block";
    }
}
window.onclick = function (event) {
    if (document.getElementsByClassName('dropdown_list')[0].contains(event.target)) {
        deleteTable();
        document.getElementById('courses_id').style.display = "none";
    }
}; 

function deleteTable() {
    let rowCount = document.getElementById("reimbursements-tbl").rows.length
    for(let i =0;i<=rowCount;i+1){
        document.getElementById("reimbursements-tbl").deleteRow(i);
   
  }
}



async function populateReimbursementsTable() {
    const URL = 'http://34.134.113.192:8080/reimbursements';

    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
        }
        // credentials: 'include' // This is if you're using HttpSession w/ JSESSIONID cookies
    })
    let tbody = document.querySelector('#reimbursements-tbl > tbody');
    tbody.innerHTML = '';
    let thead = document.querySelector('#reimbursements-tbl > thead');
    thead.innerHTML='';

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
                td6.style.color = 'limegreen';
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

    async function populateDeniedReimbursementsTable() {    
        const URL = 'http://34.134.113.192:8080/reimbursements/denied';
    
        let res = await fetch(URL, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
            }
            // credentials: 'include' // This is if you're using HttpSession w/ JSESSIONID cookies
        })
    
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
                }
                if(reimbursement.reimbStatusId ==2){
                    td6.innerText = "Pending";
                }
                if(reimbursement.reimbStatusId ==3){
                    td6.innerText = "Rejected";
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
    
                let tbody = document.querySelector('#reimbursements-tbl > tbody');
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
            let thead= document.querySelector('#reimbursements-tbl > thead');
            thead.appendChild(tr);
    
                
    
            
            }
            
            
        }
        async function populateApprovedReimbursementsTable() {
            const URL = 'http://34.134.113.192:8080/reimbursements/approved';
        
            let res = await fetch(URL, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                }
                // credentials: 'include' // This is if you're using HttpSession w/ JSESSIONID cookies
            })
        
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
                        td6.innerText = "Approved";
                        td6.style.color = 'limegreen';
                    }
                    if(reimbursement.reimbStatusId ==2){
                        td6.innerText = "Pending"
                    }
                    if(reimbursement.reimbStatusId ==3){
                        td6.innerText = "Rejected"
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
        
                    let tbody = document.querySelector('#reimbursements-tbl > tbody');
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
                let tbody = document.querySelector('#reimbursements-tbl > thead');
                tbody.appendChild(tr);
        
                    
        
                
                }
            }
            async function populatePendingReimbursementsTable() {
                const URL = 'http://34.134.113.192:8080/reimbursements/pending';
            
                let res = await fetch(URL, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                    }
                    // credentials: 'include' // This is if you're using HttpSession w/ JSESSIONID cookies
                })
            
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
                            td6.innerText = "Approved";
                        }
                        if(reimbursement.reimbStatusId ==2){
                            td6.innerText = "Pending";
                            td6.style.color = 'yellow';
                        }
                        if(reimbursement.reimbStatusId ==3){
                            td6.innerText = "Rejected";
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
            
                        let tbody = document.querySelector('#reimbursements-tbl > tbody');
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
                    let tbody = document.querySelector('#reimbursements-tbl > thead');
                    tbody.appendChild(tr);
            
                        
            
                    
                    }
                }
                async function approveReimbursementsTable() {
                    const URL = 'http://34.134.113.192:8080/reimbursements/pending';
                
                    let res = await fetch(URL, {
                        method: 'GET',
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                        }
                        // credentials: 'include' // This is if you're using HttpSession w/ JSESSIONID cookies
                    })
                
                    if (res.status === 200) {
                        let reimbursements = await res.json();
                        let tbody = document.querySelector('#reimbursements-tbl > tbody');
                        tbody.innerHTML = '';
                        let thead = document.querySelector('#reimbursements-tbl > thead');
                        thead.innerHTML = '';
                
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
                                td6.innerText = "Approved";
                            }
                            if(reimbursement.reimbStatusId ==2){
                                td6.innerText = "Pending";
                                td6.style.color = 'yellow';
                            }
                            if(reimbursement.reimbStatusId ==3){
                                td6.innerText = "Rejected";
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
                            
                            let dateInput = document.createElement('input');
                            dateInput.setAttribute('type','date');


                            let approveInput = document.createElement('select');
                            approveInput.id = 'mySelect';
                            let option = document.createElement("option");
                            option.value=2;
                            option.text = 'Pending';
                            approveInput.appendChild(option);
                            let option2 = document.createElement("option");
                            option2.value=3;
                            option2.text = 'Deny';
                            approveInput.appendChild(option2);
                            let option3 = document.createElement("option");
                            option3.value=1;
                            option3.text = 'Approve';
                            approveInput.appendChild(option3);
                            let submitButton = document.createElement('button');
                            submitButton.innerText = 'Submit';
                            submitButton.style.backgroundColor='yellow';

                            submitButton.addEventListener('click',async() =>{
                                let statusVal = approveInput.value;
                                try {
                                    let res = await fetch(`http://34.134.113.192:8080/reimbursements/${reimbursement.id}?reimb_resolved = ${dateInput.value}&reimb_status_id=${statusVal}`, {
                                        // credentials: 'include' // HttpSession based login + authorization
                                        method: 'PATCH',
                                        headers: {
                                            'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                                        }
                                    });
            
                                    if (res.status === 200) {
                                        approveReimbursementsTable(); // Have this function call itself
                                    }
            
                                } catch (e) {
                                    console.log(e);
                                }

                            });
                
                            tr.appendChild(td1);
                            tr.appendChild(td2);
                            tr.appendChild(td3);
                            tr.appendChild(td4);
                            tr.appendChild(receiptImage);
                            tr.appendChild(td5);
                            tr.appendChild(approveInput);
                            tr.appendChild(td7);
                            tr.appendChild(td8);
                            tr.appendChild(td9);
                            tr.appendChild(dateInput);
                            tr.appendChild(submitButton);
                
                            
                            
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
                        let td10 = document.createElement('th');
                        td10.innerText = "Date Resolved";
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
                        tr.appendChild(td10);
                        
                        
                        
                        thead.appendChild(tr);
                        
                
                            
                
                        
                        }
                    }


                    async function deleteReimbursementsTable() {
                        const URL = 'http://34.134.113.192:8080/reimbursements';
                    
                        let res = await fetch(URL, {
                            method: 'GET',
                            headers: {
                                'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                            }
                            // credentials: 'include' // This is if you're using HttpSession w/ JSESSIONID cookies
                        })
                    
                        if (res.status === 200) {
                            let reimbursements = await res.json();
                            let tbody = document.querySelector('#reimbursements-tbl > tbody');
                            tbody.innerHTML = '';
                            let thead = document.querySelector('#reimbursements-tbl > thead');
                            thead.innerHTML = '';
                    
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
                                    td6.innerText = "Approved";
                                }
                                if(reimbursement.reimbStatusId ==2){
                                    td6.innerText = "Pending";
                                    td6.style.color = 'yellow';
                                }
                                if(reimbursement.reimbStatusId ==3){
                                    td6.innerText = "Rejected";
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
                                
                                let td10 = document.createElement('button');
                                //td10.innerText = 'test';
                                //td10.createElement('button');
                                td10.innerText = 'Delete';
                                td10.style.marginTop='50px';
                                td10.style.backgroundColor = 'yellow';
                                //submitButton.style.marginTop= '100px';
                                //submitButton.style.marginBottom = "100%";
    
                                td10.addEventListener('click',async() =>{
                                    try {
                                        let res = await fetch(`http://34.134.113.192:8080/reimbursements/${reimbursement.id}`, {
                                            // credentials: 'include' // HttpSession based login + authorization
                                            method: 'DELETE',
                                            headers: {
                                                'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                                            }
                                        });
                
                                        if (res.status === 200) {
                                            deleteReimbursementsTable(); // Have this function call itself
                                        }
                
                                    } catch (e) {
                                        console.log(e);
                                    }
    
                                });
                    
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
                                tr.appendChild(td10);
                                //tr.appendChild(submitButton);
                    
                                
                                
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
                            let td10 = document.createElement('th');
                            td10.innerText = "Delete";
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
                            tr.appendChild(td10);
                            
                            
                            
                            thead.appendChild(tr);
                            
                    
                                
                    
                            
                            }
                        }
    