package com.tphtwe.newsjava.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tphtwe.newsjava.R;
import com.tphtwe.newsjava.fragment.adapter.NewsAdapter;
import com.tphtwe.newsjava.viewmodel.NewsViewModel;


public class ArticleFragment extends Fragment {
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;
    NewsAdapter newsAdapter;
    ProgressBar loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_article, container, false);
        recyclerView=root.findViewById(R.id.newsRecycler);
        loading=root.findViewById(R.id.loadingView);
        newsViewModel=new ViewModelProvider(this).get(NewsViewModel.class);
        newsAdapter=new NewsAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);

        newsViewModel.laodResults();
        newsViewModel.getResutlts().observe(
                getViewLifecycleOwner(),news -> {
                    newsAdapter.updateArticle(news.getArticles());
                }
        );
        newsViewModel.getLoading().observe(
                getViewLifecycleOwner(),isLoading->{
                    if(isLoading){
                        loading.setVisibility(View.VISIBLE);
                    }
                    else {
                        loading.setVisibility(View.INVISIBLE);
                    }
                }

        );



        return root;
    }


}