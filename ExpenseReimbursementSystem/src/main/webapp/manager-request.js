document.addEventListener("DOMContentLoaded", function (event) {
    loadPendingRequests();
});

function loadPendingRequests() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ExpenseReimbursementSystem/ManagerRequestController', true);
    xhr.onload = function () {
        if (this.status == 200) {
            let reimbursementRequests = JSON.parse(this.responseText);
            let output1 = '';
            let output2 = '';
            for (let i = 0; i < reimbursementRequests.length; i++) {
                for (let j = 1; j <= reimbursementRequests.length; j++) {
                    output1 += '<tr><td width="20">' + reimbursementRequests[0][i].description + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].amount + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].requester.firstName
                            + ' ' + reimbursementRequests[0][i].requester.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].approver.firstName
                            + ' ' + reimbursementRequests[0][i].approver.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].status.status + '</td></tr>';
                    output2 += '<tr><td width="20">' + reimbursementRequests[0][i].description + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].amount + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].requester.firstName
                            + ' ' + reimbursementRequests[0][i].requester.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].approver.firstName
                            + ' ' + reimbursementRequests[0][i].approver.lastName + '</td>'
                            + '<td width="20">' + reimbursementRequests[0][i].status.status + '</td></tr>';
                }
            }
            document.getElementById('pendingRequests').innerHTML = output1;
            document.getElementById('resolvedRequests').innerHTML = output2;
        }
    };
    xhr.send();
}