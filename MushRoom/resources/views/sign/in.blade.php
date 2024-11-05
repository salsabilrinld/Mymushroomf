<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MushRoom</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="/assets/vendors/feather/feather.css">
    <link rel="stylesheet" href="/assets/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="/assets/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/vendors/mdi/css/materialdesignicons.min.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/assets/css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="/assets/img/logomushroom.png" />
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth px-0">
          <div class="row w-100 mx-0">
            <div class="col-lg-4 mx-auto">
              <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                <div class="brand-logo">
                  <img src="/assets/images/mush room.png" alt="logo">
                </div>
                <h4>Hello! let's get started</h4>
                <h6 class="font-weight-light">Sign in to continue.</h6>
                <form class="pt-3" action="/signin" method="post" id="form">
                  @csrf
                  <div class="form-group">
                    <input type="email" class="form-control form-control-lg" id="email" placeholder="Email" name="email" required>
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-lg" id="password" placeholder="Password" name="password" required>
                  </div>
                  <div class="my-2 d-flex justify-content-between align-items-center">
                    <div class="form-check">
                      <label class="form-check-label text-muted">
                        <input type="checkbox" class="form-check-input"> Keep me signed in </label>
                    </div>
                    <a href="#" class="auth-link text-black">Forgot password?</a>
                  </div>
                  <div class="mt-3 d-grid gap-2">
                  <input type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" value="SIGN IN">
                  </div>
                  <div class="text-center mt-4 font-weight-light"> Don't have an account? <a href="register.html" class="text-primary">Create</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="/assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="/assets/vendors/jquery-3.7.1.min.js"></script>
    <script src="/assets/vendors/jquery-validation/jquery.validate.min.js"></script>
    <script src="/assets/vendors/jquery-validation/additional-methods.min.js"></script>
    <script src="/assets/vendors/sweetalert/sweetalert.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="/assets/js/off-canvas.js"></script>
    <script src="/assets/js/template.js"></script>
    <script src="/assets/js/settings.js"></script>
    <script src="/assets/js/todolist.js"></script>
    <!-- endinject -->
    <script>
      $(document).ready(function() {
        $('#form').validate({
          rules: {
            email: {
              required: true,
              email: true
            },
            password: {
              required: true
            }
          },
          messages: {
            email: {
              required: "Email harus diisi",
              email: "Harus sesuai format email"
            },
            password: {
              required: "Password harus diisi"
            }
          },
          errorClass: "text-danger",
          submitHandler: function() {
            $.ajax({
              url: '{{url("/api/signin")}}',
              method: 'POST',
              type: 'POST',
              data: {
                email: $('#email').val(),
                password: $('#password').val(),
                _token: "{{csrf_token()}}"
              },
              dataType: 'json',
              success: function(res) {
                if (res) {
                  window.location.href = '/dashboard';
                } else {
                  swal({
                    title: "Gagal",
                    text: "Pengguna tidak ditemukan",
                    icon: "error"
                  });
                }
              },
              error: function(err) {
                console.log(err);

                swal({
                    title: "Gagal",
                    text: err.responseJSON.message,
                    icon: "error"
                  });
              }
            });
          }
        });
      });
    </script>
</body>

</html>
