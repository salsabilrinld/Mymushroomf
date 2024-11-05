<!doctype html>
<html
  lang="en"
  class="light-style layout-menu-fixed layout-compact"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="../assets/"
  data-template="vertical-menu-template-free"
  data-style="light">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=0.8, user-scalable=no, minimum-scale=0.8, maximum-scale=1.0" />
    <title>MUSHROOM</title>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="../assets/img/logomushroom.png" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet" />
    <link rel="stylesheet" href="../assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="../assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="../assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="../assets/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
    <link rel="stylesheet" href="../assets/vendor/libs/apex-charts/apex-charts.css" />
    <style>

   /* font color my product , product.html */
        .app-brand-text {
            font-size: 50px;
            color: #4D2703;
        }

        .menu-text {
            font-family: Arial, sans-serif;
        }

        .bg-white {
            background-color: #ffffff;
            padding: 16px;
            border-radius: 8px;
        }
    </style>
    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="../assets/vendor/js/helpers.js"></script>
    <script src="../assets/vendor/js/script.js"></script>
    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="../assets/js/config.js"></script>
  </head>

  <body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->

        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
          <div class="app-brand demo">
            <a href="index-admin.html" class="app-brand-link">
              <img src="../assets/img/logomushroom.png" alt="Mush Room Logo" class="app-brand-logo demo" style="width: 40px; height: auto;">
              <span class="app-brand-text demo menu-text fw-bold ms-2">MUSH ROOM</span>
            </a>
            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm d-flex align-items-center justify-content-center"></i>
            </a>
          </div>

          <div class="menu-inner-shadow"></div>

          <ul class="menu-inner py-1">
            <li class="menu-item">
                <a href="{{url('dashboard')}}" class="menu-link" id="dashboard-link">
                  <i class="menu-icon tf-icons bx bx-chart"></i>
                  <div class="text-truncate" data-i18n="Dashboard">Dashboard</div>
                </a>
            </li>
             <!-- Notifications menu item -->
             <!-- <li class="menu-item">
                <a href="notif.html" class="menu-link">
                    <i class="menu-icon tf-icons bx bx-bell"></i>
                    <div class="text-truncate">Notifications</div>
                    <span class="badge rounded-pill bg-danger ms-auto">5</span>
                </a>
            </li> -->
           <!-- Product menu item -->
           <li class="menu-item active">
            <a href="{{url('product')}}" class="menu-link">
                <i class="menu-icon tf-icons bx bx-shopping-bag"></i>
                <div class="text-truncate">Product</div>
            </a>
        </li>
         <!-- Rate menu item -->
         <li class="menu-item">
            <a href="{{url('rate')}}" class="menu-link">
                <i class="menu-icon tf-icons bx bx-star"></i>
                <div class="text-truncate">Rate</div>
            </a>
        </li>
        <!-- ORDER SECTION -->
        <li class="menu-item">
            <a href="{{url('order')}}" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-layout"></i>
                <div class="text-truncate">Order</div>
                    </a>
        <ul class="menu-sub">
            <li class="menu-item">
                <a href="allorder.html" class="menu-link">
                <div class="text-truncate" data-i18n="All Order">All Order</div>
                </a>
            </li>
            <li class="menu-item">
                <a href="purchaseorder.html" class="menu-link">
                <div class="text-truncate" data-i18n="Purchase Order">Purchase Order</div>
                </a>
            </li>
            <li class="menu-item">
                <a href="delivered.html" class="menu-link">
                <div class="text-truncate" data-i18n="Fluid">Delivered</div>
                </a>
            </li>
            <li class="menu-item">
                <a href="canceled.html" class="menu-link">
                <div class="text-truncate" data-i18n="Container">Canceled</div>
                </a>
            </li>
        </ul>
        <div class="account-center">
            <div class="menu-header small text-uppercase">
                <span class="menu-header-text">ACCOUNT CENTER</span>
            </div>
            <img src="../assets/img/acc.png"alt = "Account Center" class="img-fluid" />
            <button class="btn btn-secondary w-100" onclick="logout()">LOGOUT</button>
          </div>
      </li>
</ul>

</aside>
  <!-- Layout container -->
  <div class="layout-page">
    <!-- Navbar -->
    <nav
      class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
      id="layout-navbar">
      <div class="layout-menu-toggle navbar-nav align-items-xl-center me-4 me-xl-0 d-xl-none">
        <a class="nav-item nav-link px-0 me-xl-6" href="javascript:void(0)">
          <i class="bx bx-menu bx-md"></i>
        </a>
      </div>
      <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
        <!-- Search -->
        <div class="navbar-nav align-items-center">
          <div class="nav-item d-flex align-items-center">
            <i class="bx bx-search bx-md search-icon"></i>
            <input
              type="text"
              class="form-control border-0 shadow-none ps-1 ps-sm-2 search-input"
              placeholder="Search"
              aria-label="Search" />
          </div>
        </div>
        <!-- /Search -->
        <ul class="navbar-nav flex-row align-items-center ms-auto">
            <li class="nav-item navbar-dropdown dropdown-user dropdown">
              <a
                class="nav-link dropdown-toggle hide-arrow p-0 notification-icon"
                href="javascript:void(0);"
                data-bs-toggle="dropdown"
                aria-label="Notifications">
                <i class="bx bx-bell"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-end" id="notificationDropdown">
                <li>
                  <div class="dropdown-divider my-1"></div>
                </li>
                <li>
                  <a class="dropdown-item" href="#">
                    <i class="bx bx-user bx-md me-3"></i>
                    <span>Isi Notif Sama Bawahnya 1 minute ago</span>
                  </a>
                </li>
                <li>
                  <a class="dropdown-item" href="#">
                    <i class="bx bx-user bx-md me-3"></i>
                    <span>Isi Notif Sama Bawahnya 2 minutes ago</span>
                  </a>
                </li>
                <li>
                  <a class="dropdown-item" href="#">
                    <i class="bx bx-user bx-md me-3"></i>
                    <span>Isi Notif Sama Bawahnya 3 minutes ago</span>
                  </a>
                </li>
                <li>
                  <div class="dropdown-divider my-1"></div>
                </li>
              </ul>
            </li>
          </ul>
          <!-- User -->
          <li class="nav-item navbar-dropdown dropdown-user dropdown">
            <a
              class="nav-link dropdown-toggle hide-arrow p-0"
              href="javascript:void(0);"
              data-bs-toggle="dropdown">
              <div class="avatar avatar-online">
                <img src="../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
              </div>
            </a>
            <ul class="dropdown-menu dropdown-menu-end">
              <li>
                <a class="dropdown-item" href="#">
                  <div class="d-flex">
                    <div class="flex-shrink-0 me-3">
                      <div class="avatar avatar-online">
                        <img src="../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                      </div>
                    </div>
                    <div class="flex-grow-1">
                      <h6 class="mb-0">John Doe</h6>
                      <small class="text-muted">Admin</small>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <div class="dropdown-divider my-1"></div>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  <i class="bx bx-user bx-md me-3"></i><span>My Profile</span>
                </a>
              </li>
              <li>
                <a class="dropdown-item" href="#"> <i class="bx bx-cog bx-md me-3"></i><span>Settings</span> </a>
              </li>

              <li>
                <div class="dropdown-divider my-1"></div>
              </li>
              <li>
                <a class="dropdown-item" href="javascript:void(0);">
                  <i class="bx bx-power-off bx-md me-3"></i><span>Log Out</span>
                </a>
              </li>
            </ul>
          </li>
          <!--/ User -->
        </ul>
      </div>
    </nav>

    <div class="container-fluid flex-grow-1 container-p-y">
   <!-- MAIN  -->
<div class="container bg-white p-4 mb-4" style="border-radius: 8px;">
    <span class="app-brand-text demo menu-text fw-bold ms-2">MY PRODUCT</span>
    <!-- product -->
    <div class="row mb-12 g-6">
        <!-- jamur enoki -->
        <div class="col-md-6 col-lg-4">
            <div class="card h-100">
                <img class="card-img-top" src="../assets/img/jamur1.png" alt="Card image cap" />
                <div class="card-body">
                    <!-- Judul produk -->
                    <span class="app-brand-text demo menu-text fw-bold ms-2">Jamur Enoki</span>

                    <!-- Bintang rating -->
                    <div class="rating ms-2" style="color: #4D2703;"> <!-- Warna  -->
                        <i class="bx bxs-star"></i>
                        <i class="bx bxs-star"></i>
                        <i class="bx bxs-star"></i>
                        <i class="bx bxs-star"></i>
                        <i class="bx bxs-star"></i>
                    </div>
                    <!-- Harga produk -->

                    <div class="app-brand-text demo menu-text fw- ms-2">Rp.10.000,-</div>

                    <!-- Tombol -->
                    <div class="icon-actions" style="text-align: right;">
                        <a href="javascript:void(0)" class="btn-action mt-2" title="Edit">
                            <i class='bx bx-edit'></i> Edit
                        </a>
                        <a href="javascript:void(0)" class="btn-action mt-2" title="Delete">
                            <i class='bx bxs-trash'></i> Delete
                        </a>
                    </div>
            </div>
        </div>
    </div>
       <!-- Jamur Kancing -->
    <div class="col-md-6 col-lg-4">
        <div class="card h-100">
            <img class="card-img-top" src="../assets/img/jamur2tiram.png" alt="Jamur Kancing" />
            <div class="card-body">
                <span class="app-brand-text demo menu-text fw-bold ms-2">Jamur Kancing</span>
                <div class="rating ms-2" style="color: #4D2703;">
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star-half"></i>
                </div>
                <div class="app-brand-text demo menu-text fw- ms-2">Rp.12.000,-</div>
                <div class="icon-actions" style="text-align: right;">
                    <a href="javascript:void(0)" class="btn-action mt-2" title="Edit">
                        <i class='bx bx-edit'></i> Edit
                    </a>
                    <a href="javascript:void(0)" class="btn-action mt-2" title="Delete">
                        <i class='bx bxs-trash'></i> Delete
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Jamur Shiitake -->
    <div class="col-md-6 col-lg-4">
        <div class="card h-100">
            <img class="card-img-top" src="../assets/img/jamur3.png" alt="Jamur Shiitake" />
            <div class="card-body">
                <span class="app-brand-text demo menu-text fw-bold ms-2">Jamur Shiitake</span>
                <div class="rating ms-2" style="color: #4D2703;">
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star-half"></i>
                    <i class="bx bx-star"></i>
                </div>
                <div class="app-brand-text demo menu-text fw- ms-2">Rp.15.000,-</div>
                <div class="icon-actions" style="text-align: right;">
                    <a href="javascript:void(0)" class="btn-action mt-2" title="Edit">
                        <i class='bx bx-edit'></i> Edit
                    </a>
                    <a href="javascript:void(0)" class="btn-action mt-2" title="Delete">
                        <i class='bx bxs-trash'></i> Delete
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card h-100">
            <img class="card-img-top" src="../assets/img/jamur1.png" alt="Card image cap" />
            <div class="card-body">
                <!-- Judul produk -->
                <span class="app-brand-text demo menu-text fw-bold ms-2">Jamur Enoki</span>

                <!-- Bintang rating -->
                <div class="rating ms-2" style="color: #4D2703;"> <!-- Warna  -->
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                    <i class="bx bxs-star"></i>
                </div>
                <!-- Harga produk -->

                <div class="app-brand-text demo menu-text fw- ms-2">Rp.10.000,-</div>

                <!-- Tombol -->
                <div class="icon-actions" style="text-align: right;">
                    <a href="javascript:void(0)" class="btn-action mt-2" title="Edit">
                        <i class='bx bx-edit'></i> Edit
                    </a>
                    <a href="javascript:void(0)" class="btn-action mt-2" title="Delete">
                        <i class='bx bxs-trash'></i> Delete
                    </a>
                </div>
        </div>
    </div>
</div>
   <!-- Jamur Kancing -->
<div class="col-md-6 col-lg-4">
    <div class="card h-100">
        <img class="card-img-top" src="../assets/img/jamur2tiram.png" alt="Jamur Kancing" />
        <div class="card-body">
            <span class="app-brand-text demo menu-text fw-bold ms-2">Jamur Kancing</span>
            <div class="rating ms-2" style="color: #4D2703;">
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star-half"></i>
            </div>
            <div class="app-brand-text demo menu-text fw- ms-2">Rp.12.000,-</div>
            <div class="icon-actions" style="text-align: right;">
                <a href="javascript:void(0)" class="btn-action mt-2" title="Edit">
                    <i class='bx bx-edit'></i> Edit
                </a>
                <a href="javascript:void(0)" class="btn-action mt-2" title="Delete">
                    <i class='bx bxs-trash'></i> Delete
                </a>
            </div>
        </div>
    </div>
</div>

<!-- Jamur Shiitake -->
<div class="col-md-6 col-lg-4">
    <div class="card h-100">
        <img class="card-img-top" src="../assets/img/jamur3.png" alt="Jamur Shiitake" />
        <div class="card-body">
            <span class="app-brand-text demo menu-text fw-bold ms-2">Jamur Shiitake</span>
            <div class="rating ms-2" style="color: #4D2703;">
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star"></i>
                <i class="bx bxs-star-half"></i>
                <i class="bx bx-star"></i>
            </div>
            <div class="app-brand-text demo menu-text fw- ms-2">Rp.15.000,-</div>
            <div class="icon-actions" style="text-align: right;">
                <a href="javascript:void(0)" class="btn-action mt-2" title="Edit">
                    <i class='bx bx-edit'></i> Edit
                </a>
                <a href="javascript:void(0)" class="btn-action mt-2" title="Delete">
                    <i class='bx bxs-trash'></i> Delete
                </a>
            </div>
        </div>
    </div>
</div>
</div>




 <div class="content-backdrop fade"></div>
    </div>
    <!-- Content wrapper -->
    </div>
    <!-- content -->

    <!-- / Layout page -->
    </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    <!-- <div class="buy-now">
      <a
        href="https://themeselection.com/item/sneat-dashboard-pro-bootstrap/"
        target="_blank"
        class="btn btn-danger btn-buy-now"
        >Upgrade to Pro</a
      >
    </div> -->

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->

    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../assets/vendor/libs/popper/popper.js"></script>
    <script src="../assets/vendor/js/bootstrap.js"></script>
    <script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="../assets/vendor/js/menu.js"></script>

    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="../assets/vendor/libs/apex-charts/apexcharts.js"></script>
    <script src="../assets/vendor/js/script.js"></script>
    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="../assets/js/dashboards-analytics.js"></script>

    <!-- Place this tag before closing body tag for github widget button. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>

  </body>
</html>
