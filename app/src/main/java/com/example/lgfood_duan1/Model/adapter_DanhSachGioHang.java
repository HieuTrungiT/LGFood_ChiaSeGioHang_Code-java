package com.example.lgfood_duan1.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Activity.Xac_Nhan_DH_Activity;
import com.example.lgfood_duan1.Activity.khoHang_Activity;
import com.example.lgfood_duan1.Activity.trangChu_SanPham_Activity;
import com.example.lgfood_duan1.Adapter.trangChu_showDoc_adapter;
import com.example.lgfood_duan1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class adapter_DanhSachGioHang extends  RecyclerView.Adapter<adapter_DanhSachGioHang.gioHangHolder> {

    private ArrayList<model_SanPham> arrListSanPham;
    private Xac_Nhan_DH_Activity context;

    private trangChu_showDoc_adapter.IClickListener mIClickListener;

    public adapter_DanhSachGioHang(ArrayList<model_SanPham> arrListSanPham, Xac_Nhan_DH_Activity context, adapter_DanhSachGioHang.IClickListener mIClickListener) {
        this.arrListSanPham = arrListSanPham;
        this.context = context;
        this.mIClickListener = (trangChu_showDoc_adapter.IClickListener) mIClickListener;
    }

    public void setLayoutManager(GridLayoutManager gridLayoutManager) {
    }

    public void setItemAnimator(DefaultItemAnimator defaultItemAnimator) {
    }

    public void setAdapter(adapter_DanhSachGioHang adapter_danhSachGioHang) {
    }

    public interface IClickListener{
        void onClickShowItem(model_SanPham sanPham);
    }

    @NonNull

    @Override
    public gioHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom3,parent,false);


        return new gioHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_DanhSachGioHang.gioHangHolder holder, int position) {

        model_SanPham sanPham = arrListSanPham.get(position);
        if (sanPham == null){
            return;
        }

        Glide.with(context)
                .load(arrListSanPham.get(position).getAnhSanPham())
                .into(holder.anhSanPham);
        holder.tenSanPham.setText(arrListSanPham.get(position).getTenSanPham());
        holder.xuatSu.setText(arrListSanPham.get(position).getXuatXuSanPham());
        holder.giaSanPham.setText(arrListSanPham.get(position).getGiaBanSanPham()+"");

//      Show chi tiết sản phẩm
        holder.ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListener.onClickShowItem(sanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrListSanPham !=null){
            return  arrListSanPham.size();
        }
        return 0;
    }

    public class gioHangHolder extends RecyclerView.ViewHolder {
        private ImageView anhSanPham;
        private TextView
                tenSanPham,
                xuatSu,
                giaSanPham;
        LinearLayout ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham;

        public gioHangHolder(@NonNull  View itemView) {
            super(itemView);
            ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_llout_btn_showChiTietSanPham);

            anhSanPham = itemView.findViewById(R.id.xuLi_img_anhItem);
            tenSanPham = itemView.findViewById(R.id.xuLi_txt_tenItem);
            xuatSu = itemView.findViewById(R.id.xuLi_txt_xuatXu);
            giaSanPham = itemView.findViewById(R.id.xuLi_txt_giaItem);

        }
    }

}
