package com.example.appchat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchat.Model.Message;
import com.example.appchat.Model.User;
import com.example.appchat.R;
import com.example.appchat.Service.SessionManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{
    private List<Message> arrMess;
    private Context context;

    SessionManagement session;
    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;

    public MessageAdapter(List<Message> arrMess, Context context) {
        this.arrMess = arrMess;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right, parent, false);
            return new MessageViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left, parent, false);
            return new MessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
            Message message = arrMess.get(position);
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return arrMess.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_mess);
        }
    }

    @Override
    public int getItemViewType(int position) {
        session =new SessionManagement(context.getApplicationContext());
        HashMap<String, String> getUser = session.getUserDetails();
        JSONObject object;
        User user = null;
        try {
             object = new JSONObject(getUser.get(SessionManagement.KEY_NAME));
            String id = object.getString("id");
            String phone = object.getString("phone");
            String password = object.getString("password");
            String fullName = object.getString("fullName");
            String birthday = object.getString("birthday");
            String gender = object.getString("gender");
            byte[] photoProfile = (byte[]) object.get("photoProfile");
            user = new User(id, phone, password, fullName, birthday, gender, photoProfile);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(arrMess.get(position).getPersonSender().equals(user.getId())){
            return  MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;

        }
    }
}
