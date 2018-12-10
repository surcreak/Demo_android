package com.example.gl.demo_android.mvvm.adpter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.gl.demo_android.MvvmRecycleItemBinding;
import com.example.gl.demo_android.R;
import com.example.gl.demo_android.mvvm.SwordsMan;

import java.util.List;

public class SwordsmanAdapter extends RecyclerView.Adapter<SwordsmanAdapter.SwordsmanViewHolder> {

    private List<SwordsMan> mList;

    public SwordsmanAdapter(List<SwordsMan> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public SwordsmanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MvvmRecycleItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.mvvm_recycle_item,
                viewGroup,
                false
        );
        return new SwordsmanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SwordsmanViewHolder swordsmanViewHolder, int i) {
        SwordsMan swordsMan = mList.get(i);
        swordsmanViewHolder.getBinding().setSwordsman(swordsMan);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SwordsmanViewHolder extends RecyclerView.ViewHolder {
        MvvmRecycleItemBinding binding;

        public SwordsmanViewHolder(MvvmRecycleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public MvvmRecycleItemBinding getBinding() {
            return binding;
        }
    }
}
