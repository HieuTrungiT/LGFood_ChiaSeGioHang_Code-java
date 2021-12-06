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
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.R;

import java.util.ArrayList;

public class addToGioHangAdapter extends RecyclerView.Adapter<addToGioHangAdapter.myViewHolder> {
    Context mContext;
    ArrayList<model_addToCart> cartArrayList;
    ArrayList<model_Cart> arrListGioHangs;
    private IClickListener mIClickListener;

    public addToGioHangAdapter() {

    }

    public interface IClickListener {
        void onCLickMinusItem(model_Cart cart, model_addToCart newCart);

        void onClickPlusItem(model_Cart cart, model_addToCart newCart);

        void onLongClickDelete(model_addToCart cart);

        void onClickShowItem(model_addToCart cart, model_Cart arrGioHangs, int viTri);
    }

    public addToGioHangAdapter(Context mContext, ArrayList<model_addToCart> cartArrayList, ArrayList<model_Cart> arrListGioHangs, IClickListener mIClickListener) {
        this.mContext = mContext;
        this.cartArrayList = cartArrayList;
        this.arrListGioHangs = arrListGioHangs;
        this.mIClickListener = mIClickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_custom1, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addToGioHangAdapter.myViewHolder holder, int position) {

        model_addToCart cart = cartArrayList.get(position);
        model_Cart arrGioHangs = arrListGioHangs.get(position);

        holder.xuLi_txt_tenItem.setText(cart.getTenSp());
        holder.xuLi_txt_giaItem.setText(String.valueOf(cart.getGiaBanSp()) + "00vnđ");
        holder.xuLi_txt_soLuong.setText(String.valueOf(arrGioHangs.getSoLuong()));
        holder.xuLi_txt_xuatXuItem.setText(cart.getXuatXuSp());
        Glide.with(mContext)
                .load(cart.getAnhSp())
                .into(holder.xuLi_img_anhItem);
        holder.xuLi_img_anhItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickListener.onClickShowItem(cart, arrGioHangs, position);
            }
        });
        holder.xuLi_cardView_formItem1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mIClickListener.onLongClickDelete(cart);
                return false;
            }
        });
        holder.xuLi_cardView_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.soLuong = Integer.parseInt(arrGioHangs.getSoLuong());
                holder.soLuong = holder.soLuong - 1;
                if (holder.soLuong > 0) {

                    holder.xuLi_txt_soLuong.setText(String.valueOf(holder.soLuong));
                    mIClickListener.onCLickMinusItem(arrGioHangs, cart);
                }else{
                    holder.xuLi_cardView_minus.setAlpha((float) 0.2);
                    holder.xuLi_img_giaItem.setAlpha((float) 0.3);
                    holder.soLuong = 1;
                }

            }
        });
        holder.xuLi_cardView_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.xuLi_img_giaItem.setAlpha((float) 1);
                holder.xuLi_cardView_minus.setAlpha((float) 1);
                holder.soLuong = Integer.parseInt(arrGioHangs.getSoLuong());
                holder.soLuong = holder.soLuong + 1;
                holder.xuLi_txt_soLuong.setText(String.valueOf(holder.soLuong));
                mIClickListener.onClickPlusItem(arrGioHangs, cart);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        int soLuong = 0;
        ImageView xuLi_img_anhItem,xuLi_img_giaItem;
        TextView xuLi_txt_tenItem, xuLi_txt_xuatXuItem, xuLi_txt_giaItem, xuLi_txt_soLuong;
        CardView xuLi_cardView_minus, xuLi_cardView_plus, xuLi_cardView_formItem1;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            xuLi_img_giaItem= itemView.findViewById(R.id.xuLi_img_giaItem);
            xuLi_img_anhItem = itemView.findViewById(R.id.xuLi_img_anhItem);
            xuLi_txt_tenItem = itemView.findViewById(R.id.xuLi_txt_tenItem);
            xuLi_txt_xuatXuItem = itemView.findViewById(R.id.xuLi_txt_xuatXuItem);
            xuLi_txt_giaItem = itemView.findViewById(R.id.xuLi_txt_giaItem);
            xuLi_txt_soLuong = itemView.findViewById(R.id.xuLi_txt_soLuong);
            xuLi_cardView_minus = itemView.findViewById(R.id.xuLi_cardView_minus);
            xuLi_cardView_plus = itemView.findViewById(R.id.xuLi_cardView_plus);
            xuLi_cardView_formItem1 = itemView.findViewById(R.id.xuLi_cardView_formItem1);


        }
    }
}
