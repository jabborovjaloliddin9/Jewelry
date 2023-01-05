package info.jewelry.sqlite.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.jewelry.sqlite.R;
import info.jewelry.sqlite.database.DatabaseHelper;
import info.jewelry.sqlite.database.model.User;
import info.jewelry.sqlite.utils.MyDividerItemDecoration;
import info.jewelry.sqlite.utils.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {
    private UserAdapter mAdapter;
    private List<User> usersList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);

        db = new DatabaseHelper(this);

        usersList.addAll(db.getAllUsers());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> showUserDialog(false, null, -1));

        mAdapter = new UserAdapter(this, usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyNotes();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
    }


    private void createUser(String firstName, String latName, String phone) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertUser(firstName, latName, phone);

        // get the newly inserted note from db
        User user = db.getUser(id);

        if (user != null) {
            // adding new note to array list at 0 position
            usersList.add(0, user);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }


    private void updateUser(String firstname,String lastname,String phone, int position) {
        User n = usersList.get(position);
        // updating note text
        n.setFirstName(firstname);
        n.setLastName(lastname);
        n.setPhone(phone);

        // updating note in db
        db.updateUser(n);

        // refreshing the list
        usersList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }


    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteUser(usersList.get(position));

        // removing the note from the list
        usersList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }


    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, (dialog, which) -> {
            if (which == 0) {
                showUserDialog(true, usersList.get(position), position);
            } else {
                deleteNote(position);
            }
        });
        builder.show();
    }


    private void showUserDialog(final boolean shouldUpdate, final User user, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText firstname = view.findViewById(R.id.et_firstname);
        final EditText lastname = view.findViewById(R.id.et_lastname);
        final EditText phone = view.findViewById(R.id.et_phone);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));

        if (shouldUpdate && user != null) {
            firstname.setText(user.getFirstName());
            lastname.setText(user.getLastName());
            phone.setText(user.getPhone());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", (dialogBox, id) -> {

                })
                .setNegativeButton("cancel",
                        (dialogBox, id) -> dialogBox.cancel());

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            // Show toast message when no text is entered
            if (TextUtils.isEmpty(firstname.getText().toString())) {
                Toast.makeText(MainActivity.this, "Enter!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                alertDialog.dismiss();
            }

            // check if user updating note
            if (shouldUpdate && user != null) {
                // update note by it's id
                updateUser(firstname.getText().toString(),lastname.getText().toString(),phone.getText().toString(), position);
            } else {
                // create new note
                createUser(firstname.getText().toString(),lastname.getText().toString(),phone.getText().toString());
            }
        });
    }


    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getUserCount() > 0) {
            noNotesView.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
        }
    }
}
