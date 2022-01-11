package com.example.appchat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchat.Model.Message;
import com.example.appchat.R;
import com.example.appchat.Service.SessionManagement;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListChatApdapter extends RecyclerView.Adapter<ListChatApdapter.ListChatViewHolder>{
    private List<Message> arrMess;
    private Context context;

    public ListChatApdapter(List<Message> arrMess, Context context) {
        this.arrMess = arrMess;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item, parent, false);
        return new ListChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListChatViewHolder holder, int position) {
            Message message = arrMess.get(position);
            //Check id của session với ia của người chat. Nếu khác là bạn chát;
            holder.tvMessageRecently.setText(arrMess.get(0).getMessage());
            holder.tvChatPersonName.setText(message.getPersonSender());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListChatViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView photoListChat;
        private TextView tvChatPersonName, tvMessageRecently;
        private RelativeLayout listPersonChat;
        public ListChatViewHolder(@NonNull View itemView) {
            super(itemView);
            
            photoListChat = itemView.findViewById(R.id.photoListChat);
            tvChatPersonName = itemView.findViewById(R.id.tvPersonNameChat);
            tvMessageRecently = itemView.findViewById(R.id.tvMessageRecently);
            listPersonChat = itemView.findViewById(R.id.listPersonChat);

        }
    }
}
