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
// Trung
public class trangChu_showDoc_adapter extends RecyclerView.Adapter<trangChu_showDoc_adapter.ViewHolder> {
    private ArrayList<model_SanPham> arrListSanPham;
    private trangChu_SanPham_Activity context;


    public trangChu_showDoc_adapter(ArrayList<model_SanPham> arrListSanPham, trangChu_SanPham_Activity context) {
        this.arrListSanPham = arrListSanPham;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuttom_sanpham_trangchu_doc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(trangChu_showDoc_adapter.ViewHolder holder, int position) {

        Glide.with(context)
                .load(arrListSanPham.get(position).getAnhSanPham())
                .into(holder.ItemCuttomTrangChu_doc_imgShowAnhSanPham);
        holder.ItemCuttomTrangChu_doc_tv_xuatXu.setText(arrListSanPham.get(position).getXuatXuSanPham());
        holder.ItemCuttomTrangChu_doc_tv_tenSanPham.setText(arrListSanPham.get(position).getTenSanPham());
        holder.ItemCuttomTrangChu_doc_tv_giaSanPham.setText(Double.parseDouble(arrListSanPham.get(position).getGiaBanSanPham() + "") + "00đ");
        holder.ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich.setText("0");
//     Thêm sản phẩm vào yêu thích sản phẩm
        holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Thích nè :>", Toast.LENGTH_SHORT).show();
            }
        });

//      Show chi tiết sản phẩm
        holder.ItemCuttomTrangChu_doc_img_btn_showChiTietSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, arrListSanPham.get(position).getTenSanPham()+"", Toast.LENGTH_SHORT).show();
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
        //        gán kiểu dữ liệu
        ImageView
                ItemCuttomTrangChu_doc_img_btn_chonYeuThich,
                ItemCuttomTrangChu_doc_imgShowAnhSanPham,
                ItemCuttomTrangChu_doc_img_btn_showChiTietSanPham;
        TextView
                ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich,
                ItemCuttomTrangChu_doc_tv_xuatXu,
                ItemCuttomTrangChu_doc_tv_tenSanPham,
                ItemCuttomTrangChu_doc_tv_giaSanPham;

        //
        public ViewHolder(View itemView) {
            super(itemView);
//            ánh xạ\
//            ImgeView
            ItemCuttomTrangChu_doc_imgShowAnhSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_imgShowAnhSanPham);
            ItemCuttomTrangChu_doc_img_btn_showChiTietSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_img_btn_showChiTietSanPham);
            ItemCuttomTrangChu_doc_img_btn_chonYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_doc_img_btn_chonYeuThich);
            //            Textview
            ItemCuttomTrangChu_doc_tv_xuatXu = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_xuatXu);
            ItemCuttomTrangChu_doc_tv_tenSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_tenSanPham);
            ItemCuttomTrangChu_doc_tv_giaSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_giaSanPham);
            ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich);
        }
    }
}
