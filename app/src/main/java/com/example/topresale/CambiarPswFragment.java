package com.example.topresale;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topresale.ViewModel.ConfiguracionActivity;
import com.example.topresale.model.UserManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CambiarPswFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CambiarPswFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String vContra;
    private String nContra;
    private String rContra;
    EditText viejaContra;
    EditText nuevaContra;
    EditText repiteContra;
    Button boton;
    private UserManager userManager;

    public CambiarPswFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CambiarPswFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CambiarPswFragment newInstance(String param1, String param2) {
        CambiarPswFragment fragment = new CambiarPswFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = UserManager.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_cambiar_psw, container, false);
        viejaContra = view.findViewById(R.id.antiguaPsw_editText);
        nuevaContra = view.findViewById(R.id.nuevaPsw_editText);
        repiteContra = view.findViewById(R.id.repiteNuevaPsw_editText);
        boton = view.findViewById(R.id.confirmar_button);
        boton.setOnClickListener(this);



        return view;
    }



    public void comprobar(){
        vContra = viejaContra.getText().toString();
        nContra = nuevaContra.getText().toString();
        rContra = repiteContra.getText().toString();

        String comprovar = userManager.provesNovaContransenya(vContra, nContra,rContra);

        if(!comprovar.equalsIgnoreCase("correcte")){
            Toast.makeText(getActivity(), comprovar, Toast.LENGTH_SHORT).show();
        }else{
            userManager.canviarContrasenyaFireAuth(nContra);
            userManager.canviarContasenyaFireStore(nContra);
            Toast.makeText(getActivity(), "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
        }
        viejaContra.setText("");
        nuevaContra.setText("");
        repiteContra.setText("");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmar_button:
                comprobar();
                break;
        }
    }
}