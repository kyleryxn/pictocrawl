// Function to toggle the state of a checkbox based on its ID
function toggleCheckbox(checkboxId) {
    const checkbox = document.getElementById(checkboxId);
    checkbox.checked = !checkbox.checked;

    // When a checkbox is selected via the button, deselect the radio button
    document.getElementById("option_all").checked = false;
}

// Function to handle the radio button state change
function toggleRadio() {
    const checkboxes = document.querySelectorAll("input[type='checkbox'][name='imageType']");
    const radio = document.getElementById("option_all");

    // When the radio button is selected, deselect all checkboxes
    if (radio.checked) {
        checkboxes.forEach(checkbox => (checkbox.checked = false));
    } else {
        // When at least one checkbox is selected, deselect the radio button
        const anyCheckboxChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
        radio.checked = !anyCheckboxChecked;
    }
}

// Code executed when the DOM content is loaded
document.addEventListener("DOMContentLoaded", function() {
    // Prevent form submission when pressing Enter key inside the URL input
    document.getElementById("url").addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
        }
    });

    // Add event listeners to all buttons to handle clicks
    const buttons = document.querySelectorAll("button.option");
    buttons.forEach(button => {
        button.addEventListener("click", function() {
            const checkboxId = this.getAttribute("data-checkbox-id");
            toggleCheckbox(checkboxId);
        });
    });

    // Add event listener to form to handle clicks on checkboxes directly
    const form = document.querySelector(".crawl_form");
    form.addEventListener("click", function(event) {
        const target = event.target;
        if (target.matches(".selection")) {
            // When a checkbox is clicked directly, deselect the radio button
            document.getElementById("option_all").checked = false;
        }
    });

    // Add event listener to form to handle clicks on radio button directly
    document.getElementById("option_all").addEventListener("click", function() {
        const checkboxes = document.querySelectorAll("input[type='checkbox'][name='imageType']");
        checkboxes.forEach(checkbox => (checkbox.checked = false));
    });
});
