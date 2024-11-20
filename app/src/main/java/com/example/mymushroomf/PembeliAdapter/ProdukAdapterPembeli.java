package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.PembeliActivity.Keranjang1Activity;
import com.example.mymushroomf.PembeliActivity.ProductDetailActivity;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.PembeliActivity.Popup;
import com.example.mymushroomf.R;

import java.util.List;

public class ProdukAdapterPembeli extends RecyclerView.Adapter<ProdukAdapterPembeli.ProductViewHolder> {

    private List<Produk1> productList;
    private Context context;

    public ProdukAdapterPembeli(Context context, List<Produk1> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each product item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk1, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Produk1 product = productList.get(position);
        holder.productName.setText(product.getProduct_name()); // Mengambil product_name dari JSON
        holder.productCategory.setText(product.getCategory()); // Menampilkan kategori
        holder.productPrice.setText("Rp " + product.getPrice()); // Format harga

        // Memuat gambar produk dari file_path
        Glide.with(context)
                .load("http://192.168.1.15/product_images" + product.getFile_path()) // Tambahkan URL base untuk file_path
//                .placeholder(R.drawable.placeholder_image) // Placeholder jika gambar gagal dimuat
//                .error(R.drawable.error_image) // Placeholder jika ada error
                .into(holder.productImage);

        // Aksi klik card untuk membuka detail produk
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", product.getId());
            context.startActivity(intent);
        });

        // Aksi klik tombol tambah ke keranjang
        holder.addToCartButton.setOnClickListener(view -> {
            // Tambahkan logika untuk menambah produk ke keranjang
            Toast.makeText(context, product.getProduct_name() + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Metode untuk memperbarui data produk dan menyegarkan tampilan
    public void updateProductList(List<Produk1> newProductList) {
        this.productList.clear();
        this.productList.addAll(newProductList);
        notifyDataSetChanged(); // Refresh RecyclerView
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productCategory, productPrice;
        ImageButton addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productCategory = itemView.findViewById(R.id.product_category);
            productPrice = itemView.findViewById(R.id.product_price);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }

}