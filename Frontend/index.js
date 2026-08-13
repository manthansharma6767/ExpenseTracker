const BASE_URL = "http://localhost:8080";

// ===============================
// ADD EXPENSE
// ===============================

function addExpense() {

    const data = {
        amount: document.getElementById("amount").value,
        category: document.getElementById("category").value,
        description: document.getElementById("description").value,
        date: document.getElementById("date").value,
        studentId: 1
    };

    fetch(`${BASE_URL}/expenses`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to add expense");
            }

            return response.json();
        })
        .then(data => {
            alert("Expense Added!");

            // Clear form
            document.getElementById("amount").value = "";
            document.getElementById("category").value = "";
            document.getElementById("description").value = "";
            document.getElementById("date").value = "";

            getExpenses();
        })
        .catch(error => {
            console.error("Error adding expense:", error);
            alert("Failed to add expense");
        });
}


// ===============================
// GET ALL EXPENSES
// ===============================

function getExpenses() {

    fetch(`${BASE_URL}/expenses`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch expenses");
            }

            return response.json();
        })
        .then(data => {

            const list = document.getElementById("expenseList");

            list.innerHTML = "";

            data.forEach(expense => {

                const li = document.createElement("li");

                li.innerText =
                    `${expense.amount} - ${expense.category} - ${expense.description} - ${expense.date}`;

                list.appendChild(li);
            });
        })
        .catch(error => {
            console.error("Error fetching expenses:", error);
        });
}


// ===============================
// LOAD EXPENSES WHEN PAGE OPENS
// ===============================

document.addEventListener("DOMContentLoaded", () => {
    getExpenses();
});