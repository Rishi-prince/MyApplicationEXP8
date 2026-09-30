package com.shareplate.myapplicationexp_8;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText urlEditText;
    private static final String DEFAULT_URL = "https://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        webView = findViewById(R.id.webView);
        urlEditText = findViewById(R.id.urlEditText);
        Button goButton = findViewById(R.id.goButton);

        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (urlEditText != null) {
                    urlEditText.setText(url);
                }
            }
        });

        // Load default URL
        webView.loadUrl(DEFAULT_URL);

        goButton.setOnClickListener(v -> loadUrlFromInput());

        urlEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                loadUrlFromInput();
                return true;
            }
            return false;
        });

        // Handle back press for WebView navigation
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    private void loadUrlFromInput() {
        String input = urlEditText.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
            return;
        }

        String targetUrl;
        if (input.startsWith("http://") || input.startsWith("https://")) {
            targetUrl = input;
        } else if (input.contains(".") && !input.contains(" ")) {
            targetUrl = "https://" + input;
        } else {
            targetUrl = "https://www.google.com/search?q=" + android.net.Uri.encode(input);
        }

        webView.loadUrl(targetUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            webView.loadUrl(DEFAULT_URL);
            Toast.makeText(this, "Loaded Home Page", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_reload) {
            webView.reload();
            Toast.makeText(this, "Reloading page", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_zoom_in) {
            webView.zoomIn();
            return true;
        } else if (id == R.id.action_zoom_out) {
            webView.zoomOut();
            return true;
        } else if (id == R.id.action_about) {
            showAboutDialog();
            return true;
        } else if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About / Developer Info")
                .setMessage("Experiment 8: Menus and WebView in an Android Application\n\n" +
                        "Developer Name: Aldrin Jose Antony\n" +
                        "USN: 1SG21CS001\n" +
                        "Course: Android Application Development\n\n" +
                        "Demonstrates Options Menu, WebView navigation, Zoom controls, and custom web loading.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
