package com.example.tourism.view.catefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tourism.R;
import com.example.tourism.databinding.CateFoodFragmentBinding;
import com.example.tourism.viewmodel.catefragment.CateFragmentViewModel;

public class CateFoodFragment extends Fragment {
    private static final String TAG = CateFoodFragment.class.getSimpleName();
    private CateFoodFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.cate_food_fragment,container,false);
        setup();
        return binding.getRoot();
    }

    private void setup() {
        binding.setClick(new CateFragmentViewModel());
        binding.row.setClickable(true);
    }
}
