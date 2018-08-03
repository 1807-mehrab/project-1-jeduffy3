document.addEventListener("DOMContentLoaded", function (event) {
    loadPendingRequests();
});

function loadPendingRequests() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ExpenseReimbursementSystem/EmployeeRequestController', true);
    xhr.onload = function () {
        if (this.status == 200) {
            let reimbursementRequests = JSON.parse(this.responseText);
            let output1 = '';
            let output2 = '';
            for (let i = 0; i < 2; i++) {
                for (let j = 1; j < 3; j++) {
                    output1 += '<tr><td width="20">' + reimbursementRequests[0][i].description + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].amount + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].requester.firstName
                            + ' ' + reimbursementRequests[0][i].requester.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].approver.firstName
                            + ' ' + reimbursementRequests[0][i].approver.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].status.status + '</td></tr>';

                    output2 += '<tr><td width="20">' + reimbursementRequests[0][j].description + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][j].amount + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][j].requester.firstName
                            + ' ' + reimbursementRequests[0][j].requester.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][j].approver.firstName
                            + ' ' + reimbursementRequests[0][j].approver.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][j].status.status + '</td></tr>';
                }
            }
            document.getElementById('pendingRequests').innerHTML = output1;
            document.getElementById('resolvedRequests').innerHTML = output2;
        }
    };
    xhr.send();
}