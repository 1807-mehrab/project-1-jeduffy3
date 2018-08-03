function submitRequest() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/ExpenseReimbursementSystem/NewRequestController', true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.onload = function () {
        if (this.status == 200) {
        }
    };
    let data = JSON.stringify({
        description: document.getElementById('for-request').value,
        amount: document.getElementById('request-amount').value,
        reimbursementRequestId: document.getElementById('reimbursementRequestId').value,
        requester: {
            requestId: document.getElementById('requesterId').value,
            firstName: document.getElementById('requesterFirstName').value,
            lastName: document.getElementById('requesterLastName').value,
            email: document.getElementById('requesterEmail').value,
            role: {
                roleId: document.getElementById('requesterRoleId').value,
                role: document.getElementById('requesterRole').value
            },
        },
        requester: {
            requestId: document.getElementById('requesterId').value,
            firstName: document.getElementById('requesterFirstName').value,
            lastName: document.getElementById('requesterLastName').value,
            email: document.getElementById('requesterEmail').value
        },
        requesterId: document.getElementById('requesterFirstName').value,
        description: document.getElementById('for-request').value,
        amount: document.getElementById('request-amount').value,
        reimbursementRequestId: document.getElementById('reimbursementRequestId').value,
        requesterId: document.getElementById('requesterId').value,
        role: {
            roleId: document.getElementById('editRoleId').value,
            role: document.getElementById('editRole').value
        }
    });
    xhr.send(data);
}