let logoutBtn = document.querySelector('#logout-btn');

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
    if (!event.target.matches('.dropdown_button')) {
        document.getElementById('courses_id').style.display = "none";
    }
}; 

function deleteTable() {
    let rowCount = document.getElementById("reimbursements-tbl").rows.length
    for(let i =0;i<=rowCount;i+1){
        document.getElementById("reimbursements-tbl").deleteRow(i);
   
  }
}


function createHideTableButton(){
    let button = document.createElement("button");
    button.innerHTML = "Delete Table";
    button.setAttribute("id","hide-btn");
    button.style.visibility = "show";

// 2. Append somewhere
    let body = document.getElementsByTagName("body")[0];
    body.appendChild(button);

// 3. Add event handler
    button.addEventListener ("click", function() {
        button.style.visibility = "hidden";
        deleteTable();
        

    });
}


async function populateReimbursementsTable() {
    createHideTableButton();
    const URL = 'http://localhost:8080/reimbursements';

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

            let td5 = document.createElement('td');
            td5.innerText = reimbursement.reimbAuthor;

            let td6 = document.createElement('td');
            if(reimbursement.reimbStatusId ==1){
                td6.innerText = "Approved"
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
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        let tbody = document.querySelector('#reimbursements-tbl > thead');
        tbody.appendChild(tr);

            

        
        }
    }

    async function populateDeniedReimbursementsTable() {
        createHideTableButton();
        
        const URL = 'http://localhost:8080/reimbursements/denied';
    
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
    
                let td5 = document.createElement('td');
                td5.innerText = reimbursement.reimbAuthor;
    
                let td6 = document.createElement('td');
                if(reimbursement.reimbStatusId ==1){
                    td6.innerText = "Approved"
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
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(td9);
            let tbody = document.querySelector('#reimbursements-tbl > thead');
            tbody.appendChild(tr);
    
                
    
            
            }
            
            
        }
        async function populateApprovedReimbursementsTable() {
            createHideTableButton();
            const URL = 'http://localhost:8080/reimbursements/approved';
        
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
        
                    let td5 = document.createElement('td');
                    td5.innerText = reimbursement.reimbAuthor;
        
                    let td6 = document.createElement('td');
                    if(reimbursement.reimbStatusId ==1){
                        td6.innerText = "Approved"
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
                createHideTableButton();
                const URL = 'http://localhost:8080/reimbursements/pending';
            
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
            
                        let td5 = document.createElement('td');
                        td5.innerText = reimbursement.reimbAuthor;
            
                        let td6 = document.createElement('td');
                        if(reimbursement.reimbStatusId ==1){
                            td6.innerText = "Approved"
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
                    tr.appendChild(td5);
                    tr.appendChild(td6);
                    tr.appendChild(td7);
                    tr.appendChild(td8);
                    tr.appendChild(td9);
                    let tbody = document.querySelector('#reimbursements-tbl > thead');
                    tbody.appendChild(tr);
            
                        
            
                    
                    }
                }
        
    

