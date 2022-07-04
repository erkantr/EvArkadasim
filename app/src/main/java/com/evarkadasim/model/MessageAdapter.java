package com.evarkadasim.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evarkadasim.R;

import java.util.ArrayList;
import java.util.List;

import static com.evarkadasim.ilan_ekrani1.a;
import static com.evarkadasim.login_fragment.login_user_name;
import static com.evarkadasim.ui.chats.chats_fragment.gonderen_profil;
import static com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment.image;
import static com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment.image1;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context mContext;
    private List<MessageModel> model = new ArrayList<>();
    ImageView image2;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    public MessageAdapter(Context mContext, List<MessageModel> model, ImageView image2) {

        this.mContext = mContext;
        this.model = model;
        this.image2 = image2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MessageModel chat = model.get(position);

        holder.show_message.setText(chat.getMesaj());

        holder.profile_image.setImageResource(R.mipmap.ic_launcher);

        if (a == 1) {
            holder.profile_image.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        }
        if (a == 2) {
            holder.profile_image.setImageBitmap(BitmapFactory.decodeByteArray(image1, 0, image1.length));
        }
        if (a == 3) {
            holder.profile_image.setImageBitmap(BitmapFactory.decodeByteArray(gonderen_profil, 0, gonderen_profil.length));
        }

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (model.get(position).getGonderen().equals(login_user_name)) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
