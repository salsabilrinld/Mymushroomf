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
            <!-- Dashboards -->
            <!-- <li class="menu-item">
                <a href="javascript:void(0);" class="menu-link id="dashboard-link">
                  <i class="menu-icon tf-icons bx bx-chart"></i>
                  <div class="text-truncate" data-i18n="Dashboards">Dashboard</div>
                </a>
              </li> -->

            <li class="menu-item active">
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
           <li class="menu-item">
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

        <!-- order -->
            <!-- <li class="menu-item active open">
                <a href="javascript:void(0);" class="menu-link menu-toggle"  onclick="toggleSubMenu('order-submenu')">
                <i class="menu-icon tf-icons bx bx-layout"></i>
                <div class="text-truncate" data-i18n="order">Order</div>
                </a> -->
        <li class="menu-item">
            <a href="{{url('rate')}}" class="menu-link menu-toggle">
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
        <!-- ACCOUNT CENTER SECTION -->
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

  <!-- bagian atas header Content  -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <div class="row">
                <div class="col-xxl-8 mb-6 order-0">
                  <div class="card">
                    <div class="d-flex align-items-start row">
                      <div class="col-sm-7">
                        <div class="card-body">
                          <h5 class="card-title text-primary mb-3">Welcome {{session('name');}} 🎉</h5>
                          <p class="mb-6 custom-text-color">
                            | Manage your products, orders, and customer feedback efficiently.<br /> <br />| Stay updated with real-time notifications and insights.
                          </p>

                          <!-- <a href="javascript:;" class="btn btn-sm btn-outline-primary">View Badges</a> -->
                        </div>
                      </div>

                      <div class="col-sm-5 text-center text-sm-left">
                        <div class="welcome-image">
                          <img
                            src="../assets/img/hdfoto.png"
                            height="200"
                            class="scaleX-n1-rtl"
                            alt="welcome image" />
                        </div>
                      </div>

                    </div>

                  </div>

                </div>

                <!--/ Total Revenue -->
                <div class="col-12 col-md-8 col-lg-12 col-xxl-4 order-3 order-md-2">
                  <div class="row">
                    <div class="col-6 mb-6">
                      <div class="card h-100">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between mb-4">
                            <!-- <div class="avatar flex-shrink-0">
                              <img src="../assets/img/icons/unicons/paypal.png" alt="paypal" class="rounded" />
                            </div> -->
                            <!-- logo total order -->
                            <div class="avatar flex-shrink-0">
                                <i class="bx bx-cart" style="font-size: 24px; color: #4D2703;"></i>
                              </div>

                            <div class="dropdown">
                              <button
                                class="btn p-0"
                                type="button"
                                id="cardOpt4"
                                data-bs-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                                <i class="bx bx-dots-vertical-rounded text-muted"></i>
                              </button>
                              <div class="dropdown-menu dropdown-menu-end" aria-labelledby="cardOpt4">
                                <a class="dropdown-item" href="javascript:void(0);">View More</a>
                                <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                              </div>
                            </div>
                          </div>
                          <p class="mb-1 custom-text-color">TOTAL ORDER</p>
                          <h4 class="card-title mb-3">115</h4>
                          <!-- <small class="text-danger fw-medium"><i class="bx bx-down-arrow-alt"></i> -14.82%</small> -->
                        </div>
                      </div>
                    </div>
                    <div class="col-6 mb-6">
                      <div class="card h-100">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between mb-4">
                            <!-- <div class="avatar flex-shrink-0">
                              <img src="../assets/img/icons/unicons/cc-primary.png" alt="Credit Card" class="rounded" />
                            </div> -->

                            <!-- logo total produk -->
                            <div class="avatar flex-shrink-0">
                                <i class="bx bx-box" style="font-size: 24px; color: #4D2703;"></i>
                              </div>


                            <div class="dropdown">
                              <button
                                class="btn p-0"
                                type="button"
                                id="cardOpt1"
                                data-bs-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                                <i class="bx bx-dots-vertical-rounded text-muted"></i>
                              </button>
                              <div class="dropdown-menu" aria-labelledby="cardOpt1">
                                <a class="dropdown-item" href="javascript:void(0);">View More</a>
                                <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                              </div>
                            </div>
                          </div>
                          <p class="mb-1 custom-text-color">TOTAL PRODUCT</p>
                          <h4 class="card-title mb-3">250</h4>
                          <!-- <small class="text-success fw-medium"><i class="bx bx-up-arrow-alt"></i> +28.14%</small> -->
                        </div>
                      </div>
                    </div>
                    <!-- RECENT ORDER -->
                    <div class="col-12 col-md-8 col-lg-12 col-xxl-4 order-3 order-md-2">
                      <div class="card">
                          <h5 class="card-header">RECENT ORDERS</h5>
                          <div class="table-responsive text-nowrap">
                            <div class="table-container">
                              <table class="table">
                                  <thead>
                                      <tr class="text-nowrap">
                                          <th>No.</th>
                                          <th>Order No</th>
                                          <th>Customer Name</th>
                                          <th>Status</th>
                                          <th>Date</th>
                                      </tr>
                                  </thead>
                                  <tbody class="table-border-bottom-0">
                                      <tr>
                                          <th scope="row">1</th>
                                          <td>001</td>
                                          <td>John Doe</td>
                                          <td>Processing</td>
                                          <td>2024-10-25</td>
                                      </tr>
                                      <tr>
                                          <th scope="row">2</th>
                                          <td>002</td>
                                          <td>Jane Smith</td>
                                          <td>Completed</td>
                                          <td>2024-10-24</td>
                                      </tr>
                                      <tr>
                                        <th scope="row">3</th>
                                        <td>003</td>
                                        <td>Jamal</td>
                                        <td>Completed</td>
                                        <td>2024-10-28</td>
                                    </tr>
                                    <tr>
                                      <th scope="row">4</th>
                                      <td>004</td>
                                      <td>Mark Lee</td>
                                      <td>Completed</td>
                                      <td>2024-10-29</td>
                                  </tr>
                                  <tr>
                                    <th scope="row">5</th>
                                    <td>005</td>
                                    <td>Harry Potter</td>
                                    <td>Completed</td>
                                    <td>2024-10-30</td>
                                </tr>
                                      <!-- Additional rows here -->
                                  </tbody>
                              </table>
                            </div>
                          </div>
                        </div>
                      </div>

                      <!-- NOTIF -->
                      <div class="col-12 col-md-8 col-lg-12 col-xxl-4 order-3 order-md-2  mb-4 mt-4">
                        <div class="card">
                            <h5 class="card-header">NOTIFICATIONS</h5>
                            <div class="table-responsive text-nowrap">
                              <div class="table-container">
                                <table class="table">
                                    <thead>
                                        <tr class="text-nowrap">
                                            <th>No.</th>
                                            <th>Message</th>
                                            <th>Last Update</th>

                                        </tr>
                                    </thead>
                                    <tbody class="table-border-bottom-0">
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>New order from a customer.</td>
                                            <td>1 Minutes Ago</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>Low stock for Product A.</td>
                                            <td>13 Minutes Ago</td>
                                        </tr>
                                        <tr>
                                          <th scope="row">3</th>
                                          <td>New order from a customer.</td>
                                          <td>1 Minutes Ago</td>
                                        </tr>
                                      <tr>
                                        <th scope="row">4</th>
                                        <td>Low stock for Product A.</td>
                                        <td>13 Minutes Ago</td>
                                    </tr>
                                        <!-- Additional rows here -->
                                    </tbody>
                                </table>
                              </div>
                            </div>
                          </div>
                        </div>
                      <!-- QUICK ACCESS -->
<div class="col-12 col-md-8 col-lg-12 col-xxl-4 order-3 order-md-2 mb-4 mt-4">
  <div class="card mb-4">
    <h5 class="card-header text-start">QUICK ACCESS</h5>
    <div class="card-body">
      <div class="d-flex flex-wrap gap-4">
        <a href="/manage-products" class="btn btn-primary">Manage Products</a>
        <a href="/manage-orders" class="btn btn-secondary">Manage Orders</a>
        <a href="/manage-ratings" class="btn btn-primary">Manage Ratings</a>
        <a href="/notifications" class="btn btn-primary">Notifications</a>
      </div>
    </div>
  </div>
</div>



                    </div>

                  </div>

                </div>

              </div>



            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
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
