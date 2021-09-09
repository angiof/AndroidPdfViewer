package com.example.androidpdfviewer

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class OnlinePdfReaderAcitivi : AppCompatActivity() {
    lateinit var pdfButton: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_pdf_reader_acitivi)



        provaOnline()
    }

    fun provaOnline() {

        val uri = Uri.parse("https://helpx.adobe.com/it/pdf/acrobat_reference.pdf")
        pdfButton = findViewById(R.id.pdfView)
        pdfButton.fromUri(uri).enableSwipe(true)

            .defaultPage(0)
            .onError {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
            .onPageError { page, t ->

                Log.d("mario", "${t.cause}")
                Log.d("mario", "${t.message}")
                Log.d("mario", "${t.stackTrace}")
                Toast.makeText(this, "${t.cause}", Toast.LENGTH_SHORT).show()
            }.load()

    }
}