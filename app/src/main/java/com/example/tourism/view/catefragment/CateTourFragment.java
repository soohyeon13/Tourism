package com.example.tourism.view.catefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tourism.R;
import com.example.tourism.databinding.CateTourFragmentBinding;
import com.example.tourism.viewmodel.catefragment.CateFragmentViewModel;

public class CateTourFragment extends Fragment {

    private CateTourFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.cate_tour_fragment,container,false);
        setupView();
        return  binding.getRoot();
    }

    private void setupView() {
        binding.setClick(new CateFragmentViewModel());
    }
}
