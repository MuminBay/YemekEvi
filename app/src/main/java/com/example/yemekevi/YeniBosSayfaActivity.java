package com.example.yemekevi;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class YeniBosSayfaActivity extends AppCompatActivity {

    private WebView webView;
    private TextView aciklama;
    private String yemekTuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_bos_sayfa);

        initializeViews();
        setupWebView();
        loadContent();
    }

    private void initializeViews() {
        webView = findViewById(R.id.webView1);
        aciklama = findViewById(R.id.aciklama1);
        yemekTuru = getIntent().getStringExtra("yemek_turu");
    }

    private void setupWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }

    private void loadContent() {
        String videoId = getVideoId();
        String aciklamaText = getAciklamaText();

        if (videoId != null && aciklamaText != null) {
            loadVideo(videoId);
            aciklama.setText(aciklamaText);
        } else {
            handleUnknownYemek();
        }
    }

    private void loadVideo(String videoId) {
        String iframeHtml = "<html><body style='margin:0;padding:0;'>" +
                "<iframe width='100%' height='100%' src='https://www.youtube.com/embed/" +
                videoId + "' frameborder='0' allowfullscreen></iframe></body></html>";
        webView.loadData(iframeHtml, "text/html", "utf-8");
    }

    private String getVideoId() {
        switch (yemekTuru) {
            case "kebap": return "lh1LrsgsVK8";
            case "sushi": return "uIgpY1DWhtA";
            case "tacos": return "IUCN2eFEJfU";
            case "pizza": return "n-VRntrbypI";
            case "doner": return "ql8fORsoRqc";
            case "tokat_kebabi": return "wZmx2JxyAHU";
            default: return null;
        }
    }

    private String getAciklamaText() {
        switch (yemekTuru) {
            case "kebap":
                return "Adana kebabı, Türkiye'nin Adana şehrine özgü, dana ya da kuzu etinin zırh adı verilen özel bir bıçakla incecik kıyılmasıyla hazırlanan, baharatlarla tatlandırılmış bir kebap çeşididir. Genellikle geniş şişlere sarılarak közde pişirilir ve lavaş, sumaklı soğan, yeşillik, domates, biber gibi garnitürlerle servis edilir.";
            case "sushi":
                return "Suşi, Japon mutfağının en ikonik lezzetlerinden biri olup, sirkeyle tatlandırılmış pirincin, genellikle çiğ balık, deniz ürünleri, sebzeler veya yosunla bir araya getirilerek hazırlandığı bir yemektir. Çeşitlerine göre nori adı verilen yosun yaprağına sarılarak (maki), balık dilimleri üzerine yerleştirilerek (nigiri) veya sade bir şekilde (sashimi) sunulabilir.";
            case "tacos":
                return "Taco, Meksika mutfağının vazgeçilmez bir lezzeti olup, mısır veya buğdaydan yapılan küçük tortilla ekmeği içine çeşitli malzemelerin doldurulmasıyla hazırlanır. Geleneksel olarak et, tavuk, balık veya sebzelerle doldurulan tacolar; salsa sosu, avokado, limon, peynir ve taze kişniş gibi malzemelerle tatlandırılır.";
            case "pizza":
                return "Pizza, İtalyan mutfağının dünyaca ünlü bir klasiği olup, ince veya kalın açılmış hamur üzerine domates sosu, peynir ve çeşitli malzemelerin eklenerek fırında pişirilmesiyle hazırlanır. Geleneksel olarak odun ateşinde pişirilen pizzalar, Margherita gibi sade tariflerden, et, sebze veya deniz ürünleriyle zenginleştirilmiş çeşitlere kadar geniş bir yelpazeye sahiptir.";
            case "doner":
                return "Döner, Türk mutfağının en sevilen lezzetlerinden biri olup, dikey bir şişe yerleştirilen ince dilimlenmiş etlerin döndürülerek pişirilmesiyle hazırlanır. Genellikle kuzu, tavuk veya dana etinden yapılan döner, odun ateşi veya elektrikli ızgarada pişirilerek lezzetlendirilir. Lavaş ya da pide arasında dürüm şeklinde, pilav üstü ya da porsiyon olarak servis edilebilir ve yanında domates, biber, turşu gibi garnitürlerle sunulur.";
            case "tokat_kebabi":
                return "Tokat kebabı, Tokat yöresine özgü, odun ateşinde pişirilen lezzetli bir kebap çeşididir. Genellikle kuzu eti, patlıcan, domates, biber, patates ve sarımsak gibi malzemelerle hazırlanır ve özel taş fırınlarda, dik bir düzenekle pişirilir. Etlerin ve sebzelerin doğal aromalarının birleşimiyle ortaya çıkan Tokat kebabı, genellikle lavaş veya tırnaklı pide ile servis edilir.";
            default:
                return null;
        }
    }

    private void handleUnknownYemek() {
        webView.loadData("Yakında eklenecek: " + yemekTuru, "text/html", "utf-8");
        aciklama.setText(yemekTuru + " için açıklama yakında eklenecek.");
    }
} 