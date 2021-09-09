package com.example.androidpdfviewer

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        provaOnline()
        val bottom: Button = findViewById(R.id.btnGoOnline)
        bottom.setOnClickListener {
            startActivity(Intent(this, MainPdfOnline::class.java))
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun provaOnline() {
        // val url = "https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf"


        var pdfButton: PDFView = findViewById(R.id.pdfviewlocale)
        val input = this.assets.open("pdf.pdf")
        val buffer = ByteArrayOutputStream()
        var nRead: Int
        val data = ByteArray(10965864)
        while (input.read(data, 0, data.size).also({ nRead = it }) != -1) {
            buffer.write(data, 0, nRead);
        }

        val byteArray = buffer.toByteArray()

        pdfButton.fromBytes(byteArray)
            .onError {
                Toast.makeText(this, "errore local", Toast.LENGTH_SHORT).show()
            }
            .enableSwipe(true)
            .enableAntialiasing(true)
            .enableAnnotationRendering(true)
            .defaultPage(0)
            .enableAnnotationRendering(true)
            .onPageError { _, t ->
                Toast.makeText(this@MainActivity, "${t?.stackTrace}", Toast.LENGTH_SHORT).show()
                Log.d("mariaErrore", "${t?.message}")
            }.load()

    }

    private fun provaOffine() {
        val uri = Uri.parse("file:///android_asset/pdf.pdf")
        val config = PdfActivityConfiguration.Builder(this).build()
        PdfActivity.showDocument(this, uri, config)
    }


}