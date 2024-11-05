<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('sign.in');
});

Route::get('/dashboard', function () {
    return view('Dashboard.index');
});

Route::get('/product', function () {
    return view('Product.index');
});

Route::get('/rate', function () {
    return view('Rate.index');
});

Route::get('/order', function () {
    return view('Order.index');
});