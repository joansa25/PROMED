
  document.querySelector('.toggle-password').addEventListener('click', function () {
    const passwordInput = document.querySelector('#contraseña');
    const icon = this.querySelector('i');
    
    // Alternar el tipo de input entre 'password' y 'text'
    if (passwordInput.type === 'password') {
      passwordInput.type = 'text';
      icon.classList.remove('fa-eye');
      icon.classList.add('fa-eye-slash');
    } else {
      passwordInput.type = 'password';
      icon.classList.remove('fa-eye-slash');
      icon.classList.add('fa-eye');
    }
  });

