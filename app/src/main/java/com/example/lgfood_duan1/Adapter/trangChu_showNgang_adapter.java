package com.example.lgfood_duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Activity.trangChu_SanPham_Activity;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.R;

import java.util.ArrayList;

public class trangChu_showNgang_adapter extends RecyclerView.Adapter<trangChu_showNgang_adapter.ViewHolder> {
    private ArrayList<model_SanPham> arrListSanPham;
    private trangChu_SanPham_Activity context;

    public trangChu_showNgang_adapter(ArrayList<model_SanPham> arrListSanPham, trangChu_SanPham_Activity context) {
        this.arrListSanPham = arrListSanPham;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuttom_sanpham_trangchu_ngang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(trangChu_showNgang_adapter.ViewHolder holder, int position) {
        holder.ItemCuttomTrangChu_ngang_tv_soLuongSanPhamMuaYeuThich.setText("0");
        holder.ItemCuttomTrangChu_ngang_tv_xuatXuSanPham.setText(arrListSanPham.get(position).getXuatXuSanPham());
        holder.ItemCuttomTrangChu_ngang_tv_tenSanPham.setText(arrListSanPham.get(position).getTenSanPham());
        holder.ItemCuttomTrangChu_ngang_tv_giaSanPham.setText(Double.parseDouble(arrListSanPham.get(position).getGiaBanSanPham() + "") + "00đ");
        Glide.with(context).load(arrListSanPham.get(position).getAnhSanPham())
                .into(holder.ItemCuttomTrangChu_ngang_img_showAnhSanPham);

//     Thêm sản phẩm vào yêu thích sản phẩm
        holder.ItemCuttomTrangChu_ngang_img_btn_chonYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                code chuyển btn thành yêu thích
                Toast.makeText(context, "Thích nè :>", Toast.LENGTH_SHORT).show();
            }
        });
//      Show chi tiết sản phẩm

        holder.ItemCuttomTrangChu_ngang_img_btn_themSanPhamVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        code thêm sản phẩm vào giỏ hàng
                Toast.makeText(context, arrListSanPham.get(position).getTenSanPham() + "", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrListSanPham != null) {
            return arrListSanPham.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView
                ItemCuttomTrangChu_ngang_tv_soLuongSanPhamMuaYeuThich,
                ItemCuttomTrangChu_ngang_tv_xuatXuSanPham,
                ItemCuttomTrangChu_ngang_tv_tenSanPham,
                ItemCuttomTrangChu_ngang_tv_giaSanPham;

        ImageView
                ItemCuttomTrangChu_ngang_img_showAnhSanPham,
                ItemCuttomTrangChu_ngang_img_btn_chonYeuThich,
                ItemCuttomTrangChu_ngang_img_btn_themSanPhamVaoGioHang;

        public ViewHolder(View itemView) {
            super(itemView);

            ItemCuttomTrangChu_ngang_tv_soLuongSanPhamMuaYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_tv_soLuongSanPhamMuaYeuThich);
            ItemCuttomTrangChu_ngang_tv_xuatXuSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_tv_xuatXuSanPham);
            ItemCuttomTrangChu_ngang_tv_tenSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_tv_tenSanPham);
            ItemCuttomTrangChu_ngang_tv_giaSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_tv_giaSanPham);

            ItemCuttomTrangChu_ngang_img_showAnhSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_img_showAnhSanPham);
            ItemCuttomTrangChu_ngang_img_btn_chonYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_img_btn_chonYeuThich);
            ItemCuttomTrangChu_ngang_img_btn_themSanPhamVaoGioHang = itemView.findViewById(R.id.itemCuttomTrangChu_ngang_img_btn_themSanPhamVaoGioHang);
        }
    }
}
