<?php

use App\Http\Controllers\MahasiswaController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Models\User;

// Route::get('/user', function (Request $request) {
//     return $request->user();
// })->middleware('auth:sanctum');

Route::get('/user', function(){
    return User::all();
});

Route::get('/user/{id}', function(string $id){
    return User::where('id', $id)->get();
});

Route::post('/user', function(Request $request){
    $response = User::create([
        'name' => $request->input('name'),
        'email' => $request->input('email'),
        'password' => $request->input('password')
    ]);

    return $response;
});

Route::put('/user/{id}', function(Request $request, string $id){
    $response = User::where('id', $id)->update($request->all());

    return $response;
});;

Route::delete('/user/{id}', function(string $id){
    $response = User::where('id', $id)->delete();

    return $response;
});

Route::group(['middleware' => ['web']], function(){
    Route::post('/signin', function(Request $request){
        $response = User::where('email', $request->input('email'))
                        ->where('password', md5($request->input('password')))
                        ->get();
    
        if(count($response)) {
            $request->session()->put('name', $response[0]->name);
        }
        
        return count($response);
    });

    Route::get('/Mahasiswa', [MahasiswaController::class, 'index']);
    Route::get('/AddMahasiswa', [MahasiswaController::class, 'create']);
    Route::post('/Mahasiswa', [MahasiswaController::class, 'store']);
    Route::delete('/Mahasiswa', [MahasiswaController::class, 'destroy']);
});
