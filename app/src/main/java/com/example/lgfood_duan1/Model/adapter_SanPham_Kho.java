package com.example.lgfood_duan1.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.R;
import com.example.lgfood_duan1.khoHang_Activity;

import java.util.ArrayList;
import java.util.List;

public class adapter_SanPham_Kho extends RecyclerView.Adapter<adapter_SanPham_Kho.ViewHolder> {
    private List<model_SanPham> arrListSanPham;


    private khoHang_Activity context;

    public adapter_SanPham_Kho(List<model_SanPham> arrListSanPham, khoHang_Activity context) {
        this.arrListSanPham = arrListSanPham;
        this.context = context;
    }

    public adapter_SanPham_Kho(khoHang_Activity khoHang_activity, int item_custom2, ArrayList<model_SanPham> arrayList) {

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_custom2, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  adapter_SanPham_Kho.ViewHolder holder, int position) {

        Glide.with(context)
                .load(arrListSanPham.get(position).getAnhSanPham())
                .into(holder.imageView);
        holder.txtTen.setText(arrListSanPham.get(position).getTenSanPham());
        holder.txtXuatXu.setText(arrListSanPham.get(position).getXuatXuSanPham());
        holder.txtGia.setText(arrListSanPham.get(position).getGiaBanSanPham()+"");
        holder.txtSoLuong.setText(arrListSanPham.get(position).getSoLuongSanPham()+"");

        holder.ItemkhoHang_imgBtn_xoaItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //                bắt sự kiện khi nhấn vào nút xóa
                context.setXoaItem_gioHang(arrListSanPham.get(position).getIdSanPham(), position);
//                gửi id sản phẩm
                return false;
            }
        });


        holder.XuLi_relative_formItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             chuyển dữ liệu quá trang kho hàng add vô bule
                String idSanPham = arrListSanPham.get(position).getIdSanPham();
                String anhSanPham = arrListSanPham.get(position).getAnhSanPham();
                String tenSanPham = arrListSanPham.get(position).getTenSanPham();
                String giaNhapSanPham = String.valueOf( arrListSanPham.get(position).getGiaNhapSanPham()) ;
                String giaSanPham = String.valueOf(arrListSanPham.get(position).getGiaBanSanPham());
                String giamGiaSanPham =String.valueOf( arrListSanPham.get(position).getGiamGiaSanPham());
                String soLuongSanPham = String.valueOf(arrListSanPham.get(position).getSoLuongSanPham());
                String xuatXu = arrListSanPham.get(position).getXuatXuSanPham();
                String ngaySanXuat = String.valueOf(arrListSanPham.get(position).getNgaySanXuatSanPham());
                String loaiSanPham = arrListSanPham.get(position).getLoaiSanPham();

                context.setShowItem_gioHang(idSanPham, anhSanPham, tenSanPham, giaSanPham, giamGiaSanPham , soLuongSanPham, giaNhapSanPham ,xuatXu, ngaySanXuat, loaiSanPham);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (arrListSanPham  != null ){
            return arrListSanPham.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTen;
        TextView txtGia;
        TextView txtSoLuong;
        TextView txtXuatXu;
        ImageButton ItemkhoHang_imgBtn_xoaItem;
        CardView XuLi_relative_formItem2;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.xuLi_img_anhItem2) ;
            txtTen = (TextView) itemView.findViewById(R.id.xuLi_txt_tenItem2) ;
            txtGia = (TextView) itemView.findViewById(R.id.xuLi_txt_giaItem2) ;
            txtSoLuong = (TextView) itemView.findViewById(R.id.xuLi_txt_soLuongItem2) ;
            txtXuatXu = (TextView) itemView.findViewById(R.id.xuLi_txt_xuatXuItem2) ;
            XuLi_relative_formItem2 = itemView.findViewById(R.id.xuLi_cardView_formItem2);
            ItemkhoHang_imgBtn_xoaItem = itemView.findViewById(R.id.itemkhoHang_imgBtn_xoaItem);


        }
    }
}
