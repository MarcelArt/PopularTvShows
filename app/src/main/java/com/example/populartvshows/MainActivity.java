package com.example.populartvshows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.populartvshows.adapters.TVAdapter;
import com.example.populartvshows.api.APIService;
import com.example.populartvshows.models.Tv;
import com.example.populartvshows.models.Tvs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RecyclerView rv_tv;
    private RecyclerView.Adapter tvAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Tvs tvs;
    private Spinner spin_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hubungin view2nya ke layout
        rv_tv = (RecyclerView) findViewById(R.id.rv_tvs);
        spin_tv = (Spinner) findViewById(R.id.spin_tv);
        rv_tv.setHasFixedSize(true);

        //configurasi layout recycler view
        layoutManager = new LinearLayoutManager(this);
        rv_tv.setLayoutManager(layoutManager);

        //ambil data dari themoviedb
        fetchTvs();

        //event listener untuk ketika item spinner dipilih
        spin_tv.setOnItemSelectedListener(this);
    }

    public void fetchTvs() {
        //membuat koneksi ke themoviedb mungkin lebih bagus jika dibuat kelas statik agar dapat didaur ulang untuk halaman lainnya
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //panggil api
        APIService service = retrofit.create(APIService.class);
        Call<Tvs> call = service.listTvs();
        call.enqueue(new Callback<Tvs>() {
            @Override
            public void onResponse(Call<Tvs> call, Response<Tvs> response) {
                tvs = response.body(); //simpan response api ke variabel

                //menyimpan hasil response ke recylerview
                tvAdapter = new TVAdapter(tvs.getResults());
                rv_tv.setAdapter(tvAdapter);

                //simpan hasil response ke spinner
                ArrayAdapter<Tv> spinnerAdapter = new ArrayAdapter<Tv>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, tvs.getResults());
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin_tv.setAdapter(spinnerAdapter);
            }

            @Override
            public void onFailure(Call<Tvs> call, Throwable t) {
                Log.d("data", "error: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "id:" + tvs.getResults().get(position).getId(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
