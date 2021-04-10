package io.osvaldocabral.recyclerviewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AgendaArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Incluindo o adapter no recycler
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AgendaArrayAdapter();
        recyclerView.setAdapter(adapter);

        // Adicionando decoração ao recycler
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        // O Recycler view necessita saber a orientação/layout que deve ser utilizado
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setClickListener(new AgendaArrayAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Agenda agenda = DataSingleton.getInstance().listAgenda.get(position);
                DataSingleton.getInstance().currentEditAgenda = agenda;

                Intent intent = new Intent(MainActivity.this, EditAgenda.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item) {
            DataSingleton.getInstance().listAgenda.add(0, new Agenda("Click here to edit", "", ""));
            adapter.notifyItemInserted(0);

            View contentView = findViewById(android.R.id.content);
            Snackbar.make(contentView, R.string.alert_added_item, Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataSingleton.getInstance().listAgenda.remove(0);
                            adapter.notifyItemRemoved(0);
                        }
                    })
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }
}