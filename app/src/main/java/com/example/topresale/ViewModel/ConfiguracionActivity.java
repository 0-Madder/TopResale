package com.example.topresale.ViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.topresale.CambiarPswFragment;
import com.example.topresale.LogoutFragment;
import com.example.topresale.R;
import com.example.topresale.TerminosFragment;
import com.example.topresale.databinding.ActivityConfiguracionBinding;
import com.example.topresale.databinding.ActivityMainBinding;

public class ConfiguracionActivity extends AppCompatActivity {

    ActivityConfiguracionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new TerminosFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.hoja:
                    replaceFragment(new TerminosFragment());
                    break;
                case R.id.candado:
                    replaceFragment(new CambiarPswFragment());
                    break;
                case R.id.puerta:
                    replaceFragment(new LogoutFragment());
                    break;
            }


            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}