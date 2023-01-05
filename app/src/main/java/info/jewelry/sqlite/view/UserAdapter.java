package info.jewelry.sqlite.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.jewelry.sqlite.R;
import info.jewelry.sqlite.database.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView firstname;
        public TextView lastname;
        public TextView phone;

        public MyViewHolder(View view) {
            super(view);
            firstname = view.findViewById(R.id.firstname);
            lastname = view.findViewById(R.id.lastname);
            phone = view.findViewById(R.id.phone);
        }
    }


    public UserAdapter(Context context, List<User> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = notesList.get(position);

        holder.firstname.setText(user.getFirstName());
        holder.lastname.setText(user.getLastName());
        holder.phone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}