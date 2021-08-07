package com.del.studyenglish1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CheckLevelPage extends Fragment {

    private WebView webView;
    private ImageView imageViewClose;

    private SelectLevelPage selectLevelPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_check_level_page, container, false);
        imageViewClose = v.findViewById(R.id.image_view_close);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.webView);
        openWebView(webView);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectLevelPage();
            }
        });

    }
    public void openWebView(WebView webView) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://test-english.com/level-test/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public void openSelectLevelPage() {
        selectLevelPage = new SelectLevelPage();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, selectLevelPage);
        fragmentTransaction.commit();
    }

}
