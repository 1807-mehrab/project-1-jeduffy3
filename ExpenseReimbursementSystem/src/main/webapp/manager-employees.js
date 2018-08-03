document.addEventListener("DOMContentLoaded", function (event) {
    loadEmployees();
});

function loadEmployees() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ExpenseReimbursementSystem/ManagerEmployeesController', true);
    xhr.onload = function () {
        if (this.status == 200) {
            let employees = JSON.parse(this.responseText);
            let output = '';
            for (var i = 0; i < employees.length; i++) {
                output += '<tr><td class="col-md-4">' + employees[i].firstName + '</td>'
                        + '<td class="col-md-4">' + employees[i].lastName + '</td>'
                        + '<td class="col-md-4">' + employees[i].email + '</td></tr>';
            }
            document.getElementById('employees').innerHTML = output;
        }
    };
    xhr.send();
}