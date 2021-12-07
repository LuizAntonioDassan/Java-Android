package com.example.atmnavigation.ui.contato;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmnavigation.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contatoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contatoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public contatoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contatoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static contatoFragment newInstance(String param1, String param2) {
        contatoFragment fragment = new contatoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String descricao = "Fusce et mi ultrices turpis placerat porttitor non a neque." +
                " Aenean dictum auctor convallis. Duis vulputate et velit a cursus. " +
                "Mauris dictum tempus lacus vel vulputate.";
        Element versao = new Element();
        versao.setTitle("Versão 1.0");

        return new AboutPage(getActivity())
                .setImage( R.drawable.logo)
                .setDescription(descricao)

                .addGroup("Entre em Contato")
                .addEmail("ATM.atendimento@yahoo.com")
                .addWebsite("https://www.google.com/" , "Acesse nosso site")

                .addGroup("Redes Sociais")
                .addFacebook("LuizAntonio", "Facebook")
                .addInstagram("Luiz", "Instagram")
                .addTwitter("luiz", "Twitter")
                .addGitHub("LuizDassan", "GitHub")

                .addItem( versao )

                .create();

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_contato, container, false);
    }
}