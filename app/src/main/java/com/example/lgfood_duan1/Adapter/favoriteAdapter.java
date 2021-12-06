package com.example.lgfood_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.Model.model_yeuThichShow;
import com.example.lgfood_duan1.R;

import java.util.ArrayList;

public class favoriteAdapter extends RecyclerView.Adapter<addToGioHangAdapter.myViewHolder> {
    Context mContext;
    ArrayList<model_yeuThichShow> yeuThichShowArrayList;

    private IClickListener mIClickListener;

    public favoriteAdapter(){

    }

    public interface IClickListener{
        void onCLickMinusItem(model_yeuThichShow yeuThichShow);

        void onClickPlusItem(model_yeuThichShow yeuThichShow);

        void onLongClickDelete(model_yeuThichShow yeuThichShow);

        void onClickShowItem(model_yeuThichShow yeuThichShow);
    }

    public favoriteAdapter(Context mContext, ArrayList<model_yeuThichShow> yeuThichShowArrayList, IClickListener mIClickListener) {
        this.mContext = mContext;
        this.yeuThichShowArrayList = yeuThichShowArrayList;
        this.mIClickListener = mIClickListener;
    }

    @NonNull
    @Override
    public addToGioHangAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.item_custom_favorite,parent,false);

        return new addToGioHangAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addToGioHangAdapter.myViewHolder holder, int position) {

        model_yeuThichShow yeuThichShow=yeuThichShowArrayList.get(position);

        holder.xuLi_txt_tenItem.setText(yeuThichShow.getTenSp());
        holder.xuLi_txt_giaItem.setText(String.valueOf(yeuThichShow.getGiaBanSp()));
//        holder.xuLi_txt_soLuong.setText(String.valueOf(yeuThichShow.getSoLuongTrongKho()));
        holder.xuLi_txt_xuatXuItem.setText(yeuThichShow.getXuatXuSp());
        Glide.with(mContext)
                .load(yeuThichShow.getAnhSp())
                .into(holder.xuLi_img_anhItem);
        holder.xuLi_img_anhItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickListener.onClickShowItem(yeuThichShow);
            }
        });
        holder.xuLi_cardView_formItem1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mIClickListener.onLongClickDelete(yeuThichShow);
                return false;
            }
        });
//        holder.xuLi_cardView_minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mIClickListener.onCLickMinusItem(yeuThichShow);
//            }
//        });
//        holder.xuLi_cardView_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mIClickListener.onClickPlusItem(yeuThichShow);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return yeuThichShowArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView xuLi_img_anhItem;
        TextView xuLi_txt_tenItem,xuLi_txt_xuatXuItem,xuLi_txt_giaItem,xuLi_txt_soLuong;
        CardView xuLi_cardView_minus,xuLi_cardView_plus,xuLi_cardView_formItem1;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            xuLi_img_anhItem=itemView.findViewById(R.id.xuLi_img_anhItem);
            xuLi_txt_tenItem=itemView.findViewById(R.id.xuLi_txt_tenItem);
            xuLi_txt_xuatXuItem=itemView.findViewById(R.id.xuLi_txt_xuatXuItem);
            xuLi_txt_giaItem=itemView.findViewById(R.id.xuLi_txt_giaItem);
//            xuLi_txt_soLuong=itemView.findViewById(R.id.xuLi_txt_soLuong);
//            xuLi_cardView_minus=itemView.findViewById(R.id.xuLi_cardView_minus);
//            xuLi_cardView_plus=itemView.findViewById(R.id.xuLi_cardView_plus);
            xuLi_cardView_formItem1=itemView.findViewById(R.id.xuLi_cardView_formItem1);


        }
    }
}
