document.addEventListener("DOMContentLoaded", () => {
    const codeInputs = document.querySelectorAll(".code-box");
  
    codeInputs.forEach((input, index) => {
      input.addEventListener("input", (e) => {
        if (e.target.value.length === 1 && index < codeInputs.length - 1) {
          codeInputs[index + 1].focus();
        }
      });
  
      input.addEventListener("keydown", (e) => {
        if (e.key === "Backspace" && e.target.value === "") {
          if (index > 0) {
            codeInputs[index - 1].focus();
            codeInputs[index - 1].value = "";  // Clear previous box on backspace
          }
        }
      });
    });
  });