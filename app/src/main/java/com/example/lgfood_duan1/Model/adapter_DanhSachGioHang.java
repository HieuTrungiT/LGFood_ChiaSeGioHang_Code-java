package com.example.lgfood_duan1.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Activity.Xac_Nhan_DH_Activity;
import com.example.lgfood_duan1.Activity.khoHang_Activity;
import com.example.lgfood_duan1.R;

import java.util.List;

public class adapter_DanhSachGioHang extends  RecyclerView.Adapter<adapter_DanhSachGioHang.gioHangHolder> {

    private List<model_SanPham> mListSanPham;
    public adapter_DanhSachGioHang(Xac_Nhan_DH_Activity xac_nhan_dh_activity, int item_custom3, List<model_SanPham> mListSanPham){
        this.mListSanPham = mListSanPham;
    }


    private khoHang_Activity context;
    @NonNull

    @Override
    public gioHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom3,parent,false);


        return new gioHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_DanhSachGioHang.gioHangHolder holder, int position) {

        model_SanPham model_sanPham = mListSanPham.get(position);
        if (model_sanPham == null){
            return;
        }

        Glide.with(context)
                .load(mListSanPham.get(position).getAnhSanPham())
                .into(holder.anhSanPham);
        holder.tenSanPham.setText(mListSanPham.get(position).getTenSanPham());
        holder.xuatSu.setText(mListSanPham.get(position).getXuatXuSanPham());
        holder.giaSanPham.setText(mListSanPham.get(position).getGiaBanSanPham()+"");


    }

    @Override
    public int getItemCount() {
        if (mListSanPham !=null){
            return  mListSanPham.size();
        }
        return 0;
    }

    public class gioHangHolder extends RecyclerView.ViewHolder {
        private ImageView anhSanPham;
        private TextView
                tenSanPham,
                xuatSu,
                giaSanPham;

        public gioHangHolder(@NonNull  View itemView) {
            super(itemView);
            anhSanPham = itemView.findViewById(R.id.xuLi_img_anhItem);
            tenSanPham = itemView.findViewById(R.id.xuLi_txt_tenItem);
            xuatSu = itemView.findViewById(R.id.xuLi_txt_xuatXu);
            giaSanPham = itemView.findViewById(R.id.xuLi_txt_giaItem);

        }
    }
}
