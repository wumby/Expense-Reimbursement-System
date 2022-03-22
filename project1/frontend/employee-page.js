let logoutBtn = document.querySelector('#logout-btn');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwt');

    window.location = '/index.html';
});

let loadReimbTableBtn = document.querySelector('#loadReimb-btn');
loadReimbTableBtn.addEventListener('click', (event) => {
    populateReimbursementsTable();
}, {once : true});

async function populateReimbursementsTable() {
    const URL = `http://localhost:8080/users/${localStorage.getItem('user_id')}/reimbursements`;

    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
        }
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
            td6.innerText = reimbursement.reimbStatusId;
            
            let td7 = document.createElement('td');
            td7.innerText = reimbursement.reimbTypeId;

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
        td6.innerText = "Status Id";
            
        let td7 = document.createElement('th');
        td7.innerText = "Type Id";

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
    