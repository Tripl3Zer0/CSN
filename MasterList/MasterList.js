// Handle Add Button Click
document.querySelector('.add-btn').addEventListener('click', function() {
  window.location.href = "add_student.html"; // Redirect to another page for adding students
});

// Handle Delete Button Click
const deleteButtons = document.querySelectorAll('.delete-btn');
deleteButtons.forEach(button => {
  button.addEventListener('click', function() {
      const confirmation = confirm("Are you sure you want to delete this student?");
      if (confirmation) {
          const row = this.closest('tr');
          row.remove();
      }
  });
});

// Handle Edit Button Click
const editButtons = document.querySelectorAll('.edit-btn');
editButtons.forEach(button => {
  button.addEventListener('click', function() {
      window.location.href = "edit_student.html"; // Redirect to edit page
  });
});

// Search Button and Search Bar Functionality
document.querySelector('.search-btn').addEventListener('click', function() {
  const searchValue = document.querySelector('.search-bar').value.toLowerCase();
  const rows = document.querySelectorAll('tbody tr');
  
  rows.forEach(row => {
      const studentName = row.cells[1].textContent.toLowerCase();
      if (studentName.includes(searchValue)) {
          row.style.display = '';
      } else {
          row.style.display = 'none';
      }
  });
});

// Example filter for dropdown (Name, Age, Address, Diagnosis filters)
document.querySelector('.filter-dropdown').addEventListener('change', function() {
  const filterValue = this.value;
  const rows = document.querySelectorAll('tbody tr');

  rows.forEach(row => {
      let shouldShow = false;
      if (filterValue === 'a-z') {
          shouldShow = true;
      } else if (filterValue === '1-18') {
          const age = parseInt(row.cells[3].textContent);
          if (age >= 1 && age <= 18) shouldShow = true;
      } else if (row.cells[4].textContent.includes(filterValue)) {
          shouldShow = true;
      }
      row.style.display = shouldShow ? '' : 'none';
  });
});
// Handle Manage Account button
document.querySelector('.manage-btn').addEventListener('click', function() {
  window.location.href = "manage_account.html"; // Redirect to manage account page
});

// Handle Log Out button
document.querySelector('.logout-btn').addEventListener('click', function() {
  // Log out logic (e.g., clear session, redirect to login page)
  window.location.href = "login.html"; // Redirect to login page
});
