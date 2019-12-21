package com.final_test.moneylovely.fragment;


import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.final_test.moneylovely.MainActivity;
import com.final_test.moneylovely.R;
import com.final_test.moneylovely.adapter.Info_RecyclerViewAdapter;
import com.final_test.moneylovely.model.User;
import com.final_test.moneylovely.view.CallbackFragment;
import com.final_test.moneylovely.view.Show_Hide_Dialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class IntroduceFragment extends Fragment {
    CircleImageView imgUser;
    TextView txtUserName;
    final int REQUSE_CODE_CAMERA = 123;
    final int REQUES_CODE_FILE = 456;
    String linkphoto;
    int UserID;
    ProgressDialog progressDialog;
    ArrayList<User> userArrayList;
    RecyclerView recyclerView;
    Info_RecyclerViewAdapter info_recyclerViewAdapter;
    FragmentManager fragmentManager;
    DatabaseReference mData;
    StorageReference storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce, container, false);
        anhxa(view);
        setUser();
        eventImg();
        //onclick callback
        CallbackFragment.callBackFragment(view, fragmentManager);
        return view;


    }

    private void setUser() {
        imgUser.setEnabled(true);
        mData.child("users").child(UserID + "").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                userArrayList.clear();
                userArrayList.add(new User(value.getUserID(), value.getUsername(), value.getFull_name(), value.getPassword(), value.getAddress()));
                recyclerView.setAdapter(info_recyclerViewAdapter);
                txtUserName.setText(value.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updatePhoto(Bitmap bitmap, final String idUser) {
        Show_Hide_Dialog.showProgressDialogWithTitle("Đang cập nhật...", progressDialog);
        final StorageReference mountainsRef = storage.child("users").child(idUser);
        Log.d("iduser", idUser);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        final byte[] data = baos.toByteArray();
        final UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(exception -> Toast.makeText(getActivity(), "Cập nhật ảnh thất bại!", Toast.LENGTH_SHORT).show()).addOnSuccessListener(taskSnapshot -> mountainsRef.getDownloadUrl().addOnSuccessListener(uri -> {
            linkphoto = uri.toString();
            updateLinkPhotoUser(UserID + "", linkphoto);
        }));
    }

    public void updateLinkPhotoUser(String idUser, String linkPhotoUser) {
        mData.child("users").child(idUser).child("image").setValue(linkPhotoUser).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Show_Hide_Dialog.hideProgressDialogWithTitle(progressDialog);
                Toast.makeText(getActivity(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Show_Hide_Dialog.hideProgressDialogWithTitle(progressDialog);
                Toast.makeText(getActivity(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eventImg() {
        imgUser.setOnClickListener(v -> dialog_ChooseAddAnhSV());
    }

    private void dialog_ChooseAddAnhSV() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_chooseanhsv);
        ImageView btnChooseAnhFile = dialog.findViewById(R.id.btnchooseanhFile);
        ImageView btnChooseAnhCamera = dialog.findViewById(R.id.btnchooseanhCamera);


        btnChooseAnhCamera.setOnClickListener(v -> {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUSE_CODE_CAMERA);
            dialog.dismiss();
        });
        btnChooseAnhFile.setOnClickListener(v -> {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUES_CODE_FILE);
            dialog.dismiss();
        });


        dialog.show();

    }
    //xử lí xin quyền camera và file

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUSE_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUSE_CODE_CAMERA);
                } else {
                    Toast.makeText(getActivity(), "Bạn chưa cấp quyền Camera!", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUES_CODE_FILE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUES_CODE_FILE);
                } else {
                    Toast.makeText(getActivity(), "Bạn chưa cấp quyền truy cập thư viện!", Toast.LENGTH_SHORT).show();
                }
                break;

        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // đổ ảnh
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUSE_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            updatePhoto(bitmap, UserID + "");
            imgUser.setImageBitmap(bitmap);
        }
        if (requestCode == REQUES_CODE_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getApplicationContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                updatePhoto(bitmap, UserID + "");
                imgUser.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa(View view) {
        mData = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance().getReference();
        UserID = MainActivity.UserID;
        imgUser = view.findViewById(R.id.imgUser);
        progressDialog = new ProgressDialog(getActivity());
        txtUserName = view.findViewById(R.id.txtUserName);
        userArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.Info_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        info_recyclerViewAdapter = new Info_RecyclerViewAdapter(userArrayList, getActivity());


    }


}