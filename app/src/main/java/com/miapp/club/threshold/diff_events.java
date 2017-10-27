package com.miapp.club.threshold;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class diff_events extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_events);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pdfView = (PDFView) findViewById(R.id.pdf_events);

        File root = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        assert root != null;
        File dir = new File(root.getAbsolutePath() + "/download/magazines");
        dir.mkdirs();

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl(getIntent().getStringExtra("eve_id"));
        final File localFile = new File(dir, getIntent().getStringExtra("eve_name"));
        mStorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                pdfView.fromFile(localFile)
                        .spacing(3)
                        .load();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(diff_events.this, exception.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        pdfView.fromFile(localFile)
                .spacing(3)
                .load();
        getSupportActionBar().setTitle(getIntent().getStringExtra("eve_name"));

    }
}
