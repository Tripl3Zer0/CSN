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
        // Here you could implement deletion logic (e.g., removing from a database)
        const row = this.closest('tr');
        row.remove();
      }
    });
  });
  
  // Handle Edit Button Click
  const editButtons = document.querySelectorAll('.edit-btn');
  editButtons.forEach(button => {
    button.addEventListener('click', function() {
      // Here you could redirect to another page to edit the student's information
      window.location.href = "edit_student.html"; // Redirect to edit page
    });
  });
  
  // Example function to filter table rows (Search bar functionality)
  document.querySelector('.search-bar').addEventListener('input', function() {
    const searchValue = this.value.toLowerCase();
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
  
  // Example filter for dropdown (for "Name", "Age", "Address", "Diagnosis" filters)
  document.querySelector('.filter-dropdown').addEventListener('change', function() {
    const filterValue = this.value;
    const rows = document.querySelectorAll('tbody tr');
    
    rows.forEach(row => {
      let shouldShow = false;
      
      // Example filter logic (you can expand this for other filters)
      if (filterValue === 'a-z') {
        shouldShow = true; // For alphabetical order, you'd need to sort the rows
      } else if (filterValue === '1-18') {
        const age = parseInt(row.cells[3].textContent);
        if (age >= 1 && age <= 18) shouldShow = true;
      } else if (row.cells[4].textContent.includes(filterValue)) {
        shouldShow = true; // Filtering based on address or diagnosis
      }
      
      row.style.display = shouldShow ? '' : 'none';
    });
  });
  