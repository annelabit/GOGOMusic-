document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("checkoutForm");

  const fields = {
    cap: {
      regex: /^\d{5}$/,
      message: "Il CAP deve contenere esattamente 5 cifre.",
    },
    cardNumber: {
      regex: /^\d{16}$/,
      message: "Il numero di carta deve contenere 16 cifre senza spazi.",
    },
    scadenza: {
      regex: /^(0[1-9]|1[0-2])\/\d{2}$/,
      message: "La scadenza deve essere nel formato MM/AA.",
    },
    cvv: {
      regex: /^\d{3}$/,
      message: "Il CVV deve contenere esattamente 3 cifre.",
    }
  };

  for (const fieldName in fields) {
    const input = form[fieldName];
    const errorDiv = document.getElementById(`${fieldName}-error`);
    input.addEventListener("change", function () {
      validateField(input, fields[fieldName], errorDiv);
    });
  }

  form.addEventListener("submit", function (e) {
    let valid = true;
    for (const fieldName in fields) {
      const input = form[fieldName];
      const errorDiv = document.getElementById(`${fieldName}-error`);
      const isValid = validateField(input, fields[fieldName], errorDiv);
      if (!isValid) valid = false;
    }

    if (!valid) e.preventDefault();
  });

  function validateField(input, rule, errorDiv) {
    const value = input.value.trim().replace(/\s/g, "");
    if (!rule.regex.test(value)) {
      errorDiv.textContent = rule.message;
      input.classList.add("invalid");
      return false;
    } else {
      errorDiv.textContent = "";
      input.classList.remove("invalid");
      return true;
    }
  }
});
