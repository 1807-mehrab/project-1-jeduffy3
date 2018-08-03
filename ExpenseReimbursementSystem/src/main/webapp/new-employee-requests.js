function loadPendingRequests() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/ExpenseReimbursementSystem/EmployeeRequestController', true);
    xhr.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
    xhr.onload = function () {
        if (this.status == 200) {
            let reimbursementRequests = JSON.parse(this.responseText);


        }
        document.getElementById('pendingRequests').innerHTML = output1;
        document.getElementById('resolvedRequests').innerHTML = output2;
    };
    xhr.send();
}