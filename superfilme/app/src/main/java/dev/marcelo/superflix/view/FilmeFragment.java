package dev.marcelo.superflix.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.domain.model.Filme;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilmeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "filme";

    // TODO: Rename and change types of parameters
    private Filme filme;

    public FilmeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FilmeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilmeFragment newInstance(Filme param1) {
        FilmeFragment fragment = new FilmeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filme = getArguments().getParcelable(ARG_PARAM1);
        }
        if(filme != null) {
            TextView tv;
            tv = this.getActivity().findViewById(R.id.tvNome);
            tv.setText(filme.nome);
            tv = this.getActivity().findViewById(R.id.tvDescricao);
            tv.setText(filme.descricao);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filme, container, false);
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
        if(filme != null) {
            TextView tv;
            tv = this.getActivity().findViewById(R.id.tvNome);
            tv.setText(filme.nome);
            tv = this.getActivity().findViewById(R.id.tvDescricao);
            tv.setText(filme.descricao);
        }
    }
}