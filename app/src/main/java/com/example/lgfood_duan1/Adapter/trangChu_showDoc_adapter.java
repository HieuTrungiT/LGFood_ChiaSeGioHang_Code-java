package com.example.lgfood_duan1.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Activity.trangChu_SanPham_Activity;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_yeuThich;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.lgfood_duan1.Activity.trangChu_SanPham_Activity.CHECK;

// Trung
public class trangChu_showDoc_adapter extends RecyclerView.Adapter<trangChu_showDoc_adapter.ViewHolder> {
    private ArrayList<model_SanPham> arrListSanPham;
    private trangChu_SanPham_Activity context;
    private ArrayList<model_yeuThich> arrListYeuThich;

    //thai: onClickItem
    private IClickListener mIClickListener;
    //Firebase
    private DatabaseReference dataRef;
    private FirebaseDatabase database;
    SharedPreferences sharedPreferences;

    public trangChu_showDoc_adapter(ArrayList<model_SanPham> arrListSanPham, trangChu_SanPham_Activity context, ArrayList<model_yeuThich> arrListYeuThich, IClickListener mIClickListener) {
        this.arrListSanPham = arrListSanPham;
        this.context = context;
        this.mIClickListener = mIClickListener;
    }

    public interface IClickListener {
        void onClickShowItem(model_SanPham sanPham);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuttom_sanpham_trangchu_doc, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(trangChu_showDoc_adapter.ViewHolder holder, int position) {
        model_SanPham sanPham = arrListSanPham.get(position);


        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        sharedPreferences = context.getSharedPreferences("USER_FILE", context.MODE_PRIVATE);
        arrListYeuThich = new ArrayList<>();
//         lấy danh sách yêu thích từ firebase về

        dataRef = database.getReference("danhSachSanPhamYeuThich").child(sharedPreferences.getString("IDDANHSACHYEUTHICH", ""));
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrListYeuThich.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    model_yeuThich arrYeuThich = ds.getValue(model_yeuThich.class);
                    arrListYeuThich.add(arrYeuThich);
                }

// khi đã có danh sách firebase bắt đầu kiểm tra coi trong danh sách sản phẩm có sản phẩm nào được chọn chưa
            try {
                model_yeuThich arrYeuThich = null;
                if (arrListYeuThich != null || arrListYeuThich.size() != 0) {
                    for (int i = 0; i < arrListYeuThich.size(); i++) {
//                        nếu có rồi thì set vị trí arr yêu thích cho model
                        if (arrListYeuThich.get(i).getIdSanPham().equals(sanPham.getIdSanPham())) {
                            holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setImageResource(R.drawable.ic_btn_love_red);
                        }
                    }
                }
            }catch (Exception e){

            }

                //     Thêm sản phẩm vào yêu thích sản phẩm

//                     bắt sự kiện thi tacsc động đến item tim
                holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int check = 0;
                        int viTri = 0;
//                code chuyển btn thành yêu thích
                        if (sharedPreferences.getString("IDDANHSACHYEUTHICH", "").isEmpty()) {
                            Toast.makeText(context, "Bạn chưa đăng nhập!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
//                                 kiểm tra nếu như trong danh sách yêu thích đã có 1 sản phẩm yêu thích giống vậy thì xóa đi
//                                 nếu như đã có id sản phẩm trùng rồi thì xóa iton yêu thích
                                for (int i = 0; i < arrListYeuThich.size(); i++) {
                                    if (sanPham.getIdSanPham().equals(arrListYeuThich.get(i).getIdSanPham())) {
                                        check++;

                                        context.onClicktHeartItemDelete(arrListYeuThich.get(i).getIdYeuThich(), position, true);
                                        holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setImageResource(R.drawable.ic_btn_love_white);
                                        arrListYeuThich.remove(i);
                                        context.setNotifyitem(position);
                                    }
                                }


                                if (check == 0) {
                                    holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setImageResource(R.drawable.ic_btn_love_red);
                                    context.onClickHeartItem(sanPham.getIdSanPham(), position, true);

                                }
                            } catch (Exception e) {
                                context.onClickHeartItem(sanPham.getIdSanPham(), position, true);
                                holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setImageResource(R.drawable.ic_btn_love_red);

                            }


                        }

                    }
                });
//           String sss = arrListYeuThich.get(position).getIdYeuThich();
//            Log.d("eee",sss+"");

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        Glide.with(context)
                .load(arrListSanPham.get(position).getAnhSanPham())
                .into(holder.ItemCuttomTrangChu_doc_imgShowAnhSanPham);


        holder.ItemCuttomTrangChu_doc_tv_xuatXu.setText(arrListSanPham.get(position).getXuatXuSanPham());
        holder.ItemCuttomTrangChu_doc_tv_tenSanPham.setText(arrListSanPham.get(position).getTenSanPham());
        holder.ItemCuttomTrangChu_doc_tv_giaSanPham.setText(Double.parseDouble(arrListSanPham.get(position).getGiaBanSanPham() + "") + "00đ");
        holder.ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich.setText("0");


////      Show chi tiết sản phẩm
        holder.ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListener.onClickShowItem(sanPham);
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
                ItemCuttomTrangChu_doc_imgShowAnhSanPham;
        LinearLayout ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham;
        TextView
                ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich,
                ItemCuttomTrangChu_doc_tv_xuatXu,
                ItemCuttomTrangChu_doc_tv_tenSanPham,
                ItemCuttomTrangChu_doc_tv_giaSanPham;

        //
        public ViewHolder(View itemView) {
            super(itemView);
//            ánh xạ\

            ItemCuttomTrangChu_doc_imgShowAnhSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_imgShowAnhSanPham);
            ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_llout_btn_showChiTietSanPham);
            ItemCuttomTrangChu_doc_img_btn_chonYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_doc_img_btn_chonYeuThich);
            //            Textview
            ItemCuttomTrangChu_doc_tv_xuatXu = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_xuatXu);
            ItemCuttomTrangChu_doc_tv_tenSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_tenSanPham);
            ItemCuttomTrangChu_doc_tv_giaSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_giaSanPham);
            ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich);
        }
    }
}
