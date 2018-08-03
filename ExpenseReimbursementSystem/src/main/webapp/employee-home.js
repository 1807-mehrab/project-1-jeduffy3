document.addEventListener("DOMContentLoaded", function (event) {
    document.getElementById('firstName').value = '';
    document.getElementById('lastName').value = '';
    document.getElementById('email').value = '';
    document.getElementById('editFirstName').placeholder = '';
    document.getElementById('editLastName').placeholder = '';
    document.getElementById('editEmail').placeholder = '';
    loadEmployee();
});

function loadEmployee() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ExpenseReimbursementSystem/EmployeeHomeController', true);
    xhr.onload = function () {
        if (this.status == 200) {
            let employee = JSON.parse(this.responseText);
            document.getElementById('firstName').value = employee.firstName;
            document.getElementById('lastName').value = employee.lastName;
            document.getElementById('email').value = employee.email;
            document.getElementById('editFirstName').placeholder = employee.firstName;
            document.getElementById('editLastName').placeholder = employee.lastName;
            document.getElementById('editEmail').placeholder = employee.email;
            document.getElementById('editEmployeeId').value = employee.employeeId;
            document.getElementById('editRoleId').value = employee.role.roleId;
            document.getElementById('editRole').value = employee.role.role;
        }
    };
    xhr.send();
}

function submitEmployee() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/ExpenseReimbursementSystem/EditEmployeeController', true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.onload = function () {
        if (this.status == 200) {
        }
    };
    let data = JSON.stringify({
        employeeId: document.getElementById('editEmployeeId').value,
        firstName: document.getElementById('editFirstName').value,
        lastName: document.getElementById('editLastName').value,
        email: document.getElementById('editEmail').value,
        role: {
            roleId: document.getElementById('editRoleId').value,
            role: document.getElementById('editRole').value
        }
    });
    xhr.send(data);
}