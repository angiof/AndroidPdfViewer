package com.example.androidpdfviewer

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

class MainPdfOnline : AppCompatActivity() {
    var pdfView: PDFView? = null
    private lateinit var btn :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pdf_java)



        btn=findViewById(R.id.btnMian)
        btn.setOnClickListener {

            downloadFile("https://helpx.adobe.com/it/pdf/acrobat_reference.pdf") //Your URL
        }



    }

    fun pdfOpen() {
        val pdfView = findViewById<View>(R.id.pdfViewJava) as PDFView
        val url = "https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf"
        pdfView.fromUri(Uri.parse(url))
            .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
            .password(null)
            .scrollHandle(null)
            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
            // spacing between pages in dp. To define spacing color, set view background
            .onError { t: Throwable -> Log.e("mario", t.localizedMessage) }
            .spacing(0)
            .load()
    }



    fun downloadFile(uRl: String) {
        val direct = File(getExternalFilesDir(null), "/pdfs")

        if (!direct.exists()) {
            direct.mkdirs()
        }

        val mgr = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(uRl)
        val request = DownloadManager.Request(
            downloadUri
        )

        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE
        )
            .setAllowedOverRoaming(false).setTitle("pdf demo") //Download Manager Title
            .setDescription("Downloading...") //Download Manager description
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "mypdf.pdf"  //Your User define(Non Standard Directory name)/File Name
            )

        mgr.enqueue(request)

    }
}



